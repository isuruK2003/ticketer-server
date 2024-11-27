package me.ticketing_system.ticketpool;

import java.util.Vector;

public class TicketPool {
    private Vector<Ticket> tickets;

    public TicketPool() {
        this.tickets = new Vector<Ticket>();
    }

    public void addTicket(Ticket ticket) {
        this.tickets.addLast(ticket);
    }

    public Ticket removeTicket() {
        return this.tickets.removeFirst();
    }
}
