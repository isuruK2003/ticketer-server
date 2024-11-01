package me.ticketing_system.event;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/")
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@Valid @RequestBody Event newEvent) {
        eventRepository.createEvent(newEvent);
    }
}
