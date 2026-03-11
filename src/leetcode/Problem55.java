package leetcode;

public class Problem55 {
    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {2, 3, 1, 1, 4};
        boolean result1 = canJump(nums1);

        System.out.println("Input: [2,3,1,1,4]");
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: true");
        System.out.println("----------------------------------");


        // Test Case 2
        int[] nums2 = {3, 2, 1, 0, 4};
        boolean result2 = canJump(nums2);

        System.out.println("Input: [3,2,1,0,4]");
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: false");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Element
        int[] nums3 = {0};
        boolean result3 = canJump(nums3);

        System.out.println("Input: [0]");
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: true");
        System.out.println("----------------------------------");


        // Test Case 4 - All Ones
        int[] nums4 = {1, 1, 1, 1};
        boolean result4 = canJump(nums4);

        System.out.println("Input: [1,1,1,1]");
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: true");
        System.out.println("----------------------------------");


        // Test Case 5 - Stuck at Zero
        int[] nums5 = {2, 0, 0};
        boolean result5 = canJump(nums5);

        System.out.println("Input: [2,0,0]");
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: true");
        System.out.println("----------------------------------");


        // Test Case 6 - Cannot Cross Zero
        int[] nums6 = {1, 0, 1, 0};
        boolean result6 = canJump(nums6);

        System.out.println("Input: [1,0,1,0]");
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: false");
        System.out.println("----------------------------------");


        // Test Case 7 - Large First Jump
        int[] nums7 = {5, 0, 0, 0, 0};
        boolean result7 = canJump(nums7);

        System.out.println("Input: [5,0,0,0,0]");
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: true");
        System.out.println("----------------------------------");
    }

    static public boolean canJump(int[] nums) {
        int maxJump = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxJump) {
                return false;
            }

            maxJump = Math.max(maxJump, i + nums[i]);
        }
        return true;
    }
}
