package com.bewary.Models;

import java.util.Date;

public class Comment {

    private User author;
    private Event event;
    private Date date;
    private String message;

    public Comment() {
    }

    public Comment(User author, Event event, Date date, String message) {

        this.author = author;
        this.event = event;
        this.date = date;
        this.message = message;
    }

    public User getAuthor() {

        return author;
    }

    public Event getEvent() {

        return event;
    }

    public Date getDate() {

        return date;
    }

    public String getMessage() {

        return message;
    }

}