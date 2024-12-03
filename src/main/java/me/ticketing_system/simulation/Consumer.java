package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.TicketPool;

public class Consumer implements Runnable {
    private TicketPool ticketPool;
    private Integer sleepTimeMilli;

    public Consumer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public Consumer(TicketPool ticketPool, Integer sleepTimeMilli) {
        this.ticketPool = ticketPool;
        this.sleepTimeMilli = sleepTimeMilli;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.ticketPool.removeTicket();
                System.out.println("removed from thread");
                Thread.sleep(this.sleepTimeMilli);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Vendor");
            }
        }
    }
}
