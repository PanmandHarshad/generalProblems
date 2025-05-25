package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem1971 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {3, 5},
                {5, 4},
                {4, 3}
        };
        int start = 0;
        int end = 5;

        boolean result = validPath(n, edges, start, end);
        System.out.println("Path exists: " + result);  // Expected: false
    }

    static public boolean validPath(int n, int[][] edges, int start, int end) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        return dfs(graph, visited, start, end);
    }

    static private boolean dfs(List<List<Integer>> graph, boolean[] visited, int current, int end) {
        if (current == end)
            return true;

        visited[current] = true;

        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                if (dfs(graph, visited, neighbor, end))
                    return true;
            }
        }

        return false;
    }
}
