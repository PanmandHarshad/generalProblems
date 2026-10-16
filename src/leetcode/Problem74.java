package leetcode;

public class Problem74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int left = 0;
        int right = rows * columns - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int row = mid / columns;
            int col = mid % columns;
            if (matrix[row][col] == target) {
                return true;
            }

            if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        System.out.println(searchMatrix(matrix, 3));  // true
        System.out.println(searchMatrix(matrix, 13)); // false
        System.out.println(searchMatrix(matrix, 60)); // true
        System.out.println(searchMatrix(matrix, 1));  // true
        System.out.println(searchMatrix(matrix, 11)); // true
    }
}
