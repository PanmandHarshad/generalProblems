package leetcode;

import java.util.*;

public class Problem1466 {

    public static void main(String[] args) {
        Problem1466 solution = new Problem1466();

        int[][][] testCases = {
                {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}},
                {{1, 0}, {1, 2}, {3, 2}, {3, 4}},
                {{2, 0}, {3, 2}, {1, 3}, {0, 4}, {4, 5}},
                {{0, 1}, {2, 0}},  // Already correct
                {{1, 0}, {2, 0}},  // Already correct
        };

        int[] nodeCounts = {6, 5, 6, 3, 3};

        System.out.println("=== BFS Results ===");
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.minReorderBFS(nodeCounts[i], testCases[i]);
            System.out.println("Test case " + (i + 1) + ": " + result);
        }

        System.out.println("\n=== DFS Results ===");
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.minReorderDFS(nodeCounts[i], testCases[i]);
            System.out.println("Test case " + (i + 1) + ": " + result);
        }
    }

    // ==================== BFS Approach ====================
    public int minReorderBFS(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<String> directedEdges = new HashSet<>();

        for (int[] conn : connections) {
            int from = conn[0], to = conn[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
            directedEdges.add(from + "->" + to);
        }

        int reversals = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : graph.get(curr)) {
                if (visited.contains(neighbor)) continue;

                if (directedEdges.contains(curr + "->" + neighbor)) {
                    reversals++;
                }

                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }

        return reversals;
    }

    // ==================== DFS Approach ====================
    private int reversals;

    public int minReorderDFS(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<String> directedEdges = new HashSet<>();

        for (int[] conn : connections) {
            int from = conn[0], to = conn[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
            directedEdges.add(from + "->" + to);
        }

        reversals = 0;
        Set<Integer> visited = new HashSet<>();
        dfs(0, graph, visited, directedEdges);
        return reversals;
    }

    private void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<String> directedEdges) {
        visited.add(node);

        for (int neighbor : graph.get(node)) {
            if (visited.contains(neighbor)) continue;

            if (directedEdges.contains(node + "->" + neighbor)) {
                reversals++;
            }

            dfs(neighbor, graph, visited, directedEdges);
        }
    }


}
