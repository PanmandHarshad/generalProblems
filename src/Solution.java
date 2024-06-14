public class Solution {

    public static String maxValue(String n, int x) {
        char digit = (char) ('0' + x);
        int length = n.length();
        StringBuilder result = new StringBuilder(n);
        int index = 0;
        if (n.charAt(0) == '-') {
            while (index < length && n.charAt(index) <= digit) {
                index++;
            }
        } else {
            while (index < length && n.charAt(index) >= digit) {
                index++;
            }
        }
        result.insert(index, digit);
        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
//        System.out.println(maxValue("99", 9));
//        System.out.println(maxValue("268", 5));   // Output: 5268
        System.out.println(maxValue("-432", 5));
        System.out.println(maxValue("432",5));
        System.out.println(maxValue("670", 5));   // Output: 6750
        System.out.println(maxValue("0", 5));     // Output: 50
        System.out.println(maxValue("-999", 5));  // Output: -5999
    }
}
