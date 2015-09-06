package com.bewary.Models;

import java.util.Date;

public class Comment {

    private User author;
    private Event event;
    private Date date;
    private String message;

<<<<<<< HEAD
    public String getMessage() {
        return message;
    }

    public Comment(User user, Event event, Date date, String message) {
        this.id = 0;
        this.user = user;
        this.event = event;
        this.date = date;
        this.message = message;

=======
    public Comment() {
>>>>>>> 01f34b5f8dc75afedc925367c594867f0a94a518
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