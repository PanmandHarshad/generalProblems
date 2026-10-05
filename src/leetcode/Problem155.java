package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem155 {
    class MinStack {

        private final Deque<Integer> dataStack;
        private final Deque<Integer> minStack;

        public MinStack() {
            dataStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

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
    }
}
