package me.ticketing_system.simulation;

public record SimulationConfiguration(
        Integer totalTicketsForVendor,
        Integer totalTicketsForCustomer,
        Integer totalVendors,
        Integer totalConsumer, // todo: change to totalConsumers
        Integer vendorSleepTime,
        Integer consumerSleepTime
) { }
