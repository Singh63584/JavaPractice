package org.StringProblems;

public class CharGetFrequency {
    public static int[] getFrequency(String A) {
        int[] freq = new int[26];

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            freq[ch - 'a']++;
        }
        return freq;
    }
    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // For testing
    public static void main(String[] args) {
        int[] result1 = getFrequency("abcdefghijklmnopqrstuvwxyz");
        printArray(result1);

        int[] result2 = getFrequency("interviewbit");
        printArray(result2);
    }
}
