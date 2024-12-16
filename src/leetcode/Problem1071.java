package leetcode;

public class Problem1071 {

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABABAB", "ABAB"));
        System.out.println(gcdOfStrings("abcabc", "abc"));
        System.out.println(gcdOfStrings("ababab", "ab"));
        System.out.println(gcdOfStrings("leet", "code"));
        System.out.println(gcdOfStrings("abcabcabca", "abc"));

        System.out.println(gcdOfStrings1("ABABAB", "ABAB"));
        System.out.println(gcdOfStrings1("abcabc", "abc"));
        System.out.println(gcdOfStrings1("ababab", "ab"));
        System.out.println(gcdOfStrings1("leet", "code"));
        System.out.println(gcdOfStrings1("abcabcabca", "abc"));
    }

    static public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int gcdLength = gcd(str1.length(), str2.length());

        return str1.substring(0, gcdLength);
    }

    static private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static public String gcdOfStrings1(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        if (str1.length() == str2.length()) {
            return str1;
        } else if (str1.length() > str2.length()) {
            return gcdOfStrings1(str1.substring(str2.length()), str2);
        } else {
            return gcdOfStrings1(str1, str2.substring(str1.length()));
        }
    }
}
