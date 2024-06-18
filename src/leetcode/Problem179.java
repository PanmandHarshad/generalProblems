package leetcode;

import java.util.Arrays;

public class Problem179 {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{10, 2}));
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    private static void merge(String[] strs, int start, int mid, int end) {
        int leftArrLength = mid - start + 1;
        int rightArrLength = end - mid;

        String[] leftArray = new String[leftArrLength];
        String[] rightArray = new String[rightArrLength];

        System.arraycopy(strs, start, leftArray, 0, leftArrLength);
        System.arraycopy(strs, mid + 1, rightArray, 0, rightArrLength);

        int index1 = 0, index2 = 0;
        int counter = start;

        while (index1 < leftArrLength && index2 < rightArrLength) {
            String combination1 = leftArray[index1] + rightArray[index2];
            String combination2 = rightArray[index2] + leftArray[index1];

            if (combination1.compareTo(combination2) > 0) {
                strs[counter++] = leftArray[index1++];
            } else {
                strs[counter++] = rightArray[index2++];
            }
        }

        while (index1 < leftArrLength) {
            strs[counter++] = leftArray[index1++];
        }

        while (index2 < rightArrLength) {
            strs[counter++] = rightArray[index2++];
        }
    }

    private static void divide(String[] nums, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            divide(nums, start, mid);
            divide(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    static public String largestNumber1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        // Convert the integer array to a string array
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        divide(strs, 0, nums.length - 1);

        if (strs[0].equals("0")) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }

    static public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        int length = nums.length;
        String[] numbStrArr = new String[length];
        for (int i = 0; i < length; i++) {
            numbStrArr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numbStrArr, (a, b) -> {
            String combination1 = a + b;
            String combination2 = b + a;
            return combination2.compareTo(combination1);
        });

        if (numbStrArr[0].equals("0")) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String str : numbStrArr) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }
}
