package patterns.behavioural.strategy.payment.Paypal;

import patterns.behavioural.strategy.payment.PaymentStrategy;

public class PaymentByPayPal implements PaymentStrategy {
    private String email;
    private String password;

    @Override
    public void collectPaymentDetails() {
        // Pop-up collect PayPal mail and password
        email = "...";
        password = "...";
    }

    @Override
    public boolean validatePaymentDetails() {
        // Validate account ...
        return false;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal");
    }
}
