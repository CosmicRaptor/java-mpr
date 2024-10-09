import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TransferMoneyPage extends JFrame {
    Account selectedAccount;
    DebitCard selectedCard;

    // Constructor to initialize the UI
    public TransferMoneyPage() {
        // Set up frame properties
        setTitle("Transfer Money");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridBagLayout());

        JComboBox<String> cardDropdown;

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Labels and text fields for entering details
        JLabel fromAccountLabel = new JLabel("From Account:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fromAccountLabel, gbc);

        // Dropdown for selecting which account/card
        JLabel cardSelectLabel = new JLabel("Select Account:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cardSelectLabel, gbc);

        cardDropdown = new JComboBox<>();
        for (Account account : UsernameData.accounts) {
            cardDropdown.addItem(account.name);
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cardDropdown, gbc);

        cardDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCardDetails(cardDropdown.getSelectedIndex());
            }
        });

        // "To Account" label and text field
        JLabel toAccountLabel = new JLabel("To Account:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toAccountLabel, gbc);

        JTextField toAccountField = new JTextField();
        toAccountField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(toAccountField, gbc);

        // "Amount" label and text field
        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(amountField, gbc);

        // "Notes" label and text field
        JLabel notesLabel = new JLabel("Notes (optional):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(notesLabel, gbc);

        JTextField notesField = new JTextField();
        notesField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(notesField, gbc);

        // Transfer button
        JButton transferButton = new JButton("Transfer");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(transferButton, gbc);

        // Action listener for the transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAccount = cardDropdown.getSelectedItem().toString();
                String toAccount = toAccountField.getText();
                String amount = amountField.getText();

                // Simple validation and success message
                if (toAccount.isEmpty() || amount.isEmpty()) {
                    JOptionPane.showMessageDialog(TransferMoneyPage.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Transaction transaction = new Transaction(
                        String.valueOf(System.currentTimeMillis()),
                        fromAccount,
                        toAccount,
                        "Transfer",
                        Integer.parseInt(amount),
                        new Date(),
                        1000
                    );
                    TransactionUtil.appendTransaction(transaction);
                    JOptionPane.showMessageDialog(TransferMoneyPage.this, "Transfer of $" + amount + " to account " + toAccount + " successful.", "Transfer Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Initialize with the first account details
        updateCardDetails(0);
    }

    // Method to update the card details when selecting an account
    private void updateCardDetails(int index) {
        selectedAccount = UsernameData.accounts[index];
        selectedCard = selectedAccount.debitCard;
    }

    public static void main(String[] args) {
        // Launch the TransferMoneyPage
        TransferMoneyPage transferMoneyPage = new TransferMoneyPage();
        transferMoneyPage.setVisible(true);
    }
}
