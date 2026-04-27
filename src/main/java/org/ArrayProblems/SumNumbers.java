package org.ArrayProblems;

public class SumNumbers {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5, 6, 7, 8, 9, 10, 11};
        int target = 10;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + target);
                }
            }
        }
    }
}

