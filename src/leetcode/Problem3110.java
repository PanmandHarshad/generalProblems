package leetcode;

public class Problem3110 {

    public static void main(String[] args) {
        System.out.println(scoreOfString("hello"));
        System.out.println(scoreOfString("zaz"));
    }

    static public int scoreOfString(String s) {
        int sum = 0;

        for (int i = 1; i < s.length(); i++) {
            sum = sum + Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return sum;
    }
}
