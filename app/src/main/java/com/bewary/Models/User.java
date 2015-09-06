package com.bewary.Models;

public class User {
    private int id;
    private String name;
    private String email;
    private String imageURL;

    public User(String name, String email, String imageURL) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageURL() {
        return imageURL;
    }
}
