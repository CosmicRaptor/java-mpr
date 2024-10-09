import javax.swing.*;
import java.awt.*;

public class SetSpendingLimitPage extends JFrame {

    private JTextField dailyLimitField;
    private JTextField weeklyLimitField;
    private JTextField monthlyLimitField;
    private JTextField transactionLimitField;

    private JCheckBox atmWithdrawlLimitCheckbox;
    private JCheckBox onlineTransactionLimitCheckbox;
    private JCheckBox merchantTransactionLimitCheckbox;
    private JCheckBox internationalLimitCheckbox;

    // Constructor to set up the UI
    public SetSpendingLimitPage() {

        setTitle("Set Spending Limits");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setLocationRelativeTo(null); // Center the frame

        // Create a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3, 10, 10)); // 5 rows, 3 columns, with spacing

        // Limit labels and input fields
        JLabel dailyLimitLabel = new JLabel("ATM Limit ($):");
        dailyLimitField = new JTextField();
        dailyLimitField.setText(String.valueOf(UsernameData.selecteDebitCard.getSpendingLimit().getAtmLimit()));
        atmWithdrawlLimitCheckbox = new JCheckBox("Apply");

        JLabel weeklyLimitLabel = new JLabel("Online transaction Limit :");
        weeklyLimitField = new JTextField();
        weeklyLimitField.setText(String.valueOf(UsernameData.selecteDebitCard.getSpendingLimit().getOnlineLimit()));
        onlineTransactionLimitCheckbox = new JCheckBox("Apply");

        JLabel monthlyLimitLabel = new JLabel("Merchant Limit :");
        monthlyLimitField = new JTextField();
        monthlyLimitField.setText(String.valueOf(UsernameData.selecteDebitCard.getSpendingLimit().getMerchantLimit()));
        merchantTransactionLimitCheckbox = new JCheckBox("Apply");

        JLabel transactionLimitLabel = new JLabel("International Limit :");
        transactionLimitField = new JTextField();
        transactionLimitField.setText(String.valueOf(UsernameData.selecteDebitCard.getSpendingLimit().getInternationalLimit()));
        internationalLimitCheckbox = new JCheckBox("Apply");

        // Confirm and Cancel buttons
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        // ActionListener for the confirm button
        confirmButton.addActionListener(e -> {

            Integer atmLimit = atmWithdrawlLimitCheckbox.isSelected() ? Integer.parseInt(dailyLimitField.getText()) : UsernameData.selecteDebitCard.getSpendingLimit().getAtmLimit();
            Integer onlineLimit = onlineTransactionLimitCheckbox.isSelected() ? Integer.parseInt(weeklyLimitField.getText()) : UsernameData.selecteDebitCard.getSpendingLimit().getOnlineLimit();
            Integer merchantLimit = merchantTransactionLimitCheckbox.isSelected() ? Integer.parseInt(monthlyLimitField.getText()) : UsernameData.selecteDebitCard.getSpendingLimit().getMerchantLimit();
            Integer internationalLimit = internationalLimitCheckbox.isSelected() ? Integer.parseInt(transactionLimitField.getText()) : UsernameData.selecteDebitCard.getSpendingLimit().getInternationalLimit();

            DebitCardLimit spendingLimit = new DebitCardLimit(onlineLimit, merchantLimit, atmLimit, internationalLimit);
            UsernameData.selecteDebitCard.setSpendingLimit(spendingLimit);
            JOptionPane.showMessageDialog(null, "Spending Limits Updated!");
        });

        // ActionListener for the cancel button
        cancelButton.addActionListener(e -> dispose()); // Close the window without saving

        // Add components to the panel
        panel.add(dailyLimitLabel);
        panel.add(dailyLimitField);
        panel.add(atmWithdrawlLimitCheckbox);

        panel.add(weeklyLimitLabel);
        panel.add(weeklyLimitField);
        panel.add(onlineTransactionLimitCheckbox);

        panel.add(monthlyLimitLabel);
        panel.add(monthlyLimitField);
        panel.add(merchantTransactionLimitCheckbox);

        panel.add(transactionLimitLabel);
        panel.add(transactionLimitField);
        panel.add(internationalLimitCheckbox);

        panel.add(confirmButton);
        panel.add(cancelButton);

        // Add the panel to the frame
        add(panel);
    }
}
