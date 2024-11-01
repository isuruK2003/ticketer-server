package me.ticketing_system.config;

import jakarta.validation.constraints.NotNull;

public record Configuration(
        @NotNull
        String configId,
        @NotNull
        Integer totalTickets,
        @NotNull
        Integer ticketReleaseRate,
        @NotNull
        Integer customerRetrievalRate,
        @NotNull
        Integer maximumTicketCapacity
) {
    public Configuration {
        if (totalTickets >  maximumTicketCapacity) {
            throw new IllegalArgumentException("'totalTickets' should be less than or equal to 'maximumTicketCapacity'");
        }

        if (totalTickets <= 0) {
            throw new IllegalArgumentException("'totalTickets should be greater than zero'");
        }

        if (ticketReleaseRate <= 0) {
            throw new IllegalArgumentException("'ticketReleaseRate' should be greater than zero");
        }

        if (customerRetrievalRate <= 0) {
            throw new IllegalArgumentException("'customerRetrievalRate' should be greater than zero");
        }
    }
}