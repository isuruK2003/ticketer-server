package me.ticketing_system.ticketpool;

public class Consumer implements Runnable {
    private TicketPool ticketPool;

    public Consumer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.ticketPool.removeTicket();
                System.out.println("removed from thread");
                Thread.sleep(4000);
            } catch(InterruptedException e) {
                System.out.println("Interrupted Vendor");
            }
        }
    }
}
