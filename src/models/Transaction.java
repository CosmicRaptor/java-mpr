import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Transaction {
    private final String transactionId;
    private final String accountName;
    private final String toAccount;
    private final String transactionType;
    private final Integer amount;
    private final Date transactionDate;
    private final Integer balanceAfterTransaction;

    @JsonCreator
    public Transaction(
        @JsonProperty("transactionId") String transactionId,
        @JsonProperty("accountName") String accountName,
        @JsonProperty("toAccount") String toAccount,
        @JsonProperty("transactionType") String transactionType,
        @JsonProperty("amount") Integer amount,
        @JsonProperty("transactionDate") Date transactionDate,
        @JsonProperty("balanceAfterTransaction") Integer balanceAfterTransaction
    ) {
        this.transactionId = transactionId;
        this.accountName = accountName;
        this.toAccount = toAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    // Getter methods
    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Integer getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Integer getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }
}
