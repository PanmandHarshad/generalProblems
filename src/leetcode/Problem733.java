package leetcode;

public class Problem733 {

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1; // starting row
        int sc = 1; // starting column
        int newColor = 2;
        printImage(floodFill(image, sr, sc, newColor));

        int[][] image1 = {
                {0, 0, 0},
                {0, 0, 0}
        };
        sr = 1;
        sc = 0;
        newColor = 2;
        printImage(floodFill(image1, sr, sc, newColor));


        int[][] image2 = {{1, 1, 0}};
        sr = 0;
        sc = 0;
        newColor = 2;
        printImage(floodFill(image2, sr, sc, newColor));
    }

    private static void printImage(int[][] result) {
        System.out.println("Output image:");
        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }

    static public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }

        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    static private void dfs(int[][] image, int row, int column, int color, int newColor) {
        if (row < 0 || column < 0 || row >= image.length || column >= image[0].length) {
            return;
        }
        if (image[row][column] == color) {
            image[row][column] = newColor;
            int[][] directions = {{row, column + 1}, {row + 1, column}, {row, column - 1}, {row - 1, column}};
            for (int[] direction : directions) {
                dfs(image, direction[0], direction[1], color, newColor);
            }
        }
    }
}
