package me.ticketing_system.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException() {
        super("Configuration Not Found");
    }
}
