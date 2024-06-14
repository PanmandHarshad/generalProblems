package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Simulating a computation that may throw an exception
            double random = Math.random();
            if (random < 0.5) {
                throw new RuntimeException("Computation failed.");
            } else {
                System.out.println("Random value is : " + random);
            }
            return 42;
        });

        System.out.println();

        CompletableFuture<Integer> resultFuture = future.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return -1; // Fallback value or error handling logic
        });

        System.out.println("Result Future : " + resultFuture.get());
    }
}
