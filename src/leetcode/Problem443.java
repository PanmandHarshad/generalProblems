package leetcode;

public class Problem443 {

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(compress(new char[]{'a'}));
        System.out.println(compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }

    // Time complexity: O(n), Space complexity: O(log n)
    static public int compress(char[] chars) {
        int length = chars.length;
        if (length == 1) {
            return 1;
        }

        int index = 0;
        int i = 0;

        while (i < length) {
            char currentChar = chars[i];
            int count = 0;

            while (i < length && currentChar == chars[i]) {
                count++;
                i++;
            }

            chars[index++] = currentChar;

            if (count > 1) {
                for (char ch : String.valueOf(count).toCharArray()) {
                    chars[index++] = ch;
                }
            }
        }
        return index;
    }
}
