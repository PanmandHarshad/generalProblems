package leetcode;

public class Problem238 {
    public static void main(String[] args) {
        int[] ints = productExceptSelf1(new int[]{1, 2, 3, 4});
        for (int num : ints) {
            System.out.print(num + ", ");
        }
        System.out.println();
        int[] ints1 = productExceptSelf1(new int[]{-1, 1, 0, -3, 3});
        for (int num : ints1) {
            System.out.print(num + ", ");
        }
    }

    static public int[] productExceptSelf(int[] nums) {
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
    static public int[] productExceptSelf1(int[] nums) {
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
