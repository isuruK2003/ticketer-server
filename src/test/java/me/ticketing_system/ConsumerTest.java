package me.ticketing_system;

import me.ticketing_system.ticketpool.Consumer;
import me.ticketing_system.ticketpool.Ticket;
import me.ticketing_system.ticketpool.TicketPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {

    private TicketPool ticketPool;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticketPool = new TicketPool();
        ticket = new Ticket(1, 100); // 1, 100 are sample ids
        ticketPool.setMaxTicketCapacity(3);
    }

    // Test case for Consumer removing tickets from the pool
    @Test
    void testConsumerRemovesTicket() throws InterruptedException {
        // Add a few tickets to the pool
        ticketPool.addTicket(ticket);
        ticketPool.addTicket(new Ticket(2, 100));  // Add a second ticket

        // create and start a Consumer thread to remove tickets
        Consumer consumer = new Consumer(200, ticketPool, 1, 1);  // Consumer ID 200, removing 1 ticket
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        Thread.sleep(1000);
        assertEquals(1, ticketPool.getTickets().size(), "Ticket pool should contain 1 ticket after consumer removes one.");

        consumerThread.join();
    }
}
