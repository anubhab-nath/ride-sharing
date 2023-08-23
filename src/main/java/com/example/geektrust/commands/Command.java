package com.example.geektrust.commands;

public interface Command {
    void setParams(String[] params);
    void execute();
}
