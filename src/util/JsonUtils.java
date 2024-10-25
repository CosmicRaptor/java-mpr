import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    // Method to update the balance for the given account index
    public static void updateAccountBalance(int accountIndex, double newBalance) {
        try {
            // Initialize ObjectMapper and read the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("userinfo.json"));

            // Navigate to the balance field of the specified account
            JsonNode accountsNode = rootNode.path("user").path("accounts");
            if (accountsNode.isArray() && accountsNode.has(accountIndex)) {
                ObjectNode accountNode = (ObjectNode) accountsNode.get(accountIndex);
                accountNode.put("balance", newBalance); // Update the balance
            } else {
                System.out.println("Account index out of bounds.");
                return;
            }

            // Write the updated JSON back to the file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("userinfo.json"), rootNode);
            System.out.println("Balance updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
