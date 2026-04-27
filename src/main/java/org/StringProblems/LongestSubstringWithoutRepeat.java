package org.StringProblems;

public class LongestSubstringWithoutRepeat {
    public static int lengthOfLongestSubstring(String A) {
        int[] checkChar = new int[256]; // ASCII characters
        for (int i = 0; i < 256; i++) {
            checkChar[i] = -1;
        }
        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < A.length(); right++) {
            char ch = A.charAt(right);
            // If character seen inside current window
            if (checkChar[ch] >= left) {
                left = checkChar[ch] + 1;
            }
            checkChar[ch] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("AaaAbgz"));     //5
        System.out.println(lengthOfLongestSubstring("Deepaksiingh")); //6
        System.out.println(lengthOfLongestSubstring("122345679"));     //7


    }
}
