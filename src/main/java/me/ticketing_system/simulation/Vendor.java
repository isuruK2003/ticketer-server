package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.Ticket;
import me.ticketing_system.ticketpool.TicketPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vendor implements Runnable {
    private final Integer vendorId;
    private final TicketPool ticketPool;
    private final Integer sleepTimeMilli;
    private final Integer totalTickets;
    private final Logger logger = LoggerFactory.getLogger(Vendor.class);

    public Vendor(Integer vendorId, TicketPool ticketPool, Integer sleepTimeMilli, Integer totalTickets) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.sleepTimeMilli = sleepTimeMilli;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {
        for (int i = this.totalTickets; i > 0 ; i--) {
            try {
                Ticket newTicket = new Ticket(i);
                this.ticketPool.addTicket(newTicket);
                Thread.sleep(this.sleepTimeMilli);
                logger.info("New ticket added by Vendor {}: {}", this.vendorId, newTicket);
            } catch (InterruptedException e) {
                logger.error("Error occurred at Vendor at run() : {}", e.getMessage());
                break;
            }
        }
    }
}
