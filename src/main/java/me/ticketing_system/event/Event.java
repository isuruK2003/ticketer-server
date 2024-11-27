package me.ticketing_system.event;

import java.sql.Date;
import java.sql.Time;

import me.ticketing_system.vendor.Vendor;
import me.ticketing_system.config.Configuration;

public class Event {
    private Configuration config;
    private Vendor vendor;

    private String eventId;
    private String eventName;
    private String eventDescription;
    // @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    // @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private String location;
    
    public Event(String eventId, String eventName, 
                 String eventDescription, Date startDate, 
                 Date endDate, Time startTime, 
                 Time endTime, String location) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }
    
    public void setConfig(Configuration config) {
        this.config = config;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getEventId() {
        return eventId;
    }

    public Configuration getConfig() {
        return config;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Event [config=" + config + ", vendor=" + vendor + ", eventId=" + eventId + ", eventName=" + eventName
                + ", eventDescription=" + eventDescription + ", startDate=" + startDate + ", endDate=" + endDate
                + ", startTime=" + startTime + ", endTime=" + endTime + ", location=" + location + "]";
    }
}
