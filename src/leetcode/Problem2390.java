package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Problem2390 {

    public static void main(String[] args) {
        System.out.println(removeStars("leet**cod*e"));
        System.out.println(removeStars("erase*****"));
    }

    static public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        int stars = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            char ch = s.charAt(i);
            if (ch == '*') {
                ++stars;
            } else if (stars > 0) {
                --stars;
            } else {
                sb.append(ch);
            }
        }
        return sb.reverse().toString();
    }
}
