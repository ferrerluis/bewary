package com.bewary.Models;

import java.util.Date;

public class Comment {
    private int id;
    private User user;
    private Event event;
    private Date date;
    private String message;

    public String getMessage() {
        return message;
    }

    public Comment(User user, Event event, Date date, String message) {
        this.id = 0;
        this.user = user;
        this.event = event;
        this.date = date;
        this.message = message;

    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public Date getDate() {
        return date;
    }
}