package leetcode;

import java.util.Arrays;

public class Problem452 {

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(findMinArrowShots(points));
    }

    static public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int end = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                // If the balloon starts before the end of the current arrow, continue
                continue;
            }
            // If it doesn't, we need another arrow
            arrows++;
            end = points[i][1];
        }

        return arrows;
    }

}
