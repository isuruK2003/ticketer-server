package me.ticketing_system.ticketpool;

import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // This method can be invoked anywhere in your service layer
    public void broadcastTicketRemoval(Ticket ticket) {
        System.out.println("Broadcasted");
        messagingTemplate.convertAndSend("/topic/ticket-removed", ticket);
    }
}
