package leetcode;

public class Problem1732 {
    public static void main(String[] args) {
        System.out.println(largestAltitude(new int[]{-5, 1, 5, 0, -7}));
        System.out.println(largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2}));
    }

    static public int largestAltitude(int[] gain) {
        int highestAltitude = 0;
        int sum = 0;
        for (int num : gain) {
            sum += num;
            if (highestAltitude < sum) {
                highestAltitude = sum;
            }
        }

        return highestAltitude;
    }
}
