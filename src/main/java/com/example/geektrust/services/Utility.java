package com.example.geektrust.services;

import com.example.geektrust.entities.Location;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utility {

    private Utility() {}

    public static double calculateDistance(Location riderPos, Location driverPos) {
        double xDiff = (double) riderPos.getX() - driverPos.getX();
        double yDiff = (double) riderPos.getY() - driverPos.getY();
        return roundOff(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
    }

    public static int ceil(double decimal) {
        return (int) decimal + 1;
    }

    public static double roundOff(double value) {
        BigDecimal decimal = BigDecimal.valueOf(value);
        return decimal.setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
