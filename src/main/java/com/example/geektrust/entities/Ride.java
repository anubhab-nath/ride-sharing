package com.example.geektrust.entities;

public class Ride {
    private final String id;
    private Driver driver;
    private Rider rider;
    private RideStatus rideStatus = RideStatus.CREATED;

    public Ride(String rideId) {
        this.id = rideId;
    }

    public boolean alreadyExists() {
        return rideStatus != RideStatus.CREATED;
    }

    public void printRideId() {
        System.out.println("RIDE_STARTED " + this.id);
    }

    public void start(Rider rider, Driver driver) {
        this.rider = rider;
        this.driver = driver;
        this.rideStatus = RideStatus.STARTED;
    }
}
