package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem155 {
    public static class MinStack {

        private final Deque<Integer> dataStack = new ArrayDeque<>();
        private final Deque<Integer> minStack = new ArrayDeque<>();

        public void push(int value) {
            dataStack.push(value);

            if (minStack.isEmpty()) {
                minStack.push(value);
            } else {
                minStack.push(Math.min(minStack.peek(), value));
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

        public static void main(String[] args) {

            MinStack stack = new MinStack();

            System.out.println("========== TEST 1: Basic operations ==========");

            stack.push(5);
            stack.push(3);
            stack.push(7);
            stack.push(2);

            System.out.println("After push(5), push(3), push(7), push(2)");
            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());

            System.out.println("\nPop()");
            stack.pop();

            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());


            System.out.println("\n========== TEST 2: Duplicate minimum ==========");

            stack = new MinStack();

            stack.push(5);
            stack.push(3);
            stack.push(3);
            stack.push(7);

            System.out.println("After push(5), push(3), push(3), push(7)");
            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());

            System.out.println("\nPop() -> removes 7");
            stack.pop();
            System.out.println("Min     = " + stack.getMin());

            System.out.println("\nPop() -> removes one 3");
            stack.pop();
            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());


            System.out.println("\n========== TEST 3: Decreasing values ==========");

            stack = new MinStack();

            stack.push(10);
            stack.push(8);
            stack.push(6);
            stack.push(4);
            stack.push(2);

            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());

            stack.pop();

            System.out.println("\nAfter pop()");
            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());


            System.out.println("\n========== TEST 4: Increasing values ==========");

            stack = new MinStack();

            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);

            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());

            stack.pop();

            System.out.println("\nAfter pop()");
            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());


            System.out.println("\n========== TEST 5: Negative values ==========");

            stack = new MinStack();

            stack.push(-2);
            stack.push(0);
            stack.push(-3);

            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());

            stack.pop();

            System.out.println("\nAfter pop()");
            System.out.println("Top     = " + stack.top());
            System.out.println("Min     = " + stack.getMin());


            System.out.println("\n========== ALL TESTS COMPLETED ==========");
        }
    }
}
