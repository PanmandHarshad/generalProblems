package lld.parkinglot;

public class Payment {
    String transactionId;
    Double amount;
    PaymentStatus paymentStatus;

    public PaymentStatus getStatus() {
        return paymentStatus;
    }
}
