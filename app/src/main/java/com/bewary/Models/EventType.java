package com.bewary.Models;

public class EventType {

    private String name;
    private int level;

    public EventType() {
    }

    public EventType(String name, int level) {

        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
