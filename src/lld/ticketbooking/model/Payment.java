package lld.ticketbooking.model;

import lld.ticketbooking.model.enums.PaymentStatus;
import lld.ticketbooking.model.enums.PaymentType;

import java.time.Instant;
import java.util.UUID;

public class Payment {
    private final String paymentId = UUID.randomUUID().toString();
    private final String bookingId;
    private final double amount;
    private final PaymentType paymentType;

    private String transactionId; // Provided by the gateway
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private final Instant createdAt = Instant.now();

    public Payment(String bookingId, String transactionId, double amount,
                   PaymentType paymentType) {
        this.bookingId = bookingId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
