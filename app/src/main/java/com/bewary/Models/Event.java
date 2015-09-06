package com.bewary.Models;

import java.util.Date;

public class Event {

    private EventType eventType;
    private Location location;
    private Date date;
    private User author;
    private String description;

    public Event() {
    }

    public Event(EventType eventType, Location location, User author, Date date) {

        this.eventType = eventType;
        this.location = location;
        this.author = author;
        this.date = date;
        this.description = null;
    }

    public Event(EventType eventType, Location location, User author, Date date, String description) {

        this(eventType, location, author, date);
        this.description = description;
    }

    public EventType getEventType() { return eventType; }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public User getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}