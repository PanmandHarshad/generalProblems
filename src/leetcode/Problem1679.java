package leetcode;

import java.util.Arrays;

public class Problem1679 {
    public static void main(String[] args) {
        System.out.println("Max number of k-sum pairs: " + maxOperations(new int[]{1, 2, 3, 4, 5}, 5));
        System.out.println("Max number of k-sum pairs: " + maxOperations(new int[]{3, 1, 3, 4, 3}, 6));

    }

    static public int maxOperations(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int count = 0;

        while (left < right) {
            if ((nums[right] + nums[left]) == k) {
                count++;
                left++;
                right--;
            } else if ((nums[right] + nums[left]) < k) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
