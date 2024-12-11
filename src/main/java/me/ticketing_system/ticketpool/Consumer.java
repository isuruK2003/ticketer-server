package me.ticketing_system.ticketpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {
    private final Integer consumerId;
    private final TicketPool ticketPool;
    private final Integer customerRetrievalRate;
    private final Integer totalTickets;
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Consumer(Integer consumerId, TicketPool ticketPool, Integer customerRetrievalRate, Integer totalTickets) {
        this.consumerId = consumerId;
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.totalTickets ; i++) {
            try {
                Ticket removedTicket = this.ticketPool.removeTicket(this.consumerId);
                logger.info("Ticket removed by Consumer {}: {}", consumerId, removedTicket);
                Thread.sleep(1000 / this.customerRetrievalRate);
            } catch (InterruptedException e) {
                logger.error("Error occurred at Consumer at run() : {}", e.getMessage());
                break;
            }
        }
    }
}
