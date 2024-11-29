package me.ticketing_system.ticketpool;

import jakarta.validation.constraints.PositiveOrZero;

public record Ticket(
        @PositiveOrZero Integer ticketId) {
}