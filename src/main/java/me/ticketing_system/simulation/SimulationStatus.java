package me.ticketing_system.simulation;

public record SimulationStatus(
        Integer totalActiveVendors,
        Integer totalActiveConsumers,
        Integer totalTickets
) {}