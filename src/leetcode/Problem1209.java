package leetcode;

import java.util.Stack;

public class Problem1209 {

    public static void main(String[] args) {
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2));
        System.out.println(removeDuplicates("abcd", 2));
    }

    static public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>(); // Stack to store [character, frequency]

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                stack.peek()[1]++;
                if (stack.peek()[1] == k) {
                    stack.pop();
                }
            } else {
                stack.push(new int[]{c, 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            for (int i = 0; i < top[1]; i++) {
                sb.append((char) top[0]);
            }
        }

        return sb.reverse().toString();
    }

    static public String removeDuplicates1(String s, int k) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                countStack.push(countStack.pop() + 1);
                if (countStack.peek() == k) {
                    stack.pop();
                    countStack.pop();
                }
            } else {
                stack.push(ch);
                countStack.push(1);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            int count = countStack.pop();
            while (count > 0) {
                sb.append(ch);
                count--;
            }
        }

        return sb.reverse().toString();
    }
}
