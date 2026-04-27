package org.karat.EducationPlateform;

public class Assignment {

    Student student;
    String assignmentName;
    double score;
    String submissionDate;
    String subject;

    Assignment(Student student, String assignmentName, double score, String submissionDate, String subject) {
        this.student = student;
        this.assignmentName = assignmentName;
        this.score = score;
        this.submissionDate = submissionDate;
        this.subject = subject;
    }
}
