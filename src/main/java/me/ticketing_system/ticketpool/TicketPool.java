package me.ticketing_system.ticketpool;

import java.util.Vector;

import me.ticketing_system.event.Event;

public class TicketPool {
    private Event event;
    private Vector<Ticket> queue;


    public TicketPool(Event event) {
        this.event = event;
        this.queue = new Vector<Ticket>(event.getConfig().maximumTicketCapacity());
    }

    public void addTicket(Ticket ticket) {
        this.queue.addLast(ticket);
    }

    public Ticket removeTicket() {
        return this.queue.removeFirst();
    }

    // remove later
    public Event getEvent() {
        return event;
    }
}
