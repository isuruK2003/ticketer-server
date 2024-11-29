package me.ticketing_system.ticketpool;

import java.util.Vector;

import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {

    TicketPool ticketPool;
    TicketPoolConfiguration ticketPoolConfiguration;

    public TicketPoolService() {
        this.ticketPool = new TicketPool();
    }

    public Ticket removeTicket() {
        try {
            return ticketPool.removeTicket();
        } catch (InterruptedException e) {
            return null;
            // todo: add error logging
        }
	}

	public boolean addTicket(Ticket ticket) {
        try {
            ticketPool.addTicket(ticket);
            return true;
        } catch (InterruptedException e) {
            return false;
            // todo: add error logging
        }
	}

	public Vector<Ticket> getTicketPool() {
		return this.ticketPool.getTickets();
	}

    public TicketPoolConfiguration configureTicketpool(TicketPoolConfiguration config) {
        this.ticketPoolConfiguration = config;
        this.ticketPool.setMaxTicketCapacity(config.getMaximumTicketCapacity());
        System.out.println("Added Configuration");
        return config;
    }
}