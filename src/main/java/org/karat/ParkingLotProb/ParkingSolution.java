package org.karat.ParkingLotProb;

import org.junit.Assert;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class ParkingSolution {
    public static void main(String[] args) {
        testParkingSession();
        testParkingLot();
        testGetLongestParkingSession();
    }

    public static void testParkingSession() {
        System.out.println("Running testParkingSession :-->");
        Vehicle vehicle = new Vehicle("ABC123", "Car", "Blue");
        ParkingSession session = new ParkingSession(vehicle, LocalTime.parse("09:00"), LocalTime.parse("11:30"), LocalDate.parse("2024-02-01"), 5.0);
        Assert.assertEquals(150, session.getDurationMinutes());
        Assert.assertEquals(12.5, session.getFee(), 0.01);

        System.out.println("Duration Minutes: " + session.getDurationMinutes());
        System.out.println("Parking Fee: " + session.getFee());

    }

    public static void testParkingLot() {
        System.out.println("Running testParkingLot :-->");
        ParkingLot lot = new ParkingLot("Main Lot");

        Assert.assertEquals(0, lot.getTotalSessions());
        Assert.assertEquals(0, lot.getCurrentlyParkedCount());

        Vehicle v1 = new Vehicle("ABC123", "Car", "Blue");
        Vehicle v2 = new Vehicle("XYZ789", "SUV", "Red");
        Vehicle v3 = new Vehicle("DEF456", "Motorcycle", "Black");

        ParkingSession s1 = new ParkingSession(v1, LocalTime.parse("09:00"), LocalTime.parse("11:00"), LocalDate.parse("2024-02-01"), 5.0);
        ParkingSession s2 = new ParkingSession(v2, LocalTime.parse("10:00"), LocalTime.parse("12:30"), LocalDate.parse("2024-02-01"), 5.0);
        ParkingSession s3 = new ParkingSession(v3, LocalTime.parse("14:00"), null, LocalDate.parse("2024-02-01"), 3.0);

        lot.addSession(s1);
        lot.addSession(s2);
        lot.addSession(s3);

        Assert.assertEquals(3, lot.getTotalSessions());
        Assert.assertEquals(1, lot.getCurrentlyParkedCount());
        Assert.assertEquals(22.5, lot.getTotalRevenue(), 0.01);
        Assert.assertEquals(135.0, lot.getAverageParkingDuration(), 0.01);
        System.out.println("Total Sessions: " + lot.getTotalSessions());
        System.out.println("Currently Parked Count: " + lot.getCurrentlyParkedCount());
        System.out.println("Total Revenue: " + lot.getTotalRevenue());
        System.out.println("Average Parking Duration: " + lot.getAverageParkingDuration());
    }

    public static void testGetLongestParkingSession() {
        System.out.println("Running testGetLongestParkingSession :-->");
        ParkingLot lot = new ParkingLot("Main Lot");

        Assert.assertNull(lot.getLongestParkingSession());

        Vehicle v1 = new Vehicle("ABC123", "Car", "Blue");
        Vehicle v2 = new Vehicle("XYZ789", "SUV", "Red");
        Vehicle v3 = new Vehicle("DEF456", "Motorcycle", "Black");

        ParkingSession s1 = new ParkingSession(v1, LocalTime.parse("09:00"), LocalTime.parse("11:00"), LocalDate.parse("2024-02-01"), 5.0);
        ParkingSession s2 = new ParkingSession(v2, LocalTime.parse("10:00"), LocalTime.parse("12:30"), LocalDate.parse("2024-02-01"), 5.0);
        ParkingSession s3 = new ParkingSession(v3, LocalTime.parse("14:00"), null, LocalDate.parse("2024-02-01"), 3.0);

        lot.addSession(s1);
        lot.addSession(s2);
        lot.addSession(s3);

        Assert.assertArrayEquals(new Object[]{"XYZ789", 150}, lot.getLongestParkingSession());
        System.out.println("Longest Parking Time :--> " + Arrays.toString(lot.getLongestParkingSession()));
    }
}
