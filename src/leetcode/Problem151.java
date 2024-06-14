package leetcode;

public class Problem151 {
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello world  "));
    }

    static public String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        String[] strs = s.split("\\s+");
        if (strs.length == 1) {
            return strs[0];
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            stringBuilder.append(strs[i]).append(" ");
        }

        return stringBuilder.toString().trim();
    }
}
