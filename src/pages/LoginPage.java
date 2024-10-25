import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoginPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object>[] node = new Map[1];

        try {
            node[0] = mapper.readValue(new File("userinfo.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            node[0] = Map.of(); // Assign an empty immutable map
            JOptionPane.showMessageDialog(frame, "Error reading user info file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(userLabel, gbc);

        JTextField userText = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(passwordText, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = String.valueOf(passwordText.getPassword());

                if (node[0].containsKey(username)) {
                    Map<String, Object> userInfo = (Map<String, Object>) node[0].get(username);
                    String storedPassword = (String) userInfo.get("password");
                    if (storedPassword.equals(password)) {
                        UsernameData.password = storedPassword;
                        JOptionPane.showMessageDialog(frame, "Login Successful!");
                        UsernameData.username = username;
                        System.out.println(userInfo);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode userInfoNode = objectMapper.convertValue(userInfo, JsonNode.class);

                        // Store it in UsernameData
                        UsernameData.userInfo = userInfoNode;
                        // Initialize accounts array based on data from UsernameData
                        JsonNode userAccounts = UsernameData.userInfo.get("accounts");
                        Account[] accounts;
                        accounts = new Account[userAccounts.size()];

                        for (int i = 0; i < userAccounts.size(); i++) {
                            JsonNode accountNode = (JsonNode) userAccounts.get(i);
                            JsonNode debitCardNode = accountNode.get("debitCard");

                            accounts[i] = new Account(
                                    accountNode.get("name").asText(),
                                    accountNode.get("balance").asDouble(),
                                    accountNode.get("type").asText(),
                                    new DebitCard(
                                            debitCardNode.get("cardNumber").asText(),
                                            debitCardNode.get("cardHolder").asText(),
                                            debitCardNode.get("expiryDate").asText(),
                                            debitCardNode.get("cvv").asText(),
                                            new DebitCardLimit(
                                                debitCardNode.get("spendingLimit").get("onlineLimit").asInt(), 
                                                debitCardNode.get("spendingLimit").get("merchantLimit").asInt(), 
                                                debitCardNode.get("spendingLimit").get("atmLimit").asInt(), 
                                                debitCardNode.get("spendingLimit").get("internationalLimit").asInt()),
                                            debitCardNode.get("disabled").asBoolean()));
                        }
                        UsernameData.accounts = accounts;
                        TransactionUtil.setTransactionGlobal();
                        frame.dispose();
                        DashboardPage.main(null);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
