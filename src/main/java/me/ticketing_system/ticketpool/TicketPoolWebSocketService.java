package me.ticketing_system.ticketpool;

import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class TicketPoolWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public TicketPoolWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastTicketRemoval(Ticket ticket) {
        messagingTemplate.convertAndSend("/topic/ticket-removed", ticket);
    }
}