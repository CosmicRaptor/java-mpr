import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetSpendingLimitPage extends JFrame {

    private JTextField dailyLimitField;
    private JTextField weeklyLimitField;
    private JTextField monthlyLimitField;
    private JTextField transactionLimitField;

    private JCheckBox dailyLimitCheckbox;
    private JCheckBox weeklyLimitCheckbox;
    private JCheckBox monthlyLimitCheckbox;
    private JCheckBox transactionLimitCheckbox;

    private DebitCardInfoPage parentPage;

    // Constructor to set up the UI
    public SetSpendingLimitPage(DebitCardInfoPage parentPage) {
        this.parentPage = parentPage; // Store reference to the parent page

        setTitle("Set Spending Limits");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setLocationRelativeTo(null); // Center the frame

        // Create a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3, 10, 10)); // 5 rows, 3 columns, with spacing

        // Limit labels and input fields
        JLabel dailyLimitLabel = new JLabel("Daily Limit ($):");
        dailyLimitField = new JTextField();
        dailyLimitCheckbox = new JCheckBox("Apply");

        JLabel weeklyLimitLabel = new JLabel("Weekly Limit ($):");
        weeklyLimitField = new JTextField();
        weeklyLimitCheckbox = new JCheckBox("Apply");

        JLabel monthlyLimitLabel = new JLabel("Monthly Limit ($):");
        monthlyLimitField = new JTextField();
        monthlyLimitCheckbox = new JCheckBox("Apply");

        JLabel transactionLimitLabel = new JLabel("Transaction Limit ($):");
        transactionLimitField = new JTextField();
        transactionLimitCheckbox = new JCheckBox("Apply");

        // Confirm and Cancel buttons
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        // ActionListener for the confirm button
        confirmButton.addActionListener(e -> {
            StringBuilder limitText = new StringBuilder("Limits Set: \n");

            // Check if daily limit is selected and valid
            if (dailyLimitCheckbox.isSelected()) {
                String dailyLimit = dailyLimitField.getText();
                if (!dailyLimit.isEmpty()) {
                    limitText.append("Daily Limit: $").append(dailyLimit).append("\n");
                }
            }

            // Check if weekly limit is selected and valid
            if (weeklyLimitCheckbox.isSelected()) {
                String weeklyLimit = weeklyLimitField.getText();
                if (!weeklyLimit.isEmpty()) {
                    limitText.append("Weekly Limit: $").append(weeklyLimit).append("\n");
                }
            }

            // Check if monthly limit is selected and valid
            if (monthlyLimitCheckbox.isSelected()) {
                String monthlyLimit = monthlyLimitField.getText();
                if (!monthlyLimit.isEmpty()) {
                    limitText.append("Monthly Limit: $").append(monthlyLimit).append("\n");
                }
            }

            // Check if transaction limit is selected and valid
            if (transactionLimitCheckbox.isSelected()) {
                String transactionLimit = transactionLimitField.getText();
                if (!transactionLimit.isEmpty()) {
                    limitText.append("Transaction Limit: $").append(transactionLimit).append("\n");
                }
            }

            // Display the selected limits
            if (limitText.length() > 12) {  // Check if any limit is set (length > initial "Limits Set: \n")
                JOptionPane.showMessageDialog(null, limitText.toString());
                parentPage.updateSpendingLimit(limitText.toString()); // Update spending limit in the parent page
                dispose(); // Close the window after confirming
            } else {
                JOptionPane.showMessageDialog(null, "No limits were set. Please select at least one limit.");
            }
        });

        // ActionListener for the cancel button
        cancelButton.addActionListener(e -> dispose()); // Close the window without saving

        // Add components to the panel
        panel.add(dailyLimitLabel);
        panel.add(dailyLimitField);
        panel.add(dailyLimitCheckbox);

        panel.add(weeklyLimitLabel);
        panel.add(weeklyLimitField);
        panel.add(weeklyLimitCheckbox);

        panel.add(monthlyLimitLabel);
        panel.add(monthlyLimitField);
        panel.add(monthlyLimitCheckbox);

        panel.add(transactionLimitLabel);
        panel.add(transactionLimitField);
        panel.add(transactionLimitCheckbox);

        panel.add(confirmButton);
        panel.add(cancelButton);

        // Add the panel to the frame
        add(panel);
    }
}
