
package me.ticketing_system.ticketpool;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class TicketPoolController {

	TicketPoolService ticketPoolService;
	TicketPoolWebSocketService ticketPoolWebSocketService;

	public TicketPoolController(TicketPoolWebSocketService ticketPoolWebSocketService) {
		this.ticketPoolService = new TicketPoolService();
		this.ticketPoolWebSocketService = ticketPoolWebSocketService;
	}

	@MessageMapping("/remove-ticket")
	@SendTo("/topic/ticketpool")
	public List<Ticket> removeTicket() {
		// todo: removed by consumer id should come front frontend
		Ticket removedTicket = this.ticketPoolService.removeTicket(null);
		this.ticketPoolWebSocketService.broadcastTicketRemoval(removedTicket);
		return this.ticketPoolService.getTicketPool();
	}

	@MessageMapping("/add-ticket")
	@SendTo("/topic/ticketpool")
	public List<Ticket> addTicket(Ticket ticket) {
		this.ticketPoolService.addTicket(ticket);
		return this.ticketPoolService.getTicketPool();
	}

	@MessageMapping("/get-ticketpool")
	@SendTo("/topic/ticketpool")
	public List<Ticket> getTicketPool() {
		return this.ticketPoolService.getTicketPool();
	}

	@MessageMapping("/configure-ticketpool")
	@SendTo("/topic/ticketpool")
	public TicketPoolConfiguration configureTicketPool(TicketPoolConfiguration config) {
		return this.ticketPoolService.configureTicketPool(config);
	}

	@MessageMapping("/stimulate-adding")
	@SendTo("/topic/ticketpool")
	public List<Ticket> stimulateAdding() {
		// todo: Implement endpoint logic to start simulation
		return this.ticketPoolService.getTicketPool();
	}

	@MessageMapping("/stimulate-removing")
	@SendTo("/topic/ticketpool")
	public List<Ticket> stimulateRemoving() {
		// todo: Implement endpoint logic to end simulation
		return this.ticketPoolService.getTicketPool();
	}
}