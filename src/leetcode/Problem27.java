package leetcode;

public class Problem27 {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    static public int searchInsert(int[] nums, int target) {
        if (target <= nums[0]){
            return 0;
        }
        int length = nums.length;
        int position = 0;

        int index = 0;
        while (index < length - 1) {
            if (nums[index] == target) {
                position = index;
                break;
            } else if (nums[index] < target && target < nums[index + 1]) {
                position = index + 1;
                break;
            }
            index++;
        }
        return position == 0 && index == length - 1 ? length : position;
    }
}
