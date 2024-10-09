import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DebitCardInfoPage extends JFrame {

    private JLabel cardNumberValue, cardHolderValue, expiryDateValue, cvvValue, balanceValue, spendingLimitValue;
    private JComboBox<String> cardDropdown;

    // Constructor to set up the UI
    public DebitCardInfoPage() {
        
        setTitle("Bank Management System - Debit Card Info");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Creating a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10)); // Updated for additional dropdown

        // Dropdown for selecting which card
        JLabel cardSelectLabel = new JLabel("Select Account:");
        cardDropdown = new JComboBox<>();
        for (Account account : UsernameData.accounts) {
            cardDropdown.addItem(account.name);
        }
        cardDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCardDetails(cardDropdown.getSelectedIndex());
            }
        });

        // Adding labels to display card details
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberValue = new JLabel();

        JLabel cardHolderLabel = new JLabel("Cardholder's Name:");
        cardHolderValue = new JLabel();

        JLabel expiryDateLabel = new JLabel("Expiry Date:");
        expiryDateValue = new JLabel();

        JLabel cvvLabel = new JLabel("CVV:");
        cvvValue = new JLabel();

        JLabel balanceLabel = new JLabel("Available Balance:");
        balanceValue = new JLabel();

        JLabel spendingLimitLabel = new JLabel("Spending Limit:");
        spendingLimitValue = new JLabel();

        // Add a "Disable Card" checkbox
        JCheckBox disableCardCheckbox = new JCheckBox("Disable Card");
        disableCardCheckbox.addActionListener(e -> {
            if (disableCardCheckbox.isSelected()) {
                JOptionPane.showMessageDialog(null, "Card Disabled!");
            } else {
                JOptionPane.showMessageDialog(null, "Card Enabled!");
            }
        });

        // Add a "Set Spending Limit" button
        JButton setLimitButton = new JButton("Set Spending Limit");
        setLimitButton.addActionListener(e -> {
            SetSpendingLimitPage setSpendingLimitPage = new SetSpendingLimitPage();
            setSpendingLimitPage.setVisible(true);
        });

        // Add components to the panel
        panel.add(cardSelectLabel);
        panel.add(cardDropdown);
        panel.add(cardNumberLabel);
        panel.add(cardNumberValue);
        panel.add(cardHolderLabel);
        panel.add(cardHolderValue);
        panel.add(expiryDateLabel);
        panel.add(expiryDateValue);
        panel.add(cvvLabel);
        panel.add(cvvValue);
        panel.add(balanceLabel);
        panel.add(balanceValue);
        // panel.add(spendingLimitLabel);
        // panel.add(spendingLimitValue);
        panel.add(disableCardCheckbox);
        panel.add(setLimitButton);

        // Add the panel to the frame
        add(panel);

        // Set initial card details to the first account
        updateCardDetails(0);
    }

    // Method to update card details based on selected index
    private void updateCardDetails(int index) {
        Account selectedAccount = UsernameData.accounts[index];
        DebitCard card = selectedAccount.debitCard;
        UsernameData.selecteDebitCard = card;

        cardNumberValue.setText(card.getCardNumber());
        cardHolderValue.setText(card.getCardHolder());
        expiryDateValue.setText(card.getExpiryDate());
        cvvValue.setText(card.getCvv());
        balanceValue.setText("$" + selectedAccount.balance);
        // spendingLimitValue.setText("Online: " + card.onlineLimit + ", Merchant: " + card.merchantLimit +
                // ", ATM: " + card.atmLimit + ", Intl: " + card.internationalLimit);
    }

    public static void main(String[] args) {
        // Create and display the Debit Card Info Page
        DebitCardInfoPage debitCardPage = new DebitCardInfoPage();
        debitCardPage.setVisible(true);
    }
}
