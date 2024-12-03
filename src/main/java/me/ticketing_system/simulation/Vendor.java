package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.Ticket;
import me.ticketing_system.ticketpool.TicketPool;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private Integer sleepTimeMilli;

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public Vendor(TicketPool ticketPool, Integer sleepTimeMilli) {
        this.ticketPool = ticketPool;
        this.sleepTimeMilli = sleepTimeMilli;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Ticket newTicket = new Ticket(i);
                this.ticketPool.addTicket(newTicket);
                System.out.println("from thread");
                Thread.sleep(this.sleepTimeMilli);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Vendor");
            }
        }
    }
}
