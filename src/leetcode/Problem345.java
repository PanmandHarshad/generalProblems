package leetcode;

public class Problem345 {

    public static void main(String[] args) {
        System.out.println(reverseVowels("IceCreAm"));
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));

        System.out.println(reverseVowels1("IceCreAm"));
        System.out.println(reverseVowels1("hello"));
        System.out.println(reverseVowels1("leetcode"));

        System.out.println(reverseVowels2("IceCreAm"));
        System.out.println(reverseVowels2("hello"));
        System.out.println(reverseVowels2("leetcode"));
    }

    // Most efficient solution
    static public String reverseVowels(String s) {
        int start = 0;
        int end = s.length() - 1;
        char[] arr = s.toCharArray();

        while (start < end) {
            char startingChar = arr[start];
            char endingChar = arr[end];
            if (startingChar == 'a' || startingChar == 'e' || startingChar == 'i' || startingChar == 'o' || startingChar == 'u' ||
                    startingChar == 'A' || startingChar == 'E' || startingChar == 'I' || startingChar == 'O' || startingChar == 'U') {
                if (endingChar == 'a' || endingChar == 'e' || endingChar == 'i' || endingChar == 'o' || endingChar == 'u' ||
                        endingChar == 'A' || endingChar == 'E' || endingChar == 'I' || endingChar == 'O' || endingChar == 'U') {
                    arr[start] = endingChar;
                    arr[end] = startingChar;
                    start++;
                }
                end--;
            } else {
                start++;
            }
        }

        return String.valueOf(arr);
    }

    static public String reverseVowels1(String s) {
        int start = 0;
        int end = s.length() - 1;
        char[] charArray = s.toCharArray();
        while (start < end) {
            boolean startCharVowel = isVowel(s.charAt(start));
            boolean endCharVowel = isVowel(s.charAt(end));
            if (startCharVowel && endCharVowel) {
                charArray[start] = s.charAt(end);
                charArray[end] = s.charAt(start);
                start++;
                end--;
            } else if (!startCharVowel) {
                start++;
            } else {
                end--;
            }
        }
        return String.valueOf(charArray);
    }

    static public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

    static public String reverseVowels2(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int left = 0;
        int right = s.length() - 1;

        String vowels = "aeiouAEIOU";
        char[] charArray = s.toCharArray();
        while (left < right) {

            while (left < right && vowels.indexOf(s.charAt(left)) == -1) {
                left++;
            }

            while (left < right && vowels.indexOf(s.charAt(right)) == -1) {
                right--;
            }

            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            left++;
            right--;
        }

        return String.valueOf(charArray);
    }
}