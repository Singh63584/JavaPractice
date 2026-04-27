package org.ArrayProblems;

import java.util.Arrays;

public class ZeroEnd {
    public static void moveZerosToEnd(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }
        while (index < arr.length) {
            arr[index++] = 0;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 0, 3, 0, 5};
        moveZerosToEnd(arr);
        System.out.println(Arrays.toString(arr));
        moveZerosToStart(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void moveZerosToStart(int[] arr) {
        int index = arr.length - 1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[index--] = arr[i];
            }
        }

        while (index >= 0) {
            arr[index--] = 0;
        }
    }
}