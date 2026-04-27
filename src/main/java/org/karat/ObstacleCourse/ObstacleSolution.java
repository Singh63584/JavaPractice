package org.karat.ObstacleCourse;

import java.util.Arrays;

public class ObstacleSolution {
    public static void main(String[] args) {
        testRun();
        testRunCollection();
        testBestOfBests();
        System.out.println("All tests passed ✅");
    }

    public static void testRun() {
        Run run = new Run(Arrays.asList(3, 4, 5, 6));
        Assert.assertEquals(18, run.getTotalTime());
        System.out.println("Running testRun => " + run.getTotalTime());
    }

    public static void testRunCollection() {
        RunCollection rc = new RunCollection();

        rc.addRun(new Run(Arrays.asList(3, 4, 5, 6))); // 18
        rc.addRun(new Run(Arrays.asList(4, 4, 4, 5))); // 17

        Assert.assertEquals(2, rc.getTotalRuns());
        Assert.assertEquals(17.5, rc.getAverageRunTime(), 0.01);
        System.out.println("Get total run : => " + rc.getTotalRuns());
        System.out.println("Average Best Obstacle minimal time : => " + rc.getAverageRunTime());
    }

    public static void testBestOfBests() {
        RunCollection rc = new RunCollection();

        rc.addRun(new Run(Arrays.asList(3, 4, 5, 6)));
        rc.addRun(new Run(Arrays.asList(4, 4, 4, 5)));
        rc.addRun(new Run(Arrays.asList(4, 5, 4, 6)));
        rc.addRun(new Run(Arrays.asList(5, 5, 3))); // incomplete

        Assert.assertEquals(15, rc.bestOfBests());
        System.out.println("Best Of Bests Obstacle minimal time : => " + rc.bestOfBests());
    }
}
