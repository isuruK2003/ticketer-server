package me.ticketing_system.ticketpool;

public record TicketPoolConfiguration(
        Integer configId,
        Integer totalTickets,
        Integer ticketReleaseRate,
        Integer customerRetrievalRate,
        Integer maximumTicketCapacity
) {}