
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
	WebSocketService webSocketService;

	public TicketPoolController(WebSocketService webSocketService) {
		this.ticketPoolService = new TicketPoolService();
		this.webSocketService = webSocketService;
	}

	@MessageMapping("/remove-ticket")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> removeTicket() {
		// should broadcast this removed ticket to the specific
		Ticket removedTicket = this.ticketPoolService.removeTicket();
		this.webSocketService.broadcastTicketRemoval(removedTicket);

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

	@MessageMapping("/configure-ticketpool")
	@SendTo("/topic/ticketpool")
	public TicketPoolConfiguration configureTicketPool(TicketPoolConfiguration config) {
		return this.ticketPoolService.configureTicketPool(config);
	}

	@MessageMapping("/stimulate-adding")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> stimulateAdding() {
		this.ticketPoolService.stimulateAdd();
		return this.ticketPoolService.getTicketPool();
	}

	@MessageMapping("/stimulate-removing")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> stimulateRemoving() {
		this.ticketPoolService.stimulateRemove();
		return this.ticketPoolService.getTicketPool();
	}
}