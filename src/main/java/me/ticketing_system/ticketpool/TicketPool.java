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
        this.maxTicketCapacity = 0;
        this.totalTicketsAvailable = 0;
    }

    public TicketPool(Integer maxTicketCapacity) {
        this.tickets = new Vector<>(maxTicketCapacity);
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTicketsAvailable = 0;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (this.tickets.size() == this.maxTicketCapacity) {
            wait();
        }
        this.tickets.add(ticket);
        this.totalTicketsAvailable++;
    }
    
    public synchronized void removeTicket(Ticket ticketToRemove) throws InterruptedException {
        while (this.tickets.size() == 0) {
            wait();
        }

        boolean removed = this.tickets.remove(ticketToRemove);
        if (removed) {
            this.totalTicketsAvailable--;
            System.out.println("success");
        } else {
            System.out.println("not found");
        }
    }    

    public Vector<Ticket> getTickets() {
        return tickets;
    }
}
