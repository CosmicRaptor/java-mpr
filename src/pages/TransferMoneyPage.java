import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMoneyPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Transfer Money");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Labels and text fields for entering details
        JLabel fromAccountLabel = new JLabel("From Account:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(fromAccountLabel, gbc);

        JTextField fromAccountField = new JTextField();
        fromAccountField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(fromAccountField, gbc);

        JLabel toAccountLabel = new JLabel("To Account:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(toAccountLabel, gbc);

        JTextField toAccountField = new JTextField();
        toAccountField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(toAccountField, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(amountField, gbc);

        JLabel notesLabel = new JLabel("Notes (optional):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(notesLabel, gbc);

        JTextField notesField = new JTextField();
        notesField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(notesField, gbc);

        // Transfer button
        JButton transferButton = new JButton("Transfer");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(transferButton, gbc);

        // Action listener for the transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAccount = fromAccountField.getText();
                String toAccount = toAccountField.getText();
                String amount = amountField.getText();

                // Simple validation and success message
                if (fromAccount.isEmpty() || toAccount.isEmpty() || amount.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Transfer of $" + amount + " to account " + toAccount + " successful.", "Transfer Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
