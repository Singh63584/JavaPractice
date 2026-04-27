package org.ArrayProblems;

public class ThirdLargestNumber {
    public static int findThirdLargest(int[] arr) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (num > first) {
                // Shift values
                third = second;
                second = first;
                first = num;
            }
            else if (num > second && num != first) {
                third = second;
                second = num;
            }
            else if (num > third && num != first && num != second) {
                third = num;
            }
        }
        return third;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 20, 8, 20, 15};
        System.out.println(findThirdLargest(arr)); // Output: 10
    }
}