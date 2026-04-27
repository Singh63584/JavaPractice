package org.karat.RacerHuddlesPerformance;

import org.junit.Assert;

public class RacePerformance {
    public static void main(String[] args) {
        testStageResult();
        testHurdlesRace();
        testGetBestOfBestTime();
    }

    // -------------------------------------------------------------------------
    // Test 1: Basic StageResult construction
    // -------------------------------------------------------------------------
    public static void testStageResult() {
        System.out.println("Running testStageResult");
        Athlete alice = new Athlete("A001", "Alice");
        StageResult sr = new StageResult(alice, 1, 13.5);

        Assert.assertEquals(alice, sr.athlete);
        Assert.assertEquals(1,     sr.stageNumber);
        Assert.assertEquals(13.5,  sr.timeSeconds, 0.001);
        System.out.println("testStageResult PASSED");
    }

    // -------------------------------------------------------------------------
    // Test 2: HurdlesRace core methods + getWinner bug fix
    //
    // Setup:
    //   Alice  – stages 1,2,3,4 → times 13.5, 14.2, 13.8, 14.0 → total 55.5  (finisher)
    //   Bob    – stages 1,2,3,4 → times 12.9, 13.7, 14.1, 13.5 → total 54.2  (finisher, fastest)
    //   Carol  – stages 1,2,3,4 → times 14.1, 13.5, 13.9, 14.3 → total 55.8  (finisher)
    //   Dave   – stages 1,2,3   → times 13.2, 13.4, 13.6        → total 40.2  (DNF – only 3 stages)
    //
    // Without the bug fix, Dave's partial total (40.2) < Bob's full total (54.2),
    // so buggy getWinner() incorrectly returns "Dave".
    // After the fix, DNF athletes are skipped → correct winner is "Bob".
    // -------------------------------------------------------------------------
    public static void testHurdlesRace() {
        System.out.println("Running testHurdlesRace");

        Athlete alice = new Athlete("A001", "Alice");
        Athlete bob   = new Athlete("A002", "Bob");
        Athlete carol = new Athlete("A003", "Carol");
        Athlete dave  = new Athlete("A004", "Dave");   // DNF – only 3 stages

        HurdlesRace race = new HurdlesRace();

        // Alice – all 4 stages
        race.addStageResult(new StageResult(alice, 1, 13.5));
        race.addStageResult(new StageResult(alice, 2, 14.2));
        race.addStageResult(new StageResult(alice, 3, 13.8));
        race.addStageResult(new StageResult(alice, 4, 14.0));

        // Bob – all 4 stages
        race.addStageResult(new StageResult(bob, 1, 12.9));
        race.addStageResult(new StageResult(bob, 2, 13.7));
        race.addStageResult(new StageResult(bob, 3, 14.1));
        race.addStageResult(new StageResult(bob, 4, 13.5));

        // Carol – all 4 stages
        race.addStageResult(new StageResult(carol, 1, 14.1));
        race.addStageResult(new StageResult(carol, 2, 13.5));
        race.addStageResult(new StageResult(carol, 3, 13.9));
        race.addStageResult(new StageResult(carol, 4, 14.3));

        // Dave – only 3 stages (DNF)
        race.addStageResult(new StageResult(dave, 1, 13.2));
        race.addStageResult(new StageResult(dave, 2, 13.4));
        race.addStageResult(new StageResult(dave, 3, 13.6));

        Assert.assertEquals(15, race.getTotalStageResults());

        // hasFinished checks
        Assert.assertTrue(race.hasFinished("A001"));   // Alice
        Assert.assertTrue(race.hasFinished("A002"));   // Bob
        Assert.assertTrue(race.hasFinished("A003"));   // Carol
        Assert.assertFalse(race.hasFinished("A004"));  // Dave – DNF

        // Total time checks
        Assert.assertEquals(55.5, race.getTotalTime("A001"), 0.001);  // Alice
        Assert.assertEquals(54.2, race.getTotalTime("A002"), 0.001);  // Bob
        Assert.assertEquals(55.8, race.getTotalTime("A003"), 0.001);  // Carol
        Assert.assertEquals(40.2, race.getTotalTime("A004"), 0.001);  // Dave (partial)

        // Winner must be Bob (lowest total among FINISHERS only).
        // The bug causes "Dave" to be returned without the fix.
        Assert.assertEquals("Bob", race.getWinner());
        System.out.println("testHurdlesRace PASSED");
    }

    // -------------------------------------------------------------------------
    // Test 3: getBestOfBestTime
    //
    // Using the same race data as testHurdlesRace:
    //           Stage 1  Stage 2  Stage 3  Stage 4
    //   Alice:   13.5     14.2     13.8     14.0
    //   Bob:     12.9     13.7     14.1     13.5
    //   Carol:   14.1     13.5     13.9     14.3
    //   Dave:    13.2     13.4     13.6     (no Stage 4)
    //
    // Best per stage:
    //   Stage 1: min(13.5, 12.9, 14.1, 13.2) = 12.9  ← Bob
    //   Stage 2: min(14.2, 13.7, 13.5, 13.4) = 13.4  ← Dave
    //   Stage 3: min(13.8, 14.1, 13.9, 13.6) = 13.6  ← Dave
    //   Stage 4: min(14.0, 13.5, 14.3)       = 13.5  ← Bob  (Dave has no Stage 4)
    //
    // Best of Best time = 12.9 + 13.4 + 13.6 + 13.5 = 53.4
    // -------------------------------------------------------------------------
    public static void testGetBestOfBestTime() {
        System.out.println("Running testGetBestOfBestTime");

        // Empty race → -1.0
        HurdlesRace emptyRace = new HurdlesRace();
        Assert.assertEquals(-1.0, emptyRace.getBestOfBestTime(), 0.001);

        Athlete alice = new Athlete("A001", "Alice");
        Athlete bob   = new Athlete("A002", "Bob");
        Athlete carol = new Athlete("A003", "Carol");
        Athlete dave  = new Athlete("A004", "Dave");

        HurdlesRace race = new HurdlesRace();

        race.addStageResult(new StageResult(alice, 1, 13.5));
        race.addStageResult(new StageResult(alice, 2, 14.2));
        race.addStageResult(new StageResult(alice, 3, 13.8));
        race.addStageResult(new StageResult(alice, 4, 14.0));

        race.addStageResult(new StageResult(bob, 1, 12.9));
        race.addStageResult(new StageResult(bob, 2, 13.7));
        race.addStageResult(new StageResult(bob, 3, 14.1));
        race.addStageResult(new StageResult(bob, 4, 13.5));

        race.addStageResult(new StageResult(carol, 1, 14.1));
        race.addStageResult(new StageResult(carol, 2, 13.5));
        race.addStageResult(new StageResult(carol, 3, 13.9));
        race.addStageResult(new StageResult(carol, 4, 14.3));

        // Dave: only stages 1–3 (DNF)
        race.addStageResult(new StageResult(dave, 1, 13.2));
        race.addStageResult(new StageResult(dave, 2, 13.4));
        race.addStageResult(new StageResult(dave, 3, 13.6));

        // 12.9 + 13.4 + 13.6 + 13.5 = 53.4
        Assert.assertEquals(53.4, race.getBestOfBestTime(), 0.001);
        System.out.println("testGetBestOfBestTime PASSED");
    }
}
