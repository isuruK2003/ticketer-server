package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.TicketPool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Simulation {
    private final List<Thread> vendorThreads;
    private final List<Thread> consumerThreads;
    final TicketPool ticketPool;

    private Integer totalVendors;
    private Integer totalConsumers;
    private Integer vendorSleepTimeMillis;
    private Integer consumerSleepTimeMillis;
    private Integer totalTicketsForVendor;
    private Integer totalTicketsForConsumer;

    private Boolean consumersStarted;
    private Boolean vendorsStarted;

    public Simulation(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
        this.vendorThreads = new ArrayList<>();
        this.consumerThreads = new ArrayList<>();
        this.consumersStarted = false;
        this.vendorsStarted = false;
    }

    //////// Initializers ////////

    public void initializeVendorThreads() {
        for (int i = 0; i < this.totalVendors; i++) {
            Vendor vendor = new Vendor(i, this.ticketPool, this.vendorSleepTimeMillis, this.totalTicketsForVendor);
            Thread t = new Thread(vendor);
            vendorThreads.add(t);
        }
    }

    public void initializeConsumerThreads() {
        for (int i = 0; i < this.totalConsumers; i++) {
            Consumer consumer = new Consumer(i, this.ticketPool, this.consumerSleepTimeMillis, this.totalTicketsForConsumer);
            Thread t = new Thread(consumer);
            consumerThreads.add(t);
        }
    }

    //////// Thread Starters ////////

    public void startVendorThreads() {
        for (Thread t : vendorThreads) {
            t.start();
        }
        this.vendorsStarted = true;
    }

    public void startConsumerThreads() {
        for (Thread t : consumerThreads) {
            t.start();
        }
        this.consumersStarted = true;
    }

    //////// Thread Stoppers ////////

    public void stopVendorThreads() {
        for (Thread t : vendorThreads) {
            t.interrupt();
        }
        this.vendorsStarted = false;
    }

    public void stopConsumerThreads() {
        for (Thread t : consumerThreads) {
            t.interrupt();
        }
        this.consumersStarted = false;
    }

    //////// Setters ////////

    public void setConsumerSleepTimeMillis(Integer consumerSleepTimeMillis) {
        this.consumerSleepTimeMillis = consumerSleepTimeMillis;
    }

    public void setVendorSleepTimeMillis(Integer vendorSleepTimeMillis) {
        this.vendorSleepTimeMillis = vendorSleepTimeMillis;
    }

    public void setTotalTicketsForConsumer(Integer totalTicketsForConsumer) {
        this.totalTicketsForConsumer = totalTicketsForConsumer;
    }

    public void setTotalTicketsForVendor(Integer totalTicketsForVendor) {
        this.totalTicketsForVendor = totalTicketsForVendor;
    }

    public void setTotalVendors(Integer totalVendors) {
        this.totalVendors = totalVendors;
    }

    public void setTotalConsumers(Integer totalConsumers) {
        this.totalConsumers = totalConsumers;
    }

    ///////// Clearer's ////////

    public void clearConsumerThreads() {
        this.consumerThreads.clear();
    }

    public void clearVendorThreads() {
        this.vendorThreads.clear();
    }

    //////// Checkers ////////

    public Boolean hasConfigured() {
        return (this.vendorSleepTimeMillis != null
                && this.consumerSleepTimeMillis != null
                && this.totalTicketsForVendor != null
                && this.totalTicketsForConsumer != null
                && this.totalVendors != null
                && this.totalConsumers != null
        );
    }

    public Boolean hasConsumersStarted() {
        return this.consumersStarted;
    }

    public Boolean hasVendorsStarted() {
        return this.vendorsStarted;
    }

    //// Getters ////

    public SimulationStatus getSimulationStatus() {
        return new SimulationStatus(
                null,
                this.vendorThreads.size(),
                this.consumerThreads.size(),
                this.ticketPool.getTickets().size()
        );
    }

    public SimulationConfigurationStatus getSimulationConfigurationStatus() {
        return new SimulationConfigurationStatus(
                hasConfigured(),
                hasVendorsStarted(),
                hasConsumersStarted()
        );
    }
}
