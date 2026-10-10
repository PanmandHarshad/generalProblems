package lld.parkinglot.util;

import lld.parkinglot.model.Ticket;
import lld.parkinglot.model.enums.VehicleType;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class FeeCalculator {

    private final Map<VehicleType, BigDecimal> hourlyRates;

    public FeeCalculator(Map<VehicleType, BigDecimal> hourlyRates) {
        this.hourlyRates = Map.copyOf(hourlyRates);
    }

    public BigDecimal calculate(Ticket ticket, Instant exitTime) {

        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();

        long hours = Math.max(1, (minutes + 59) / 60);

        BigDecimal rate = hourlyRates.get(ticket.getVehicle().getType());

        return rate.multiply(BigDecimal.valueOf(hours));
    }
}
