package me.ticketing_system.ticketpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vendor implements Runnable {
    private final Integer vendorId;
    private final TicketPool ticketPool;
    private final Integer ticketReleaseRate;
    private final Integer totalTickets;
    private final Logger logger = LoggerFactory.getLogger(Vendor.class);

    public Vendor(Integer vendorId, TicketPool ticketPool, Integer ticketReleaseRate, Integer totalTickets) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.totalTickets ; i++) {
            try {
                Ticket newTicket = new Ticket(i, this.vendorId);
                this.ticketPool.addTicket(newTicket);
                Thread.sleep(this.ticketReleaseRate*1000);
                logger.info("New ticket added by Vendor {}: {}", this.vendorId, newTicket);
            } catch (InterruptedException e) {
                logger.error("Error occurred at Vendor at run() : {}", e.getMessage());
                break;
            }
        }
    }
}
