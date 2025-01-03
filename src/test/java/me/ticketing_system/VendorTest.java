package me.ticketing_system;

import me.ticketing_system.ticketpool.TicketPool;
import me.ticketing_system.ticketpool.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorTest {

    private TicketPool ticketPool;

    @BeforeEach
    void setUp() {
        // Initialize the TicketPool before each test
        ticketPool = new TicketPool();
        ticketPool.setMaxTicketCapacity(3); // Set a small max capacity for testing
    }

    // Test case for Vendor adding tickets to the pool
    @Test
    void testVendorAddsTicket() throws InterruptedException {
        // Create and start a Vendor thread to add tickets
        Vendor vendor = new Vendor(100, ticketPool, 1, 3);
        Thread vendorThread = new Thread(vendor);
        vendorThread.start();
        Thread.sleep(5000);
        // Ensure the pool contains 5 tickets after the vendor has added them
        assertEquals(3, ticketPool.getTickets().size(), "Ticket pool should contain 5 tickets after vendor adds them.");
        vendorThread.join();
    }
}
