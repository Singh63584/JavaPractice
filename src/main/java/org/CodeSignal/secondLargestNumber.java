package org.CodeSignal;

public class secondLargestNumber {

    public static void main(String[] args) {
        int[] arr = {10, 20, 20, 16, 32, 1, -1, 8};

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("Second largest does not exist");
        } else {
            System.out.println("Second largest: " + secondLargest);
        }
    }
}
