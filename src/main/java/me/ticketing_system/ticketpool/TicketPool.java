package me.ticketing_system.ticketpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TicketPool {

    private final List<Ticket> tickets;
    private Integer maxTicketCapacity;
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);
    private final List<TicketPoolListChangeListener> ticketPoolChangeListeners;

    public TicketPool() {
        this.tickets = Collections.synchronizedList(new ArrayList<>());
        this.maxTicketCapacity = -1;
        this.ticketPoolChangeListeners = new ArrayList<>();
    }

    //// Synchronised Methods /////

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (this.tickets.size() == this.maxTicketCapacity) {
            logger.info("No space to add the ticket. Waiting for a ticket removal ... ");
            wait();
        }
        this.tickets.add(ticket);
        notifyAll();
        notifyListeners(ticket);
    }
    
    public synchronized Ticket removeTicket(Integer removedByConsumerId) throws InterruptedException {
        while (this.tickets.isEmpty()) {
            logger.info("No tickets available. Waiting for a new ticket ... ");
            wait();
        }
        Ticket removedTicket = this.tickets.removeLast();
        removedTicket.setConsumerId(removedByConsumerId);
        notifyAll();
        notifyListeners(removedTicket);
        return removedTicket;
    }

    ///// TicketPool List Change Listener Notifier

    private void notifyListeners(Ticket changedTicket) {
        for (TicketPoolListChangeListener listener : ticketPoolChangeListeners) {
            listener.onSizeChanged(changedTicket);
        }
    }

    public void addListener(TicketPoolListChangeListener listener) {
        ticketPoolChangeListeners.add(listener);
    }

    //// Getters and Setters ////

    public void setMaxTicketCapacity(Integer maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
