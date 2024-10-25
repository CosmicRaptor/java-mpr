import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHistoryPage extends JFrame {
    private JTable transactionTable;
    private JTextField startDateField, endDateField, minAmountField, maxAmountField;
    private JComboBox<String> transactionTypeComboBox;
    private List<Transaction> allTransactions; // Original transaction data

    // Constructor to set up the UI
    public TransactionHistoryPage() {
        TransactionUtil.setTransactionGlobal();
        allTransactions = List.of(UsernameData.transactions); // Load original transactions
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
        startDateField = new JTextField("Start Date (yyyy-MM-dd)");
        filterPanel.add(startDateField, gbc);

        gbc.gridx = 2;
        endDateField = new JTextField("End Date (yyyy-MM-dd)");
        filterPanel.add(endDateField, gbc);

        // Transaction Type Filter
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Transaction Type:"), gbc);

        gbc.gridx = 1;
        transactionTypeComboBox = new JComboBox<>(new String[]{"All", "Credit", "Debit", "Transfer", "Deposit"});
        filterPanel.add(transactionTypeComboBox, gbc);

        // Amount Range Filter
        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(new JLabel("Amount Range:"), gbc);

        gbc.gridx = 1;
        minAmountField = new JTextField("Min Amount");
        filterPanel.add(minAmountField, gbc);

        gbc.gridx = 2;
        maxAmountField = new JTextField("Max Amount");
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
        transactionTable = new JTable(new Object[0][5], columnNames);
        JScrollPane tableScrollPane = new JScrollPane(transactionTable);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Event Listeners
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFilters();
            }
        });

        // Load initial data into the table
        updateTable(allTransactions);
    }

    // Method to apply filters to the transaction history
    private void applyFilters() {
        List<Transaction> filteredTransactions = new ArrayList<>(allTransactions);

        // Filter by date range
        String startDateStr = startDateField.getText();
        String endDateStr = endDateField.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = !startDateStr.equals("Start Date (yyyy-MM-dd)") ? sdf.parse(startDateStr) : null;
            Date endDate = !endDateStr.equals("End Date (yyyy-MM-dd)") ? sdf.parse(endDateStr) : null;

            if (startDate != null) {
                filteredTransactions.removeIf(transaction -> transaction.getTransactionDate().before(startDate));
            }
            if (endDate != null) {
                filteredTransactions.removeIf(transaction -> transaction.getTransactionDate().after(endDate));
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Filter by transaction type
        String selectedType = (String) transactionTypeComboBox.getSelectedItem();
        if (!"All".equals(selectedType)) {
            filteredTransactions.removeIf(transaction -> !transaction.getTransactionType().equalsIgnoreCase(selectedType));
        }

        // Filter by amount range
        try {
            double minAmount = !minAmountField.getText().equals("Min Amount") ? Double.parseDouble(minAmountField.getText()) : Double.MIN_VALUE;
            double maxAmount = !maxAmountField.getText().equals("Max Amount") ? Double.parseDouble(maxAmountField.getText()) : Double.MAX_VALUE;
            filteredTransactions.removeIf(transaction -> transaction.getAmount() < minAmount || transaction.getAmount() > maxAmount);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the table with the filtered data
        updateTable(filteredTransactions);
    }

    // Method to reset filters to default
    private void resetFilters() {
        startDateField.setText("Start Date (yyyy-MM-dd)");
        endDateField.setText("End Date (yyyy-MM-dd)");
        transactionTypeComboBox.setSelectedIndex(0);
        minAmountField.setText("Min Amount");
        maxAmountField.setText("Max Amount");
        updateTable(allTransactions);
    }

    // Method to update the table with the filtered transaction data
    private void updateTable(List<Transaction> transactions) {
        String[] columnNames = {"Date", "Description", "Type", "Amount", "Balance"};
        Object[][] data = new Object[transactions.size()][5];
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            data[i][0] = new SimpleDateFormat("yyyy-MM-dd").format(transaction.getTransactionDate());
            data[i][1] = transaction.getNotes() != null ? transaction.getNotes() : "";
            data[i][2] = transaction.getTransactionType();
            data[i][3] = "$" + transaction.getAmount() + ".00";
            data[i][4] = "$" + transaction.getBalanceAfterTransaction() + ".00";
        }

        transactionTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
        // Create and display the Transaction History Page
        TransactionHistoryPage transactionHistoryPage = new TransactionHistoryPage();
        transactionHistoryPage.setVisible(true);
    }
}
