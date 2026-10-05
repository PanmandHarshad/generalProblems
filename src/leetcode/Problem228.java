package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem228 {
    public static List<String> summaryRanges(int[] nums) {

        if (nums == null || nums.length == 0) {
            return List.of();
        }

        List<String> result = new ArrayList<>();
        int start = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i - 1] + 1 == nums[i]) {
                continue;
            }

            int end = i - 1;

            if (start == end) {
                result.add(String.valueOf(nums[start]));
            } else {
                result.add(nums[start] + "->" + nums[end]);
            }

            start = i;
        }

        if (start == nums.length - 1) {
            result.add(String.valueOf(nums[start]));
        } else {
            result.add(nums[start] + "->" + nums[nums.length - 1]);
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] testCases = {
                {},
                {5},
                {0, 1, 2},
                {0, 1, 2, 4, 5, 7},
                {0, 2, 3, 4, 6, 8, 9},
                {1, 2, 3, 4, 5},
                {1, 3, 5, 7},
                {-3, -2, -1, 1, 2, 4},
                {Integer.MIN_VALUE, Integer.MIN_VALUE + 1, 0, 1},
                {Integer.MAX_VALUE - 1, Integer.MAX_VALUE}
        };

        for (int i = 0; i < testCases.length; i++) {

            int[] nums = testCases[i];

            System.out.println("Test Case " + (i + 1) + ": " + java.util.Arrays.toString(nums));

            List<String> result = summaryRanges(nums);

            System.out.println("Output: " + result);
            System.out.println("-----------------------------------");
        }
    }
}
