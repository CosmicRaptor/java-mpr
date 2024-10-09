import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DebitCardLimit {
    private final int onlineLimit;
    private final int merchantLimit;
    private final int atmLimit;
    private final int internationalLimit;

    @JsonCreator
    public DebitCardLimit(
        @JsonProperty("onlineLimit") int onlineLimit,
        @JsonProperty("merchantLimit")int merchantLimit,
        @JsonProperty("atmLimit")int atmLimit,
        @JsonProperty("internationalLimit")int internationalLimit
    ) {
        this.onlineLimit = onlineLimit;
        this.merchantLimit = merchantLimit;
        this.atmLimit = atmLimit;
        this.internationalLimit = internationalLimit;
    }

    // Getter methods
    public int getOnlineLimit() {
        return onlineLimit;
    }

    public int getMerchantLimit() {
        return merchantLimit;
    }

    public int getAtmLimit() {
        return atmLimit;
    }

    public int getInternationalLimit() {
        return internationalLimit;
    }


}