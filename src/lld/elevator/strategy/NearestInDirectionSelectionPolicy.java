package lld.elevator.strategy;

import lld.elevator.model.Direction;
import lld.elevator.model.Elevator;
import lld.elevator.model.ElevatorStatus;
import lld.elevator.model.ExternalRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NearestInDirectionSelectionPolicy
        implements ElevatorSelectionPolicy {

    @Override
    public Optional<Elevator> selectElevator(List<Elevator> elevators, ExternalRequest request) {

        return elevators.stream()
                .filter(elevator ->
                        elevator.getElevatorStatus() == ElevatorStatus.IDLE
                                ||
                                (
                                        elevator.getElevatorStatus() == ElevatorStatus.MOVING
                                                && elevator.getDirection() == request.getDirection()
                                                && isOnTheWay(elevator, request)
                                )
                )
                .min(Comparator.comparingInt(
                        elevator -> Math.abs(
                                elevator.getCurrentFloor()
                                        - request.getRequestFloor()
                        )
                ));
    }

    private boolean isOnTheWay(Elevator elevator, ExternalRequest request) {

        return request.getDirection() == Direction.UP
                ? elevator.getCurrentFloor() <= request.getRequestFloor()
                : elevator.getCurrentFloor() >= request.getRequestFloor();
    }
}