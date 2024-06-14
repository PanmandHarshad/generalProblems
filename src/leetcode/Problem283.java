package leetcode;

public class Problem283 {
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0, 3, 12};
        moveZeroes(arr1);
        printData(arr1);

        int[] arr2 = {0};
        moveZeroes(arr2);
        printData(arr2);

        int[] arr3 = {2, 1};
        moveZeroes(arr3);
        printData(arr3);
    }

    private static void printData(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else if (count > 0) {
                nums[i - count] = nums[i];
                nums[i] = 0;
            }
        }
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (right - left) * minHeight);

            while (left < height.length && height[left] <= minHeight) {
                left++;
            }

            while (right > 0 && height[right] <= minHeight) {
                right--;
            }
        }
        return maxArea;
    }
}

