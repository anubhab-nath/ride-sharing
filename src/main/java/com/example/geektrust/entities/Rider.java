package com.example.geektrust.entities;

import com.example.geektrust.constants.RiderConstants;
import com.example.geektrust.registers.DriverRegistry;
import com.example.geektrust.services.Utility;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Rider {
    private final String id;
    private final Location location;
    private final ArrayList<Driver> matchedDrivers = new ArrayList<>();

    public Rider(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void findNearestDrivers(DriverRegistry driverRegistry) {
        PriorityQueue<DriverDistancePair> nearestDriverMap =
                new PriorityQueue<>((d1, d2) -> {
                    if(d1.distance == d2.distance) {
                        return d1.driver.compareTo(d2.driver);
                    }
                    return Utility.ceil(d1.distance - d2.distance);
                });

        for(Driver driver: driverRegistry.getAllDrivers()) {
            double distance = Utility.calculateDistance(this.location, driver.getLocation());
            if(distance > RiderConstants.RIDER_RADIUS)
                continue;

            nearestDriverMap.add(new DriverDistancePair(driver, distance));
        }

        matchedDrivers.clear();
        for (int i = 0; i < RiderConstants.MAX_NEAREST_DRIVERS && !nearestDriverMap.isEmpty(); i++) {
            matchedDrivers.add(nearestDriverMap.poll().driver);
        }

        printNearestDrivers();
    }

    private void printNearestDrivers() {
        if(matchedDrivers.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return;
        }
        System.out.print("DRIVERS_MATCHED ");

        System.out.println(matchedDrivers.stream()
                .map(Driver::getId)
                .collect(Collectors.joining(" "))
        );
    }

    public boolean isDriverNotAvailable(Integer driverNum) {
        return matchedDrivers.size() < driverNum;
    }

    public Driver getNthNearestDriver(int driverNum) {
        return matchedDrivers.get(driverNum - 1);
    }

    static class DriverDistancePair {
        Driver driver;
        double distance;

        DriverDistancePair(Driver driver, double distance) {
            this.driver = driver;
            this.distance = distance;
        }
    }
}
