public class User {
    public String username;
    private String password;
    public Account[] accounts;

    public User(String username, String password, Account[] accounts) {
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }
}
