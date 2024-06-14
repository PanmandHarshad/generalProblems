package leetcode;

import java.util.Stack;

public class Problem394 {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]")); // Output: aaabcbc
        System.out.println(decodeString("3[a2[c]]")); // Output: accaccacc
    }

    public static String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0'); // Build the number (support for multi-digit numbers)
            } else if (ch == '[') {
                numStack.push(k);
                strStack.push(currentString);
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                int repeatTimes = numStack.pop();
                StringBuilder temp = currentString;
                currentString = strStack.pop();
                while (repeatTimes-- > 0) {
                    currentString.append(temp);
                }
            } else {
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}
