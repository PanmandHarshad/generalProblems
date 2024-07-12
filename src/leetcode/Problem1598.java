package leetcode;

public class Problem1598 {
    public static void main(String[] args) {
        System.out.println(minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}));
        System.out.println(minOperations(new String[]{"d1/", "d2/", "./", "d3/", "../", "d31/"}));
        System.out.println(minOperations(new String[]{"d1/", "../", "../", "../"}));
        System.out.println(minOperations(new String[]{"./", "../", "./"}));
    }

    static public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if (log.equals("../") && depth > 0) {
                depth--;
            } else if (!log.equals("./") && !log.equals("../")) {
                depth++;
            }
        }
        return depth;
    }
}
