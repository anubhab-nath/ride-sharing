package com.example.geektrust;

import com.example.geektrust.commands.CommandLineRunner;

public class App {
    static CommandLineRunner runner = new CommandLineRunner();
    public static void main(String[] args) {
        runner.run(args[0]);
    }
}
