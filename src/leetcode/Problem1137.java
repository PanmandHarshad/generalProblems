package leetcode;

public class Problem1137 {
    public static void main(String[] args) {
        System.out.println(tribonacci(4));
        System.out.println(tribonacci(5));
        System.out.println(tribonacci(25));
        System.out.println(tribonacci1(4));
        System.out.println(tribonacci1(5));
        System.out.println(tribonacci1(25));
    }

    // O(n)
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int t0 = 0, t1 = 1, t2 = 1;
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            int temp = t0 + t1 + t2;

            t0 = t1;
            t1 = t2;
            t2 = temp;

            sum = temp;
        }
        return sum;
    }

    // O(2n)
    public static int tribonacci1(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }
}
