package me.ticketing_system.cli;

import me.ticketing_system.simulation.Simulation;
import me.ticketing_system.simulation.SimulationConfiguration;
import me.ticketing_system.simulation.SimulationService;
import me.ticketing_system.ticketpool.TicketPool;

public class Cli extends CliUtilities {
    private final static Simulation simulation = new Simulation(new TicketPool());

    public static void init() {
        System.out.println(logo);
    }

    public static void loop() {
        String option = "";
        do {
            displayMainMenu();
            option = CliUtilities.readStringInput("Enter the option: ");
            if (option.equals("1")) {
                SimulationConfiguration simulationConfiguration = new SimulationConfiguration(
                        readIntegerInput("Total tickets for vendor: "),
                        readIntegerInput("Total tickets for consumer: "),
                        readIntegerInput("Total vendors: "),
                        readIntegerInput("Total consumers: "),
                        readIntegerInput("Vendor sleep time: "),
                        readIntegerInput("Consumer sleep time: "));

            } else {
                System.out.println("Invalid option");
            }
        } while (!option.equals(exitKey));
    }
}
