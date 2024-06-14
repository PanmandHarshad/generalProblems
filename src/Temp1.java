import java.util.HashMap;
import java.util.Map;

public class Temp1 {

    // Department wise average salary
    public static void main(String[] args) {
        System.out.println(sortString("aaaabbbbcccc"));
        System.out.println(sortString("rat"));
    }

    static public String sortString(String str) {
        int[] charCount = new int[26];
        for (char ch : str.toCharArray()) {
            charCount[ch - 'a']++;
        }

        StringBuilder result = new StringBuilder();
        while (result.length() < str.length()) {
            for (int i = 0; i < 26; i++) {
                if (charCount[i] > 0) {
                    result.append((char) ('a' + i));
                    charCount[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if (charCount[i] > 0) {
                    result.append((char) ('a' + i));
                    charCount[i]--;
                }
            }
        }
        return result.toString();
    }
}
