package org.karat.HealthcareManagement;

import java.util.*;

public class ClinicScheduler {
    /**
     * Manages appointments and provides scheduling analytics.
     */
    ArrayList<Appointment> appointments = new ArrayList<>();
    String clinicName;

    ClinicScheduler(String clinicName) {
        this.clinicName = clinicName;
    }

    void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Object[] findBusiestDoctor() {
        /**
         * Returns [doctorName, totalAppointments] for the doctor with the most appointments.
         * If there are no appointments, returns null.
         * Tie-breaker: lexicographically smaller doctor name (deterministic).
         */
        if (appointments.isEmpty()) return null;

        Map<String, Integer> counts = new HashMap<>();
        for (Appointment a : appointments) {
            
            counts.merge(a.doctorName, 1, Integer::sum);

        }

        //Map<String, Integer> counts1 = appointments.stream().collect(Collectors.groupingBy(Appointment::getDoctorName, Collectors.summingInt(e->e.)));

        Map.Entry<String, Integer> best = counts.entrySet().stream()
                .max(Comparator.<Map.Entry<String, Integer>, Integer>comparing(Map.Entry::getValue)
                        .thenComparing(Map.Entry::getKey))
                .orElse(null);

        if (best == null) return null;
        return new Object[] { best.getKey(), best.getValue() };
    }
    public static void main(String[] args) {
        testFindBusiestDoctor();
    }

    public static void testFindBusiestDoctor() {
        ClinicScheduler scheduler = new ClinicScheduler("City Health Clinic");

        Patient p1 = new Patient("P001", "Patient1", 30, "A+");
        Patient p2 = new Patient("P002", "Patient2", 45, "B+");

        Appointment a1 = new Appointment(p1, "Dr. Smith", "2024-02-21", "10:00", "Checkup", 30);
        Appointment a2 = new Appointment(p1, "Dr. Johnson", "2024-02-22", "11:00", "Checkup", 30);
        Appointment a3 = new Appointment(p2, "Dr. Johnson", "2024-02-18", "12:00", "Checkup", 30);
        Appointment a4 = new Appointment(p2, "Dr. Johnson", "2024-02-16", "09:00", "Checkup", 30);
        Appointment a5 = new Appointment(p1, "Dr. Smith", "2024-02-16", "10:00", "Checkup", 30);
        Appointment a6 = new Appointment(p1, "Dr. Smith", "2024-02-17", "10:00", "Checkup", 30);
        Appointment a7 = new Appointment(p2, "Dr. Johnson", "2024-02-123", "09:00", "Checkup", 30);
        Appointment a8 = new Appointment(p1, "Dr. Smith", "2024-02-16", "10:00", "Checkup", 30);
        Appointment a9 = new Appointment(p2, "Dr. Johnson", "2024-02-16", "09:00", "Checkup", 30);

        scheduler.addAppointment(a1);
        scheduler.addAppointment(a2);
        scheduler.addAppointment(a3);
        scheduler.addAppointment(a4);
        scheduler.addAppointment(a5);
        scheduler.addAppointment(a6);
        scheduler.addAppointment(a7);
        scheduler.addAppointment(a8);
        scheduler.addAppointment(a9);

        System.out.println(Arrays.toString(scheduler.findBusiestDoctor()));
    }

}
