import javax.swing.*;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage {
    public static void main(String[] args) {
        String username = UsernameData.username;
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridBagLayout());

        // Create a bold welcome label for the user
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(10, 10, 10, 10);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridwidth = 2; // Span across two columns
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        frame.add(welcomeLabel, gbc1); // Add the label to the frame

        // Set up the GridBagConstraints for the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Create buttons for the banking application
        JButton balanceButton = new JButton("Check Balance");
        JButton transferButton = new JButton("Transfer Money");
        JButton cardsInfoButton = new JButton("Cards Information");
        JButton fixedDepositButton = new JButton("Fixed Deposit");
        JButton statementButton = new JButton("View Statement");
        JButton addMoneyButton = new JButton("Add money");

        // Add buttons below the welcome label
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(balanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(transferButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(cardsInfoButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(fixedDepositButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(statementButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(addMoneyButton, gbc);

        // Add action listeners to buttons
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonNode userInfo = UsernameData.userInfo;
                String balance = userInfo.get("accounts").get(0).get("balance").asText();
                JOptionPane.showMessageDialog(frame, "Balance: " + balance, "Balance Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferMoneyPage.main(null);
            }
        });

        cardsInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DebitCardInfoPage.main(null);
            }
        });

        fixedDepositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FixedDepositHistoryPage.main(null);
                // JOptionPane.showMessageDialog(frame, "Fixed Deposit option selected.", "Fixed Deposit", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        statementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransactionHistoryPage.main(null);
                // JOptionPane.showMessageDialog(frame, "View Statement option selected.", "Statement", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositMoneyPage.main(null);
            }
        });

        frame.setVisible(true);
    }
}
