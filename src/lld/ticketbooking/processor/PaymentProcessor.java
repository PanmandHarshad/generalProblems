package lld.ticketbooking.processor;

import lld.ticketbooking.model.Booking;
import lld.ticketbooking.model.GatewayPaymentResponse;
import lld.ticketbooking.model.Payment;

public interface PaymentProcessor {

    GatewayPaymentResponse initiatePayment(Booking booking, Payment payment);

    GatewayPaymentResponse fetchPaymentStatus(Payment payment);
}