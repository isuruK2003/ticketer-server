package me.ticketing_system.ticketpool;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {
    private final TicketPool ticketPool;
    private static final Logger logger =  LoggerFactory.getLogger(TicketPoolService.class);

    public TicketPoolService() {this.ticketPool = new TicketPool();}

    public Ticket removeTicket() {
        try {
            Ticket removedTicket = ticketPool.removeTicket();
            logger.info("Removed the ticket: {}", removedTicket);
            return removedTicket;
        } catch (InterruptedException e) {
            logger.warn("Error at removeTicket, at TicketPoolService: {}", e.getMessage());
            return null;
        }
    }

    public void addTicket(Ticket ticket) {
        try {
            ticketPool.addTicket(ticket);
            logger.info("Added the ticket: {}", ticket);
        } catch (InterruptedException e) {
            logger.warn("Error at addTicket, at TicketPoolService: {}", e.getMessage());
        }
    }

    public TicketPoolConfiguration configureTicketPool(TicketPoolConfiguration config) {
        this.ticketPool.setMaxTicketCapacity(config.maximumTicketCapacity());
        logger.info("Successfully configured ticket pool");
        return config;
    }

    public List<Ticket> getTicketPool() {
        return this.ticketPool.getTickets();
    }
}
