package leetcode;

public class Problem27 {

    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int k1 = removeElement(nums1, val1);
        System.out.println("Input: [3,2,2,3], val = 3");
        System.out.print("Output: " + k1 + ", nums = [");
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + (i < k1 - 1 ? "," : ""));
        }
        System.out.println("]\n");

        // Test Case 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int k2 = removeElement(nums2, val2);
        System.out.println("Input: [0,1,2,2,3,0,4,2], val = 2");
        System.out.print("Output: " + k2 + ", nums = [");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + (i < k2 - 1 ? "," : ""));
        }
        System.out.println("]\n");

        // Test Case 3 (No removal)
        int[] nums3 = {1, 2, 3, 4};
        int val3 = 5;
        int k3 = removeElement(nums3, val3);
        System.out.println("Input: [1,2,3,4], val = 5");
        System.out.print("Output: " + k3 + ", nums = [");
        for (int i = 0; i < k3; i++) {
            System.out.print(nums3[i] + (i < k3 - 1 ? "," : ""));
        }
        System.out.println("]\n");

        // Test Case 4 (All removed)
        int[] nums4 = {7, 7, 7, 7};
        int val4 = 7;
        int k4 = removeElement(nums4, val4);
        System.out.println("Input: [7,7,7,7], val = 7");
        System.out.print("Output: " + k4 + ", nums = [");
        for (int i = 0; i < k4; i++) {
            System.out.print(nums4[i] + (i < k4 - 1 ? "," : ""));
        }
        System.out.println("]\n");

        // Test Case 5 (Empty array)
        int[] nums5 = {};
        int val5 = 1;
        int k5 = removeElement(nums5, val5);
        System.out.println("Input: [], val = 1");
        System.out.println("Output: " + k5 + ", nums = []");
    }

    /*
    Input: nums = [3,2,2,3], val = 3
    Output: 2, nums = [2,2,_,_]

    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
     */
    static public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == val && nums[right] == val) {
                right--;
            } else if (nums[left] == val) {
                nums[left++] = nums[right--];
            } else {
                left++;
            }
        }
        return left;
    }

    /*
    Input: nums = [3,2,2,3], val = 3
    Output: 2, nums = [2,2,_,_]

    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
     */
    static public int removeElement1(int[] nums, int val) {
        int left = 0;
        int length = nums.length;

        while (left < length) {
            if (nums[left] == val) {
                nums[left] = nums[length - 1];
                length--;
            } else {
                left++;
            }
        }
        return left;
    }
}
