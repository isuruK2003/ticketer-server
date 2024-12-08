package me.ticketing_system.simulation;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimulationWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public SimulationWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastSimulationStatus(SimulationStatus simulationStatus) {
        messagingTemplate.convertAndSend("/topic/simulation/simulation-status", simulationStatus);
    }
}
