package leetcode;

public class Problem409 {

    public static void main(String[] args) {
//        System.out.println(longestPalindrome("abccccdd"));
//        System.out.println(longestPalindrome("abccccddefg"));
//        System.out.println(longestPalindrome("bb"));
        System.out.println(longestPalindrome("ccc"));
    }

    static public int longestPalindrome(String s) {
        if (s != null && s.length() == 1) {
            return s.length();
        }

        int[] count = new int[128];
        int oddCount = 0;

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        int ans = 0;

        for (int i = 0; i < 128; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
            }
            ans += count[i] / 2 * 2;
        }

        return oddCount > 0 ? ans + 1 : ans;
    }
}
