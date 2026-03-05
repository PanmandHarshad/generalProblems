package leetcode;

public class Problem169 {
    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {3, 2, 3};
        int result1 = majorityElement(nums1);

        System.out.println("Input: [3,2,3]");
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");


        // Test Case 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        int result2 = majorityElement(nums2);

        System.out.println("Input: [2,2,1,1,1,2,2]");
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Element
        int[] nums3 = {1};
        int result3 = majorityElement(nums3);

        System.out.println("Input: [1]");
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 1");
        System.out.println("----------------------------------");


        // Test Case 4 - All Same Elements
        int[] nums4 = {5, 5, 5, 5, 5};
        int result4 = majorityElement(nums4);

        System.out.println("Input: [5,5,5,5,5]");
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 5");
        System.out.println("----------------------------------");


        // Test Case 5 - Negative Numbers
        int[] nums5 = {-1, -1, -1, 2, 2};
        int result5 = majorityElement(nums5);

        System.out.println("Input: [-1,-1,-1,2,2]");
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: -1");
        System.out.println("----------------------------------");


        // Test Case 6 - Majority at End
        int[] nums6 = {1, 3, 1, 3, 3, 3, 3};
        int result6 = majorityElement(nums6);

        System.out.println("Input: [1,3,1,3,3,3,3]");
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");


        // Test Case 7 - Large Majority
        int[] nums7 = {9, 9, 9, 9, 1, 2, 3};
        int result7 = majorityElement(nums7);

        System.out.println("Input: [9,9,9,9,1,2,3]");
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: 9");
        System.out.println("----------------------------------");
    }

    static public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
