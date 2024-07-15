package leetcode;

import java.util.Stack;

public class Problem1717 {
    public static void main(String[] args) {
        System.out.println(maximumGain("cdbcbbaaabab", 4, 5)); // Output: 19
        System.out.println(maximumGain("aabbaaxybbaabb", 5, 4)); // Output: 20
    }

    static public int maximumGain(String str, int x, int y) {
        if (str == null || str.isBlank()) {
            return 0;
        }

        int sum = 0;

        Stack<Character> stack1 = new Stack<>();
        if (x > y) {
            // "ab" => x
            // "ba" => y
            sum = getSequencedSum(str, x, stack1, 'a', 'b');
            sum += getStackedSum(stack1, y, 'b', 'a');
        } else {
            sum = getSequencedSum(str, y, stack1, 'b', 'a');
            sum += getStackedSum(stack1, x, 'a', 'b');
        }

        return sum;
    }

    private static int getStackedSum(Stack<Character> stack1, int value, char firstChar, char secondChar) {
        int sum = 0;
        Stack<Character> stack2 = new Stack<>();
        for (Character ch : stack1) {
            if (ch == secondChar) {
                if (!stack2.isEmpty() && stack2.peek() == firstChar) {
                    sum += value;
                    stack2.pop();
                } else {
                    stack2.push(ch);
                }
            } else {
                stack2.push(ch);
            }
        }
        return sum;
    }

    private static int getSequencedSum(String s, int value, Stack<Character> stackBA, char firstChar, char secondChar) {
        int sum = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == secondChar) {
                if (!stackBA.isEmpty() && stackBA.peek() == firstChar) {
                    sum += value;
                    stackBA.pop();
                } else {
                    stackBA.push(ch);
                }
            } else {
                stackBA.push(ch);
            }
        }
        return sum;
    }
}
