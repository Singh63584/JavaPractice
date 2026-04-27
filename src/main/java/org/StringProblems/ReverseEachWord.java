package org.StringProblems;

public class ReverseEachWord {
    private static void reverseEachWord(String str) {
        int start = 0;

        for (int i = 0; i <= str.length(); i++) {
            if (i == str.length() || str.charAt(i) == ' ') {
                // reverse from start to i-1
                for (int j = i - 1; j >= start; j--) {
                    System.out.print(str.charAt(j));
                }

                if (i < str.length()) {
                    System.out.print(" ");
                }
                start = i + 1;
            }
        }
    }
    public static void main(String[] args) {
        String input = "How are you! Deepak";
        reverseEachWord(input);
    }
}
