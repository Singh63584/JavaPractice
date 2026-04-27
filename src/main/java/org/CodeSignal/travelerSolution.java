package org.CodeSignal;

import java.util.*;

public class travelerSolution {
    public static int[] solution(int[][] travelPhotos) {
        // Build adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] pair : travelPhotos) {
            graph.putIfAbsent(pair[0], new ArrayList<>());
            graph.putIfAbsent(pair[1], new ArrayList<>());
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        // Find start node (degree = 1)
        int start = -1;
        for (int node : graph.keySet()) {
            if (graph.get(node).size() == 1) {
                start = node;
                break;
            }
        }

        int n = travelPhotos.length + 1;
        int[] result = new int[n];

        Set<Integer> visited = new HashSet<>();
        int index = 0;
        int current = start;

        // Traverse the path
        while (current != -1) {
            result[index++] = current;
            visited.add(current);

            int nextNode = -1;
            for (int neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    nextNode = neighbor;
                    break;
                }
            }
            current = nextNode;
        }

        return result;
    }

    // Test case
    public static void main(String[] args) {
        int[][] travelPhotos = {
                {3, 5},
                {1, 4},
                {2, 4},
                {1, 5}
        };

        int[] journey = solution(travelPhotos);
        System.out.println(Arrays.toString(journey));
    }
}
