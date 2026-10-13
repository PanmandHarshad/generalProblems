package leetcode;

public class Problem35 {
    public static int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {

        //test(new int[]{1, 3, 5, 6}, 5, 2);
        //test(new int[]{1, 3, 5, 6}, 2, 1);
        test(new int[]{1, 3, 5, 6}, 7, 4);
        test(new int[]{1, 3, 5, 6}, 0, 0);
        test(new int[]{1, 3, 5, 6}, 6, 3);
        test(new int[]{1}, 0, 0);
        test(new int[]{1}, 1, 0);
        test(new int[]{1}, 2, 1);
    }

    private static void test(int[] nums, int target, int expected) {

        int actual = searchInsert(nums, target);

        System.out.printf(
                "target=%d, expected=%d, actual=%d, result=%s%n",
                target,
                expected,
                actual,
                actual == expected ? "PASS" : "FAIL"
        );
    }
}
