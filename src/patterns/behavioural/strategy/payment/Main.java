package patterns.behavioural.strategy.payment;

import patterns.behavioural.strategy.payment.creditcard.PaymentByCreditCard;
import patterns.behavioural.strategy.payment.service.PaymentService;

public class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.setStrategy(new PaymentByCreditCard());
        paymentService.processOrder();
    }
}
