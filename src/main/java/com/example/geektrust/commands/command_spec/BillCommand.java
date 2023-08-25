package com.example.geektrust.commands.command_spec;

import com.example.geektrust.commands.Command;
import com.example.geektrust.entities.Ride;
import com.example.geektrust.exceptions.InvalidCommand;
import com.example.geektrust.exceptions.InvalidRideException;
import com.example.geektrust.registers.RideRegistry;

public class BillCommand implements Command {
    private final RideRegistry rideRegistry;
    private Ride ride;

    public BillCommand(RideRegistry rideRegistry) {
        this.rideRegistry = rideRegistry;
    }

    @Override
    public void setParams(String[] params) {
        this.ride = rideRegistry.getRide(params[1]);
    }

    @Override
    public void execute() {
        try {
            ride.calculateBill();
        } catch (InvalidRideException | InvalidCommand e) {
            System.out.println(e.getMessage());
        }
    }
}
