package com.example.geektrust.entities;

import com.example.geektrust.constants.RideConstants;
import com.example.geektrust.exceptions.InvalidCommand;
import com.example.geektrust.exceptions.InvalidRideException;
import com.example.geektrust.services.Utility;

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

        this.rider = rider;
        this.driver = rider.getNthNearestDriver(driverNum);
        driver.startRide();
        this.rideStatus = RideStatus.STARTED;

        System.out.println("RIDE_STARTED " + this.id);
    }

    public void stopRide(Location destination, Integer timeTakenInMin) throws InvalidRideException {
        if(!alreadyExists() || alreadyStopped()) {
            throw new InvalidRideException();
        }

        driver.stopRide();
        this.destination = destination;
        this.timeTakenInMin = timeTakenInMin;
        this.rideStatus = RideStatus.COMPLETED;

        System.out.println("RIDE_STOPPED " + this.id);
    }

    private boolean alreadyStopped() {
        return this.rideStatus == RideStatus.COMPLETED;
    }

    private boolean alreadyExists() {
        return rideStatus != RideStatus.CREATED;
    }

    public void calculateBill() throws InvalidRideException, InvalidCommand {
        if(rideStatus == RideStatus.CREATED)
            throw new InvalidRideException();
        else if(rideStatus != RideStatus.COMPLETED)
            throw new InvalidCommand();

        double distanceCovered = Utility.calculateDistance(rider.getLocation(), destination);
        double amountWithoutTax = RideConstants.BASE_FARE
                + Utility.roundOff(distanceCovered * RideConstants.CHARGE_PER_KM)
                + timeTakenInMin * RideConstants.CHARGE_PER_MIN;
        double totalAmount = Utility.roundOff((1 + RideConstants.SERVICE_TAX) * amountWithoutTax);
        System.out.printf("BILL %s %s %.2f%n", id, driver.getId(), totalAmount);
    }
}
