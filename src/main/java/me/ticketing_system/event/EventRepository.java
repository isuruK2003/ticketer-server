package me.ticketing_system.event;

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

    public List<Event> findAll() {
        return jdbcClient.sql("SELECT * FROM events")
                .query(Event.class)
                .list();
    }

    public Optional<Event> findById(String eventId) {        
        Event event = jdbcClient.sql("""
                SELECT eventId, eventName, eventDescription, startDateTime, endDateTime, location, configId, vendorId
                FROM events WHERE eventId = :eventId
                """)
            .param("eventId", eventId)
            .query(Event.class)
            .single();

        if (event == null) {
            return Optional.empty();
        }

        String configId = jdbcClient.sql("""
                SELECT configId FROM events WHERE eventId = :eventId
                """)
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
                INSERT INTO events (configId, vendorId, eventId, eventName, eventDescription, startDateTime, endDateTime, location)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        int created = jdbcClient.sql(sqlString)
                .params(List.of(
                        newEvent.getConfig().configId(),
                        newEvent.getVendor().getVendorId(),
                        newEvent.getEventId(),
                        newEvent.getEventName(),
                        newEvent.getEventDescription(),
                        newEvent.getStartDateTime(),
                        newEvent.getEndDateTime(),
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
