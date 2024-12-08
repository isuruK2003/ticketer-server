package me.ticketing_system.simulation;

public record SimulationConfigurationStatus(
        Boolean hasConfigured,
        Boolean hasVendorsStarted,
        Boolean hasConsumersStarted
) {
}
