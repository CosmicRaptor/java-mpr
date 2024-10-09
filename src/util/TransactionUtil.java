import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionUtil {
    
    private static final String TRANSACTION_FILE = "transactions.json";
    private static ObjectMapper objectMapper = new ObjectMapper();

    // Method to write or append a Transaction object to a JSON file
    public static void appendTransaction(Transaction transaction) {
        try {
            // Read existing transactions from file
            List<Transaction> transactions = readTransactions();

            // Add the new transaction
            transactions.add(transaction);

            // Write the updated list back to the file
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(TRANSACTION_FILE), transactions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read transactions from the JSON file
    public static List<Transaction> readTransactions() {
        try {
            File file = new File(TRANSACTION_FILE);

            // If file exists, read the existing transactions
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Transaction>>() {});
            }

            // If file doesn't exist, return a new empty list
            return new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
