package org.karat.RacerHuddlesPerformance;

import java.util.*;

public class HurdlesRace {
    /**
     * Manages all stage results for a hurdles race and provides analytics.
     * The race has exactly TOTAL_STAGES stages.
     */
    static final int TOTAL_STAGES = 4;

    ArrayList<StageResult> stageResults = new ArrayList<>();

    HurdlesRace() {
    }

    void addStageResult(StageResult stageResult) {
        stageResults.add(stageResult);
    }

    int getTotalStageResults() {
        return stageResults.size();
    }

    /**
     * Returns true if the athlete has completed ALL stages of the race.
     */
    boolean hasFinished(String athleteId) {
        long stagesCompleted = stageResults.stream()
                .filter(r -> r.athlete.athleteId.equals(athleteId))
                .map(r -> r.stageNumber)
                .distinct()
                .count();
        return stagesCompleted == TOTAL_STAGES;
    }

    /**
     * Returns the total race time for an athlete (sum of all their stage times).
     * Returns -1.0 if the athlete has no recorded results.
     */
    double getTotalTime(String athleteId) {
        List<StageResult> athleteResults = new ArrayList<>();
        for (StageResult r : stageResults) {
            if (r.athlete.athleteId.equals(athleteId)) {
                athleteResults.add(r);
            }
        }
        if (athleteResults.isEmpty()) return -1.0;
        return athleteResults.stream().mapToDouble(r -> r.timeSeconds).sum();
    }

    /**
     * Returns the name of the winner – the finisher with the lowest total time.
     * An athlete who has not completed all TOTAL_STAGES stages is excluded.
     *
     * BUG: This method has a bug – fix it!
     */
    String getWinner() {
        // Collect all unique athlete IDs seen in results
        Set<String> allIds    = new HashSet<>();
        Map<String, String> idToName  = new HashMap<>();
        for (StageResult r : stageResults) {
            allIds.add(r.athlete.athleteId);
            idToName.put(r.athlete.athleteId, r.athlete.name);
        }

        if (allIds.isEmpty()) return null;

        String winnerId = null;
        double bestTime = Double.MAX_VALUE;

        for (String id : allIds) {
            // BUG: DNF athletes (who completed fewer than TOTAL_STAGES stages)
            // are NOT filtered out.
            // Their partial total time can be smaller than a finisher's total,
            // producing a wrong winner.
            //
            if (!hasFinished(id)) continue;
            //      before computing the total time.
            double total = getTotalTime(id);
            if (total < bestTime) {
                bestTime = total;
                winnerId = id;
            }
        }

        return idToName.get(winnerId);
    }

    /**
     * Returns the theoretical best-of-best time achievable by picking the
     * fastest athlete for each individual stage (a "dream team" run).
     *
     * For each stage 1..TOTAL_STAGES, find the minimum time recorded across
     * all athletes who ran that stage, then return the sum of those per-stage
     * minimums.
     *
     * Returns -1.0 if there are no results at all.
     */
    public double getBestOfBestTime() {
        // Write your code here to solve this problem

        if (stageResults.isEmpty()) {
            return -1.0;
        }
        double totalBestTime = 0.0;
        // For each stage 1.TOTAL_STAGES
        for (int stage = 1; stage <= TOTAL_STAGES; stage++) {

            double bestStageTime = Double.MAX_VALUE;
            boolean found = false;

            for (StageResult r : stageResults) {
                if (r.stageNumber == stage) {
                    bestStageTime = Math.min(bestStageTime, r.timeSeconds);
                    found = true;
                }
            }

            // Add best time for this stage if found
            if (found) {
                totalBestTime += bestStageTime;
            }
        }

        return totalBestTime;
    }
}
