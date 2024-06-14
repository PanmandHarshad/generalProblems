public class LongestPalindromeSubString {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abhhjjjghgjjjhhhjjiiuumv"));
    }

    static private String longestPalindrome(String str) {

        int start = 0;
        int end = 0;

        for (int i = 0; i < str.length(); i++) {
            int length1 = getPalindromeLength(str, i, i);
            int length2 = getPalindromeLength(str, i, i + 1);
            int maxLen = Math.max(length2, length1);

            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        return str.substring(start, end + 1);
    }

    private static int getPalindromeLength(String str, int left, int right) {

        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
