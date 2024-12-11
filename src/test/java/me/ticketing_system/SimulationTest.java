package me.ticketing_system;

import me.ticketing_system.simulation.Simulation;
import me.ticketing_system.ticketpool.TicketPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    private Simulation simulation;
    private TicketPool ticketPool;

    @BeforeEach
    void setUp() {
        ticketPool = new TicketPool();
        simulation = new Simulation(ticketPool);
    }

    @Test
    void testHasConfiguredReturnsFalseWhenNotConfigured() {
        assertFalse(simulation.hasConfigured());
    }

    @Test
    void testHasConfiguredReturnsTrueWhenConfigured() {
        simulation.setTicketReleaseRate(10);
        simulation.setCustomerRetrievalRate(5);
        simulation.setTotalTicketsForVendor(100);
        simulation.setTotalTicketsForConsumer(50);
        simulation.setTotalVendors(2);
        simulation.setTotalConsumers(3);

        assertTrue(simulation.hasConfigured());
    }

    @Test
    void testInitializeVendorThreadsCreatesCorrectNumberOfThreads() {
        simulation.setTotalVendors(3);
        simulation.setTicketReleaseRate(10);
        simulation.setTotalTicketsForVendor(100);

        simulation.initializeVendorThreads();

        assertEquals(3, simulation.getSimulationStatus().totalActiveVendors());
    }

    @Test
    void testInitializeConsumerThreadsCreatesCorrectNumberOfThreads() {
        simulation.setTotalConsumers(4);
        simulation.setCustomerRetrievalRate(5);
        simulation.setTotalTicketsForConsumer(50);

        simulation.initializeConsumerThreads();

        assertEquals(4, simulation.getSimulationStatus().totalActiveConsumers());
    }

    @Test
    void testStartVendorThreadsMarksVendorsAsStarted() {
        simulation.setTotalVendors(2);
        simulation.setTicketReleaseRate(10);
        simulation.setTotalTicketsForVendor(100);
        simulation.initializeVendorThreads();

        simulation.startVendorThreads();

        assertTrue(simulation.hasVendorsStarted());
    }

    @Test
    void testStartConsumerThreadsMarksConsumersAsStarted() {
        simulation.setTotalConsumers(2);
        simulation.setCustomerRetrievalRate(5);
        simulation.setTotalTicketsForConsumer(50);
        simulation.initializeConsumerThreads();

        simulation.startConsumerThreads();

        assertTrue(simulation.hasConsumersStarted());
    }

    @Test
    void testStopVendorThreadsMarksVendorsAsStopped() {
        simulation.setTotalVendors(2);
        simulation.setTicketReleaseRate(10);
        simulation.setTotalTicketsForVendor(100);
        simulation.initializeVendorThreads();
        simulation.startVendorThreads();

        simulation.stopVendorThreads();

        assertFalse(simulation.hasVendorsStarted());
    }

    @Test
    void testStopConsumerThreadsMarksConsumersAsStopped() {
        simulation.setTotalConsumers(2);
        simulation.setCustomerRetrievalRate(5);
        simulation.setTotalTicketsForConsumer(50);
        simulation.initializeConsumerThreads();
        simulation.startConsumerThreads();

        simulation.stopConsumerThreads();

        assertFalse(simulation.hasConsumersStarted());
    }

    @Test
    void testClearVendorThreadsEmptiesVendorThreadsList() {
        simulation.setTotalVendors(2);
        simulation.setTicketReleaseRate(10);
        simulation.setTotalTicketsForVendor(100);
        simulation.initializeVendorThreads();

        simulation.clearVendorThreads();

        assertEquals(0, simulation.getSimulationStatus().totalActiveVendors());
    }

    @Test
    void testClearConsumerThreadsEmptiesConsumerThreadsList() {
        simulation.setTotalConsumers(2);
        simulation.setCustomerRetrievalRate(5);
        simulation.setTotalTicketsForConsumer(50);
        simulation.initializeConsumerThreads();

        simulation.clearConsumerThreads();

        assertEquals(0, simulation.getSimulationStatus().totalActiveConsumers());
    }

    @Test
    void testSetMaxTicketCapacityUpdatesTicketPool() {
        int maxCapacity = 500;
        simulation.setMaxTicketCapacity(maxCapacity);
        assertEquals(maxCapacity, ticketPool.getMaxTicketCapacity());
    }
}
