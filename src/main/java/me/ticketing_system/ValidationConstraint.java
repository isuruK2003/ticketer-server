package me.ticketing_system;

public record ValidationConstraint(
    String fieldName,
    Integer minValue,
    Integer maxValue
) { }
