package me.ticketing_system;

public record ErrorResponse (
    Integer status,
    String message
) {}


