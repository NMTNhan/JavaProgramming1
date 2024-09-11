package managers;

import models.Transaction;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {

    // File path for the transactions data file
    private static final String TRANSACTION_FILE_PATH = "src/data/transactions";

    // List to hold all transactions
    private List<Transaction> transactions = new ArrayList<>();

    // Constructor: Load transactions from file when the manager is initialized
    public TransactionManager() {
        loadTransactionsFromFile();
    }

    // Convert string line from file to a Transaction object
    private Transaction deserializeTransaction(String line) {
        // Assuming the transaction is stored as: transactionID, transactionDate, clientID, salespersonID, purchasedItems, discount, totalAmount, additionalNotes
        String[] transactionData = line.split(",");
        List<String> purchasedItems = List.of(transactionData[4].split(";")); // Assuming items are separated by semicolons
        return new Transaction(transactionData[0], transactionData[1], transactionData[2], transactionData[3],
                purchasedItems, Double.parseDouble(transactionData[5]), Double.parseDouble(transactionData[6]),
                transactionData[7]);
    }

    // Convert a Transaction object to string for writing to file
    private String serializeTransaction(Transaction transaction) {
        String purchasedItems = String.join(";", transaction.getPurchasedItems());
        return transaction.getTransactionID() + "," + transaction.getTransactionDate() + "," + transaction.getClientID() + "," +
                transaction.getSalespersonID() + "," + purchasedItems + "," + transaction.getDiscount() + "," +
                transaction.getTotalAmount() + "," + transaction.getAdditionalNotes();
    }

    // Load transactions from file
    private void loadTransactionsFromFile() {
        List<String> lines = FileUtils.readFile(TRANSACTION_FILE_PATH);
        transactions = lines.stream()
                .map(this::deserializeTransaction)
                .collect(Collectors.toList());
    }

    // Add a new transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        List<String> serializedTransactions = transactions.stream()
                .map(this::serializeTransaction)
                .collect(Collectors.toList());
        FileUtils.writeFile(TRANSACTION_FILE_PATH, serializedTransactions);
    }

    // Remove a transaction
    public void removeTransaction(String transactionId) {
        transactions = transactions.stream()
                .filter(transaction -> !transaction.getTransactionID().equals(transactionId))
                .collect(Collectors.toList());
        List<String> serializedTransactions = transactions.stream()
                .map(this::serializeTransaction)
                .collect(Collectors.toList());
        FileUtils.writeFile(TRANSACTION_FILE_PATH, serializedTransactions);
    }

    // Update an existing transaction
    public void updateTransaction(String transactionId, Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getTransactionID().equals(transactionId)) {
                transactions.set(i, updatedTransaction);
                break;
            }
        }
        List<String> serializedTransactions = transactions.stream()
                .map(this::serializeTransaction)
                .collect(Collectors.toList());
        FileUtils.writeFile(TRANSACTION_FILE_PATH, serializedTransactions);
    }
}
