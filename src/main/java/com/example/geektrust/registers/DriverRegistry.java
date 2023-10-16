package com.example.geektrust.registers;

import com.example.geektrust.constants.RiderConstants;
import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Location;
import com.example.geektrust.entities.Rider;
import com.example.geektrust.services.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DriverRegistry {
    private final ArrayList<Driver> drivers = new ArrayList<>();

    public void addDriver(String driverId, Location location) {
        drivers.add(new Driver(driverId, location));
    }

    public List<Driver> getNearestDrivers(Rider rider) {
        PriorityQueue<DriverDistancePair> nearestDriverHeap =
                new PriorityQueue<>((d1, d2) -> {
                    if(d1.distance.equals(d2.distance)) {
                        return d1.driver.compareTo(d2.driver);
                    }
                    return d1.distance.compareTo(d2.distance);
                });

        for(Driver driver: drivers) {
            if(driver.isDriverOccupied())
                continue;

            double distance = Utility.calculateDistance(rider.getLocation(), driver.getLocation());
            if(distance > RiderConstants.RIDER_RADIUS)
                continue;

            nearestDriverHeap.add(new DriverDistancePair(driver, distance));
        }

        return extractMaxNearestDrivers(nearestDriverHeap);
    }

    private List<Driver> extractMaxNearestDrivers(PriorityQueue<DriverDistancePair> nearestDriverHeap) {
        ArrayList<Driver> matchedDrivers = new ArrayList<>();
        for (int i = 0; i < RiderConstants.MAX_NEAREST_DRIVERS && !nearestDriverHeap.isEmpty(); i++) {
            matchedDrivers.add(nearestDriverHeap.poll().driver);
        }
        return matchedDrivers;
    }


    static class DriverDistancePair {
        Driver driver;
        Double distance;

        DriverDistancePair(Driver driver, Double distance) {
            this.driver = driver;
            this.distance = distance;
        }
    }
}
