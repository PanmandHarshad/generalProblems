package leetcode;

public class Problem1456 {
    public static void main(String[] args) {
        System.out.println(maxVowels("abciiidef", 3));
        System.out.println(maxVowels("aeiou", 2));
        System.out.println(maxVowels("leetcode", 3));
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

        // Slide the window over the string
        // Considering that we have moved the window by 1.
        // Here we will be adding the window from index 1 to index k
        // Subtracting the left most character (here index 0) from window.
        // Removing the count of subtracted character if that was vowel.
        for (int i = 0; i + k < s.length(); i++) {
            // Remove the effect of the character that is leaving the window
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                currentVowels--;
            }
            // Add the effect of the new character that enters the window
            char ch1 = s.charAt(i + k);
            if (isVowel(ch1)) {
                currentVowels++;
            }
            // Update the maximum vowels count
            maxVowels = Math.max(maxVowels, currentVowels);
        }

        return maxVowels;
    }


    static public int maxVowels1(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int maxCount = Integer.MIN_VALUE;

        for (int i = 0; i + k <= s.length(); i++) {
            int left = i;
            int right = i + k - 1;
            int count = 0;
            while (left < right) {
                if (isVowel(s.charAt(left))) {
                    count++;
                }
                if (isVowel(s.charAt(right))) {
                    count++;
                }
                left++;
                right--;
            }
            if (left == right) {
                if (isVowel(s.charAt(left)))
                    count++;
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    static private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
