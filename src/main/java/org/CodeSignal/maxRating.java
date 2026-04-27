package org.CodeSignal;

public class maxRating {
    public static int [] maxAndCurrentRating(int [] diff) {
        int current = 1500;
        int max = 1500;

        for( int diffs : diff) {
            current += diffs;
            if (current > max) {
                max = current;
            }
        }
        return new int[] {max, current};
    }
    public static void main(String [] args) {
        int [] diff = {100, -200, 350, 100, 600};
        int [] result = maxAndCurrentRating(diff);
        System.out.println(result[1]);
    }
}
