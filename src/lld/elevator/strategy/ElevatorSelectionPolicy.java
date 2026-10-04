package lld.elevator.strategy;

import lld.elevator.model.Elevator;
import lld.elevator.model.ExternalRequest;

import java.util.List;
import java.util.Optional;

public interface ElevatorSelectionPolicy {

    Optional<Elevator> selectElevator(List<Elevator> elevators, ExternalRequest request);
}