package leetcode;

public class Problem11 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 40, 4, 40, 3, 7}));
        System.out.println(maxArea1(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea1(new int[]{1, 8, 6, 2, 40, 4, 40, 3, 7}));

    }

    static public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        if (height.length == 1) {
            return height[0];
        }

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int area = minHeight * (right - left);

            if (area > maxArea) {
                maxArea = area;
            }

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // Most efficient solution
    static public int maxArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        if (height.length == 1) {
            return height[0];
        }

        int left = 0, right = height.length - 1;
        int area = 0;
        int minHeight;

        while (left < right) {
            minHeight = Math.min(height[left], height[right]);
            area = Math.max(area, minHeight * (right - left));

            while (left < right && height[left] <= minHeight) {
                left++;
            }

            while (left < right && height[right] <= minHeight) {
                right--;
            }
        }
        return area;
    }
}
