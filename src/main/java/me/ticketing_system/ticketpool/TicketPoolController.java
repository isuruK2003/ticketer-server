
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
	Ticket removedTicket;

	public TicketPoolController() {
		this.ticketPoolService = new TicketPoolService();
	}

	@MessageMapping("/get-removed-ticket")
	@SendTo("topic/ticket-removal")
	public Ticket broadCastTicketRemoval() {
		Ticket ticketToSend = this.removedTicket;
		this.removedTicket = null;
		return ticketToSend;
	}

	@MessageMapping("/remove-ticket")
	@SendTo("/topic/ticketpool")
	public Vector<Ticket> removeTicket() {
		this.removedTicket = this.ticketPoolService.removeTicket();
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
	public TicketPoolConfiguration configureTicketpool(TicketPoolConfiguration config) {
		return this.ticketPoolService.configureTicketpool(config);
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