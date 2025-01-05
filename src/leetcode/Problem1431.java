package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem1431 {

    public static void main(String[] args) {
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;

        // Function call and output
        List<Boolean> result = kidsWithCandies(candies, extraCandies);
        System.out.println("Result: " + result);

        // Additional test cases
        System.out.println("Test 1: " + kidsWithCandies(new int[]{4, 2, 1, 1, 2}, 1));
        System.out.println("Test 2: " + kidsWithCandies(new int[]{12, 1, 12}, 10));
    }

    static public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        for(int candy: candies){
            maxCandies = Math.max(maxCandies, candy);
        }

        List<Boolean> result = new ArrayList<>();
        for (int candy: candies){
            result.add((candy + extraCandies) >= maxCandies);
        }
        return result;
    }
}
