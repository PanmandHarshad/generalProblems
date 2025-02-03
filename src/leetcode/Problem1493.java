package leetcode;

import java.util.Map;

public class Problem1493 {

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{1, 1, 0, 1}));
        System.out.println(longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
    }

    static public int longestSubarray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int n = nums.length;
        int maxLen = 0;
        int left = 0; // Length of 1s to the left of the zero
        int right = 0; // Length of 1s to the right of the zero
        boolean zeroFound = false; // Flag to check if at least one zero is found

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                right++;
            } else {
                zeroFound = true;
                maxLen = Math.max(maxLen, left + right);
                left = right; // Now left becomes right
                right = 0; // Reset right after a zero
            }
        }

        maxLen = Math.max(maxLen, left + right); // In case the array ends with 1s

        // If no zero was found, the result should be the array length minus one
        return zeroFound ? maxLen : n - 1;
    }

    static public int longestSubarray1(int[] nums) {
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
