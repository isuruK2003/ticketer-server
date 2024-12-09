package me.ticketing_system.simulation;

import jakarta.validation.ValidationException;
import me.ticketing_system.ValidationConstraint;
import me.ticketing_system.ValidationConstraintService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SimulationConfigurationValidator {
    private final ValidationConstraintService constraintService;

    public SimulationConfigurationValidator(ValidationConstraintService validationConstraintService) {
        this.constraintService = validationConstraintService;
    }

    public void validate(SimulationConfiguration config) {
        Map<String, ValidationConstraint> constraints = constraintService.loadConstraints();

        validateField("totalTicketsForCustomer", config.totalTicketsForCustomer(), constraints);
        validateField("totalTicketsForVendor", config.totalTicketsForVendor(), constraints);
        validateField("totalVendors", config.totalVendors(), constraints);
        validateField("vendorSleepTime", config.vendorSleepTime(), constraints);
        validateField("consumerSleepTime", config.consumerSleepTime(), constraints);
    }

    public static void validateField(String fieldName, Integer value, Map<String, ValidationConstraint> constraints) {
        ValidationConstraint constraint = constraints.get(fieldName);
        if (constraint == null) {
            throw new ValidationException("Validation constraint for " + fieldName + " is not defined.");
        }

        if (value < constraint.minValue() || value > constraint.maxValue()) {
            throw new ValidationException(fieldName + " must be between " + constraint.minValue() + " and " + constraint.maxValue());
        }
    }
}
