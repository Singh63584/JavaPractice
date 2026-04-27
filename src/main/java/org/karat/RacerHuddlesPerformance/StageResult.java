package org.karat.RacerHuddlesPerformance;

public class StageResult {
    Athlete athlete;
    int     stageNumber;    // 1, 2, 3, or 4
    double  timeSeconds;    // time taken to clear this stage, in seconds

    StageResult(Athlete athlete, int stageNumber, double timeSeconds) {
        this.athlete     = athlete;
        this.stageNumber = stageNumber;
        this.timeSeconds = timeSeconds;
    }
}
