package leetcode;

public class Problem643 {
    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(findMaxAverage(new int[]{0, 1, 1, 3, 3}, 4));
    }

    static public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            if (sum > maxSum)
                maxSum = sum;
        }

        return maxSum / k;
    }

}
