package leetcode;

public class Problem1381 {

    public static void main(String[] args) {
        // Create stack with maxSize = 5
        CustomStack obj = new CustomStack(5);

        obj.push(1);        // Stack: [1]
        obj.push(2);        // Stack: [1, 2]
        obj.pop();          // Returns 2, Stack: [1]
        obj.push(2);        // Stack: [1, 2]
        obj.push(3);        // Stack: [1, 2, 3]
        obj.push(4);        // Stack: [1, 2, 3, 4]
        obj.increment(5, 100); // Stack: [101, 102, 103, 104]
        obj.increment(2, 100); // Stack: [201, 202, 103, 104]
        System.out.println(obj.pop()); // Should print 104
        System.out.println(obj.pop()); // Should print 103
        System.out.println(obj.pop()); // Should print 202
        System.out.println(obj.pop()); // Should print 201
        System.out.println(obj.pop()); // Should print -1 (empty)
    }

    static class CustomStack {
        private final int[] data;
        private int size;
        private final int capacity;

        public CustomStack(int maxSize) {
            this.data = new int[maxSize];
            this.capacity = maxSize;
            this.size = 0;
        }

        public void push(int x) {
            if (size < capacity) {
                data[size++] = x;
            }
        }

        public int pop() {
            if (size == 0)
                return -1;

            return data[--size];
        }

        public void increment(int k, int val) {
            int limit = Math.min(k, size);
            for (int i = 0; i < limit; i++) {
                data[i] += val;
            }
        }
    }
}
