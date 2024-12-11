package me.ticketing_system.cli;

import jakarta.validation.ValidationException;
import me.ticketing_system.validations.ValidationConstraint;
import me.ticketing_system.validations.ValidationConstraintService;
import me.ticketing_system.simulation.SimulationConfigurationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class CliUtilities {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, ValidationConstraint> validationConstraints = ValidationConstraintService.loadFromJson();

    private static String toSentenceCase(String str) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                res.append(" ").append(Character.toLowerCase(c));
                continue;
            }
            res.append(c);
        }
        return res.toString();
    }

    public static Integer readConfigurationValue(String fieldName) {
        while (true) {
            System.out.print("> Enter " + toSentenceCase(fieldName) + ": ");
            try {
                Integer userInput = Integer.parseInt(scanner.nextLine().trim());
                SimulationConfigurationValidator.validateField(fieldName, userInput, validationConstraints);
                return userInput;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31m" + "Error: Please enter a valid numerical value." + "\u001B[0m");
            } catch (ValidationException e) {
                System.out.println("\u001B[31m" + "Error: " + toSentenceCase(e.getMessage()) + "\u001B[0m");
            }
        }
    }
}
