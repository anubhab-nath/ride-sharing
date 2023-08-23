package com.example.geektrust.entities;

import com.example.geektrust.constants.RiderKeywords;
import com.example.geektrust.registers.DriverRegistry;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Rider {
    private final String id;
    private final Location location;
    private final ArrayList<Driver> nearestDrivers = new ArrayList<>();

    public Rider(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public void findNearestDrivers(DriverRegistry driverRegistry) {
        PriorityQueue<DriverDistancePair> distanceMap =
                new PriorityQueue<>((d1, d2) -> {
                    if(d1.distance == d2.distance) {
                        return d1.driver.compareTo(d2.driver);
                    }
                    return ceil(d1.distance - d2.distance);
                });

        for(Driver driver: driverRegistry.getAllDrivers()) {
            double distance = calculateDistance(this.location, driver.getLocation());
            if(distance > RiderKeywords.RIDER_RADIUS)
                continue;

            distanceMap.add(new DriverDistancePair(driver, distance));
        }

        for(int i = 0; i < RiderKeywords.MAX_NEAREST_DRIVERS; i++) {
            if(!distanceMap.isEmpty()) {
                nearestDrivers.add(distanceMap.poll().driver);
            }
        }
    }

    public void printNearestDrivers() {
        if(nearestDrivers.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return;
        }
        System.out.print("DRIVERS_MATCHED ");

        System.out.println(nearestDrivers.stream()
                .map(Driver::getId)
                .collect(Collectors.joining(" "))
        );
    }

    private double calculateDistance(Location riderPos, Location driverPos) {
        double xDiff = (double) riderPos.x - driverPos.x;
        double yDiff = (double) riderPos.y - driverPos.y;
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }

    private int ceil(double decimal) {
        return (int) decimal + 1;
    }

    public void startRide(Ride ride, Integer driverNum) {
        if(isDriverNotAvailable(driverNum) || ride.alreadyExists()) {
            System.out.println("INVALID_RIDE");
            return;
        }

        Driver driver = getNthNearestDriver(driverNum);
        ride.start(this, driver);
        ride.printRideId();
    }

    private boolean isDriverNotAvailable(Integer driverNum) {
        return nearestDrivers.size() >= driverNum;
    }

    private Driver getNthNearestDriver(int driverNum) {
        return nearestDrivers.get(driverNum - 1);
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
