package lld.ticketbooking.processor;

import lld.ticketbooking.model.GatewayPaymentResponse;

public interface PaymentGatewayClient {
    GatewayPaymentResponse initiatePayment(String merchantReference, double amount);

    GatewayPaymentResponse fetchPaymentStatus(String merchantReference);
}
