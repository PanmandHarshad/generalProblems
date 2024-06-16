package leetcode;

import java.util.Map;

public class Problem1493 {

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{1, 1, 0, 1}));
        System.out.println(longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
    }

    static public int longestSubarray(int[] nums) {
        int left = 0;
        int right = 0;
        int zeroCount = 0;
        int maxLength = 0;

        while (right < nums.length) {
            // If we encounter a zero, increment the zero count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zero count exceeds one, shrink the window from the left
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length of the window
            maxLength = Math.max(maxLength, right - left);

            // Move the right pointer to expand the window
            right++;
        }

        return maxLength;
    }
}
