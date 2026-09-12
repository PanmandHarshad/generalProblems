package lld.parkinglot;

public class ParkingRequest {
    private final Vehicle vehicle;
    private final boolean requiresCharging;

    public ParkingRequest(Vehicle vehicle,
                          boolean requiresCharging) {
        this.vehicle = vehicle;
        this.requiresCharging = requiresCharging;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean requiresCharging() {
        return requiresCharging;
    }
}