package com.example.geektrust.commands.command_spec;

import com.example.geektrust.commands.Command;
import com.example.geektrust.entities.Location;
import com.example.geektrust.registers.DriverRegistry;

public class AddDriverCommand implements Command {
    private String driverId;
    private Location location;
    private final DriverRegistry driverRegistry;

    public AddDriverCommand(DriverRegistry driverRegistry) {
        this.driverRegistry = driverRegistry;
    }

    @Override
    public void setParams(String[] params) {
        this.driverId = params[1];
        int xPos = Integer.parseInt(params[2]);
        int yPos = Integer.parseInt(params[3]);
        this.location = new Location(xPos, yPos);
    }

    @Override
    public void execute() {
        driverRegistry.addDriver(driverId, location);
    }
}
