package leetcode;

public class Problem162 {

    // [1,2,3,1]        => 3
    // [1,2,1,3,5,6,4]  => 5
    public static int findPeakElement(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {

        int[][] testCases = {
                {1, 2, 3, 1},
                {1, 2, 1, 3, 5, 6, 4},
                {1},
                {1, 2},
                {2, 1},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 1, 2}
        };

        for (int[] nums : testCases) {
            int index = findPeakElement(nums);

            System.out.printf("nums = %s, peak index = %d, peak value = %d%n",
                    java.util.Arrays.toString(nums),
                    index,
                    nums[index]);
        }
    }
}
