package lld.parkinglot.service;

import lld.parkinglot.model.Payment;

import java.math.BigDecimal;

public interface PaymentService {
    String getPaymentType();
    Payment doPayment(
            String ticketId,
            BigDecimal amount);}
