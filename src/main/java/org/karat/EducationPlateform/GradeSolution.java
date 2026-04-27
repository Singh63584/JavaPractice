package org.karat.EducationPlateform;

import org.junit.Assert;

public class GradeSolution {
    public static void main(String[] args) {
        testAssignment();
        testGradeBook();
        testGetImprovementTrend();
    }

    public static void testAssignment() {
        Student student = new Student("S001", "Alice", "Junior");
        Assignment assignment = new Assignment(student, "Homework 1", 85.0, "2024-02-01", "Math");
        //System.out.println("Assignment : " +  assignment);
    }

    public static void testGradeBook() {
        GradeBook gradeBook = new GradeBook("Math 101");

        Student s1 = new Student("S001", "Alice", "Junior");
        Student s2 = new Student("S002", "Bob", "Senior");

        gradeBook.addAssignment(new Assignment(s1, "HW1", 85.0, "2024-02-01", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "HW2", 90.0, "2024-02-05", "Math"));
        gradeBook.addAssignment(new Assignment(s2, "HW1", 75.0, "2024-02-01", "Math"));
    }

    public static void testGetImprovementTrend() {
        GradeBook gradeBook = new GradeBook("Math 101");
        Student s1 = new Student("S001", "Alice", "Junior");

        gradeBook.addAssignment(new Assignment(s1, "HW1", 70, "2024-02-01", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "HW2", 75, "2024-02-05", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "HW3", 72, "2024-02-10", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "HW4", 80, "2024-02-15", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "HW5", 85, "2024-02-20", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "HW6", 82, "2024-02-25", "Math"));

        Object[] result = gradeBook.getImprovementTrend("S001");
        System.out.println("Result Test: " + result[0]);
        Assert.assertEquals("Improving", result[0]);
        Assert.assertEquals(72.33, (Double) result[1], 0.1);
        Assert.assertEquals(82.33, (Double) result[2], 0.1);
    }

}
