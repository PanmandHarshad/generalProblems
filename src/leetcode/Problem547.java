package leetcode;

public class Problem547 {

    public static void main(String[] args) {
        int[][][] testCases = {
                {
                        {1, 1, 0},
                        {1, 1, 0},
                        {0, 0, 1}
                },
                {
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 1}
                },
                {
                        {1, 1, 1},
                        {1, 1, 1},
                        {1, 1, 1}
                },
                {
                        {1}
                }
        };

        for (int i = 0; i < testCases.length; i++) {
            int[][] isConnected = testCases[i];
            System.out.println("Test Case " + (i + 1) + ":");

            System.out.println("Provinces:" + findCircleNum(isConnected));

            System.out.println("----------------------------");
        }
    }

    static public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinceCount = 0;

        for (int city = 0; city < n; city++) {
            if (!visited[city]) {
                dfs(isConnected, visited, city);
                provinceCount++;
            }
        }

        return provinceCount;
    }

    static private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor);
            }
        }
    }
}
