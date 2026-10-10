package lld.ticketbooking.model;

import lld.ticketbooking.model.enums.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final User user;
    private final List<ShowSeat> showSeatList;
    private BookingStatus bookingStatus;
    private final List<Payment> paymentList;

    public Booking(User user, List<ShowSeat> showSeatList, BookingStatus bookingStatus) {
        this.bookingId = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
        this.user = user;
        this.showSeatList = List.copyOf(showSeatList);
        this.bookingStatus = bookingStatus;
        this.paymentList = new ArrayList<>();
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public List<ShowSeat> getShowSeatList() {
        return showSeatList;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public List<Payment> getPaymentList() {
        return List.copyOf(paymentList);
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void addPaymentInformation(Payment payment) {
        paymentList.add(payment);
    }

    public double getBookingAmount() {
        return showSeatList
                .stream()
                .mapToDouble(ShowSeat::getSeatPrice)
                .sum();
    }
}
