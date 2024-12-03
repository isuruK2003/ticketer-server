package me.ticketing_system.ticketpool;

import java.util.Vector;
import org.springframework.stereotype.Component;

@Component
public class TicketPool {

    private final Vector<Ticket> tickets;
    private Integer totalTicketsAvailable;
    private Integer maxTicketCapacity;

    public TicketPool() {
        this.tickets = new Vector<>();
        this.maxTicketCapacity = -1;
        this.totalTicketsAvailable = 0;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (this.tickets.size() == this.maxTicketCapacity) {
            System.out.println("waiting for space ...");
            wait();
        }
        this.tickets.add(ticket);
        System.out.println("Ticket Added: " + ticket);
        notifyAll();
        this.totalTicketsAvailable++;
    }
    
    public synchronized Ticket removeTicket() throws InterruptedException {
        while (this.tickets.isEmpty()) {
            System.out.println("waiting for new ticket...");
            wait();
        }
        Ticket removedTicket = this.tickets.removeLast();
        System.out.println("Ticket Removed: " + removedTicket);
        notifyAll();
        return removedTicket;
    }

    public Vector<Ticket> getTickets() {
        return tickets;
    }

    public void setMaxTicketCapacity(Integer maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        if (maxTicketCapacity < this.tickets.size()) {
            return;
        }
        this.tickets.setSize(maxTicketCapacity);
    }
}
