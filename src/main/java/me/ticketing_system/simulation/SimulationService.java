package me.ticketing_system.simulation;

import me.ticketing_system.ticketpool.Ticket;
import me.ticketing_system.ticketpool.TicketPool;
import me.ticketing_system.ticketpool.TicketPoolListChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimulationService extends Simulation {

    private final SimulationConfigurationValidator simulationValidator;
    private final Logger logger = LoggerFactory.getLogger(SimulationService.class);
    private final SimulationWebSocketService simulationWebSocketService;

    public SimulationService(TicketPool ticketPool,
                             SimulationConfigurationValidator simulationValidator,
                             SimulationWebSocketService simulationWebSocketService) {
        super(ticketPool);
        this.simulationValidator = simulationValidator;
        this.simulationWebSocketService = simulationWebSocketService;
    }

    public void subscribeToTicketPoolChanges() {
        this.ticketPool.addListener(new TicketPoolListChangeListener() {
            @Override
            public void onSizeChanged(Ticket ticket) {
                SimulationStatus simulationStatus = getSimulationStatus();
                SimulationStatus updatedSimulationStatus = new SimulationStatus(
                        ticket,
                        simulationStatus.totalActiveVendors(),
                        simulationStatus.totalActiveConsumers(),
                        simulationStatus.totalTickets()
                );
                simulationWebSocketService.broadcastSimulationStatus(
                        updatedSimulationStatus
                );
                logger.info("Simulation Status {}", updatedSimulationStatus);
            }
        });
    }

    public void configureSimulation(SimulationConfiguration config) {
        if (hasVendorsStarted() || hasConsumersStarted()) {
            throw new RuntimeException("Simulation already started");
        }
        simulationValidator.validate(config); // throws validation error if config is not invalid
        this.setTotalTicketsForConsumer(config.totalTicketsForCustomer());
        this.setTotalTicketsForVendor(config.totalTicketsForVendor());
        this.setCustomerRetrievalRate(config.customerRetrievalRate());
        this.setTicketReleaseRate(config.ticketReleaseRate());
        this.setTotalVendors(config.totalVendors());
        this.setTotalConsumers(config.totalConsumers());
        this.setMaxTicketCapacity(config.maxTicketCapacity());
        logger.info("Simulation Configured Successfully : {}", config);
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
        setVendorsStarted(false);
        logger.info("Vendor threads cleared successfully ");
    }

    public void clearConsumers() {
        if (this.hasConsumersStarted()) {
            throw new RuntimeException("Consumer threads are already started");
        }
        clearConsumerThreads();
        this.setConsumersStarted(false);
        logger.info("Consumer threads cleared successfully ");
    }

    public void clearTicketPool() {
        this.clearTickets();
        this.simulationWebSocketService.broadcastSimulationStatus(getSimulationStatus());
        logger.info("Ticket pool cleared successfully ");
    }
}
