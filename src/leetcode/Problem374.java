package leetcode;

public class Problem374 {
    public static void main(String[] args) {

    }

    public static int guessNumber(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);
            if (result == 0) {
                return mid;
            } else if (result == -1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int guess(int num) {
        return -1;
    }
}
