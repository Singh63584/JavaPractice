package org.karat.ParkingLotProb;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ParkingSession {
    Vehicle vehicle;
    LocalTime entryTime;    // Format: "HH:MM"
    LocalTime exitTime;     // Format: "HH:MM", null if still parked
    LocalDate date;         // Format: "YYYY-MM-DD"
    double hourlyRate;   // Parking fee per hour


    ParkingSession(Vehicle vehicle, LocalTime entryTime, LocalTime exitTime, LocalDate date, double hourlyRate) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.date = date;
        this.hourlyRate = hourlyRate;
    }

    int getDurationMinutes() {
        if (exitTime == null) return -1;
        return (int) Duration.between(entryTime, exitTime).toMinutes();
    }

    double getFee() {
        /** Returns parking fee. 0 if still parked */
        int duration = getDurationMinutes();
        if (duration < 0) return 0.0;

        return (duration / 60.0) * hourlyRate;
    }

}
