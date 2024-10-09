import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DebitCard {
    private final String cardNumber;
    private final String cardHolder;
    private final String expiryDate;
    private final String cvv;
    private DebitCardLimit spendingLimit; // Include the spending limit
    private final boolean disabled;

    @JsonCreator
    public DebitCard(
        @JsonProperty("cardNumber") String cardNumber,
        @JsonProperty("cardHolder") String cardHolder,
        @JsonProperty("expiryDate") String expiryDate,
        @JsonProperty("cvv") String cvv,
        @JsonProperty("spendingLimit") DebitCardLimit spendingLimit, // Update to include spendingLimit
        @JsonProperty("disabled") boolean disabled
    ) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.spendingLimit = spendingLimit;
        this.disabled = disabled;
    }

    // Getter methods for DebitCard properties
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public DebitCardLimit getSpendingLimit() {
        return spendingLimit;
    }

    public boolean isDisabled() {
        return disabled;
    }

    // Setter method for spendingLimit

    public void setSpendingLimit(DebitCardLimit spendingLimit) {
        this.spendingLimit = spendingLimit;
    }
}
