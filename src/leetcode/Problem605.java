package leetcode;

public class Problem605 {

    public static void main(String[] args) {
//        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
//        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 0, 1}, 2));
    }

    static public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int index = 0;
        while (index < flowerbed.length && n > 0) {
            if (flowerbed[index] == 1) {
                index += 2;
            } else if (index + 1 >= flowerbed.length || flowerbed[index + 1] == 0) {
                n--;
                index += 2;
            } else {
                index += 3;
            }
        }

        return n == 0;
    }
}
