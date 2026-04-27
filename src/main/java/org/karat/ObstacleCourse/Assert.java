package org.karat.ObstacleCourse;

public class Assert {
    static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new RuntimeException("Expected: " + expected + ", Got: " + actual);
        }
    }

    static void assertEquals(double expected, double actual, double delta) {
        if (Math.abs(expected - actual) > delta) {
            throw new RuntimeException("Expected: " + expected + ", Got: " + actual);
        }
    }
}
