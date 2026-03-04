package leetcode;

public class Problem80 {
    public static void main(String[] args) {

        // Test Case 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int len1 = removeDuplicates(nums1);

        System.out.println("Input: [1,1,1,2,2,3]");
        System.out.println("Your Output Length: " + len1);
        System.out.println("Expected Length: 5");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len1; i++) {
            System.out.print(nums1[i]);
            if (i < len1 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [1,1,2,2,3]");
        System.out.println("----------------------------------");


        // Test Case 2
        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int len2 = removeDuplicates(nums2);

        System.out.println("Input: [0,0,1,1,1,1,2,3,3]");
        System.out.println("Your Output Length: " + len2);
        System.out.println("Expected Length: 7");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len2; i++) {
            System.out.print(nums2[i]);
            if (i < len2 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [0,0,1,1,2,3,3]");
        System.out.println("----------------------------------");


        // Test Case 3 - Empty Array
        int[] nums3 = {};
        int len3 = removeDuplicates(nums3);

        System.out.println("Input: []");
        System.out.println("Your Output Length: " + len3);
        System.out.println("Expected Length: 0");

        System.out.println("Your Output Array: []");
        System.out.println("Expected Array: []");
        System.out.println("----------------------------------");


        // Test Case 4 - Single Element
        int[] nums4 = {1};
        int len4 = removeDuplicates(nums4);

        System.out.println("Input: [1]");
        System.out.println("Your Output Length: " + len4);
        System.out.println("Expected Length: 1");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len4; i++) {
            System.out.print(nums4[i]);
            if (i < len4 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [1]");
        System.out.println("----------------------------------");


        // Test Case 5 - No duplicates beyond 2
        int[] nums5 = {1, 1, 2, 2, 3, 3};
        int len5 = removeDuplicates(nums5);

        System.out.println("Input: [1,1,2,2,3,3]");
        System.out.println("Your Output Length: " + len5);
        System.out.println("Expected Length: 6");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len5; i++) {
            System.out.print(nums5[i]);
            if (i < len5 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [1,1,2,2,3,3]");
        System.out.println("----------------------------------");


        // Test Case 6 - All same elements
        int[] nums6 = {2, 2, 2, 2, 2};
        int len6 = removeDuplicates(nums6);

        System.out.println("Input: [2,2,2,2,2]");
        System.out.println("Your Output Length: " + len6);
        System.out.println("Expected Length: 2");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len6; i++) {
            System.out.print(nums6[i]);
            if (i < len6 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [2,2]");
        System.out.println("----------------------------------");
    }

    static public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int index = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[index - 2]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
