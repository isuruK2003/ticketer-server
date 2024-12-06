package me.ticketing_system.simulation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationConstraintService {

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

        return constraints.stream().collect(Collectors.toMap(ValidationConstraint::fieldName, c -> c));
    }
}
