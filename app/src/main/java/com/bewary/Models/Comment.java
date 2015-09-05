package com.bewary.Models;

import java.util.Date;

public class Comment {
    private int id;
    private User user;
    private Event event;
    private Date date;

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