package lld.elevator.controller;

import lld.elevator.model.Elevator;
import lld.elevator.model.ExternalRequest;
import lld.elevator.model.InternalRequest;
import lld.elevator.strategy.ElevatorSelectionPolicy;

import java.util.List;

public class ElevatorController {

    private final List<Elevator> elevators;
    private final ElevatorSelectionPolicy selectionPolicy;

    public ElevatorController(List<Elevator> elevators, ElevatorSelectionPolicy selectionPolicy) {

        this.elevators = List.copyOf(elevators);
        this.selectionPolicy = selectionPolicy;
    }

    public void handleExternalRequest(ExternalRequest request) {

        Elevator elevator = selectionPolicy
                .selectElevator(elevators, request)
                .orElseThrow(() -> new IllegalStateException("No elevator available"));

        elevator.addStop(request.getRequestFloor());
    }

    public void handleInternalRequest(String elevatorId, InternalRequest request) {

        Elevator elevator = findElevator(elevatorId);

        elevator.addStop(request.getDestinationFloor());
    }

    public void moveElevators() {
        elevators.forEach(Elevator::move);
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    private Elevator findElevator(String elevatorId) {

        return elevators.stream()
                .filter(elevator -> elevator.getId().equals(elevatorId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Elevator not found: " + elevatorId));
    }
}