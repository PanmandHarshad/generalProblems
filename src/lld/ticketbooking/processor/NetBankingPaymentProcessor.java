package lld.ticketbooking.processor;

import lld.ticketbooking.model.Booking;
import lld.ticketbooking.model.GatewayPaymentResponse;
import lld.ticketbooking.model.Payment;

public class NetBankingPaymentProcessor implements PaymentProcessor{
    private final PaymentGatewayClient gatewayClient;

    public NetBankingPaymentProcessor(PaymentGatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
    }

    @Override
    public GatewayPaymentResponse initiatePayment(Booking booking, Payment payment) {
        return gatewayClient.initiatePayment(payment.getPaymentId(), payment.getAmount());
    }

    @Override
    public GatewayPaymentResponse fetchPaymentStatus(Payment payment) {
        return gatewayClient.fetchPaymentStatus(payment.getPaymentId());
    }
}
