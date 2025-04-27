package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem93 {

    public static void main(String[] args) {
        String s = "25525511135";
        for (String ip : restoreIpAddresses1(s)) {
            System.out.println(ip);
        }

    }

    // TC: O(n^3), SC: O(n^3)
    static public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        int length = s.length();
        if (length < 4 || length > 12) {
            return result;
        }

        for (int i = 1; i < length && i < 4; i++) {
            for (int j = i + 1; j < length && j < i + 4; j++) {
                for (int k = j + 1; k < length && k < j + 4; k++) {
                    String part1 = s.substring(0, i);
                    String part2 = s.substring(i, j);
                    String part3 = s.substring(j, k);
                    String part4 = s.substring(k);

                    if (isValid(part1) && isValid(part2) && isValid(part3) && isValid(part4)) {
                        result.add(part1 + "." + part2 + "." + part3 + "." + part4);
                    }
                }
            }
        }
        return result;
    }

    // Most efficient solution. TC: O(3^4), SC: O(N)
    // we can choose to take 1, 2, or 3 characters, and we have 4 positions in the IP address to fill.
    // Therefore, the complexity is constant (since there are only 4 segments to form).
    static public List<String> restoreIpAddresses1(String s) {
        List<String> result = new ArrayList<>();

        backtrack(result, s, new ArrayList<>(), 0);

        return result;
    }

    static private void backtrack(List<String> result, String s, List<String> current, int start) {
        // If we have 4 segments and we've used all characters, add to the result
        if (current.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {
            int newStart = start + len;
            if (newStart > s.length())
                break;

            String part = s.substring(start, newStart);

            if (isValid(part)) {
                current.add(part);
                backtrack(result, s, current, newStart);
                current.remove(current.size() - 1);
            }
        }
    }

    static private boolean isValid(String part) {
        if (part.length() > 1 && part.charAt(0) == '0') {
            return false;
        }

        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }
}
