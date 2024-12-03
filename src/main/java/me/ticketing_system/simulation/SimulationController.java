package me.ticketing_system.simulation;

import java.util.Vector;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import me.ticketing_system.ticketpool.Ticket;

@Controller
@CrossOrigin
public class SimulationController {

    private SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @MessageMapping("/start")
    @SendTo("/topic/simulation")
    public Vector<Ticket> stimulateAdding() {
        // todo
        // get the vendorCount and consumerCount from the client
        // get the vendorThread sleep time, and consumer thread sleep time
        simulationService.startSimulation(3, 3);
        return simulationService.getTicketPool().getTickets();
    }

    @MessageMapping("/stop")
    @SendTo("/topic/simulation")
    public Vector<Ticket> stimulateRemoving() {
        return simulationService.getTicketPool().getTickets();
    }
}
