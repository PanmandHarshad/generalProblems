package lld.ticketbooking.service;

import lld.ticketbooking.model.Booking;
import lld.ticketbooking.model.SeatLock;
import lld.ticketbooking.model.ShowSeat;
import lld.ticketbooking.model.User;
import lld.ticketbooking.model.enums.BookingStatus;
import lld.ticketbooking.model.enums.PaymentStatus;
import lld.ticketbooking.model.enums.ShowSeatStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BookingService {

    public boolean cancelReservation(SeatLock seatLock) {
        return releaseReservation(
                seatLock,
                BookingStatus.CANCELLED,
                Instant.now(),
                false);
    }

    public boolean expireReservation(SeatLock seatLock) {
        return expireReservation(seatLock, Instant.now());
    }

    // The second parameter also makes expiry deterministic in tests.
    // The production caller can use expireReservation(seatLock).
    public boolean expireReservation(SeatLock seatLock, Instant now) {
        if (now == null) {
            throw new IllegalArgumentException("Current time cannot be null.");
        }

        return releaseReservation(
                seatLock,
                BookingStatus.FAILED,
                now,
                true);
    }

    private boolean releaseReservation(SeatLock seatLock, BookingStatus finalStatus,
                                       Instant now, boolean requireExpiry) {

        if (seatLock == null) {
            throw new IllegalArgumentException("SeatLock cannot be null.");
        }

        Booking booking = seatLock.getBooking();

        List<ShowSeat> orderedSeats = new ArrayList<>(seatLock.getShowSeatList());

        orderedSeats.sort(Comparator.comparing(ShowSeat::getId));

        List<ShowSeat> acquiredLocks = new ArrayList<>();

        try {
            // 1. Acquire all seat locks in a consistent order.
            for (ShowSeat seat : orderedSeats) {
                if (!seat.tryAcquireLock()) {
                    return false;
                }

                acquiredLocks.add(seat);
            }

            // 2. Recheck the booking state after acquiring all locks.
            if (booking.getBookingStatus() != BookingStatus.PENDING) {
                return false;
            }

            // 3. Never release seats while payment is unresolved,
            // or when a successful payment requires booking confirmation.
            boolean paymentUnresolvedOrSuccessful = booking.getPaymentList()
                    .stream().anyMatch(payment ->
                            payment.getPaymentStatus() == PaymentStatus.PENDING
                                    || payment.getPaymentStatus() == PaymentStatus.SUCCESS);

            if (paymentUnresolvedOrSuccessful) {
                return false;
            }

            // 4. An expiry operation must not run before the deadline.
            if (requireExpiry && now.isBefore(seatLock.getExpireAt())) {
                return false;
            }

            // 5. Validate ownership of EVERY seat before releasing any.
            for (ShowSeat seat : orderedSeats) {
                if (!seat.isLockedBy(seatLock.getSeatLockId())) {
                    return false;
                }
            }

            // 6. Release all seats.
            for (ShowSeat seat : orderedSeats) {
                seat.releaseReservation(seatLock.getSeatLockId());
            }

            // 7. Update the booking after the seats have been released.
            booking.setBookingStatus(finalStatus);

            return true;

        } finally {
            // 8. Always release Java locks in reverse order.
            for (int i = acquiredLocks.size() - 1; i >= 0; i--) {
                acquiredLocks.get(i).releaseLock();
            }
        }
    }

    public SeatLock reserveSeats(List<ShowSeat> selectedSeats, User user) {
        // 1. Validate the request.
        if (selectedSeats == null || selectedSeats.isEmpty()) {
            throw new IllegalArgumentException("Please select at least one seat.");
        }

        if (user == null) {
            throw new IllegalArgumentException("Invalid user.");
        }

        // Copy the list so we do not modify the caller's list.
        List<ShowSeat> orderedSeats = new ArrayList<>(selectedSeats);

        if (orderedSeats.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Selected seats cannot contain null.");
        }

        // 2. Reject duplicate seat IDs.
        long distinctSeatCount = orderedSeats.stream()
                .map(ShowSeat::getId)
                .distinct()
                .count();

        if (distinctSeatCount != orderedSeats.size()) {
            throw new IllegalArgumentException("Duplicate seats selected.");
        }

        if (orderedSeats.stream().anyMatch(showSeat ->
                showSeat.getShow() == null || showSeat.getShow().getId() == null)) {
            throw new IllegalArgumentException("Show and show ID cannot be null");
        }

        if (orderedSeats.stream().map(showSeat -> showSeat.getShow().getId())
                .distinct()
                .count() > 1) {
            throw new IllegalArgumentException("All show seats must belong to the same show");
        }

        // 3. Sort seats by ID to prevent deadlocks.
        orderedSeats.sort(Comparator.comparing(ShowSeat::getId));

        // Track only Java locks acquired by this thread.
        List<ShowSeat> acquiredLocks = new ArrayList<>();

        try {
            // 4. Acquire every seat lock in sorted order.
            for (ShowSeat seat : orderedSeats) {
                if (!seat.tryAcquireLock()) {
                    throw new IllegalStateException("A selected seat is currently being processed. "
                            + "Please try again.");
                }

                acquiredLocks.add(seat);
            }

            // 5. Validate ALL seats before changing ANY status.
            for (ShowSeat seat : orderedSeats) {
                if (seat.getShowSeatStatus() != ShowSeatStatus.AVAILABLE) {
                    throw new IllegalStateException("One or more selected seats are unavailable.");
                }
            }

            // 6. Create the booking and grouped business SeatLock.
            Booking booking = new Booking(user, orderedSeats, BookingStatus.PENDING);

            SeatLock seatLock = new SeatLock(orderedSeats, booking);

            // 7. Reserve all seats only after every check succeeds.
            for (ShowSeat seat : orderedSeats) {
                seat.markLockedBy(seatLock.getSeatLockId());
            }

            return seatLock;

        } finally {
            // 8. Always release Java locks in reverse order.
            for (int i = acquiredLocks.size() - 1; i >= 0; i--) {
                acquiredLocks.get(i).releaseLock();
            }
        }
    }
}
