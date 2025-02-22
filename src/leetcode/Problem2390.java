package leetcode;

public class Problem2390 {

    public static void main(String[] args) {
        System.out.println(removeStars("leet**cod*e"));
        System.out.println(removeStars("erase*****"));

        System.out.println(removeStars1("leet**cod*e"));
        System.out.println(removeStars1("erase*****"));
    }

    // Most efficient solution
    static public String removeStars(String s) {
        char[] arr = s.toCharArray();
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '*') {
                j--;
            } else {
                arr[j] = arr[i];
                j++;
            }
        }
        return new String(arr, 0, j);
    }

    static public String removeStars1(String s) {
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
