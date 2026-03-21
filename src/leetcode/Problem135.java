package leetcode;

import java.util.Arrays;

public class Problem135 {
    public static void main(String[] args) {

        // Test Case 1
        int[] ratings1 = {1, 0, 2};
        int result1 = candy(ratings1);
        System.out.println("Input Ratings: " + Arrays.toString(ratings1));
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 5");
        System.out.println("----------------------------------");

        // Test Case 2 - All same ratings
        int[] ratings2 = {1, 1, 1};
        int result2 = candy(ratings2);
        System.out.println("Input Ratings: " + Arrays.toString(ratings2));
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");

        // Test Case 3 - Strictly increasing
        int[] ratings3 = {1, 2, 3, 4, 5};
        int result3 = candy(ratings3);
        System.out.println("Input Ratings: " + Arrays.toString(ratings3));
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 15");
        System.out.println("----------------------------------");

        // Test Case 4 - Strictly decreasing
        int[] ratings4 = {5, 4, 3, 2, 1};
        int result4 = candy(ratings4);
        System.out.println("Input Ratings: " + Arrays.toString(ratings4));
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 15");
        System.out.println("----------------------------------");

        // Test Case 5 - Mixed pattern
        int[] ratings5 = {1, 3, 2, 2, 1};
        int result5 = candy(ratings5);
        System.out.println("Input Ratings: " + Arrays.toString(ratings5));
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: 7");
        System.out.println("----------------------------------");

        // Test Case 6 - Single child
        int[] ratings6 = {10};
        int result6 = candy(ratings6);
        System.out.println("Input Ratings: " + Arrays.toString(ratings6));
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: 1");
        System.out.println("----------------------------------");

        // Test Case 7 - Plateau then peak
        int[] ratings7 = {1, 2, 2};
        int result7 = candy(ratings7);
        System.out.println("Input Ratings: " + Arrays.toString(ratings7));
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: 4");
        System.out.println("----------------------------------");
    }

    // Brute force
    static public int candy1(int[] ratings) {
        int length = ratings.length;
        int[] candies = new int[length];

        Arrays.fill(candies, 1);

        for (int i = 1; i < length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                int max = Math.max(candies[i], candies[i + 1] + 1);
                if (max > candies[i + 1])
                    candies[i] = candies[i + 1] + 1;
            }
        }

        int result = 0;
        for (int i = 0; i < length; i++) {
            result += candies[i];
        }

        return result;
    }

    // Optimized
    static public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0)
            return 0;

        int candies = 1;

        int up = 0, down = 0, peak = 0;

        for (int i = 1; i < n; i++) {

            if (ratings[i] > ratings[i - 1]) {
                up++;
                peak = up;
                down = 0;
                candies += 1 + up;
            } else if (ratings[i] == ratings[i - 1]) {
                up = down = peak = 0;
                candies += 1;
            } else {
                down++;
                up = 0;

                candies += 1 + down;

                // peak adjustment is only valid if
                // the current downhill is still inside the same mountain
                if (down <= peak) {
                    candies -= 1;
                }
            }
        }

        return candies;
    }
}
