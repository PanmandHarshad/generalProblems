package leetcode;

import java.util.HashSet;

public class Problem945 {
    public static void main(String[] args) {
        System.out.println(minIncrementForUnique(new int[]{1, 2, 2}));
        System.out.println(minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7}));
    }

    static public int minIncrementForUnique(int[] nums) {
        int n = nums.length;
        int max = 0;
        int minIncrements = 0;

        // Find maximum value in array to determine range of frequencyCount array
        for (int val : nums) {
            max = Math.max(max, val);
        }

        // Create a frequencyCount array to store the frequency of each value in nums
        int[] frequencyCount = new int[n + max];

        // Populate frequencyCount array with the frequency of each value in nums
        for (int val : nums) {
            frequencyCount[val]++;
        }

        // Iterate over the frequencyCount array to make all values unique
        for (int i = 0; i < frequencyCount.length; i++) {
            if (frequencyCount[i] > 1) {
                // Determine excess occurrences, carry them over to the next value,
                // ensure single occurrence for current value, and update minIncrements.
                int duplicates = frequencyCount[i] - 1;
                frequencyCount[i + 1] += duplicates;
                frequencyCount[i] = 1;
                minIncrements += duplicates;
            }
        }

        return minIncrements;
    }

    static public int minIncrementForUnique1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int moves = 0;
        for (int num : nums) {
            while (set.contains(num)) {
                num++;
                moves++;
            }
            System.out.println();
            set.add(num);
        }
        return moves;
    }

}
