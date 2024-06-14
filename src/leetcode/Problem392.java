package leetcode;

public class Problem392 {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isSubsequence("axc", "ahbgdc"));
    }

    static public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        if (s.isBlank()) {
            return true;
        }

        int sourceLeft = 0;
        int targetLeft = 0;
        while (sourceLeft < s.length() && targetLeft < t.length()) {
            if (s.charAt(sourceLeft) == t.charAt(targetLeft)) {
                sourceLeft++;
            }
            targetLeft++;
        }

        return sourceLeft == s.length();
    }
}
