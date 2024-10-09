import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TransactionHistoryPage extends JFrame {

    // Constructor to set up the UI
    public TransactionHistoryPage() {
        TransactionUtil.setTransactionGlobal();
        setTitle("Bank Management System - Transaction History");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Main Panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Filter Panel with GridBagLayout
        JPanel filterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Date Range Filter
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterPanel.add(new JLabel("Date Range:"), gbc);

        gbc.gridx = 1;
        JTextField startDateField = new JTextField("Start Date");
        filterPanel.add(startDateField, gbc);

        gbc.gridx = 2;
        JTextField endDateField = new JTextField("End Date");
        filterPanel.add(endDateField, gbc);

        // Transaction Type Filter
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Transaction Type:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> transactionTypeComboBox = new JComboBox<>(new String[]{"All", "Credit", "Debit"});
        filterPanel.add(transactionTypeComboBox, gbc);

        // Amount Range Filter
        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(new JLabel("Amount Range:"), gbc);

        gbc.gridx = 1;
        JTextField minAmountField = new JTextField("Min Amount");
        filterPanel.add(minAmountField, gbc);

        gbc.gridx = 2;
        JTextField maxAmountField = new JTextField("Max Amount");
        filterPanel.add(maxAmountField, gbc);

        // Filter and Reset Buttons
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JButton filterButton = new JButton("Apply Filters");
        filterPanel.add(filterButton, gbc);

        gbc.gridx = 2;
        JButton resetButton = new JButton("Reset Filters");
        filterPanel.add(resetButton, gbc);

        mainPanel.add(filterPanel, BorderLayout.CENTER);

        // Transaction Table
        String[] columnNames = {"Date", "Description", "Type", "Amount", "Balance"};
        Object[][] data = new Object[UsernameData.transactions.length][5];
        for(int i = 0; i < UsernameData.transactions.length; i++){
            Transaction transaction = UsernameData.transactions[i];
            data[i][0] = transaction.getTransactionDate(); // Convert to Date for display
            data[i][1] = transaction.getNotes(); // Replace with actual description if available
            data[i][2] = transaction.getTransactionType(); // Transaction Type
            data[i][3] = "$" + transaction.getAmount() + ".00"; // Amount
            data[i][4] = "$" + transaction.getBalanceAfterTransaction() + ".00"; // Balance After Transaction
        }

        JTable transactionTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(transactionTable);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Event Listeners
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to apply filters to the transaction history
                JOptionPane.showMessageDialog(null, "Filters Applied! (Logic not implemented)");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to reset all filters to default
                startDateField.setText("Start Date");
                endDateField.setText("End Date");
                transactionTypeComboBox.setSelectedIndex(0);
                minAmountField.setText("Min Amount");
                maxAmountField.setText("Max Amount");
            }
        });
    }

    public static void main(String[] args) {
        // Create and display the Transaction History Page
        TransactionHistoryPage transactionHistoryPage = new TransactionHistoryPage();
        transactionHistoryPage.setVisible(true);
    }
}
