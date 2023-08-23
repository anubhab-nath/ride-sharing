package com.example.geektrust.entities;

public class Ride {
    private final String id;
    private Driver driver;
    private Rider rider;
    private RideStatus rideStatus = RideStatus.COMPLETED;

    public Ride(String rideId) {
        this.id = rideId;
    }

    public boolean alreadyExists() {
        return rideStatus != RideStatus.CREATED;
    }

    public void printRideId() {
        System.out.printf("RIDE_STARTED %s", this.id);
    }

    public void start(Rider rider, Driver driver) {
        this.rider = rider;
        this.driver = driver;
        this.rideStatus = RideStatus.STARTED;
    }
}
