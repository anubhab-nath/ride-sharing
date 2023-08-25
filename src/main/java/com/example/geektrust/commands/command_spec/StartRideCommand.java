package com.example.geektrust.commands.command_spec;

import com.example.geektrust.commands.Command;
import com.example.geektrust.entities.Ride;
import com.example.geektrust.entities.Rider;
import com.example.geektrust.exceptions.InvalidRideException;
import com.example.geektrust.registers.RideRegistry;
import com.example.geektrust.registers.RiderRegistry;

public class StartRideCommand implements Command {
    private final RiderRegistry riderRegistry;
    private final RideRegistry rideRegistry;
    private Ride ride;
    private Integer driverNum;
    private Rider rider;

    public StartRideCommand(RiderRegistry riderRegistry, RideRegistry rideRegistry) {
        this.riderRegistry = riderRegistry;
        this.rideRegistry = rideRegistry;
    }

    @Override
    public void setParams(String[] params) {
        this.ride = rideRegistry.getRide(params[1]);
        this.driverNum = Integer.parseInt(params[2]);
        this.rider = riderRegistry.getRider(params[3]);
    }

    @Override
    public void execute() {
        try {
            ride.startRide(rider, driverNum);
        } catch (InvalidRideException e) {
            System.out.println(e.getMessage());
        }
    }
}
