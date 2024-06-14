package leetcode;

import java.util.Arrays;

public class Problem345 {

    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }

    static public String reverseVowels(String s) {
        int start = 0;
        int end = s.length() - 1;
        char[] arr = s.toCharArray();

        while (start < end) {
            char startingChar = arr[start];
            char endingChar = arr[end];
            if (startingChar == 'a' || startingChar == 'e' || startingChar == 'i' || startingChar == 'o' || startingChar == 'u') {
                if (endingChar == 'a' || endingChar == 'e' || endingChar == 'i' || endingChar == 'o' || endingChar == 'u') {
                    arr[start] = endingChar;
                    arr[end] = startingChar;
                    start++;
                    end--;
                }else {
                    end--;
                }
            } else {
                start++;
            }
        }

        return String.valueOf(arr);
    }
}
