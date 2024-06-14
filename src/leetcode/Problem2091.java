package leetcode;

public class Problem2091 {

    public static void main(String[] args) {
        System.out.println(minimumDeletions(new int[]{2, 10, 7, 5, 4, 1, 8, 6}));
        System.out.println(minimumDeletions(new int[]{0, -4, 19, 1, 8, -2, -3, 5}));
        System.out.println(minimumDeletions(new int[]{101}));
    }

    static public int minimumDeletions(int[] nums) {
        int length = nums.length;
        if (length == 1) return 1;

        // Finding the indices of the minimum and maximum elements
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < length; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Calculating the number of deletions needed
        // Case 1: Remove both from the front
        int frontMin = Math.max(minIndex, maxIndex) + 1;

        // Case 2: Remove both from the back
        int backMin = length - Math.min(minIndex, maxIndex);

        // Case 3: Remove one from the front and one from the back
        int frontBackMin = (minIndex + 1) + (length - maxIndex);
        if (minIndex > maxIndex) {
            frontBackMin = (maxIndex + 1) + (length - minIndex);
        }

        // Case 4: Remove one from the back and one from the front
        int backFrontMin = (maxIndex + 1) + (length - minIndex);
        if (maxIndex > minIndex) {
            backFrontMin = (minIndex + 1) + (length - maxIndex);
        }

        // Return the minimum number of deletions needed
        return Math.min(frontMin, Math.min(backMin, Math.min(frontBackMin, backFrontMin)));
    }
}
