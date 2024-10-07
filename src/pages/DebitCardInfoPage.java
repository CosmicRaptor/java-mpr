import javax.swing.*;
import java.awt.*;
public class DebitCardInfoPage extends JFrame {

    private JLabel spendingLimitValue;

    // Constructor to set up the UI
    public DebitCardInfoPage() {
        setTitle("Bank Management System - Debit Card Info");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Creating a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, with spacing

        // Card details as temporary text values
        String cardNumber = "1234 5678 9012 3456";
        String cardHolder = "John Doe";
        String expiryDate = "12/25";
        String cvv = "*";
        String balance = "$1,200.50";
        String spendingLimit = "No Limit";  // Default spending limit text

        // Adding labels to display card details
        JLabel cardNumberLabel = new JLabel("Card Number:");
        JLabel cardNumberValue = new JLabel(cardNumber);

        JLabel cardHolderLabel = new JLabel("Cardholder's Name:");
        JLabel cardHolderValue = new JLabel(cardHolder);

        JLabel expiryDateLabel = new JLabel("Expiry Date:");
        JLabel expiryDateValue = new JLabel(expiryDate);

        JLabel cvvLabel = new JLabel("CVV:");
        JLabel cvvValue = new JLabel(cvv);

        JLabel balanceLabel = new JLabel("Available Balance:");
        JLabel balanceValue = new JLabel(balance);

        // Spending limit
        JLabel spendingLimitLabel = new JLabel("Spending Limit:");
        spendingLimitValue = new JLabel(spendingLimit);

        // Add a "Disable Card" checkbox
        JCheckBox disableCardCheckbox = new JCheckBox("Disable Card");

        // Add a "Set Spending Limit" button
        JButton setLimitButton = new JButton("Set Spending Limit");

        // ActionListener for the "Disable Card" checkbox
        disableCardCheckbox.addActionListener(e -> {
            if (disableCardCheckbox.isSelected()) {
                // Simulate card being disabled
                JOptionPane.showMessageDialog(null, "Card Disabled!");
            } else {
                // Simulate card being re-enabled
                JOptionPane.showMessageDialog(null, "Card Enabled!");
            }
        });

        // ActionListener for the "Set Spending Limit" button
        setLimitButton.addActionListener(e -> {
            // Open the Set Spending Limit Page
            SetSpendingLimitPage setSpendingLimitPage = new SetSpendingLimitPage();
            setSpendingLimitPage.setVisible(true);
        });

        // Add components to the panel
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
        panel.add(spendingLimitLabel);
        panel.add(spendingLimitValue);
        panel.add(disableCardCheckbox); // Adding the disable card checkbox
        panel.add(setLimitButton); // Adding the set spending limit button

        // Add the panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        // Create and display the Debit Card Info Page
        DebitCardInfoPage debitCardPage = new DebitCardInfoPage();
        debitCardPage.setVisible(true);
    }
}
