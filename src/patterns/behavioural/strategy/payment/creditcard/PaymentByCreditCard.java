package patterns.behavioural.strategy.payment.creditcard;

import patterns.behavioural.strategy.payment.PaymentStrategy;

public class PaymentByCreditCard implements PaymentStrategy {

    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        // Pop-up collect card details
        card = new CreditCard("credit card", "card expiry", "cvv");
    }

    @Override
    public boolean validatePaymentDetails() {
        // Validate the credit card
        return true;
    }

    @Override
    public void pay(int amount) {

        System.out.println("Paying amount " + amount + " using credit card");

        card.setAmount(card.getAmount() - amount);
    }
}
