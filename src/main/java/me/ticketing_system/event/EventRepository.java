package me.ticketing_system.event;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class EventRepository {
    private final JdbcClient jdbcClient;

    public EventRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Event> findAll() {
        return jdbcClient.sql("SELECT * FROM events")
                .query(Event.class)
                .list();
    }

    public Optional<Event> findById(String eventId) {
        return jdbcClient.sql("SELECT * FROM events WHERE eventId=:eventId")
                .param("eventId", eventId)
                .query(Event.class)
                .optional();
    }

    public void createEvent(Event newEvent) {
        String sqlString = """
                INSERT INTO events (configId, vendorId, eventId, eventName, eventDescription, startDateTime, endDateTime, location)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        int created = jdbcClient.sql(sqlString)
                .params(List.of(newEvent.getConfigId(),
                        newEvent.getVendorId(),
                        newEvent.getEventId(),
                        newEvent.getEventName(),
                        newEvent.getEventDescription(),
                        newEvent.getStartDateTime(),
                        newEvent.getEndDateTime(),
                        newEvent.getLocation()))
                .update();
        Assert.state(created == 1, "Failed to create the event: " + newEvent);
    }
}
