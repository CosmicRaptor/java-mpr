import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FixedDepositHistoryPage extends JFrame {

    // Constructor to set up the UI
    public FixedDepositHistoryPage() {
        setTitle("Bank Management System - Fixed Deposit History");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Main Panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel("Fixed Deposit History", SwingConstants.CENTER);
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
        filterPanel.add(new JLabel("Deposit Date Range:"), gbc);

        gbc.gridx = 1;
        JTextField startDateField = new JTextField("Start Date");
        filterPanel.add(startDateField, gbc);

        gbc.gridx = 2;
        JTextField endDateField = new JTextField("End Date");
        filterPanel.add(endDateField, gbc);

        // Maturity Period Filter
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Maturity Period (Months):"), gbc);

        gbc.gridx = 1;
        JComboBox<String> maturityPeriodComboBox = new JComboBox<>(new String[]{"All", "1-3", "4-6", "7-12", "12+"});
        filterPanel.add(maturityPeriodComboBox, gbc);

        // Deposit Amount Range Filter
        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(new JLabel("Deposit Amount Range:"), gbc);

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

        // Fixed Deposit Table
        String[] columnNames = {"Account Number", "Deposit Date", "Maturity Date", "Interest Rate (%)", "Deposit Amount"};
        Object[][] data = {
                {"123456789", "01/01/2023", "01/01/2024", "6.5", "$10,000.00"},
                {"987654321", "15/02/2023", "15/02/2025", "7.0", "$5,000.00"},
                {"456789123", "30/03/2023", "30/03/2024", "6.8", "$8,500.00"},
                {"789123456", "20/04/2023", "20/04/2026", "7.2", "$15,000.00"},
        };

        JTable fixedDepositTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(fixedDepositTable);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Event Listeners
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to apply filters to the fixed deposit history
                JOptionPane.showMessageDialog(null, "Filters Applied! (Logic not implemented)");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to reset all filters to default
                startDateField.setText("Start Date");
                endDateField.setText("End Date");
                maturityPeriodComboBox.setSelectedIndex(0);
                minAmountField.setText("Min Amount");
                maxAmountField.setText("Max Amount");
            }
        });
    }

    public static void main(String[] args) {
        // Create and display the Fixed Deposit History Page
        FixedDepositHistoryPage fixedDepositHistoryPage = new FixedDepositHistoryPage();
        fixedDepositHistoryPage.setVisible(true);
    }
}