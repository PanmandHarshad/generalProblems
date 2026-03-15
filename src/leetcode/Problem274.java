package leetcode;

import java.util.Arrays;

public class Problem274 {

    public static void main(String[] args) {

        // Test Case 1
        int[] citations1 = {3, 0, 6, 1, 5};
        int result1 = hIndex(citations1);

        System.out.println("Input: [3,0,6,1,5]");
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");


        // Test Case 2 - All Zeros
        int[] citations2 = {0, 0, 0};
        int result2 = hIndex(citations2);

        System.out.println("Input: [0,0,0]");
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Paper
        int[] citations3 = {10};
        int result3 = hIndex(citations3);

        System.out.println("Input: [10]");
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 1");
        System.out.println("----------------------------------");


        // Test Case 4 - Increasing Order
        int[] citations4 = {1, 2, 3, 4, 5};
        int result4 = hIndex(citations4);

        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");


        // Test Case 5 - Large Citations
        int[] citations5 = {10, 8, 5, 4, 3};
        int result5 = hIndex(citations5);

        System.out.println("Input: [10,8,5,4,3]");
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: 4");
        System.out.println("----------------------------------");


        // Test Case 6 - Mixed Case
        int[] citations6 = {4, 4, 0, 0};
        int result6 = hIndex(citations6);

        System.out.println("Input: [4,4,0,0]");
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");


        // Test Case 7 - Another Mixed Case
        int[] citations7 = {1, 3, 1};
        int result7 = hIndex(citations7);

        System.out.println("Input: [1,3,1]");
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: 1");
        System.out.println("----------------------------------");
    }

    static public int hIndex(int[] citations) {
        Arrays.sort(citations);

        // Reverse the array to make it descending
        int length = citations.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = citations[i];
            citations[i] = citations[length - 1 - i];
            citations[length - 1 - i] = temp;
        }

        int hIndex = 0;

        for (int i = 0; i < length; i++) {
            if (citations[i] >= i + 1) {
                hIndex = i + 1;
            }
        }

        return hIndex;
    }
}
