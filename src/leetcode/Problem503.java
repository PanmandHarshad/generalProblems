package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Problem503 {

    public static void main(String[] args) {
        printArray(nextGreaterElements(new int[]{1, 2, 1})); // expected [2,-1,2]
        printArray(nextGreaterElements(new int[]{5, 4, 3, 2, 1})); // expected [-1,5,5,5,5]
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j]);
            if (j < arr.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }

    static public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        Arrays.fill(result, -1);

        // Traverse twice from end to start to simulate circular array
        for (int i = 2 * n - 1; i >= 0; i--) {
            int currentIndex = i % n;
            int current = nums[currentIndex];

            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result[currentIndex] = stack.peek();
            }

            stack.push(current);
        }

        return result;
    }

}
