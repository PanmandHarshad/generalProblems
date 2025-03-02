package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem933 {

    private static class RecentCounter {
        private Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public static void main(String[] args) {
            RecentCounter counter = new RecentCounter();
            System.out.println(counter.ping(1)); // Output: 1
            System.out.println(counter.ping(100)); // Output: 2
            System.out.println(counter.ping(3001)); // Output: 3
            System.out.println(counter.ping(3002)); // Output: 3
        }

        public int ping(int t) {
            queue.add(t);
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }
}
