package me.ticketing_system.validations;

public record ValidationConstraint(
    String fieldName,
    Integer minValue,
    Integer maxValue
) { }
