package leetcode;

public class Problem283 {
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0, 3, 12};
        moveZeroes(arr1);
        printData(arr1);

        int[] arr2 = {0};
        moveZeroes(arr2);
        printData(arr2);

        int[] arr3 = {2, 1};
        moveZeroes(arr3);
        printData(arr3);
    }

    private static void printData(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Most efficient solution
    static public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}

