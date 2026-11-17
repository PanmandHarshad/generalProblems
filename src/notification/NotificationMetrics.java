package notification;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationMetrics {

    private final AtomicInteger completedTasks = new AtomicInteger();
    private final AtomicInteger failedTasks = new AtomicInteger();
    private final AtomicInteger rejectedTasks = new AtomicInteger();

    public void incrementCompleted() {
        completedTasks.incrementAndGet();
    }

    public void incrementFailed() {
        failedTasks.incrementAndGet();
    }

    public void incrementRejected() {
        rejectedTasks.incrementAndGet();
    }

    public int getCompleted() {
        return completedTasks.get();
    }

    public int getFailed() {
        return failedTasks.get();
    }

    public int getRejected() {
        return rejectedTasks.get();
    }
}