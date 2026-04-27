package org.karat.ParkingLotProb;

import java.util.*;

public class ParkingLot {
    ArrayList<ParkingSession> sessions = new ArrayList<>();
    String lotName;

    ParkingLot(String lotName) {
        this.lotName = lotName;
    }

    void addSession(ParkingSession session) {
        sessions.add(session);
    }

    int getTotalSessions() {
        return sessions.size();
    }

    int getCurrentlyParkedCount() {
        return (int) sessions.stream().filter(session -> session.exitTime == null).count();
    }

    double getTotalRevenue() {
        return sessions.stream().mapToDouble(session -> session.getFee()).sum();
    }

    double getAverageParkingDuration() {
        double totalMinutes = sessions.stream().filter(session -> session.exitTime != null).mapToInt(session -> session.getDurationMinutes()).sum();
        long completedSessions = sessions.stream().filter(session -> session.exitTime != null).count();
        return totalMinutes / completedSessions;
    }

    public Object[] getLongestParkingSession() {
        // Write your code here to solve this problem
        //return sessions.stream().filter(s -> s.exitTime != null).max(Comparator.comparingInt(ParkingSession::getDurationMinutes)
        // ).map(s -> new Object[]{s.vehicle.licensePlate, s.getDurationMinutes()}).orElse(null);

        ParkingSession longest = null;
        int maxDuration = 0;

        for (ParkingSession s : sessions) {
            if (s.exitTime == null) continue;

            int duration = s.getDurationMinutes();
            if (duration > maxDuration) {
                longest = s;
                maxDuration = duration;
            }
        }

        if (longest == null) return null;
        return new Object[]{longest.vehicle.licensePlate, maxDuration};
    }
}
