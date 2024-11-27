package me.ticketing_system.ticketpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticketpool")
public class TicketPoolController {
    private final TicketPool ticketPool;
    private BlockingQueue<TicketPoolAceess> accessQueue;

    private final Logger log = LoggerFactory.getLogger(TicketPoolController.class);

    public TicketPoolController() {
        this.ticketPool = new TicketPool();
        this.accessQueue = new LinkedBlockingQueue<>();
    }
    
    @GetMapping
    public TicketPool getTicketPool() {
        return this.ticketPool;
    }

    @PostMapping("/add")
    public void addTicket(@Valid @RequestBody TicketPoolAceess access) {
        this.ticketPool.addTicket(access.ticket);
        this.accessQueue.add(access);
        log.info("Added ticket" + " " + this.accessQueue);
    }

    @PostMapping("/remove")
    public Ticket removeTicket(@Valid @RequestBody TicketPoolAceess acess) {
        this.accessQueue.add(acess);
        log.info("Removed ticket" + " " + this.accessQueue);
        return this.ticketPool.removeTicket();
    }
}
