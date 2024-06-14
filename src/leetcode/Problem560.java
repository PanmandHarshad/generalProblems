package leetcode;

import java.util.HashMap;

public class Problem560 {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
    }

    static public int subarraySum(int[] nums, int k) {
        int currentSum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];

            int key = currentSum - k;
            if (key == 0) {
                count++;
            }

            if (map.containsKey(key)) {
                count++;
            }
            map.put(currentSum, i);
        }
        return count;
//        HashMap<Integer, Integer> sumCountMap = new HashMap<>();
//        sumCountMap.put(0, 1);
//
//        int count = 0;
//        int sum = 0;
//
//        for (int num : nums) {
//            sum += num;
//
//            int remainingSum = sum - k;
//            if (sumCountMap.containsKey(remainingSum)) {
//                count += sumCountMap.get(remainingSum);
//            }
//            Integer orDefault = sumCountMap.getOrDefault(sum, 0);
//            sumCountMap.put(sum, orDefault + 1);
//        }
//        return count;
    }
}
