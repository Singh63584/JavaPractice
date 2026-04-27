package org.karat.ObstacleCourse;

import java.util.List;

public class Run {
    List<Integer> obstacleTimes;

    Run(List<Integer> times) {
        this.obstacleTimes = times;
    }

    List<Integer> getTimes() {
        return obstacleTimes;
    }

    int getTotalTime() {
        int sum = 0;
        for (int t : obstacleTimes) {
            sum += t;
        }
        return sum;
    }
}
