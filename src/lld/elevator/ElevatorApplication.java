package lld.elevator;

import lld.elevator.model.Elevator;
import lld.elevator.service.ElevatorSystem;
import lld.elevator.strategy.ElevatorSelectionPolicy;
import lld.elevator.strategy.NearestInDirectionSelectionPolicy;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ElevatorApplication {

    public static void main(String[] args) throws InterruptedException {

        ElevatorSystem system = getElevatorSystem();

        printState("Initial state", system);

        // ============================================================
        // TEST 1: Concurrent addStop()
        // ============================================================

        System.out.println("\n\n========== TEST 1: Concurrent addStop() ==========");

        Elevator e2 = system.getElevators().stream()
                .filter(elevator -> elevator.getId().equals("E2"))
                .findFirst()
                .orElseThrow();

        int numberOfThreads = 10;

        CountDownLatch readyLatch = new CountDownLatch(numberOfThreads);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {

            final int floor = i + 1;

            Thread thread = new Thread(() -> {

                readyLatch.countDown();

                try {
                    startLatch.await();

                    e2.addStop(floor);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " added stop " + floor
                    );

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }

            }, "AddThread-" + floor);

            thread.start();
        }

        // Wait until all threads are ready.
        readyLatch.await();

        System.out.println("All add threads are ready. Starting simultaneously...");

        // Release all threads at approximately the same time.
        startLatch.countDown();

        // Wait until all add operations finish.
        doneLatch.await();

        printState("After concurrent addStop()", system);

        System.out.println("Expected number of stops = 10");

        System.out.println("Actual number of stops   = " + e2.getUpcomingFloors().size());


        // ============================================================
        // TEST 2: Concurrent move()
        // ============================================================

        System.out.println("\n\n========== TEST 2: Concurrent move() ==========");

        System.out.println("Pending stops before concurrent movement: " + e2.getUpcomingFloors());

        int movementThreads = 5;

        CountDownLatch movementReadyLatch = new CountDownLatch(movementThreads);

        CountDownLatch movementStartLatch = new CountDownLatch(1);

        CountDownLatch movementDoneLatch = new CountDownLatch(movementThreads);

        for (int i = 1; i <= movementThreads; i++) {

            Thread thread = new Thread(() -> {

                movementReadyLatch.countDown();

                try {
                    movementStartLatch.await();

                    System.out.println(Thread.currentThread().getName() + " attempting move()");

                    e2.move();

                    System.out.println(Thread.currentThread().getName()
                            + " completed move(). Current floor = " + e2.getCurrentFloor());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    movementDoneLatch.countDown();
                }

            }, "MoveThread-" + i);

            thread.start();
        }

        movementReadyLatch.await();

        System.out.println("All movement threads are ready. Starting simultaneously...");

        movementStartLatch.countDown();

        movementDoneLatch.await();

        printState("After concurrent move()", system);


        // ============================================================
        // TEST 3: Concurrent addStop() + move()
        // ============================================================

        System.out.println("\n\n========== TEST 3: Concurrent addStop() + move() ==========");

        // Add some initial stops.
        e2.addStop(12);
        e2.addStop(14);
        e2.addStop(16);

        printState("Before mixed concurrency test", system);

        CountDownLatch mixedStartLatch = new CountDownLatch(1);
        CountDownLatch mixedDoneLatch = new CountDownLatch(6);

        // Three request threads.
        for (int i = 1; i <= 3; i++) {

            final int floor = 17 + i;

            Thread thread = new Thread(() -> {

                try {
                    mixedStartLatch.await();

                    e2.addStop(floor);

                    System.out.println(Thread.currentThread().getName() + " added stop " + floor);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    mixedDoneLatch.countDown();
                }

            }, "RequestThread-" + i);

            thread.start();
        }

        // Three movement threads.
        for (int i = 1; i <= 3; i++) {

            Thread thread = new Thread(() -> {

                try {
                    mixedStartLatch.await();

                    e2.move();

                    System.out.println(Thread.currentThread().getName()
                            + " completed move(). Current floor = "
                            + e2.getCurrentFloor());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    mixedDoneLatch.countDown();
                }

            }, "MovementThread-" + i);

            thread.start();
        }

        System.out.println("Starting addStop() and move() threads simultaneously...");

        mixedStartLatch.countDown();

        mixedDoneLatch.await();

        printState("After mixed concurrency test", system);


        System.out.println("\n========== ALL TESTS COMPLETED ==========");
    }


    private static ElevatorSystem getElevatorSystem() {

        Elevator e1 = new Elevator(
                "E1",
                "Elevator-1",
                0,
                10,
                20
        );

        Elevator e2 = new Elevator(
                "E2",
                "Elevator-2",
                5,
                10,
                20
        );

        Elevator e3 = new Elevator(
                "E3",
                "Elevator-3",
                10,
                10,
                20
        );

        List<Elevator> elevators = List.of(e1, e2, e3);

        ElevatorSelectionPolicy selectionPolicy = new NearestInDirectionSelectionPolicy();

        return new ElevatorSystem(elevators, selectionPolicy);
    }


    private static void printState(String message, ElevatorSystem system) {

        System.out.println("\n--- " + message + " ---");

        system.getElevators().forEach(System.out::println);
    }
}