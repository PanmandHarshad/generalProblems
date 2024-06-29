import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queues {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println("Produced: " + i);
                    if (queue.size() == 10)
                        System.out.println("Threshold touched by producer queue");
                    queue.put(i); // Blocks if the queue is full
                    Thread.sleep(100); // Simulate time taken to produce an item
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer item = queue.take(); // Blocks if the queue is empty
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200); // Simulate time taken to consume an item
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        // Join threads to wait for their completion
        producer.join();
        consumer.join();

        System.out.println("Execution Completed");
    }
}
