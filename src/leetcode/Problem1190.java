package leetcode;

import java.util.Stack;

public class Problem1190 {

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(abcd)"));
        System.out.println(reverseParentheses("(u(love)i)"));
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
    }

    // "(ed(et(oc))el)" => "(ed(etco)el)"
    // "(ed(etco)el)" => "(edocteel)"
    // "(edocteel)" => "(leetcode)"
    //
    static public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stringBuilder.append(stack.pop());
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                }

                for (char c : stringBuilder.toString().toCharArray()) {
                    stack.push(c);
                }
            } else {
                stack.push(ch);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }
}
