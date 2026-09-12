package lld.parkinglot;

class ParkingSpot {

    private final String spotId;
    private final VehicleType supportedVehicleType;
    private final boolean chargingSupported;

    private ParkingStatus status = ParkingStatus.AVAILABLE;
    private Vehicle parkedVehicle;

    public ParkingSpot(
            String spotId,
            VehicleType supportedVehicleType,
            boolean chargingSupported) {

        this.spotId = spotId;
        this.supportedVehicleType = supportedVehicleType;
        this.chargingSupported = chargingSupported;
    }

    public boolean canPark(ParkingRequest request) {

        if (supportedVehicleType != request.getVehicle().getType()) {
            return false;
        }

        return !request.requiresCharging() || chargingSupported;
    }

    public synchronized boolean tryPark(ParkingRequest request) {

        if (!isAvailable() || !canPark(request)) {
            return false;
        }

        parkedVehicle = request.getVehicle();
        status = ParkingStatus.UNAVAILABLE;
        return true;
    }

    public synchronized void vacate() {
        parkedVehicle = null;
        status = ParkingStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == ParkingStatus.AVAILABLE;
    }
}