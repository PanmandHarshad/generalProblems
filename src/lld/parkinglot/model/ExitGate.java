package lld.parkinglot.model;

import lld.parkinglot.exception.PaymentFailedException;
import lld.parkinglot.model.enums.PaymentStatus;
import lld.parkinglot.model.enums.TicketStatus;
import lld.parkinglot.service.ParkingSpotService;
import lld.parkinglot.service.PaymentService;
import lld.parkinglot.util.FeeCalculator;

import java.math.BigDecimal;
import java.time.Instant;

class ExitGate {

    private final int gateId;
    private final PaymentService paymentService;
    private final FeeCalculator feeCalculator;
    private final ParkingSpotService parkingSpotService;

    public ExitGate(
            int gateId,
            PaymentService paymentService,
            FeeCalculator feeCalculator,
            ParkingSpotService parkingSpotService) {

        this.gateId = gateId;
        this.paymentService = paymentService;
        this.feeCalculator = feeCalculator;
        this.parkingSpotService = parkingSpotService;
    }

    public void exitVehicle(Ticket ticket) {

        if (ticket.getStatus() == TicketStatus.COMPLETED) {
            return;
        }

        Instant exitTime = Instant.now();

        BigDecimal amount = feeCalculator.calculate(ticket, exitTime);

        Payment payment =
                paymentService.doPayment(ticket.getTicketId(), amount);

        if (payment.getStatus() != PaymentStatus.SUCCESS) {
            throw new PaymentFailedException();
        }

        parkingSpotService.releaseParkingSpot(ticket.getParkingSpot());

        ticket.complete(exitTime, payment);
    }
}