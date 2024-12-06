package me.ticketing_system.ticketpool;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TicketPool {

    private final Vector<Ticket> tickets;
    private Integer maxTicketCapacity;
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);

    public TicketPool() {
        this.tickets = new Vector<>();
        this.maxTicketCapacity = -1;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (this.tickets.size() == this.maxTicketCapacity) {
            logger.info("No space to add the ticket. Waiting for a ticket removal ... ");
            wait();
        }
        this.tickets.add(ticket);
        notifyAll();
    }
    
    public synchronized Ticket removeTicket() throws InterruptedException {
        while (this.tickets.isEmpty()) {
            logger.info("No tickets available. Waiting for a new ticket ... ");
            wait();
        }
        Ticket removedTicket = this.tickets.removeLast();
        notifyAll();
        return removedTicket;
    }

    public void setMaxTicketCapacity(Integer maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        if (maxTicketCapacity < this.tickets.size()) {
            return;
        }
        this.tickets.setSize(maxTicketCapacity);
    }

    public Vector<Ticket> getTickets() {
        return tickets;
    }
}
