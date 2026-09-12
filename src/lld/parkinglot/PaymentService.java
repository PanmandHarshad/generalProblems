package lld.parkinglot;

import java.math.BigDecimal;

public interface PaymentService {
    String getPaymentType();
    Payment doPayment(
            String ticketId,
            BigDecimal amount);}
