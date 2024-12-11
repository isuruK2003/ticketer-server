package me.ticketing_system;

import me.ticketing_system.validations.ValidationConstraint;

import java.util.Map;

public class GlobalConstants {
    public static String validationConstraintsFileName = "src/main/resources/validation_constraints.json";
    public static final Map<String, ValidationConstraint> DEFAULT_VALIDATION_CONSTRAINTS = Map.of(
            "totalTicketsForCustomer", new ValidationConstraint("totalTicketsForCustomer", 1, 1000),
            "totalTicketsForVendor", new ValidationConstraint("totalTicketsForVendor", 1, 1000),
            "totalVendors", new ValidationConstraint("totalVendors", 1, 10),
            "totalConsumers", new ValidationConstraint("totalConsumers", 1, 10),
            "ticketReleaseRate", new ValidationConstraint("ticketReleaseRate", 1, 10),
            "customerRetrievalRate", new ValidationConstraint("customerRetrievalRate", 1, 10),
            "maxTicketCapacity", new ValidationConstraint("maxTicketCapacity", 1, 500)
    );
}
