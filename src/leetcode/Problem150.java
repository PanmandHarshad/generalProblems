package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem150 {
    static public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String str : tokens) {
            if (!str.equals("+") && !str.equals("-") && !str.equals("/") && !str.equals("*")) {
                stack.addLast(Integer.parseInt(str));
            } else {

                int val2 = stack.removeLast();
                int val1 = stack.removeLast();

                switch (str) {
                    case "+" -> stack.addLast(val1 + val2);
                    case "-" -> stack.addLast(val1 - val2);
                    case "/" -> stack.addLast(val1 / val2);
                    default -> stack.addLast(val1 * val2);
                }
            }
        }
        return stack.removeLast();
    }


    public static void main(String[] args) {
        // Test Case 1: Simple addition and multiplication
        // (2 + 1) * 3 = 9
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        int result1 = evalRPN(tokens1);
        System.out.println("Test 1: " + (result1 == 9 ? "PASSED" : "FAILED (Expected 9, got " + result1 + ")"));

        // Test Case 2: Handling division and subsequent addition
        // 4 + (13 / 5) = 4 + 2 = 6
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        int result2 = evalRPN(tokens2);
        System.out.println("Test 2: " + (result2 == 6 ? "PASSED" : "FAILED (Expected 6, got " + result2 + ")"));

        // Test Case 3: Complex operations with large numbers and negative results
        // ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        // = ((10 * (6 / (12 * -11))) + 17) + 5
        // = ((10 * (6 / -132)) + 17) + 5   -> (6 / -132 truncates to 0)
        // = ((10 * 0) + 17) + 5
        // = (0 + 17) + 5 = 22
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int result3 = evalRPN(tokens3);
        System.out.println("Test 3: " + (result3 == 22 ? "PASSED" : "FAILED (Expected 22, got " + result3 + ")"));

        // Test Case 4: Single element check
        String[] tokens4 = {"18"};
        int result4 = evalRPN(tokens4);
        System.out.println("Test 4: " + (result4 == 18 ? "PASSED" : "FAILED (Expected 18, got " + result4 + ")"));
    }

}
