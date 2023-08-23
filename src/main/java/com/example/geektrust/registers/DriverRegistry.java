package com.example.geektrust.registers;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRegistry {
    private final ArrayList<Driver> drivers = new ArrayList<>();

    public void addDriver(String driverId, Location location) {
        drivers.add(new Driver(driverId, location));
    }

    public List<Driver> getAllDrivers() {
        return drivers;
    }
}
