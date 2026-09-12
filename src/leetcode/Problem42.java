package leetcode;

import java.util.Arrays;

public class Problem42 {
    public static void main(String[] args) {

        // Test Case 1
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result1 = trap(height1);
        System.out.println("Input Heights: " + Arrays.toString(height1));
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 6");
        System.out.println("----------------------------------");

        // Test Case 2 - No trapping water
        int[] height2 = {0, 1, 2, 3, 4};
        int result2 = trap(height2);
        System.out.println("Input Heights: " + Arrays.toString(height2));
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");

        // Test Case 3 - Reverse slope
        int[] height3 = {4, 3, 2, 1, 0};
        int result3 = trap(height3);
        System.out.println("Input Heights: " + Arrays.toString(height3));
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");

        // Test Case 4 - Simple valley
        int[] height4 = {3, 0, 2, 0, 4};
        int result4 = trap(height4);
        System.out.println("Input Heights: " + Arrays.toString(height4));
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 7");
        System.out.println("----------------------------------");

        // Test Case 5 - Flat array
        int[] height5 = {2, 2, 2, 2};
        int result5 = trap(height5);
        System.out.println("Input Heights: " + Arrays.toString(height5));
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");

        // Test Case 6 - Single bar
        int[] height6 = {5};
        int result6 = trap(height6);
        System.out.println("Input Heights: " + Arrays.toString(height6));
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");

        // Test Case 7 - Multiple peaks
        int[] height7 = {5, 2, 1, 2, 1, 5};
        int result7 = trap(height7);
        System.out.println("Input Heights: " + Arrays.toString(height7));
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: 14");
        System.out.println("----------------------------------");

        int[] height8 = {4, 2, 0, 3, 2, 5};
        int result8 = trap(height8);
        System.out.println("Input Heights: " + Arrays.toString(height8));
        System.out.println("Your Output: " + result8);
        System.out.println("Expected Output: 9");
        System.out.println("----------------------------------");
    }

    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return result;
    }
}
