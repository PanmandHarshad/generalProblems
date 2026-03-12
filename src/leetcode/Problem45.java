package leetcode;

public class Problem45 {
    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {2, 3, 1, 1, 4};
        int result1 = jump(nums1);

        System.out.println("Input: [2,3,1,1,4]");
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");


        // Test Case 2
        int[] nums2 = {2, 3, 0, 1, 4};
        int result2 = jump(nums2);

        System.out.println("Input: [2,3,0,1,4]");
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Element
        int[] nums3 = {0};
        int result3 = jump(nums3);

        System.out.println("Input: [0]");
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");


        // Test Case 4 - All Ones
        int[] nums4 = {1, 1, 1, 1};
        int result4 = jump(nums4);

        System.out.println("Input: [1,1,1,1]");
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");


        // Test Case 5 - Large First Jump
        int[] nums5 = {5, 1, 1, 1, 1};
        int result5 = jump(nums5);

        System.out.println("Input: [5,1,1,1,1]");
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: 1");
        System.out.println("----------------------------------");


        // Test Case 6 - Zeros in Between
        int[] nums6 = {2, 1, 0, 3};
        int result6 = jump(nums6);

        System.out.println("Input: [2,1,0,3]");
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");


        // Test Case 7 - Longer Case
        int[] nums7 = {1, 2, 3};
        int result7 = jump(nums7);

        System.out.println("Input: [1,2,3]");
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");
    }

    static public int jump(int[] nums) {
        int jump = 0;
        int farthestJumpIndex = 0;
        int currentJumpIndex = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthestJumpIndex = Math.max(farthestJumpIndex, i + nums[i]);

            if (i == currentJumpIndex) {
                jump++;
                currentJumpIndex = farthestJumpIndex;
            }
        }

        return jump;
    }
}
