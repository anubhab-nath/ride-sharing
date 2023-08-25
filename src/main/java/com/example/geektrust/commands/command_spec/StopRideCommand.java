package com.example.geektrust.commands.command_spec;

import com.example.geektrust.commands.Command;
import com.example.geektrust.entities.Location;
import com.example.geektrust.entities.Ride;
import com.example.geektrust.exceptions.InvalidRideException;
import com.example.geektrust.registers.RideRegistry;

public class StopRideCommand implements Command {
    private final RideRegistry rideRegistry;
    private Ride ride;
    private Location destination;
    private Integer timeTakenInMin;

    public StopRideCommand(RideRegistry rideRegistry) {
        this.rideRegistry = rideRegistry;
    }

    @Override
    public void setParams(String[] params) {
        this.ride = rideRegistry.getRide(params[1]);
        int xPos = Integer.parseInt(params[2]);
        int yPos = Integer.parseInt(params[3]);
        this.destination = new Location(xPos, yPos);
        this.timeTakenInMin = Integer.parseInt(params[4]);
    }

    @Override
    public void execute() {
        try {
            ride.stopRide(destination, timeTakenInMin);
        } catch (InvalidRideException e) {
            System.out.println(e.getMessage());
        }
    }
}
