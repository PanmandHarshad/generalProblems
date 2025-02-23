package leetcode;

import java.util.Stack;

public class Problem735 {

    public static void main(String[] args) {
        print(asteroidCollision(new int[]{5, 10, -5}));
        print(asteroidCollision(new int[]{8, -8}));
        print(asteroidCollision(new int[]{10, 2, -5}));
        print(asteroidCollision(new int[]{5, 10, -11}));
        print(asteroidCollision(new int[]{-2, -1, 1, 2}));
    }

    private static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length == 0 || asteroids.length == 1) {
            return asteroids;
        }
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean alive = true;
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop();
                    continue;
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                    alive = false;
                } else {
                    alive = false;
                }
                break;
            }

            if (alive) {
                stack.push(asteroid);
            }
        }

//        int[] result = new int[stack.size()];
//        int i = 0;
//        for (int no : stack) {
//            result[i++] = no;
//        }

        /*
        while both solutions process each asteroid only once,
        the second solution’s direct manipulation of the stack (via pop and reverse indexing)
        avoids the extra cost of iterator creation and overhead,
        making it more efficient in practice.
         */

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
