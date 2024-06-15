package leetcode;

public class Problem643 {
    public static void main(String[] args) {
//        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(findMaxAverage(new int[]{0, 1, 1, 3, 3}, 4));
    }

    static public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Initialize maxSum with the sum of the first k elements
        double maxSum = sum;

        // Slide the window from start to end of the array
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];  // Add the next element and remove the element left behind
            maxSum = Math.max(maxSum, sum);  // Update maxSum if the current sum is greater
        }

        // Return the maximum average
        return maxSum / k;
    }
}
