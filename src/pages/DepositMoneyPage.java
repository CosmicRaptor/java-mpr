import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DepositMoneyPage extends JFrame {
    Account selectedAccount;
    int selectedIndex;

    // Constructor to initialize the UI
    public DepositMoneyPage() {
        // Set up frame properties
        setTitle("Deposit Money");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new GridBagLayout());

        JComboBox<String> accountDropdown;

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Labels and text fields for entering details
        JLabel accountSelectLabel = new JLabel("Select Account:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountSelectLabel, gbc);

        // Dropdown for selecting which account to deposit to
        accountDropdown = new JComboBox<>();
        for (Account account : UsernameData.accounts) {
            accountDropdown.addItem(account.name);
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(accountDropdown, gbc);

        accountDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAccountDetails(accountDropdown.getSelectedIndex());
            }
        });

        // "Amount" label and text field
        JLabel amountLabel = new JLabel("Deposit Amount:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(amountField, gbc);

        // "Notes" label and text field
        JLabel notesLabel = new JLabel("Notes (optional):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(notesLabel, gbc);

        JTextField notesField = new JTextField();
        notesField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(notesField, gbc);

        // Deposit button
        JButton depositButton = new JButton("Deposit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(depositButton, gbc);

        // Action listener for the deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = amountField.getText();
                String notes = notesField.getText();

                // Simple validation and success message
                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(DepositMoneyPage.this, "Please enter the deposit amount.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int depositAmount = Integer.parseInt(amount);
                    UsernameData.accounts[selectedIndex].balance += depositAmount;

                    Transaction transaction = new Transaction(
                        String.valueOf(System.currentTimeMillis()),
                        "Deposit",
                        selectedAccount.name,
                        "Deposit",
                        depositAmount,
                        new Date(),
                        UsernameData.accounts[selectedIndex].balance,
                        notes
                    );
                    TransactionUtil.appendTransaction(transaction);
                    JsonUtils.updateAccountBalance(selectedIndex, UsernameData.accounts[selectedIndex].balance);
                    JOptionPane.showMessageDialog(DepositMoneyPage.this, "Deposit of $" + amount + " to account " + selectedAccount.name + " successful.", "Deposit Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Initialize with the first account details
        updateAccountDetails(0);
    }

    // Method to update the account details when selecting an account
    private void updateAccountDetails(int index) {
        selectedAccount = UsernameData.accounts[index];
        selectedIndex = index;
    }

    public static void main(String[] args) {
        // Launch the DepositMoneyPage
        DepositMoneyPage depositMoneyPage = new DepositMoneyPage();
        depositMoneyPage.setVisible(true);
    }
}
