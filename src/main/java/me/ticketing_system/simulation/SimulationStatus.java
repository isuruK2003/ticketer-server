package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.Ticket;

public record SimulationStatus(
        Ticket ticket,
        Integer totalActiveVendors,
        Integer totalActiveConsumers,
        Integer totalTickets
) {}