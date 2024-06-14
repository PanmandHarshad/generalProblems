package leetcode;

public class Problem1768 {

    public static void main(String[] args) {
        System.out.println(mergeAlternately("abc", "pqr"));
        System.out.println(mergeAlternately("ab", "pqrs"));
        System.out.println(mergeAlternately("abcd", "pq"));
    }

    static public String mergeAlternately(String word1, String word2) {

        int counter1 = 0;
        int length1 = word1.length();
        int length2 = word2.length();
        String longestString = length1 < length2 ? word2 : word1;

        StringBuilder stringBuilder = new StringBuilder();
        for (; counter1 < length2 && counter1 < length1; counter1++) {
            stringBuilder.append(word1.charAt(counter1)).append(word2.charAt(counter1));
        }

        if (counter1 <= longestString.length() - 1) {
            stringBuilder.append(longestString.substring(counter1));
        }

        return stringBuilder.toString();
    }
}
