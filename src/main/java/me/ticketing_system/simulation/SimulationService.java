package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.TicketPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimulationService extends Simulation {

    private final SimulationValidator simulationValidator;
    private final Logger logger = LoggerFactory.getLogger(SimulationService.class);

    public SimulationService(TicketPool ticketPool, SimulationValidator simulationValidator) {
        super(ticketPool);
        this.simulationValidator = simulationValidator;
    }

    public void configureSimulation(SimulationConfiguration config) {
        if (hasVendorsStarted() || hasConsumersStarted()) {
            throw new RuntimeException("Simulation already started");
        }
        simulationValidator.validate(config); // throws validation error if config is not invalid
        this.setTotalTicketsForConsumer(config.totalTicketsForCustomer());
        this.setTotalTicketsForVendor(config.totalTicketsForVendor());
        this.setConsumerSleepTimeMillis(config.consumerSleepTime());
        this.setVendorSleepTimeMillis(config.vendorSleepTime());
        this.setTotalVendors(config.totalVendors());
        this.setTotalConsumers(config.totalConsumer());
        logger.info("Simulation configured Configured Successfully : {}", config);
    }

    public void initializeVendors() {
        if (!this.hasConfigured()) {
            throw new RuntimeException("Simulation is not configured");
        }
        if (this.hasVendorsStarted()) {
            throw new RuntimeException("Vendor threads are already started");
        }
        initializeVendorThreads();
        logger.info("Vendor threads initialized Successfully ");
    }

    public void initializeConsumers() {
        if (!this.hasConfigured()) {
            throw new RuntimeException("Simulation is not configured");
        }
        if (this.hasVendorsStarted()) {
            throw new RuntimeException("Vendor threads are already started");
        }
        initializeConsumerThreads();
        logger.info("Consumer threads initialized Successfully ");
    }

    public void startVendors() {
        if (!this.hasConfigured()) {
            throw new RuntimeException("Simulation is not configured");
        }
        if (this.hasVendorsStarted()) {
            throw new RuntimeException("Vendor threads are already started");
        }
        startVendorThreads();
        logger.info("Vendor threads started Successfully ");
    }

    public void startConsumers() {
        if (!this.hasConfigured()) {
            throw new RuntimeException("Simulation is not configured");
        }
        if (this.hasConsumersStarted()) {
            throw new RuntimeException("Consumer threads are already started");
        }
        startConsumerThreads();
        logger.info("Consumer threads started Successfully ");
    }

    public void stopVendors() {
        if (!this.hasVendorsStarted()) {
            throw new RuntimeException("Vendor threads are not started");
        }
        stopVendorThreads();
        logger.info("Vendor threads stopped Successfully ");
    }

    public void stopConsumers() {
        if (!this.hasConsumersStarted()) {
            throw new RuntimeException("Consumer threads are not started");
        }
        stopConsumerThreads();
        logger.info("Consumer threads stopped Successfully ");
    }

    public void clearVendors() {
        if (this.hasVendorsStarted()) {
            throw new RuntimeException("Vendor threads are already started");
        }
        clearVendorThreads();
    }

    public void clearConsumers() {
        if (this.hasConsumersStarted()) {
            throw new RuntimeException("Consumer threads are already started");
        }
        clearConsumerThreads();
    }
}
