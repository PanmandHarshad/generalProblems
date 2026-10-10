package lld.parkinglot.model;

import java.util.List;

public class ParkingLot {
    private final List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = List.copyOf(floors);
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }
}

