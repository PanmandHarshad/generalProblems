package lld.ticketbooking.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class SeatLock {
    private final String seatLockId;
    private final List<ShowSeat> showSeatList;
    private final Booking booking;
    private Instant expireAt;

    public SeatLock(List<ShowSeat> showSeatList, Booking booking) {
        this.seatLockId = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
        this.showSeatList = List.copyOf(showSeatList);
        this.booking = booking;
        this.expireAt = Instant.now().plus(Duration.ofMinutes(5));
    }

    public String getSeatLockId() {
        return seatLockId;
    }

    public List<ShowSeat> getShowSeatList() {
        return showSeatList;
    }

    public Booking getBooking() {
        return booking;
    }

    public Instant getExpireAt() {
        return expireAt;
    }

    public void updateExpireAt(Duration additionalTime) {
        if (additionalTime == null
                || additionalTime.isZero()
                || additionalTime.isNegative()
                || additionalTime.compareTo(Duration.ofMinutes(2)) > 0) {
            throw new IllegalArgumentException(
                    "Additional time must be positive and at most 2 minutes."
            );
        }

        this.expireAt = this.expireAt.plus(additionalTime);
    }
}
