package org.karat.RacerHuddlesPerformance;

import java.util.Objects;

public class Athlete {
    String athleteId;
    String name;

    Athlete(String athleteId, String name) {
        this.athleteId = athleteId;
        this.name      = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Athlete a = (Athlete) other;
        return athleteId.equals(a.athleteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(athleteId);
    }
}
