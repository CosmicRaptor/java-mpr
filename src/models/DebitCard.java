
public class DebitCard {
    String cardNumber;
    String cardHolder;
    String expiryDate;
    String cvv;
    double onlineLimit;
    double merchantLimit;
    double atmLimit;
    double internationalLimit;
    boolean disabled;

    DebitCard(String cardNumber, String cardHolder, String expiryDate, String cvv, double onlineLimit,
            double merchantLimit, double atmLimit, double internationalLimit, boolean disabled) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.onlineLimit = onlineLimit;
        this.merchantLimit = merchantLimit;
        this.atmLimit = atmLimit;
        this.internationalLimit = internationalLimit;
        this.disabled = disabled;
    }
}