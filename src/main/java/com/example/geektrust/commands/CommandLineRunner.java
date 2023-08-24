package com.example.geektrust.commands;

import com.example.geektrust.commands.command_spec.AddDriverCommand;
import com.example.geektrust.commands.command_spec.AddRiderCommand;
import com.example.geektrust.commands.command_spec.MatchCommand;
import com.example.geektrust.commands.command_spec.StartRideCommand;
import com.example.geektrust.commands.command_spec.StopRideCommand;
import com.example.geektrust.constants.CommandKeywords;
import com.example.geektrust.registers.CommandRegistry;
import com.example.geektrust.registers.DriverRegistry;
import com.example.geektrust.registers.RideRegistry;
import com.example.geektrust.registers.RiderRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CommandLineRunner {
    private final CommandRegistry commandRegistry = new CommandRegistry();

    public CommandLineRunner() {
        init();
    }

    private void init() {
        DriverRegistry driverRegistry = new DriverRegistry();
        RiderRegistry riderRegistry = new RiderRegistry();
        RideRegistry rideRegistry = new RideRegistry();

        AddDriverCommand addDriverCommand = new AddDriverCommand(driverRegistry);
        AddRiderCommand addRiderCommand = new AddRiderCommand(riderRegistry);
        MatchCommand matchCommand = new MatchCommand(driverRegistry, riderRegistry);
        StartRideCommand startRideCommand = new StartRideCommand(riderRegistry, rideRegistry);
        StopRideCommand stopRideCommand = new StopRideCommand(rideRegistry);

        commandRegistry.setCommand(CommandKeywords.ADD_DRIVER, addDriverCommand);
        commandRegistry.setCommand(CommandKeywords.ADD_RIDER, addRiderCommand);
        commandRegistry.setCommand(CommandKeywords.MATCH, matchCommand);
        commandRegistry.setCommand(CommandKeywords.START_RIDE, startRideCommand);
        commandRegistry.setCommand(CommandKeywords.STOP_RIDE, stopRideCommand);
    }

    public void run(String file) {
        try(FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis)
        ) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                Command command = commandRegistry.getCommand(line[0]);
                command.setParams(line);
                command.execute();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
