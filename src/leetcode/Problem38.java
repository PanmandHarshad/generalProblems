package leetcode;

public class Problem38 {

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

    static public String countAndSay(int n) {

        if (n == 1) {
            return "1";
        }

        String result = "1";
        for (int i = 1; i < n; i++) {
            result = sequenceGenerator(result);
        }
        return result;
    }

    static private String sequenceGenerator(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        char previous = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == previous) {
                count++;
            } else {
                stringBuilder.append(count);
                stringBuilder.append(previous);
                previous = str.charAt(i);
                count = 1;
            }
        }
        stringBuilder.append(count);
        stringBuilder.append(previous);
        return stringBuilder.toString();
    }
}
