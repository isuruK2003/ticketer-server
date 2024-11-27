package me.ticketing_system.ticketpool;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateAcessEntityException extends RuntimeException {
    public DuplicateAcessEntityException() {
        super("An entity is already assigned to this acess object");
    }
}
