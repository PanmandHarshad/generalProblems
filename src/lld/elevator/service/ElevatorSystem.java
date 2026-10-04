package lld.elevator.service;

import lld.elevator.controller.ElevatorController;
import lld.elevator.model.Direction;
import lld.elevator.model.Elevator;
import lld.elevator.model.ExternalRequest;
import lld.elevator.model.InternalRequest;
import lld.elevator.strategy.ElevatorSelectionPolicy;

import java.util.List;

public class ElevatorSystem {

    private final ElevatorController elevatorController;

    public ElevatorSystem(List<Elevator> elevators, ElevatorSelectionPolicy selectionPolicy) {

        this.elevatorController = new ElevatorController(elevators, selectionPolicy);
    }

    public void requestElevator(int floor, Direction direction) {

        elevatorController.handleExternalRequest(new ExternalRequest(floor, direction));
    }

    public void selectFloor(String elevatorId, int destinationFloor) {

        elevatorController.handleInternalRequest(elevatorId, new InternalRequest(destinationFloor));
    }

    public void moveElevators() {
        elevatorController.moveElevators();
    }

    public List<Elevator> getElevators() {
        return elevatorController.getElevators();
    }
}