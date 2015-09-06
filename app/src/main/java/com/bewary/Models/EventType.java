package com.bewary.Models;

public class EventType {
    private int id;
    private String name;
    private int level;

    public EventType(String name, int level) {
        this.name = name;
        this.level = level;
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
