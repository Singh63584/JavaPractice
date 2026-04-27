package org.karat.HealthcareManagement;

public class Appointment {
    /** Data about a scheduled appointment. */
    Patient patient;
    String doctorName;
    String appointmentDate;     // Format: "YYYY-MM-DD"
    String appointmentTime;     // Format: "HH:MM"
    String appointmentType;     // "Checkup", "Follow-up", "Emergency", "Consultation"
    int durationMinutes;

    Appointment(Patient patient, String doctorName, String appointmentDate,
                String appointmentTime, String appointmentType, int durationMinutes) {
        this.patient = patient;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentType = appointmentType;
        this.durationMinutes = durationMinutes;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
