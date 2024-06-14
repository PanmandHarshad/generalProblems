package leetcode;

public class Problem5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abrbadaadab"));
    }

    static public String longestPalindrome(String str) {
        int length = str.length();
        if (length <= 1) {
            return str;
        }

        String LPS = "";
        for (int i = 0; i < length - 1; i++) {
            // Check for odd-length palindromes
            String oddPalindrome = findPalindrome(str, i, i);
            if (oddPalindrome.length() > LPS.length()) {
                LPS = oddPalindrome;
            }

            // Check for even-length palindromes
            String evenPalindrome = findPalindrome(str, i, i + 1);
            if (evenPalindrome.length() > LPS.length()) {
                LPS = evenPalindrome;
            }
        }
        return LPS;
    }

    static private String findPalindrome(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }
}
