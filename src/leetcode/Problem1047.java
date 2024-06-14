package leetcode;

import java.util.Stack;

public class Problem1047 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbaca"));
        System.out.println(removeDuplicates("azxxzy"));
        System.out.println(removeDuplicates("abcd"));
    }

    static public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        int i = -1;

        for (char c : chars) {
            if (i < 0 || chars[i] != c) {
                i++;
                chars[i] = c;
            } else {
                i--;
            }
        }

        return new String(chars, 0, i + 1);
    }

    static public String removeDuplicates1(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            sb.append(ch);
        }

        return sb.reverse().toString();
    }
}
