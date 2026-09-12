package lld.parkinglot;

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

