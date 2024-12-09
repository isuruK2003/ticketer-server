package me.ticketing_system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidationConstraintService {

    private final JdbcTemplate jdbcTemplate;
    private static final Gson gson = new Gson();;
    private static final Logger logger = LoggerFactory.getLogger(ValidationConstraintService.class);

    public ValidationConstraintService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        Map<String, ValidationConstraint> map = toMap(constraints);
        saveToJson(map);
        return map;
    }

    private static Map<String, ValidationConstraint> toMap(List<ValidationConstraint> constraints) {
        Map<String, ValidationConstraint> map = new HashMap<>();
        for (ValidationConstraint constraint : constraints) {
            map.put(constraint.fieldName(), constraint);
        }
        return map;
    }

    public static void saveToJson(Map<String, ValidationConstraint> object) {
        try (FileWriter writer = new FileWriter(GlobalConstants.validationConstraintsFileName)) {
            String json = gson.toJson(object);
            writer.write(json);
            logger.info("Successfully saved to {}", GlobalConstants.validationConstraintsFileName);
        } catch (IOException e) {
            logger.error("Error occurred while saving the constraints: {}", e.getMessage());
        }
    }

    public static Map<String, ValidationConstraint> loadFromJson() {
        try (Reader reader = new FileReader(GlobalConstants.validationConstraintsFileName)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ValidationConstraint.class, new ValidationConstraintDeserializer())
                    .create();
            return gson.fromJson(reader, new TypeToken<Map<String, ValidationConstraint>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
