package leetcode;

import java.util.*;

public class Problem2215 {

    public static void main(String[] args) {
        System.out.println(findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6}));
        System.out.println(findDifference(new int[]{1, 2, 3, 3}, new int[]{1, 1, 2, 2}));
    }

    static public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new LinkedList<>();
        HashSet<Integer> given = new HashSet<>();

        for (int num : nums2)
            given.add(num);

        List<Integer> subResult = new LinkedList<>();
        HashSet<Integer> noRepeat = new HashSet<>();

        for (int num : nums1) {
            if (given.contains(num)) {
                given.remove(num);
                noRepeat.add(num);
            } else {
                if (!noRepeat.contains(num)) {
                    subResult.add(num);
                    noRepeat.add(num);
                }
            }
        }

        result.add(subResult);
        subResult = new LinkedList<>(given);

        result.add(subResult);
        return result;
    }
}
