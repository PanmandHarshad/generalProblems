package leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem128 {

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    static public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> numbersSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int streak = 0;

        for (int num : numbersSet) {
            if (numbersSet.contains(num - 1)) { // Skip if current number is not the start of a sequence
                continue;
            }
            int currentNumber = num;
            int curStreak = 1;

            while (numbersSet.contains(currentNumber + 1)) {
                currentNumber++;
                curStreak++;
            }
            streak = Math.max(curStreak, streak);
        }

        return streak;
    }
}
