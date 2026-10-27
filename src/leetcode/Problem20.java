package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem20 {
    static public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    if (ch == ')' && stack.peek() == '(') {
                        stack.pop();
                    } else if (ch == ']' && stack.peek() == '[') {
                        stack.pop();
                    } else if (ch == '}' && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    static public boolean isValid1(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        // ([{}])
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
