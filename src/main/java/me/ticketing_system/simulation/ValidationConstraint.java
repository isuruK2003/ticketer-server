package me.ticketing_system.simulation;

public record ValidationConstraint(
    String fieldName,
    Integer minValue,
    Integer maxValue
) { }
