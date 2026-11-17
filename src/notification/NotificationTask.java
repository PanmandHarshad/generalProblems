package notification;

public class NotificationTask implements Runnable {

    private final int notificationId;
    private final NotificationMetrics metrics;

    public NotificationTask(int notificationId, NotificationMetrics metrics) {
        this.notificationId = notificationId;
        this.metrics = metrics;
    }

    @Override
    public void run() {
        System.out.println(
                "Processing notification: " + notificationId
                        + " | Thread: " + Thread.currentThread().getName()
        );

        try {
            Thread.sleep(1000); // Simulate external API call
            System.out.println("Notification sent: " + notificationId);
            metrics.incrementCompleted();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Notification interrupted: " + notificationId);
            metrics.incrementFailed();
        } catch (Exception exception) {
            System.out.println("Notification failed: " + notificationId +
                    " | Reason: " + exception.getMessage());
            metrics.incrementFailed();
        }
    }
}
