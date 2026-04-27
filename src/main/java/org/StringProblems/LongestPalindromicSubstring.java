package org.StringProblems;

public class LongestPalindromicSubstring {
    public static String longestPalindrome(String A) {
        int n = A.length();
        if (n <= 1) return A;

        int start = 0, maxLen = 1;
        for (int i = 0; i < n; i++) {
            // Odd length palindromes
            int len1 = expand(A, i, i);
            // Even length palindromes
            int len2 = expand(A, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return A.substring(start, start + maxLen);
    }

    private static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // Test
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaaabaaa")); // aaabaaa
        System.out.println(longestPalindrome("babad"));    // bab
        System.out.println(longestPalindrome("cbbd"));     // bb
    }
}
