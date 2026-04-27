package org.ArrayProblems;
import java.util.*;

public class Array2DTo1D {
    public static void main(String[] args){

        int[][] arr2D = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[] arr1D = Arrays.stream(arr2D)
                .flatMapToInt(Arrays::stream)
                .toArray();
    }
}
