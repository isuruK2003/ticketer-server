package me.ticketing_system.simulation;

public record SimulationConfiguration(
        Integer totalTicketsForVendor,
        Integer totalTicketsForCustomer,
        Integer totalVendors,
        Integer totalConsumer,
        Integer vendorSleepTime,
        Integer consumerSleepTime
) { }
