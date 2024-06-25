package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem2352 {
    public static void main(String[] args) {
        //System.out.println(equalPairs(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}));
        System.out.println(equalPairs(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}}));
    }

    static public int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        int[][] transpose = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = grid[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Arrays.equals(grid[i], transpose[j])) count++;
            }
        }

        return count;
    }

    static public void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static public int equalPairs2(int[][] grid) {
        int n = grid.length;
        int count = 0;

        // Maps to store row and column frequencies
        Map<String, Integer> rowMap = new HashMap<>();
        Map<String, Integer> colMap = new HashMap<>();

        // Calculate frequencies
        for (int i = 0; i < n; i++) {
            StringBuilder rowKey = new StringBuilder();
            StringBuilder colKey = new StringBuilder();

            for (int j = 0; j < n; j++) {
                rowKey.append(grid[i][j]).append(",");
                colKey.append(grid[j][i]).append(",");
            }

            String rowStr = rowKey.toString();
            String colStr = colKey.toString();

            rowMap.put(rowStr, rowMap.getOrDefault(rowStr, 0) + 1);
            colMap.put(colStr, colMap.getOrDefault(colStr, 0) + 1);
        }

        // Count equal row and column pairs
        for (Map.Entry<String, Integer> entry : rowMap.entrySet()) {
            String rowKey = entry.getKey();
            int rowFreq = entry.getValue();
            count += rowFreq * colMap.getOrDefault(rowKey, 0);
        }

        return count;
    }

    static public int equalPairs1(int[][] grid) {
        // 00 01 02
        // 10 11 12
        // 20 21 22

        int n = grid.length;
        int count = 0;

        // Compare each row with each column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isEqual = true;

                for (int k = 0; k < n; k++) {
                    // Comparing row element with column element
                    if (grid[i][k] != grid[k][j]) {
                        isEqual = false;
                        break;
                    }
                }

                if (isEqual) {
                    count++;
                }
            }
        }

        return count;
    }
}
