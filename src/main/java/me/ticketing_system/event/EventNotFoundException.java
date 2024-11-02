package me.ticketing_system.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() {
        super("Event Not Found");
    }
}
