package com.example.geektrust.entities;

public class Driver {
    private final String id;
    private Location location;
    private DriverStatus driverStatus;

    public Driver(String id, Location location) {
        this.id = id;
        this.location = location;
        this.driverStatus = DriverStatus.AVAILABLE;
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

    public void startRide() {
        driverStatus = DriverStatus.OCCUPIED;
    }

    public void stopRide() {
        driverStatus = DriverStatus.AVAILABLE;
    }

    public boolean isDriverOccupied() {
        return driverStatus == DriverStatus.OCCUPIED;
    }
}
