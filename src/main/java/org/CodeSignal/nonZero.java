package org.CodeSignal;

public class nonZero {

    public static int solution(int[] numbers) {
        int result = 0;

        while (true) {
            int i = -1;
            for (int idx = 0; idx < numbers.length; idx++) {
                if (numbers[idx] != 0) {
                    i = idx;
                    break;
                }
            }

            if (i == -1) {
                break;
            }

            int x = numbers[i];
            for (int j = i; j < numbers.length; j++) {
                if (numbers[j] < x) {
                    break;
                } else {
                    numbers[j] -= x;
                }
            }
            result += x;
        }

        return result;
    }

    // For testing
    public static void main(String[] args) {
        int[] numbers = {3, 3, 5, 2, 3};
        System.out.println(solution(numbers)); // Output: 6
        int[] numbers1 = {5, 5, 5, 5, 5, 5};
        System.out.println(solution(numbers1)); // Output: 5
    }
}
