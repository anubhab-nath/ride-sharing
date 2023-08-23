package com.example.geektrust.commands.command_spec;

import com.example.geektrust.commands.Command;
import com.example.geektrust.entities.Rider;
import com.example.geektrust.registers.DriverRegistry;
import com.example.geektrust.registers.RiderRegistry;

public class MatchCommand implements Command {
    private Rider rider;
    private final RiderRegistry riderRegistry;
    private final DriverRegistry driverRegistry;

    public MatchCommand(DriverRegistry driverRegistry, RiderRegistry riderRegistry) {
        this.driverRegistry = driverRegistry;
        this.riderRegistry = riderRegistry;
    }

    @Override
    public void setParams(String[] params) {
        this.rider = riderRegistry.getRider(params[1]);
    }

    @Override
    public void execute() {
        rider.findNearestDrivers(driverRegistry);
        rider.printNearestDrivers();
    }
}
