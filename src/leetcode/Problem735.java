package leetcode;

import java.util.Stack;

public class Problem735 {

    public static void main(String[] args) {
        int[] result1 = asteroidCollision(new int[]{5, 10, -5});
        print(result1);
        int[] result2 = asteroidCollision(new int[]{8, -8});
        print(result2);
        int[] result3 = asteroidCollision(new int[]{10, 2, -5});
        print(result3);
        int[] result4 = asteroidCollision(new int[]{5, 10, -11});
        print(result4);
        int[] result5 = asteroidCollision(new int[]{-2, -1, 1, 2});
        print(result5);

    }

    private static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean isDestroyed = false;

            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop(); // top asteroid is smaller, it gets destroyed
                } else if (stack.peek() == -asteroid) {
                    stack.pop(); // both are of equal size, both get destroyed
                    isDestroyed = true;
                    break;
                } else {
                    isDestroyed = true; // current asteroid is destroyed
                    break;
                }
            }

            if (!isDestroyed) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
