package leetcode;

import java.util.Arrays;

public class Problem134 {

    public static void main(String[] args) {

        // Test Case 1
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        int result1 = canCompleteCircuit(gas1, cost1);
        System.out.println("Input Gas: " + Arrays.toString(gas1));
        System.out.println("Input Cost: " + Arrays.toString(cost1));
        System.out.println("Your Output: " + result1);
        System.out.println("Expected Output: 3");
        System.out.println("----------------------------------");

        // Test Case 2 - No Solution
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        int result2 = canCompleteCircuit(gas2, cost2);
        System.out.println("Input Gas: " + Arrays.toString(gas2));
        System.out.println("Input Cost: " + Arrays.toString(cost2));
        System.out.println("Your Output: " + result2);
        System.out.println("Expected Output: -1");
        System.out.println("----------------------------------");

        // Test Case 3 - Start at index 0
        int[] gas3 = {5, 1, 2, 3, 4};
        int[] cost3 = {4, 4, 1, 5, 1};
        int result3 = canCompleteCircuit(gas3, cost3);
        System.out.println("Input Gas: " + Arrays.toString(gas3));
        System.out.println("Input Cost: " + Arrays.toString(cost3));
        System.out.println("Your Output: " + result3);
        System.out.println("Expected Output: 4");
        System.out.println("----------------------------------");

        // Test Case 4 - Single station
        int[] gas4 = {1};
        int[] cost4 = {1};
        int result4 = canCompleteCircuit(gas4, cost4);
        System.out.println("Input Gas: " + Arrays.toString(gas4));
        System.out.println("Input Cost: " + Arrays.toString(cost4));
        System.out.println("Your Output: " + result4);
        System.out.println("Expected Output: 0");
        System.out.println("----------------------------------");

        // Test Case 5 - Large numbers
        int[] gas5 = {100, 200, 300, 400};
        int[] cost5 = {150, 250, 200, 200};
        int result5 = canCompleteCircuit(gas5, cost5);
        System.out.println("Input Gas: " + Arrays.toString(gas5));
        System.out.println("Input Cost: " + Arrays.toString(cost5));
        System.out.println("Your Output: " + result5);
        System.out.println("Expected Output: 2");
        System.out.println("----------------------------------");
    }


    static public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;

        int totalFuel = 0;
        int currentFuel = 0;
        int start = 0;

        for (int i = 0; i < length; i++) {
            int net = gas[i] - cost[i];

            totalFuel += net;
            currentFuel += net;

            if (currentFuel < 0) {
                start = i + 1;
                currentFuel = 0;
            }
        }
        return totalFuel >= 0 ? start : -1;
    }
}
