package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem2643 {
    /*
    Example 1:
    Input: mat = [[0,1],[1,0]]
    Output: [0,1]

    Example 2:
    Input: mat = [[0,0,0],[0,1,1]]
    Output: [1,2]

    Example 3:
    Input: mat = [[0,0],[1,1],[0,0]]
    Output: [1,2]
     */
    public static void main(String[] args) {
        int[][] matrix1 = {{0, 1}, {1, 0}};
        System.out.println(Arrays.toString(rowAndMaximumOnes(matrix1)));
        int[][] matrix2 = {{0, 0, 0}, {0, 1, 1}};
        System.out.println(Arrays.toString(rowAndMaximumOnes(matrix2)));
        int[][] matrix3 = {{0, 0}, {1, 1}, {0, 0}};
        System.out.println(Arrays.toString(rowAndMaximumOnes(matrix3)));
    }

    static public int[] rowAndMaximumOnes(int[][] mat) {
        int row = 0;
        int max1s = Integer.MIN_VALUE;

        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int element : mat[i]) {
                if (element == 1)
                    count++;
            }

            if (count > max1s) {
                row = i;
                max1s = count;
            }
        }
        return new int[]{row, max1s};
    }
}
