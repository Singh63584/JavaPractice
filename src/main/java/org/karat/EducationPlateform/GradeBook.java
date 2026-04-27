package org.karat.EducationPlateform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GradeBook {

    ArrayList<Assignment> assignments = new ArrayList<>();
    String className;

    GradeBook(String className) {
        this.className = className;
    }

    void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    int getTotalAssignments() {
        return assignments.size();
    }

    // ✅ BUG FIXED
    double getAverageScore() {
        if (assignments.isEmpty()) return 0.0;
        double total = assignments.stream().mapToDouble(a -> a.score).sum();
        return total / assignments.size();
    }

    double getStudentAverage(String studentId) {
        List<Assignment> studentAssignments = assignments.stream().filter(a -> a.student.studentId.equals(studentId)).toList();

        if (studentAssignments.isEmpty()) return 0.0;

        double total = studentAssignments.stream().mapToDouble(a -> a.score).sum();
        return total / studentAssignments.size();
    }

    int getAssignmentsBySubject(String subject) {
        return (int) assignments.stream().filter(a -> a.subject.equals(subject)).count();
    }

    // ✅ NEW FEATURE
    public Object[] getImprovementTrend(String studentId) {
        List<Assignment> studentAssignments = assignments.stream().filter(a -> a.student.studentId.equals(studentId)).sorted(Comparator.comparing(a -> a.submissionDate)).toList();

        int n = studentAssignments.size();
        if (n < 2) {
            double avg = n == 0 ? 0.0 : studentAssignments.get(0).score;
            return new Object[]{"Stable", avg, avg};
        }

        int mid = n / 2;
        double firstAvg = studentAssignments.subList(0, mid).stream().mapToDouble(a -> a.score).average().orElse(0.0);
        double secondAvg = studentAssignments.subList(mid, n).stream().mapToDouble(a -> a.score).average().orElse(0.0);
        double change = (secondAvg - firstAvg) / firstAvg;

        String trend;
        if (change >= 0.05) {
            trend = "Improving";
        } else if (change <= -0.05) {
            trend = "Declining";
        } else {
            trend = "Stable";
        }
        return new Object[]{trend, firstAvg, secondAvg};
    }
}
