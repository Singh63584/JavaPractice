package org.karat.ParkingLotProb;

import java.util.Objects;

public class Vehicle {
    String licensePlate;
    String vehicleType;  // "Car", "Motorcycle", "Truck", "SUV"
    String color;

    Vehicle(String licensePlate, String vehicleType, String color) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Vehicle vehicle = (Vehicle) other;
        return licensePlate.equals(vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }
}
