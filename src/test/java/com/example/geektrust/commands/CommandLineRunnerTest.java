package com.example.geektrust.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandLineRunnerTest {
    CommandLineRunner runner = new CommandLineRunner();

    @Test
    void testRun_WhenInvalidFile_ThrowsException() {
        String file = "file";
        assertThrows(IllegalArgumentException.class, () -> runner.run(file), "Provide an invalid file");
    }

    @Test
    void testRun_WhenValidFile() {
        String file = "sample_input/input3.txt";
        assertDoesNotThrow(() -> runner.run(file), "Provide a valid file");
    }

    void testIf_AddDriverCommand_UpdatesDriverRegistry() {

    }
}
