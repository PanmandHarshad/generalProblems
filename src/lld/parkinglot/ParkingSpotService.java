package lld.parkinglot;

class ParkingSpotService {

    private final ParkingLot parkingLot;

    public ParkingSpotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingSpot allotParkingSpot(ParkingRequest request) throws ParkingSpotNotAvailableException {

        for (ParkingFloor floor : parkingLot.getFloors()) {
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot.tryPark(request)) {
                    return spot;
                }
            }
        }

        throw new ParkingSpotNotAvailableException();
    }

    public void releaseParkingSpot(
            ParkingSpot spot) {
        spot.vacate();
    }
}