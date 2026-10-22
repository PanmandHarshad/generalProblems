package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueProducerConsumerExample {

    private static final int QUEUE_CAPACITY = 3;
    private static final int WAITER_COUNT = 2;
    private static final int CHEF_COUNT = 3;
    private static final int ORDERS_PER_WAITER = 4;

    // A shared, bounded queue used by all waiters and chefs.
    private static final BlockingQueue<Order> orders =
            new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    private static final AtomicInteger nextOrderId = new AtomicInteger(1);

    // One poison pill is sent to each chef.
    private static final Order POISON_PILL = new Order(-1, true);

    private static final class Order {
        private final int id;
        private final boolean poisonPill;

        private Order(int id, boolean poisonPill) {
            this.id = id;
            this.poisonPill = poisonPill;
        }

        static Order create(int id) {
            return new Order(id, false);
        }

        boolean isPoisonPill() {
            return poisonPill;
        }

        @Override
        public String toString() {
            return "Order-" + id;
        }
    }

    private static final class Waiter implements Runnable {
        private final int waiterId;

        private Waiter(int waiterId) {
            this.waiterId = waiterId;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < ORDERS_PER_WAITER; i++) {
                    Order order = Order.create(nextOrderId.getAndIncrement());

                    // Blocks when the bounded queue is full.
                    orders.put(order);
                    System.out.printf("Waiter-%d submitted %s%n", waiterId, order);
                }
            } catch (InterruptedException e) {
                // Preserve the interruption signal and stop producing.
                Thread.currentThread().interrupt();
                System.out.printf("Waiter-%d interrupted; stopping.%n", waiterId);
            }
        }
    }

    private static final class Chef implements Runnable {
        private final int chefId;

        private Chef(int chefId) {
            this.chefId = chefId;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Blocks while the queue is empty.
                    Order order = orders.take();

                    if (order.isPoisonPill()) {
                        System.out.printf("Chef-%d shutting down.%n", chefId);
                        return;
                    }

                    prepare(order);
                }
            } catch (InterruptedException e) {
                // This worker exits on interruption. In a real service,
                // any order already taken should be handled according to
                // the application's retry/recovery policy.
                Thread.currentThread().interrupt();
                System.out.printf("Chef-%d interrupted; stopping.%n", chefId);
            }
        }

        private void prepare(Order order) throws InterruptedException {
            System.out.printf("Chef-%d preparing %s%n", chefId, order);
            // Simulate preparation time.
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.printf("Chef-%d completed %s%n", chefId, order);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> chefs = new ArrayList<>();
        List<Thread> waiters = new ArrayList<>();

        // Start consumers first, so they can drain the queue as orders arrive.
        for (int i = 1; i <= CHEF_COUNT; i++) {
            Thread chef = new Thread(new Chef(i), "Chef-" + i);
            chefs.add(chef);
            chef.start();
        }

        // Start producers.
        for (int i = 1; i <= WAITER_COUNT; i++) {
            Thread waiter = new Thread(new Waiter(i), "Waiter-" + i);
            waiters.add(waiter);
            waiter.start();
        }

        // Wait until every waiter has finished submitting its orders.
        for (Thread waiter : waiters) {
            waiter.join();
        }

        // All real orders have now been submitted. FIFO ordering ensures
        // each poison pill follows the real orders already in the queue.
        for (int i = 0; i < CHEF_COUNT; i++) {
            orders.put(POISON_PILL);
        }

        // Wait until every chef has consumed a poison pill and terminated.
        for (Thread chef : chefs) {
            chef.join();
        }

        System.out.println("Restaurant simulation finished.");
    }
}
