package me.ticketing_system.cli;

import me.ticketing_system.simulation.Simulation;
import me.ticketing_system.ticketpool.TicketPool;

public class CliApp {

    private static Simulation simulation = new Simulation(new TicketPool());

    private static void configure() {
        simulation.setTotalVendors(CliUtilities.readConfigurationValue("totalVendors"));
        simulation.setTotalConsumers(CliUtilities.readConfigurationValue("totalConsumer"));
        simulation.setTotalTicketsForVendor(CliUtilities.readConfigurationValue("totalTicketsForVendor"));
        simulation.setTotalTicketsForConsumer(CliUtilities.readConfigurationValue("totalTicketsForCustomer"));
        simulation.setVendorSleepTimeMillis(CliUtilities.readConfigurationValue("vendorSleepTime"));
        simulation.setConsumerSleepTimeMillis(CliUtilities.readConfigurationValue("consumerSleepTime"));
    }

    private static void startSimulation() {
        if (simulation.hasConfigured()) {
            System.out.println("Initializing vendors ...");
            simulation.initializeVendorThreads();
            System.out.println("Initializing consumers ...");
            simulation.initializeConsumerThreads();
            System.out.println("Starting vendors ...");
            simulation.startVendorThreads();
            System.out.println("Starting consumers ...");
            simulation.startConsumerThreads();
            System.out.println("Simulation started successfully");
        }
    }

    public static void main(String[] args) {
        configure();
        startSimulation();
    }
}
