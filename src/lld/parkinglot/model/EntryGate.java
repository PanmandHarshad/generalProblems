package lld.parkinglot.model;

import lld.parkinglot.exception.ParkingSpotNotAvailableException;
import lld.parkinglot.service.ParkingSpotService;

import java.time.Instant;
import java.util.UUID;

class EntryGate {

    private final int gateId;
    private final ParkingSpotService parkingSpotService;

    public EntryGate(int gateId, ParkingSpotService parkingSpotService) {
        this.gateId = gateId;
        this.parkingSpotService = parkingSpotService;
    }

    public Ticket enter(ParkingRequest request) throws ParkingSpotNotAvailableException {

        ParkingSpot spot = parkingSpotService.allotParkingSpot(request);

        try {
            return new Ticket(
                    generateTicketId(),
                    request.getVehicle(),
                    spot,
                    Instant.now(),
                    gateId
            );

        } catch (RuntimeException e) {
            parkingSpotService.releaseParkingSpot(spot);
            throw e;
        }
    }

    private String generateTicketId() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "");
    }
}