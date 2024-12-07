package me.ticketing_system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationConstraintService implements JsonService<Map<String, ValidationConstraint>> {

    private final JdbcTemplate jdbcTemplate;

    public ValidationConstraintService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, ValidationConstraint> loadConstraints() {
        String sql = "SELECT field_name, min_value, max_value FROM validation_constraints";
        List<ValidationConstraint> constraints = jdbcTemplate.query(sql, (rs, rowNum) ->
                new ValidationConstraint(
                        rs.getString("field_name"),
                        rs.getInt("min_value"),
                        rs.getInt("max_value")
                )
        );
        Map<String, ValidationConstraint> map = constraints.stream().collect(Collectors.toMap(ValidationConstraint::fieldName, c -> c));
        return map;
    }

    @Override
    public void saveToJson(Map<String, ValidationConstraint> object) throws IOException {
        Gson gson = new Gson();
        gson.toJson(object, new FileWriter("validation_constraints.json"));
    }

    @Override
    public Map<String, ValidationConstraint> readFromJson() throws IOException {
        Gson gson = new Gson();
        // todo: remove this comment: Create a Type object representing the Map<String, ValidationConstraint>
        Type type = new TypeToken<Map<String, ValidationConstraint>>(){}.getType();
        return gson.fromJson(new FileReader("validation_constraints.json"), type);
    }
}
