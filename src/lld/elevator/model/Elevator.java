package lld.elevator.model;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Elevator {

    private final String id;
    private final String name;

    private int currentFloor;
    private ElevatorStatus elevatorStatus;
    private Direction direction;

    private final NavigableSet<Integer> upcomingFloors;

    private final int maxCapacity;
    private final int maxFloor;

    public Elevator(String id, String name, int currentFloor, int maxCapacity, int maxFloor) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Elevator id is required");
        }

        if (currentFloor < 0 || currentFloor > maxFloor) {
            throw new IllegalArgumentException("Invalid initial floor");
        }

        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Maximum capacity must be positive");
        }

        this.id = id;
        this.name = name;
        this.currentFloor = currentFloor;
        this.maxFloor = maxFloor;
        this.maxCapacity = maxCapacity;

        this.direction = Direction.NA;
        this.elevatorStatus = ElevatorStatus.IDLE;
        this.upcomingFloors = new TreeSet<>();
    }

    public synchronized void addStop(int floor) {
        validateFloor(floor);

        if (elevatorStatus == ElevatorStatus.OUT_OF_SERVICE) {
            throw new IllegalStateException("Cannot add stop to out-of-service elevator");
        }

        upcomingFloors.add(floor);
    }

    private void removeStop(int floor) {
        upcomingFloors.remove(floor);
    }

    private Integer getNextStop() {

        if (upcomingFloors.isEmpty()) {
            direction = Direction.NA;
            elevatorStatus = ElevatorStatus.IDLE;
            return null;
        }

        // Serve current floor before moving elsewhere.
        if (upcomingFloors.contains(currentFloor)) {
            return currentFloor;
        }

        if (direction == Direction.UP) {

            Integer nextUpFloor = findNextUpFloor();

            if (nextUpFloor == null) {
                toggleDirection();
                return findNextDownFloor();
            }

            return nextUpFloor;
        }

        if (direction == Direction.DOWN) {

            Integer nextDownFloor = findNextDownFloor();

            if (nextDownFloor == null) {
                toggleDirection();
                return findNextUpFloor();
            }

            return nextDownFloor;
        }

        return getNextFloorForIdleElevator();
    }

    private int getNextFloorForIdleElevator() {

        Integer nextUpFloor = findNextUpFloor();
        Integer nextDownFloor = findNextDownFloor();

        /*
         * Defensive check.
         * Normally unreachable because:
         * - upcomingFloors isn't empty
         * - currentFloor isn't in upcomingFloors
         */
        if (nextUpFloor == null && nextDownFloor == null) {
            return currentFloor;
        }

        if (nextUpFloor == null) {
            direction = Direction.DOWN;
            return nextDownFloor;
        }

        if (nextDownFloor == null) {
            direction = Direction.UP;
            return nextUpFloor;
        }

        int distanceUp = Math.abs(currentFloor - nextUpFloor);
        int distanceDown = Math.abs(currentFloor - nextDownFloor);

        if (distanceUp <= distanceDown) {
            direction = Direction.UP;
            return nextUpFloor;
        }

        direction = Direction.DOWN;
        return nextDownFloor;
    }

    private Integer findNextUpFloor() {
        return upcomingFloors.higher(currentFloor);
    }

    private Integer findNextDownFloor() {
        return upcomingFloors.lower(currentFloor);
    }

    private void toggleDirection() {

        if (direction == Direction.UP) {
            direction = Direction.DOWN;
        } else if (direction == Direction.DOWN) {
            direction = Direction.UP;
        } else {
            throw new IllegalStateException("Cannot toggle direction when elevator is idle");
        }
    }

    /*
     * move() represents one simulation step.
     *
     * We don't simulate the physical time taken by the elevator
     * to travel between floors.
     */
    public synchronized void move() {

        if (elevatorStatus == ElevatorStatus.OUT_OF_SERVICE) {
            return;
        }

        Integer nextStop = getNextStop();

        if (nextStop == null) {
            return;
        }

        currentFloor = nextStop;
        removeStop(nextStop);

        if (upcomingFloors.isEmpty()) {
            elevatorStatus = ElevatorStatus.IDLE;
            direction = Direction.NA;
        } else {
            elevatorStatus = ElevatorStatus.MOVING;
        }
    }

    private void validateFloor(int floor) {
        if (floor < 0 || floor > maxFloor) {
            throw new IllegalArgumentException("Invalid floor: " + floor);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public synchronized int getCurrentFloor() {
        return currentFloor;
    }

    public synchronized ElevatorStatus getElevatorStatus() {
        return elevatorStatus;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized Set<Integer> getUpcomingFloors() {
        return Set.copyOf(upcomingFloors);
    }

    @Override
    public synchronized String toString() {
        return "Elevator{" +
                "id='" + id + '\'' +
                ", floor=" + currentFloor +
                ", status=" + elevatorStatus +
                ", direction=" + direction +
                ", stops=" + upcomingFloors +
                '}';
    }
}