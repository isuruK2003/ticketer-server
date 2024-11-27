package me.ticketing_system.ticketpool;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import me.ticketing_system.event.Event;

public record Ticket(
    @NotNull
    @PositiveOrZero
    Integer ticketId,
    Event event
) {}