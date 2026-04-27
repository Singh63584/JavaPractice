package org.CodeSignal;

public class largestNumber {
    public static void main(String[] args) {
        int[] arr = {10, 20, 20, 16, 32, 1, -1, 8};
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Largest number: " + max);
    }
}
