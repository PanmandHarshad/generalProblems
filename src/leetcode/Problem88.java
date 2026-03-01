package leetcode;

public class Problem88 {

    public static void main(String[] args) {

        // -------- Sample Test Case 1 --------
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.print("Output: ");
        for (int num : nums1) {
            System.out.print(num + " ");
        }

        System.out.println("\nExpected: 1 2 2 3 5 6");

        // -------- Sample Test Case 2 --------
        int[] nums3 = {4, 5, 6, 0, 0, 0};
        int m2 = 3;
        int[] nums4 = {1, 2, 3};
        int n2 = 3;

        merge(nums3, m2, nums4, n2);

        System.out.print("\nOutput: ");
        for (int num : nums3) {
            System.out.print(num + " ");
        }

        System.out.println("\nExpected: 1 2 3 4 5 6");

        // -------- Sample Test Case 3 --------
        int[] nums5 = {0};
        int m3 = 0;
        int[] nums6 = {1};
        int n3 = 1;

        merge(nums5, m3, nums6, n3);

        System.out.print("\nOutput: ");
        for (int num : nums5) {
            System.out.print(num + " ");
        }

        System.out.println("\nExpected: 1");

        // -------- Sample Test Case 4 --------
        int[] nums7 = {1};
        int m4 = 1;
        int[] nums8 = {};
        int n4 = 0;

        merge(nums7, m4, nums8, n4);

        System.out.print("\nOutput: ");
        for (int num : nums7) {
            System.out.print(num + " ");
        }

        System.out.println("\nExpected: 1");
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int resultLength = nums1.length - 1;

        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[resultLength--] = nums1[m--];
            } else {
                nums1[resultLength--] = nums2[n--];
            }
        }

        while (n >= 0) {
            nums1[resultLength--] = nums2[n--];
        }
    }
}
