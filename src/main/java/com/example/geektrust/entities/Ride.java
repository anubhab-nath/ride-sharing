package com.example.geektrust.entities;

import com.example.geektrust.exceptions.InvalidRideException;

public class Ride {
    private final String id;
    private Driver driver;
    private Rider rider;
    private Location destination;
    private Integer timeTakenInMin;
    private RideStatus rideStatus = RideStatus.CREATED;

    public Ride(String rideId) {
        this.id = rideId;
    }

    public void startRide(Rider rider, Integer driverNum) throws InvalidRideException {
        if(rider.isDriverNotAvailable(driverNum) || alreadyExists()) {
            throw new InvalidRideException();
        }

        this.driver = rider.getNthNearestDriver(driverNum);
        this.rider = rider;
        this.rideStatus = RideStatus.STARTED;
        printRideStatus();
    }

    public void stopRide(Location destination, Integer timeTakenInMin) throws InvalidRideException {
        if(!alreadyExists() || alreadyStopped()) {
            throw new InvalidRideException();
        }

        this.destination = destination;
        this.timeTakenInMin = timeTakenInMin;
        this.rideStatus = RideStatus.COMPLETED;
        printRideStatus();
    }

    private boolean alreadyStopped() {
        return this.rideStatus == RideStatus.COMPLETED;
    }

    private boolean alreadyExists() {
        return rideStatus != RideStatus.CREATED;
    }

    private void printRideStatus() {
        if(rideStatus == RideStatus.STARTED)
            System.out.println("RIDE_STARTED " + this.id);
        if(rideStatus == RideStatus.COMPLETED)
            System.out.println("RIDE_STOPPED " + this.id);
    }
}
