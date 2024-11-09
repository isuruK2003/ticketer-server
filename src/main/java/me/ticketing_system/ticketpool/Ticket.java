package me.ticketing_system.ticketpool;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record Ticket(
    @NotNull
    @PositiveOrZero
    Integer ticketId
) {}