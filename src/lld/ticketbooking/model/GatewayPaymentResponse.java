package lld.ticketbooking.model;

import lld.ticketbooking.model.enums.PaymentStatus;

public record GatewayPaymentResponse(String transactionId, PaymentStatus status) {
}
