package com.example.geektrust.registers;

import com.example.geektrust.entities.Location;
import com.example.geektrust.entities.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderRegistry {
    private final Map<String, Rider> riders = new HashMap<>();

    public void addRider(String riderId, Location location) {
        Rider rider = new Rider(riderId, location);
        riders.put(riderId, rider);
    }

    public Rider getRider(String riderId) {
        return riders.get(riderId);
    }
}
