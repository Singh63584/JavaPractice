package org.karat.GymMemebrship;

import java.util.*;
import java.util.stream.Collectors;

public class Membership {
    /*
       Data for managing a gym membership, and methods which staff can
       use to perform any queries or updates.
   */
//    public List<Member> members;
//
//    public Membership() {
//        members = new ArrayList<>();
//    }
//
//    public void addMember(Member member) {
//        members.add(member);
//    }
//
//    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
//        for (Member member : members) {
//            if (member.memberId == memberId) {
//                member.membershipStatus = membershipStatus;
//                break;
//            }
//        }
//    }
//
//    public MembershipStatistics getMembershipStatistics() {
//        int totalMembers = members.size();
//        int totalPaidMembers = 0;
//
//        for (Member member : members) {
//            if (member.membershipStatus == MembershipStatus.GOLD ||member.membershipStatus == MembershipStatus.SILVER) {
//                totalPaidMembers++;
//            }
//        }
//
//        double conversionRate = totalPaidMembers == 0 ? 0.0 : (totalPaidMembers / (double) totalMembers) * 100.0;
//        System.out.print(members.size());
//        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
//
//    }
//
//    public Map<Integer, Double> getAverageWorkoutDurations() {
//        addWorkout(this.);
//
//    }
//
//    public void addWorkout(int i, Workout testWorkout1) {
//    }

    public List<Member> members;

    public Membership() {
        members = new ArrayList<>();
        workoutsByMember = new HashMap<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
        for (Member member : members) {
            if (member.memberId == memberId) {
                member.membershipStatus = membershipStatus;
                break;
            }
        }
    }

    public MembershipStatistics getMembershipStatistics() {
        int totalMembers = members.size();
        int totalPaidMembers = 0;

        for (Member member : members) {
            if (member.membershipStatus == MembershipStatus.GOLD || member.membershipStatus == MembershipStatus.SILVER) {
                totalPaidMembers++;
            }
        }

        double conversionRate = totalMembers == 0 ? 0.0 : (totalPaidMembers / (double) totalMembers) * 100.0;

        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }

    private Map<Integer, List<Workout>> workoutsByMember;
    public void addWorkout(int memberId, Workout workout) {
        boolean memberExists = false;
        for (Member m : members) {
            if (m.memberId == memberId) {
                memberExists = true;
                break;
            }
        }

        if (!memberExists) return;

        workoutsByMember.computeIfAbsent(memberId, k -> new ArrayList<>()).add(workout);
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, List<Workout>> entry : workoutsByMember.entrySet()) {
            int memberId = entry.getKey();
            List<Workout> workouts = entry.getValue();

            if (workouts.isEmpty()) continue;

            int totalDuration = 0;
            for (Workout w : workouts) {
                totalDuration += w.getDuration();
            }

            result.put(memberId, totalDuration / (double) workouts.size());
        }

        return result;
    }

    /*
    public List<Member> members;
    private Map<Integer, List<Workout>> workoutsByMember;

    public Membership() {
        members = new ArrayList<>();
        workoutsByMember = new HashMap<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
        members.stream().filter(m -> m.memberId == memberId).findFirst().ifPresent(m -> m.membershipStatus = membershipStatus);
    }

    public MembershipStatistics getMembershipStatistics() {
        int totalMembers = members.size();
        long totalPaidMembers = members.stream().filter(m ->
                m.membershipStatus == MembershipStatus.SILVER || m.membershipStatus == MembershipStatus.GOLD).count();
        double conversionRate = totalMembers == 0 ? 0.0 : (totalPaidMembers / (double) totalMembers) * 100.0;
        return new MembershipStatistics(totalMembers, (int) totalPaidMembers, conversionRate);
    }

    public void addWorkout(int memberId, Workout workout) {
        boolean memberExists = members.stream().anyMatch(m -> m.memberId == memberId);
        if (!memberExists) return;
        workoutsByMember.computeIfAbsent(memberId, k -> new ArrayList<>()).add(workout);
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        return workoutsByMember.entrySet().stream().filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .mapToInt(Workout::getDuration).average().orElse(0.0)));
    }
    */

    /**
    public List<Member> members;
    private List<MemberWorkout> memberWorkouts;

    public Membership() {
        members = new ArrayList<>();
        memberWorkouts = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
        members.stream()
                .filter(m -> m.memberId == memberId)
                .findFirst()
                .ifPresent(m -> m.membershipStatus = membershipStatus);
    }

    // ---------- FIXED BUG + STREAM VERSION ----------
    public MembershipStatistics getMembershipStatistics() {
        int totalMembers = members.size();

        long totalPaidMembers = members.stream()
                .filter(m -> m.membershipStatus == MembershipStatus.SILVER
                        || m.membershipStatus == MembershipStatus.GOLD)
                .count();

        double conversionRate = totalMembers == 0
                ? 0.0
                : (totalPaidMembers / (double) totalMembers) * 100;

        return new MembershipStatistics(
                totalMembers,
                (int) totalPaidMembers,
                conversionRate
        );
    }

    //---------- ADD WORKOUT (NO MAP) ----------
    public void addWorkout(int memberId, Workout workout) {
        boolean memberExists = members.stream()
                .anyMatch(m -> m.memberId == memberId);

        if (!memberExists) return;

        memberWorkouts.add(new MemberWorkout(memberId, workout));
    }

    //---------- STREAM AGGREGATION ----------
    public Map<Integer, Double> getAverageWorkoutDurations() {

        return memberWorkouts.stream()
                .collect(Collectors.groupingBy(
                        mw -> mw.memberId,
                        Collectors.averagingInt(mw -> mw.workout.getDuration())
                ));
    }
    */

}
