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
    private final Double balanceAfterTransaction;
    private final String notes;

    @JsonCreator
    public Transaction(
        @JsonProperty("transactionId") String transactionId,
        @JsonProperty("accountName") String accountName,
        @JsonProperty("toAccount") String toAccount,
        @JsonProperty("transactionType") String transactionType,
        @JsonProperty("amount") Integer amount,
        @JsonProperty("transactionDate") Date transactionDate,
        @JsonProperty("balanceAfterTransaction") Double balanceAfterTransaction,
        @JsonProperty("notes") String notes
    ) {
        this.transactionId = transactionId;
        this.accountName = accountName;
        this.toAccount = toAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.notes = notes;
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

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public String getNotes() {
        return notes;
    }
}
