package org.karat.ObstacleCourse;

import java.util.ArrayList;
import java.util.List;

public class RunCollection {
    List<Run> runs = new ArrayList<>();

    void addRun(Run run) {
        runs.add(run);
    }

    int getTotalRuns() {
        return runs.size();
    }

    double getAverageRunTime() {
        /**
         * BUG: Fix this method
         */
        int total = 0;
        for (Run r : runs) {
            total += r.getTotalTime();
        }
        return (double) total / getTotalRuns();
    }

    int bestOfBests() {
        // Write your code here
        List<Integer> best = new ArrayList<>();

        for (Run r : runs) {
            List<Integer> times = r.getTimes();
            for (int i = 0; i < times.size(); i++) {
                if (i == best.size())
                    best.add(times.get(i));
                else
                    best.set(i, Math.min(best.get(i), times.get(i)));
            }
        }

        int sum = 0;
        for (int t : best) sum += t;
        return sum;

    }
}
