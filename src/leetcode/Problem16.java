package leetcode;

import java.util.Arrays;

public class Problem16 {

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    static public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int closestSum = Integer.MAX_VALUE;
        int minDifference = Integer.MAX_VALUE;

        // -4,-1,1,2
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                int difference = Math.abs(currentSum - target);

                if (difference < minDifference) {
                    minDifference = difference;
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    return currentSum;
                }
            }
        }
        return closestSum;
    }
}
