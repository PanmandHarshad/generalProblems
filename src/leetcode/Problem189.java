package leetcode;

import java.util.Arrays;

public class Problem189 {

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        rotate(nums1, k1);

        System.out.println("Input: [1,2,3,4,5,6,7], k = 3");
        System.out.println("Your Output: " + Arrays.toString(nums1));
        System.out.println("Expected Output: [5,6,7,1,2,3,4]");
        System.out.println("----------------------------------");


        // Test Case 2
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;
        rotate(nums2, k2);

        System.out.println("Input: [-1,-100,3,99], k = 2");
        System.out.println("Your Output: " + Arrays.toString(nums2));
        System.out.println("Expected Output: [3,99,-1,-100]");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Element
        int[] nums3 = {1};
        int k3 = 5;
        rotate(nums3, k3);

        System.out.println("Input: [1], k = 5");
        System.out.println("Your Output: " + Arrays.toString(nums3));
        System.out.println("Expected Output: [1]");
        System.out.println("----------------------------------");


        // Test Case 4 - k = 0
        int[] nums4 = {1, 2, 3};
        int k4 = 0;
        rotate(nums4, k4);

        System.out.println("Input: [1,2,3], k = 0");
        System.out.println("Your Output: " + Arrays.toString(nums4));
        System.out.println("Expected Output: [1,2,3]");
        System.out.println("----------------------------------");


        // Test Case 5 - k greater than array length
        int[] nums5 = {1, 2, 3, 4};
        int k5 = 6;
        rotate(nums5, k5);

        System.out.println("Input: [1,2,3,4], k = 6");
        System.out.println("Your Output: " + Arrays.toString(nums5));
        System.out.println("Expected Output: [3,4,1,2]");
        System.out.println("----------------------------------");


        // Test Case 6 - All Same Elements
        int[] nums6 = {5, 5, 5, 5};
        int k6 = 3;
        rotate(nums6, k6);

        System.out.println("Input: [5,5,5,5], k = 3");
        System.out.println("Your Output: " + Arrays.toString(nums6));
        System.out.println("Expected Output: [5,5,5,5]");
        System.out.println("----------------------------------");


        // Test Case 7 - Large Rotation
        int[] nums7 = {10, 20, 30, 40, 50};
        int k7 = 12;
        rotate(nums7, k7);

        System.out.println("Input: [10,20,30,40,50], k = 12");
        System.out.println("Your Output: " + Arrays.toString(nums7));
        System.out.println("Expected Output: [40,50,10,20,30]");
        System.out.println("----------------------------------");
    }

    // Brute Force
    static public void rotate1(int[] nums, int k) {
        int length = nums.length;
        k = k % length;

        for (int i = 0; i < k; i++) {
            int last = nums[length - 1];
            for (int j = length - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = last;
        }
    }

    static public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (length == 1 || k <= 0) {
            return;
        }

        rotateKElements(nums, 0, length - 1);
        rotateKElements(nums, 0, k - 1);
        rotateKElements(nums, k, length - 1);
    }

    static public void rotateKElements(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}