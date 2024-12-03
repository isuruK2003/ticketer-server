package me.ticketing_system.ticketpool;

import java.util.Vector;

import org.springframework.stereotype.Service;

import me.ticketing_system.simulation.Consumer;
import me.ticketing_system.simulation.Vendor;

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

    public void addTicket(Ticket ticket) {
        try {
            ticketPool.addTicket(ticket);
        } catch (InterruptedException e) {
            // todo: add error logging
        }
    }

    public Vector<Ticket> getTicketPool() {
        return this.ticketPool.getTickets();
    }

    public TicketPoolConfiguration configureTicketPool(TicketPoolConfiguration config) {
        this.ticketPoolConfiguration = config;
        this.ticketPool.setMaxTicketCapacity(config.getMaximumTicketCapacity());
        System.out.println("Added Configuration");
        return config;
    }

    public void stimulateAdd() {
        Vendor vendorThread = new Vendor(this.ticketPool);
        Thread t = new Thread(vendorThread);
        t.start();
    }

    public void stimulateRemove() {
        Consumer consumerThread = new Consumer(this.ticketPool);
        Thread t = new Thread(consumerThread);
        t.start();
    }
}
