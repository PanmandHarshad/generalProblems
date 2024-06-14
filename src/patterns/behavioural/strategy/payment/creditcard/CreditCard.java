package patterns.behavioural.strategy.payment.creditcard;

public class CreditCard {
    private String cardNumber;
    private String cardExpiry;
    private String cardCVV;
    private int amount;

    public CreditCard(String cardNumber, String cardExpiry, String cardCVV) {
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cardCVV = cardCVV;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
