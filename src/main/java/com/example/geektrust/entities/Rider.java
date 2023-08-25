package com.example.geektrust.entities;

import com.example.geektrust.registers.DriverRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class Rider {
    private final String id;
    private final Location location;
    private List<Driver> matchedDrivers;

    public Rider(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void findNearestDrivers(DriverRegistry driverRegistry) {
        matchedDrivers = driverRegistry.getNearestDrivers(this);
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
}
