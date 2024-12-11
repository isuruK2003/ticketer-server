package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.Consumer;
import me.ticketing_system.ticketpool.TicketPool;
import me.ticketing_system.ticketpool.Vendor;
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
    private Integer ticketReleaseRate;
    private Integer customerRetrievalRate;
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

    public void initializeVendorThreads() {
        for (int i = 0; i < this.totalVendors; i++) {
            Vendor vendor = new Vendor(i, this.ticketPool, this.ticketReleaseRate, this.totalTicketsForVendor);
            Thread t = new Thread(vendor);
            vendorThreads.add(t);
        }
    }

    public void initializeConsumerThreads() {
        for (int i = 0; i < this.totalConsumers; i++) {
            Consumer consumer = new Consumer(i, this.ticketPool, this.customerRetrievalRate, this.totalTicketsForConsumer);
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

    public void setCustomerRetrievalRate(Integer customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setTicketReleaseRate(Integer ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
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

    public void setMaxTicketCapacity(Integer maxTicketCapacity) {
        this.ticketPool.setMaxTicketCapacity(maxTicketCapacity);
    }

    public void setConsumersStarted(Boolean consumersStarted) {
        this.consumersStarted = consumersStarted;
    }

    public void setVendorsStarted(Boolean vendorsStarted) {
        this.vendorsStarted = vendorsStarted;
    }

    ///////// Clearer's ////////

    public void clearConsumerThreads() {
        this.consumerThreads.clear();
    }

    public void clearVendorThreads() {
        this.vendorThreads.clear();
    }

    public void clearTickets() {
        this.ticketPool.getTickets().clear();
    }

    //////// Checkers ////////

    public Boolean hasConfigured() {
        return (this.ticketReleaseRate != null
                && this.customerRetrievalRate != null
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
}
