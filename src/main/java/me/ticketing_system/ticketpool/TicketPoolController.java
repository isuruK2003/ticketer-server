package me.ticketing_system.ticketpool;

import java.util.Vector;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class TicketPoolController {

    TicketPoolService ticketPoolService;

    public TicketPoolController() {
        this.ticketPoolService = new TicketPoolService();
    }

    @MessageMapping("/remove-ticket")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> removerTicket(Ticket ticket) {
		this.ticketPoolService.addTicket(ticket);
		return this.ticketPoolService.getTicketPool();
	}

    @MessageMapping("/add-ticket")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> addTicket(Ticket ticket) {
		this.ticketPoolService.addTicket(ticket);
		return this.ticketPoolService.getTicketPool();
	}

	@MessageMapping("/get-ticketpool")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> getTicketPool() {
		return this.ticketPoolService.getTicketPool();
	}
}
