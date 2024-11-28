package me.ticketing_system.ticketpool;

import java.util.Vector;

import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {

    TicketPool ticketPool;

    public TicketPoolService() {
        this.ticketPool = new TicketPool(100);
    }

    public boolean removeTicket(Ticket ticket) {
        try {
            ticketPool.removeTicket(ticket);
            return true;
        } catch (InterruptedException e) {
            return false;
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
}
