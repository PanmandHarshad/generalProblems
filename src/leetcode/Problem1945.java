package leetcode;

public class Problem1945 {
    public static void main(String[] args) {
        System.out.println(getLucky("leetcode", 2));
    }

    static public int getLucky(String s, int k) {
        StringBuilder numericStr = new StringBuilder();
        for (char c : s.toCharArray()) {
            numericStr.append(c - 'a' + 1);
        }

        String transformedStr = numericStr.toString();
        for (int i = 0; i < k; i++) {
            transformedStr = sumOfDigits(transformedStr);
        }

        return Integer.parseInt(transformedStr);
    }

    static private String sumOfDigits(String numStr) {
        int sum = 0;
        for (char c : numStr.toCharArray()) {
            sum += c - '0';
        }
        return String.valueOf(sum);
    }

}
