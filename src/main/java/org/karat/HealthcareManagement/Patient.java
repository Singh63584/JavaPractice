package org.karat.HealthcareManagement;
import java.util.Objects;

public class Patient {
    /** Data about a patient. */
    String patientId;
    String name;
    int age;
    String bloodType;

    Patient(String patientId, String name, int age, String bloodType) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Patient patient = (Patient) other;
        return Objects.equals(patientId, patient.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }
}
