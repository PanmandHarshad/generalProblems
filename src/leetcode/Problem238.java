package leetcode;

import java.util.Arrays;

public class Problem238 {
    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = productExceptSelf(nums1);

        System.out.println("Input: [1,2,3,4]");
        System.out.println("Your Output: " + Arrays.toString(result1));
        System.out.println("Expected Output: [24,12,8,6]");
        System.out.println("----------------------------------");


        // Test Case 2 - With Zero
        int[] nums2 = {1, 2, 0, 4};
        int[] result2 = productExceptSelf(nums2);

        System.out.println("Input: [1,2,0,4]");
        System.out.println("Your Output: " + Arrays.toString(result2));
        System.out.println("Expected Output: [0,0,8,0]");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Zero
        int[] nums3 = {0, 1, 2, 3};
        int[] result3 = productExceptSelf(nums3);

        System.out.println("Input: [0,1,2,3]");
        System.out.println("Your Output: " + Arrays.toString(result3));
        System.out.println("Expected Output: [6,0,0,0]");
        System.out.println("----------------------------------");


        // Test Case 4 - All Ones
        int[] nums4 = {1, 1, 1, 1};
        int[] result4 = productExceptSelf(nums4);

        System.out.println("Input: [1,1,1,1]");
        System.out.println("Your Output: " + Arrays.toString(result4));
        System.out.println("Expected Output: [1,1,1,1]");
        System.out.println("----------------------------------");


        // Test Case 5 - Negative Numbers
        int[] nums5 = {-1, 1, -1, 1};
        int[] result5 = productExceptSelf(nums5);

        System.out.println("Input: [-1,1,-1,1]");
        System.out.println("Your Output: " + Arrays.toString(result5));
        System.out.println("Expected Output: [-1,1,-1,1]");
        System.out.println("----------------------------------");


        // Test Case 6 - Larger Numbers
        int[] nums6 = {2, 3, 4, 5};
        int[] result6 = productExceptSelf(nums6);

        System.out.println("Input: [2,3,4,5]");
        System.out.println("Your Output: " + Arrays.toString(result6));
        System.out.println("Expected Output: [60,40,30,24]");
        System.out.println("----------------------------------");


        // Test Case 7 - Two Elements
        int[] nums7 = {5, 10};
        int[] result7 = productExceptSelf(nums7);

        System.out.println("Input: [5,10]");
        System.out.println("Your Output: " + Arrays.toString(result7));
        System.out.println("Expected Output: [10,5]");
        System.out.println("----------------------------------");
    }

    static public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        if (n == 0)
            return result;

        if (n == 1) {
            result[0] = 1;
            return result;
        }

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    // Most efficient solution
    static public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];

        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * suffix;
            suffix = suffix * nums[i];
        }
        return result;
    }
}
