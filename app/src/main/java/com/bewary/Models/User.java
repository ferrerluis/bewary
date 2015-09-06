package com.bewary.Models;

public class User {

    private String name;
    private String email;
    private String picture;

    public User() {
    }

    public User(String name, String email) {

        this.name = name;
        this.email = email;
        this.picture = null;
    }

    public User(String name, String email, String picture) {

        this(name, email);
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageURL() {
        return picture;
    }
}
