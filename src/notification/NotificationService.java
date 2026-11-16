package notification;

import java.util.concurrent.*;

public class NotificationService {


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,                              // Core pool size
                5,                              // Maximum pool size
                60,                             // Keep-alive time
                TimeUnit.SECONDS,               // Time unit
                new ArrayBlockingQueue<>(10),   // Queue capacity
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() // Rejection policy
        );

        NotificationMetrics metrics = new NotificationMetrics();

        for (int i = 1; i <= 100; i++) {
            try {
                executor.execute(new NotificationTask(i, metrics));
            } catch (RejectedExecutionException e) {
                metrics.rejectedTasks.incrementAndGet();
            }
        }


        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Print only after termination
        System.out.println("Completed: " + metrics.completedTasks.get());
        System.out.println("Failed: " + metrics.failedTasks.get());
        System.out.println("Rejected: " + metrics.rejectedTasks.get());
    }
}
