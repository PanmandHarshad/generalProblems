package leetcode;

public class Problem344 {

    public static void main(String[] args) {
        char[] charArray = "hello".toCharArray();
        reverseString(charArray);
        System.out.println(charArray);
    }

    static public void reverseString(char[] strArr) {
        if (strArr == null || strArr.length == 1) {
            return;
        }

        int start = 0;
        int end = strArr.length - 1;

        while (start < end) {
            char temp = strArr[start];
            strArr[start] = strArr[end];
            strArr[end] = temp;
            start++;
            end--;
        }
    }
}
