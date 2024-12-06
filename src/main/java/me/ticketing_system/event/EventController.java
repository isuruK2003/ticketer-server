package me.ticketing_system.event;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
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

    @GetMapping("/{eventId}")
    public Event getById(@PathVariable String eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }
        return event.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@Valid @RequestBody Event newEvent) {
        eventRepository.createEvent(newEvent);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable String eventId) {
        eventRepository.deleteEvent(eventId);
    }
}
