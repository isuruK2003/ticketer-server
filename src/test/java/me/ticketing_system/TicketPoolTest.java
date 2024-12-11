package me.ticketing_system;

import me.ticketing_system.ticketpool.Ticket;
import me.ticketing_system.ticketpool.TicketPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketPoolTest {

    private TicketPool ticketPool;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        // Initialize the TicketPool before each test
        ticketPool = new TicketPool();
        ticket = new Ticket(1, 100);  // Example ticket with ID 1 and Vendor ID 100
        ticketPool.setMaxTicketCapacity(3); // Set a small max capacity for testing
    }

    @Test
    void testAddTicket() throws InterruptedException {
        ticketPool.addTicket(ticket); // Add a single ticket
        assertEquals(1, ticketPool.getTickets().size(), "Ticket pool should contain one ticket.");
    }

    @Test
    void testRemoveTicket() throws InterruptedException {
        ticketPool.addTicket(ticket);  // Add a ticket
        Ticket removedTicket = ticketPool.removeTicket(200);  // Consumer with ID 200 removes a ticket
        assertEquals(ticket.getTicketId(), removedTicket.getTicketId(), "Removed ticket should match the added ticket.");
        assertEquals(200, removedTicket.getConsumerId(), "Consumer ID should be set correctly on the removed ticket.");
    }

    @Test
    void testMaxCapacityExceeded() throws InterruptedException {
        // Add 3 tickets, which should fill the pool to its capacity
        ticketPool.addTicket(new Ticket(1, 101));
        ticketPool.addTicket(new Ticket(2, 102));
        ticketPool.addTicket(new Ticket(3, 103));

        // Try adding one more ticket, which should block (as the pool is full)
        Ticket newTicket = new Ticket(4, 104);

        Thread addTicketThread = new Thread(() -> {
            try {
                ticketPool.addTicket(newTicket); // This will be blocked until a ticket is removed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        addTicketThread.start();
        Thread.sleep(500); // Simulate some delay
        ticketPool.removeTicket(200); // Remove a ticket and unblock the addTicketThread

        addTicketThread.join(); // Wait for the thread to finish
        assertEquals(3, ticketPool.getTickets().size(), "Ticket pool should have three tickets after adding and removing.");
    }

    @Test
    void testAddTicketCapacity() throws InterruptedException {
        ticketPool.addTicket(ticket);
        assertEquals(1, ticketPool.getTickets().size(), "Ticket pool should contain one ticket.");

        // Set capacity to 2 and test adding more tickets
        ticketPool.setMaxTicketCapacity(2);
        ticketPool.addTicket(new Ticket(2, 101));

        assertEquals(2, ticketPool.getTickets().size(), "Ticket pool should contain two tickets.");

        // Try adding a third ticket (should block until a ticket is removed)
        Ticket newTicket = new Ticket(3, 102);

        Thread addTicketThread = new Thread(() -> {
            try {
                ticketPool.addTicket(newTicket); // This will be blocked until a ticket is removed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        addTicketThread.start();
        Thread.sleep(500); // Simulate some delay
        ticketPool.removeTicket(200); // Remove a ticket and unblock the addTicketThread

        addTicketThread.join(); // Wait for the thread to finish
        assertEquals(2, ticketPool.getTickets().size(), "Ticket pool should have two tickets after adding and removing.");
    }
}
