import java.util.Date;

public class Transaction {
    final String transactionId;
    final String accountName;
    final String accountType;
    final String transactionType;
    final Integer amount;
    final Date transactionDate;
    final Integer balanceAfterTransaction;

    public Transaction(String transactionId, String accountName, String accountType, String transactionType, Integer amount, Date transactionDate, Integer balanceAfterTransaction) {
        this.transactionId = transactionId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
}
