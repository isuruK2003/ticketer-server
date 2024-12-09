package me.ticketing_system.cli;

import jakarta.validation.ValidationException;
import me.ticketing_system.GlobalConstants;
import me.ticketing_system.ValidationConstraint;
import me.ticketing_system.ValidationConstraintService;
import me.ticketing_system.simulation.SimulationConfigurationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class CliUtilities {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(CliUtilities.class);
    private static final Map<String, ValidationConstraint> validationConstraints = readValidatorConstraints();

    private static Map<String, ValidationConstraint> readValidatorConstraints() {
        try {
            return ValidationConstraintService.loadFromJson();
        } catch (RuntimeException e) {
            logger.error("Cannot read the {}: ", GlobalConstants.validationConstraintsFileName + ": " + e.getMessage());
        }
        return null;
    }

    public static Integer readConfigurationValue(String fieldName) {
        while (true) {
            System.out.print("Enter " + fieldName + ": ");
            try {
                Integer userInput = Integer.parseInt(scanner.nextLine().trim());
                SimulationConfigurationValidator.validateField(fieldName, userInput, validationConstraints);
                return userInput;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numerical value.");
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // todo : return the default, for this add a field called default in to the validation constraints db
    }
}
