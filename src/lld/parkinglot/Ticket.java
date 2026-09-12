package lld.parkinglot;

import java.time.Instant;

class Ticket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final Instant entryTime;
    private final int entryGateId;

    private TicketStatus status =
            TicketStatus.ACTIVE;

    private Instant exitTime;
    private Payment payment;

    public Ticket(
            String ticketId,
            Vehicle vehicle,
            ParkingSpot parkingSpot,
            Instant entryTime,
            int entryGateId) {

        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
        this.entryGateId = entryGateId;
    }

    public void complete(
            Instant exitTime,
            Payment payment) {

        if (status != TicketStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Ticket already completed"
            );
        }

        this.exitTime = exitTime;
        this.payment = payment;
        this.status = TicketStatus.COMPLETED;
    }

    public String getTicketId(){
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public TicketStatus getStatus() {
        return status;
    }
}