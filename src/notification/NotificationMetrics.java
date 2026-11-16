package notification;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationMetrics {

    AtomicInteger completedTasks = new AtomicInteger();
    AtomicInteger failedTasks = new AtomicInteger();
    AtomicInteger rejectedTasks = new AtomicInteger();
}
