package com.example.geektrust.registers;

import com.example.geektrust.entities.Ride;

import java.util.HashMap;
import java.util.Map;

public class RideRegistry {
    private final Map<String, Ride> ridesTaken = new HashMap<>();

    public Ride getRide(String rideId) {
        Ride ride = ridesTaken.get(rideId);
        if(ride == null) {
            Ride newRide = new Ride(rideId);
            ridesTaken.put(rideId, newRide);
            return newRide;
        }
        return ride;
    }
}
