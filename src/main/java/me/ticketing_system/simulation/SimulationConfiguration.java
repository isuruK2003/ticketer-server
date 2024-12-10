package me.ticketing_system.simulation;

public record SimulationConfiguration(
        Integer totalTicketsForVendor,
        Integer totalTicketsForCustomer,
        Integer totalVendors,
        Integer totalConsumers,
        Integer ticketReleaseRate,
        Integer customerRetrievalRate,
        Integer maxTicketCapacity
) { }
