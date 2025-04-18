package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem496 {

    public static void main(String[] args) {

        int[][] nums1Tests = {
                {4, 1, 2},
                {2, 4},
                {1, 3, 5, 2, 4},
                {1},
                {},
                {6, 7, 8}
        };

        int[][] nums2Tests = {
                {1, 3, 4, 2},
                {1, 2, 3, 4},
                {6, 5, 4, 3, 2, 1, 7},
                {1, 2, 3},
                {1, 2, 3},
                {5, 6, 7, 8, 9}
        };

        for (int i = 0; i < nums1Tests.length; i++) {
            int[] result = nextGreaterElement1(nums1Tests[i], nums2Tests[i]);

            System.out.print("Test case " + (i + 1) + ": ");
            System.out.print("nums1 = ");
            printArray(nums1Tests[i]);
            System.out.print(", nums2 = ");
            printArray(nums2Tests[i]);
            System.out.print(" => Result = ");
            printArray(result);
            System.out.println();
        }
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j]);
            if (j < arr.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }

    static public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    boolean found = false;
                    for (int k = j + 1; k < nums2.length; k++) {
                        if (nums2[k] > nums2[j]) {
                            answer[i] = nums2[k];
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        answer[i] = -1;
                    }
                    break;
                }
            }
        }
        return answer;
    }

    // Most efficient solution O(n)
    static public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            answer[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }

        return answer;
    }
}

