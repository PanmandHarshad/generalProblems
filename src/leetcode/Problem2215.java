package leetcode;

import java.util.*;

public class Problem2215 {

    public static void main(String[] args) {
        System.out.println(findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6}));
        System.out.println(findDifference(new int[]{1, 2, 3, 3}, new int[]{1, 1, 2, 2}));
    }

    // Most efficient solution
    static public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int no : nums1) {
            set1.add(no);
        }

        for (int no : nums2) {
            set2.add(no);
        }

        for (int no : set1) {
            if (!set2.contains(no)) {
                list1.add(no);
            }
        }

        for (int no : set2) {
            if (!set1.contains(no)) {
                list2.add(no);
            }
        }

        return Arrays.asList(list1, list2);
    }
}