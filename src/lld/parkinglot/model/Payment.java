package lld.parkinglot.model;

import lld.parkinglot.model.enums.PaymentStatus;

public class Payment {
    String transactionId;
    Double amount;
    PaymentStatus paymentStatus;

    public PaymentStatus getStatus() {
        return paymentStatus;
    }
}
