package com.example.geektrust.registers;

import com.example.geektrust.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commandMap = new HashMap<>();

    public void setCommand(String cmdString, Command command) {
        commandMap.put(cmdString, command);
    }

    public Command getCommand(String cmd) {
        Command command = commandMap.get(cmd);
        if(command == null)
            throw new IllegalArgumentException("Invalid command request...!");
        return command;
    }
}
