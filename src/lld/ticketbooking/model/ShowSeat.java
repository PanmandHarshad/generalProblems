package lld.ticketbooking.model;

import lld.ticketbooking.model.enums.SeatType;
import lld.ticketbooking.model.enums.ShowSeatStatus;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class ShowSeat {
    private final String id;
    private final Show show;
    private final Seat seat;
    private ShowSeatStatus showSeatStatus;
    private final double seatPrice;
    private String activeSeatLockId;

    private final ReentrantLock lock = new ReentrantLock();

    public ShowSeat(String id, Show show, Seat seat, ShowSeatStatus showSeatStatus, double seatPrice) {
        this.id = id;
        this.show = show;
        this.seat = seat;
        this.showSeatStatus = showSeatStatus;
        this.seatPrice = seatPrice;
        this.activeSeatLockId = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public Seat getSeat() {
        lock.lock();
        try {
            return seat;
        } finally {
            lock.unlock();
        }
    }

    public ShowSeatStatus getShowSeatStatus() {
        lock.lock();
        try {
            return showSeatStatus;
        } finally {
            lock.unlock();
        }
    }

    public void setShowSeatStatus(ShowSeatStatus showSeatStatus) {
        lock.lock();
        try {
            this.showSeatStatus = showSeatStatus;
        } finally {
            lock.unlock();
        }
    }

    public double getSeatPrice() {
        return seat.getSeatType() == SeatType.REGULAR ? seatPrice : seatPrice * 1.5;
    }

    public String getActiveSeatLockId() {
        return activeSeatLockId;
    }

    public boolean isLockedBy(String seatLockId) {
        lock.lock();
        try {
            return showSeatStatus == ShowSeatStatus.LOCKED
                    && Objects.equals(activeSeatLockId, seatLockId);
        } finally {
            lock.unlock();
        }
    }

    public void markLockedBy(String seatLockId) {
        lock.lock();
        try {
            if (showSeatStatus != ShowSeatStatus.AVAILABLE) {
                throw new IllegalStateException("Seat is not available.");
            }

            this.activeSeatLockId = seatLockId;
            this.showSeatStatus = ShowSeatStatus.LOCKED;
        } finally {
            lock.unlock();
        }
    }

    public void markBookedBy(String seatLockId) {
        lock.lock();
        try {
            if (showSeatStatus != ShowSeatStatus.LOCKED
                    || !java.util.Objects.equals(
                    activeSeatLockId, seatLockId)) {
                throw new IllegalStateException(
                        "Seat is not locked by this reservation.");
            }

            showSeatStatus = ShowSeatStatus.BOOKED;
            activeSeatLockId = null;
        } finally {
            lock.unlock();
        }
    }

    public void releaseReservation(String seatLockId) {
        lock.lock();
        try {
            if (showSeatStatus != ShowSeatStatus.LOCKED
                    || !Objects.equals(activeSeatLockId, seatLockId)) {
                throw new IllegalStateException("Seat is not locked by this reservation.");
            }

            this.showSeatStatus = ShowSeatStatus.AVAILABLE;
            this.activeSeatLockId = null;
        } finally {
            lock.unlock();
        }
    }


    public boolean tryAcquireLock() {
        return lock.tryLock();
    }

    public void releaseLock() {
        lock.unlock();
    }
}
