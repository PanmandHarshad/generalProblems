package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem22 {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(2);
        for (String str : strings) {
            System.out.println(str);
        }
    }

    static public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return List.of();
        }
        if (n == 1) {
            return List.of("()");
        }

        List<String> list = new ArrayList<>();
        helper("", n, n, list);
        return list;
    }

    private static void helper(String currStr, int open, int close, List<String> result) {
        if (open == 0 && close == 0) {
            result.add(currStr);
            return;
        }

        if (open > 0) {
            helper(currStr + "(", open - 1, close, result);
        }

        if (close > open) {
            helper(currStr + ")", open, close - 1, result);
        }
    }
}
