package lld.ticketbooking.service;

import lld.ticketbooking.exception.PaymentOutcomeUnknownException;
import lld.ticketbooking.model.Booking;
import lld.ticketbooking.model.GatewayPaymentResponse;
import lld.ticketbooking.model.Payment;
import lld.ticketbooking.model.enums.PaymentStatus;
import lld.ticketbooking.model.enums.PaymentType;
import lld.ticketbooking.processor.PaymentProcessor;

import java.util.Map;

public class PaymentService {

    private final Map<PaymentType, PaymentProcessor> processors;

    public PaymentService(Map<PaymentType, PaymentProcessor> processors) {
        this.processors = Map.copyOf(processors);
    }

    public Payment pay(Booking booking, Payment payment) {
        PaymentProcessor processor = getProcessor(payment.getPaymentType());

        // Payment must already be recorded on the Booking
        // with PENDING status before this external call.
        try {

            GatewayPaymentResponse response = processor.initiatePayment(booking, payment);
            applyResponse(payment, response);

        } catch (PaymentOutcomeUnknownException e) {
            // Do NOT mark FAILED.
            // The gateway might have completed the payment.
            payment.setPaymentStatus(PaymentStatus.PENDING);
        }

        return payment;
    }

    public PaymentStatus refreshStatus(Payment payment) {
        if (payment.getPaymentStatus() != PaymentStatus.PENDING) {
            return payment.getPaymentStatus();
        }

        PaymentProcessor processor = getProcessor(payment.getPaymentType());

        try {

            GatewayPaymentResponse response = processor.fetchPaymentStatus(payment);
            applyResponse(payment, response);

        } catch (PaymentOutcomeUnknownException e) {
            // Status is still unknown. Leave the payment PENDING.
        }

        return payment.getPaymentStatus();
    }

    private PaymentProcessor getProcessor(PaymentType paymentType) {
        PaymentProcessor processor = processors.get(paymentType);

        if (processor == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }

        return processor;
    }

    private void applyResponse(Payment payment, GatewayPaymentResponse response) {
        if (response.transactionId() != null) {
            payment.setTransactionId(response.transactionId());
        }

        payment.setPaymentStatus(response.status());
    }
}