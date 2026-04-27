package org.CodeSignal;

public class Solution {

    public static String solution(int[] numbers, int pivot) {

        int countGreater = 0; // counts numbers greater than pivot
        int countLess = 0;    // counts numbers less than pivot

        for (int num : numbers) {
            if (num > pivot) {
                countGreater++;
            } else if (num < pivot) {
                countLess++;
            }
            // numbers equal to pivot are ignored
        }

        if (countGreater > countLess) {
            return "greater";
        } else if (countGreater < countLess) {
            return "smaller";
        } else {
            return "tie";
        }
    }

    // Main method to test the solution
    public static void main(String[] args) {

        System.out.println(solution(
                new int[]{1, 3, 0, -1, 1, 4, 3}, 2));   // smaller

        System.out.println(solution(
                new int[]{3, 4, 5, 1, 0}, 3));         // tie

        System.out.println(solution(
                new int[]{9, 8, -5}, -1));             // greater
    }
}
