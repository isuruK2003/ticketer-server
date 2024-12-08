package me.ticketing_system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationConstraintService implements JsonService<Map<String, ValidationConstraint>> {

    private final JdbcTemplate jdbcTemplate;
    private final Gson gson;
    private final Logger logger;

    public ValidationConstraintService(JdbcTemplate jdbcTemplate) {
        this.gson = new Gson();
        this.jdbcTemplate = jdbcTemplate;
        this.logger = LoggerFactory.getLogger(ValidationConstraintService.class);

    }

    public Map<String, ValidationConstraint> loadConstraints() {
        String sql = "SELECT field_name, min_value, max_value FROM simulation_configuration_validation_constraints";
        List<ValidationConstraint> constraints = jdbcTemplate.query(sql, (rs, rowNum) ->
                new ValidationConstraint(
                        rs.getString("field_name"),
                        rs.getInt("min_value"),
                        rs.getInt("max_value")
                )
        );

        Map<String, ValidationConstraint> map = new HashMap<>();

        for (ValidationConstraint constraint : constraints) {
            map.put(constraint.fieldName(), constraint);
        }
        try {saveToJson(map);} catch (IOException e) {
            logger.error("ValidationConstraints saving to Json failed: {}", e.getMessage());
        }
        return map;
    }

    @Override
    public void saveToJson(Map<String, ValidationConstraint> object) throws IOException {
        gson.toJson(object, new FileWriter("validation_constraints.json"));
    }

    @Override
    public Map<String, ValidationConstraint> readFromJson() throws IOException {
        //ToDo: Note: Following code creates a "Type" object representing the "Map<String, ValidationConstraint>"
        Type type = new TypeToken<Map<String, ValidationConstraint>>(){}.getType();
        return gson.fromJson(new FileReader("validation_constraints.json"), type);
    }
}
