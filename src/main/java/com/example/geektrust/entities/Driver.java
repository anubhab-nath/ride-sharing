package com.example.geektrust.entities;

public class Driver {
    private final String id;
    private Location location;

    public Driver(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public int compareTo(Driver anotherDriver) {
        return id.compareTo(anotherDriver.getId());
    }
}
