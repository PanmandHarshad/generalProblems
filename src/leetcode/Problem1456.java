package leetcode;

import jdk.swing.interop.DispatcherWrapper;

public class Problem1456 {
    public static void main(String[] args) {
        System.out.println(maxVowels("abciiidef", 3));
        System.out.println(maxVowels("aeiou", 2));
        System.out.println(maxVowels("leetcode", 3));
        System.out.println(maxVowels("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33));
        System.out.println("--------------------------------");
        System.out.println(maxVowels1("abciiidef", 3));
        System.out.println(maxVowels1("aeiou", 2));
        System.out.println(maxVowels1("leetcode", 3));
        System.out.println(maxVowels1("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33));
    }

    static public int maxVowels(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int maxVowels = 0;
        int currentVowels = 0;

        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentVowels++;
            }
        }

        if (currentVowels >= k)
            return k;

        maxVowels = currentVowels;

        for (int i = 0; i + k < s.length(); i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                currentVowels--;
            }

            char ch1 = s.charAt(i + k);
            if (isVowel(ch1)) {
                currentVowels++;
            }

            maxVowels = Math.max(maxVowels, currentVowels);
        }

        return maxVowels;
    }

    // Most efficient solution
    static public int maxVowels1(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int length = s.length();
        int count = 0;
        int maxCount;

        int[] vowels = new int[128];
        vowels['a'] = 1;
        vowels['e'] = 1;
        vowels['i'] = 1;
        vowels['o'] = 1;
        vowels['u'] = 1;

        for (int i = 0; i < k; i++) {
            count += vowels[s.charAt(i)];
        }

        if (count >= k) {
            return k;
        }

        maxCount = count;

        for (int i = k; i < length; i++) {
            count += vowels[s.charAt(i)] - vowels[s.charAt(i - k)];

            if (count >= k)
                return k;

            if (count > maxCount)
                maxCount = count;
        }

        return maxCount;
    }

    static private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
