package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Problem316 {

    public static void main(String[] args) {
        String text = "india is my Country, all indians are my brothers and sisters";
        String words[] = text.split("\\s+");
        for (String word : words){
            System.out.println(word);
        }
        //System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }

    static public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        int[] lastOccurrence = new int[26];
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int visitorIndex = ch - 'a';
            if (!visited[visitorIndex]) {
                while (!stack.isEmpty() && stack.peek() > ch && lastOccurrence[stack.peek() - 'a'] > i) {
                    visited[stack.pop() - 'a'] = false;
                }
                stack.push(ch);
                visited[visitorIndex] = true;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : stack) {
            stringBuilder.append(ch);
        }

        return stringBuilder.toString();
    }
}
