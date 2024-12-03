package me.ticketing_system.ticketpool;

public class Vendor implements Runnable {
    private TicketPool ticketPool;

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Ticket newTicket = new Ticket(i);
                this.ticketPool.addTicket(newTicket);
                System.out.println("from thread");
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                System.out.println("Interrupted Vendor");
            }
        }
    }
}
