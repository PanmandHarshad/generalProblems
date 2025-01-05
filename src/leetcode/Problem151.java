package leetcode;

public class Problem151 {
    public static void main(String[] args) {
//        System.out.println(reverseWords("the sky is blue"));
//        System.out.println(reverseWords("  hello  world  "));
//        System.out.println(reverseWords("a good   example"));
//
//        System.out.println(reverseWords1("the sky is blue"));
//        System.out.println(reverseWords1("  hello  world  "));
        System.out.println(reverseWords2("  a good   example  "));
    }

    static public String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        String[] strs = s.split("\\s+");
        if (strs.length == 1) {
            return strs[0];
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            stringBuilder.append(strs[i]).append(" ");
        }

        return stringBuilder.toString().trim();
    }

    static public String reverseWords1(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        int length = s.length();
        boolean wordFound = false;

        // Step 1: Traverse the string in reverse to handle leading spaces
        for (int i = length - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);

            // If it's a space, and we already have a word collected
            if (currentChar == ' ') {
                if (wordFound) {
                    result.append(word.reverse()).append(" "); // Add the word to result
                    word.setLength(0); // Clear word for next use
                    wordFound = false;
                }
            } else {
                word.append(currentChar); // Add characters to word
                wordFound = true;
            }
        }

        // Step 2: Add the last word (if present)
        if (wordFound) {
            result.append(word.reverse());
        }

        // Step 3: Remove the trailing space if present
        int end = result.length();
        if (end > 0 && result.charAt(end - 1) == ' ') {
            result.deleteCharAt(end - 1);
        }

        return result.toString();
    }

    // Most efficient solution
    static public String reverseWords2(String str) {
        char[] chars = str.toCharArray();
        // Step 1: reverse entire string
        // ex: "  India is  my country   " => "   yrtnuoc ym  si aidnI  "
        reverseString(chars, 0, chars.length - 1);

        // Step 2: reverse the words
        // ex: "   yrtnuoc ym  si aidnI  " => "   country my  is India  "
        reverseWords(chars);

        // Remove spaces
        // ex: "   country my  is India  " => "country my is India"
        return removeSpaces(chars);
    }

    private static void reverseString(char[] chars, int left, int right) {
        while (left < right) {
            char ch = chars[left];
            chars[left] = chars[right];
            chars[right] = ch;
            left++;
            right--;
        }
    }

    private static void reverseWords(char[] chars) {
        int left = 0;
        int right = 0;

        while (left < chars.length) {
            while (left < chars.length && chars[left] == ' ') {
                left++;
            }

            right = left;

            while (right < chars.length && chars[right] != ' ') {
                right++;
            }

            reverseString(chars, left, right - 1);
            left = right;
        }
    }

    private static String removeSpaces(char[] chars) {
        int left = 0;
        int right = 0;

        while (right < chars.length) {
            while (right < chars.length && chars[right] == ' ')
                right++;

            while (right < chars.length && chars[right] != ' ') {
                chars[left] = chars[right];
                left++;
                right++;
            }

            while (right < chars.length && chars[right] == ' ')
                right++;

            if (right < chars.length) {
                chars[left] = ' ';
                left++;
            }
        }
        return new String(chars).substring(0, left);
    }
}
