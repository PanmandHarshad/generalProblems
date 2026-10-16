package leetcode;

public class Problem34 {

    public static int[] searchRange(int[] nums, int target) {
        int first = findPosition(nums, target, true);
        int last = findPosition(nums, target, false);

        return new int[]{first, last};
    }

    private static int findPosition(int[] nums, int target, boolean findFirst) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                ans = mid;
                if (findFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        int[][] testArrays = {
                {5, 7, 7, 8, 8, 10},
                {5, 7, 7, 8, 8, 10},
                {1},
                {2, 2, 2, 2},
                {},
                {1, 2, 3, 4, 5},
                {1, 1, 2, 2, 3, 3},
                {3, 3, 3, 3, 3},
                {1, 3, 5, 7, 9}
        };

        int[] targets = {
                8, 6, 1, 2, 0, 1, 2, 3, 5
        };

        for (int i = 0; i < testArrays.length; i++) {
            int[] nums = testArrays[i];
            int target = targets[i];

            int[] result = searchRange(nums, target);

            System.out.printf(
                    "Test %d: target = %d, result = [%d, %d]%n",
                    i + 1, target, result[0], result[1]
            );
        }
    }
}