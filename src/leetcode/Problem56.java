package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem56 {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];

        // [[1,3],[2,6],[8,10],[15,18]]
        // [[1,3], [2,6], [8,10], [9,12], [15,18]]
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] >= intervals[i][0]) {
                current[1] = Math.max(current[1], intervals[i][1]);
                continue;
            }
            result.add(current);
            current = intervals[i];
        }

        // current has not been added yet
        result.add(current);

        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {

        int[][][] testCases = {
                // 1. Basic overlapping intervals
                {{1, 3}, {2, 6}, {8, 10}, {15, 18}},

                // 2. Touching intervals
                {{1, 4}, {4, 5}},

                // 3. Completely contained intervals
                {{1, 10}, {2, 3}, {4, 8}},

                // 4. Unsorted input
                {{8, 10}, {1, 3}, {2, 6}},

                // 5. Single interval
                {{1, 2}},

                // 6. No overlapping intervals
                {{1, 2}, {3, 4}, {5, 6}},

                // 7. Multiple intervals merging into one
                {{1, 4}, {2, 5}, {3, 6}, {5, 8}},

                // 8. Negative values
                {{-10, -5}, {-8, -2}, {0, 3}}
        };

        for (int i = 0; i < testCases.length; i++) {

            System.out.println("Test Case " + (i + 1));

            int[][] input = testCases[i];

            System.out.println("Input:");
            printIntervals(input);

            int[][] result = merge(input);

            System.out.println("Output:");
            printIntervals(result);

            System.out.println("-----------------------------------");
        }
    }

    private static void printIntervals(int[][] intervals) {

        System.out.print("[");

        for (int i = 0; i < intervals.length; i++) {

            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");

            if (i < intervals.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}
