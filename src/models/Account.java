 // Dummy classes to simulate the data structure
public class Account {
    String name;
    double balance;
    String type;
    DebitCard debitCard;

    Account(String name, double balance, String type, DebitCard debitCard) {
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.debitCard = debitCard;
    }
}