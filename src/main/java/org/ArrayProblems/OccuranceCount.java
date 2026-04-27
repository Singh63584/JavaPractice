package org.ArrayProblems;
import java.util.*;

public class OccuranceCount {
        public static int[] solve(int[] A) {
            // Step 1: Count frequencies
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : A) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }
            // Step 2: Sort the keys
            List<Integer> keys = new ArrayList<>(freqMap.keySet());
            Collections.sort(keys);
            // Step 3: Prepare result
            int[] result = new int[keys.size()];
            int index = 0;
            for (int key : keys) {
                result[index++] = freqMap.get(key);
            }
            return result;
        }

        // For testing
        public static void main(String[] args) {
            int[] A1 = {1, 2, 3};
            System.out.println(Arrays.toString(solve(A1))); // [1, 1, 1]

            int[] A2 = {4, 3, 3};
            System.out.println(Arrays.toString(solve(A2))); // [2, 1]
        }
}
