package me.ticketing_system.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import me.ticketing_system.config.ConfigRepository;
import me.ticketing_system.vendor.VendorRepository;

@Repository
public class EventRepository {
    private final JdbcClient jdbcClient;
    private final ConfigRepository configRepository;
    private final VendorRepository vendorRepository;

    public EventRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.configRepository = new ConfigRepository(jdbcClient);
        this.vendorRepository = new VendorRepository(jdbcClient);
    }

    public List<String> findEventIds() {
        return jdbcClient.sql("SELECT eventId FROM events")
                         .query(String.class)
                         .list();
    }

    public List<Event> findAll() {
        List<String> eventIds = findEventIds();
        List<Event> events = new ArrayList<>();
        for (String eventId : eventIds) {
            Optional<Event> eventOptional = findById(eventId);
            eventOptional.ifPresent(events::add);
        }
        return events;
    }

    public Optional<Event> findById(String eventId) {
        if (!this.findEventIds().contains(eventId)) {
            return Optional.empty();
        }
        
        Event event = jdbcClient
        .sql("""
            SELECT eventId, eventName, eventDescription, startDate, endDate, startTime, endTime, location, configId, vendorId
            FROM events WHERE eventId = :eventId
            """)
        .param("eventId", eventId)
        .query(Event.class)
        .single();

        String configId = jdbcClient
            .sql("SELECT configId FROM events WHERE eventId = :eventId")
            .param("eventId", eventId)
            .query(String.class)
            .single();

        String vendorId = jdbcClient.sql("""
                SELECT vendorId FROM events WHERE eventId = :eventId
                """)
            .param("eventId", eventId)
            .query(String.class)
            .single();

        configRepository.findById(configId).ifPresent(event::setConfig);
        vendorRepository.findById(vendorId).ifPresent(event::setVendor);

        return Optional.of(event);
    }

    public void createEvent(Event newEvent) {
        String sqlString = """
                INSERT INTO events (configId, vendorId, eventId, eventName, eventDescription, startDate, endDate, startTime, endTime, location)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        int created = jdbcClient.sql(sqlString)
                .params(List.of(
                        newEvent.getConfig().configId(),
                        newEvent.getVendor().getVendorId(),
                        newEvent.getEventId(),
                        newEvent.getEventName(),
                        newEvent.getEventDescription(),
                        newEvent.getStartDate(),
                        newEvent.getEndDate(),
                        newEvent.getStartTime(),
                        newEvent.getEndTime(),
                        newEvent.getLocation()))
                .update();
        Assert.state(created == 1, "Failed to create the event: " + newEvent);
    }

    public void deleteEvent(String eventId) {
        int deleted = jdbcClient.sql("DELETE FROM events WHERE eventId = :eventId")
                .param("eventId", eventId)
                .update();
        Assert.state(deleted == 1, "Failed to delete the event with eventId: " + eventId);
    }
}
