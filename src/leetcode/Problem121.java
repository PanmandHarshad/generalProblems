package leetcode;

public class Problem121 {

    public static void main(String[] args) {

        // Test Case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = maxProfit(prices1);

        System.out.println("Input: [7,1,5,3,6,4]");
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 5");
        System.out.println("----------------------------------");


        // Test Case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        int result2 = maxProfit(prices2);

        System.out.println("Input: [7,6,4,3,1]");
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");


        // Test Case 3 - Single Day
        int[] prices3 = {5};
        int result3 = maxProfit(prices3);

        System.out.println("Input: [5]");
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");


        // Test Case 4 - Increasing Prices
        int[] prices4 = {1, 2, 3, 4, 5};
        int result4 = maxProfit(prices4);

        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 4");
        System.out.println("----------------------------------");


        // Test Case 5 - Decreasing Prices
        int[] prices5 = {9, 8, 7, 6, 5};
        int result5 = maxProfit(prices5);

        System.out.println("Input: [9,8,7,6,5]");
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");


        // Test Case 6 - Profit at End
        int[] prices6 = {3, 2, 6, 1, 8};
        int result6 = maxProfit(prices6);

        System.out.println("Input: [3,2,6,1,8]");
        System.out.println("Your Output: " + result6);
        System.out.println("Expected Output: 7");
        System.out.println("----------------------------------");


        // Test Case 7 - All Same Prices
        int[] prices7 = {4, 4, 4, 4};
        int result7 = maxProfit(prices7);

        System.out.println("Input: [4,4,4,4]");
        System.out.println("Your Output: " + result7);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");
    }

    static public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                profit = Math.max(profit, price - minPrice);
            }
        }
        return profit;
    }
}
