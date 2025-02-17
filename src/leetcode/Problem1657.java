package leetcode;

import java.util.Arrays;

public class Problem1657 {

    public static void main(String[] args) {
        System.out.println(closeStrings("abc", "bca"));
        System.out.println(closeStrings("a", "aa"));
        System.out.println(closeStrings("cabbba", "abbccc"));
        System.out.println(closeStrings("cabbba", "aabbss"));
        System.out.println(closeStrings("abbzzca", "babzzcz"));
    }

    static public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        if (word1.equals(word2))
            return true;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        boolean[] exist1 = new boolean[26];
        boolean[] exist2 = new boolean[26];

        for (char ch : word1.toCharArray()) {
            freq1[ch - 'a']++;
            exist1[ch - 'a'] = true;
        }

        for (char ch : word2.toCharArray()) {
            freq2[ch - 'a']++;
            exist2[ch - 'a'] = true;
        }

        for (int i = 0; i < 26; i++) {
            if (exist1[i] != exist2[i]) {
                return false;
            }
        }

        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
}
