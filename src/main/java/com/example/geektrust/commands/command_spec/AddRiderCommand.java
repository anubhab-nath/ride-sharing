package com.example.geektrust.commands.command_spec;

import com.example.geektrust.commands.Command;
import com.example.geektrust.entities.Location;
import com.example.geektrust.registers.RiderRegistry;

public class AddRiderCommand implements Command {
    private String riderId;
    private Location location;
    private final RiderRegistry riderRegistry;

    public AddRiderCommand(RiderRegistry riderRegistry) {
        this.riderRegistry = riderRegistry;
    }

    @Override
    public void setParams(String[] params) {
        this.riderId = params[1];
        int xPos = Integer.parseInt(params[2]);
        int yPos = Integer.parseInt(params[3]);
        this.location = new Location(xPos, yPos);
    }

    @Override
    public void execute() {
        riderRegistry.addRider(riderId, location);
    }
}
