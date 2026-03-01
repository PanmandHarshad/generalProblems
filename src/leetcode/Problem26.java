package leetcode;

public class Problem26 {
    public static void main(String[] args) {

        // ---------- Test Case 1 ----------
        int[] nums1 = {1, 1, 2};
        int len1 = removeDuplicates(nums1);

        System.out.println("Input: [1,1,2]");
        System.out.println("Your Output Length: " + len1);
        System.out.println("Expected Length: 2");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len1; i++) {
            System.out.print(nums1[i]);
            if (i < len1 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [1,2]");
        System.out.println("----------------------------------");


        // ---------- Test Case 2 ----------
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len2 = removeDuplicates(nums2);

        System.out.println("Input: [0,0,1,1,1,2,2,3,3,4]");
        System.out.println("Your Output Length: " + len2);
        System.out.println("Expected Length: 5");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len2; i++) {
            System.out.print(nums2[i]);
            if (i < len2 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [0,1,2,3,4]");
        System.out.println("----------------------------------");


        // ---------- Test Case 3 ----------
        int[] nums3 = {1, 2, 3, 4, 5};
        int len3 = removeDuplicates(nums3);

        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("Your Output Length: " + len3);
        System.out.println("Expected Length: 5");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len3; i++) {
            System.out.print(nums3[i]);
            if (i < len3 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [1,2,3,4,5]");
        System.out.println("----------------------------------");


        // ---------- Test Case 4 ----------
        int[] nums4 = {2, 2, 2, 2, 2};
        int len4 = removeDuplicates(nums4);

        System.out.println("Input: [2,2,2,2,2]");
        System.out.println("Your Output Length: " + len4);
        System.out.println("Expected Length: 1");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len4; i++) {
            System.out.print(nums4[i]);
            if (i < len4 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [2]");
        System.out.println("----------------------------------");


        // ---------- Test Case 5 ----------
        int[] nums5 = {10};
        int len5 = removeDuplicates(nums5);

        System.out.println("Input: [10]");
        System.out.println("Your Output Length: " + len5);
        System.out.println("Expected Length: 1");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len5; i++) {
            System.out.print(nums5[i]);
            if (i < len5 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [10]");
        System.out.println("----------------------------------");


        // ---------- Test Case 6 ----------
        int[] nums6 = {-3, -3, -2, -1, -1, 0, 0, 1};
        int len6 = removeDuplicates(nums6);

        System.out.println("Input: [-3,-3,-2,-1,-1,0,0,1]");
        System.out.println("Your Output Length: " + len6);
        System.out.println("Expected Length: 5");

        System.out.print("Your Output Array: [");
        for (int i = 0; i < len6; i++) {
            System.out.print(nums6[i]);
            if (i < len6 - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected Array: [-3,-2,-1,0,1]");
        System.out.println("----------------------------------");


        // ---------- Test Case 7 ----------
        int[] nums7 = {};
        int len7 = removeDuplicates(nums7);

        System.out.println("Input: []");
        System.out.println("Your Output Length: " + len7);
        System.out.println("Expected Length: 0");

        System.out.println("Your Output Array: []");
        System.out.println("Expected Array: []");
        System.out.println("----------------------------------");
    }

    /*
    Input: nums = [1,1,2]
    Output: 2, nums = [1,2,_]

    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     */
    static public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        int numLength = nums.length;
        int firstIndex = 0;
        int secondIndex = 1;

        while (secondIndex < numLength) {
            if (nums[firstIndex] == nums[secondIndex]) {
                secondIndex++;
            } else {
                nums[++firstIndex] = nums[secondIndex++];
            }
        }
        return firstIndex + 1;
    }

}
