package com.bewary.Models;

import java.util.Date;

public class Event {
    private int id;
    private EventType eventType;
    private Location location;
    private Date date;
    private User user;
    private String description;

    public int getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }
}