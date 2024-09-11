package managers;

import models.Transaction;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {

    // File path for the transactions data file
    private static final String TRANSACTION_FILE_PATH = "src/data/transactions";

    // List to hold all transactions
    private List<Transaction> transactions = new ArrayList<>();

    // Method to process a new transaction
    public void processTransaction(Transaction transaction, Client client) {
        double amount = transaction.getAmount();
        client.purchase(amount);  // <-- Apply discount and update spending
        transactions.add(transaction);
        saveTransactionsToFile();  // <-- Save transactions after processing
    }

    // Method to save transactions back to the file
    private void saveTransactionsToFile() {
        // Logic to save transactions
    }

    // Constructor: Load transactions from file when the manager is initialized
    public TransactionManager() {
        loadTransactionsFromFile();
    }

    // Method to load transactions from the file
    private void loadTransactionsFromFile() {
        List<String> transactionData = FileUtils.readFile(TRANSACTION_FILE_PATH);
        System.out.println("Total lines read from transactions.txt: " + transactionData.size());  // Debug output
        for (String line : transactionData) {
            System.out.println("Reading line: " + line);  // Debug output
            String[] parts = line.split("\\|");
            if (parts.length == 6) {
                Transaction transaction = new Transaction(
                    parts[0], // transactionID
                    LocalDate.parse(parts[1]), // date
                    parts[2], // clientID
                    parts[3], // carID
                    parts[4], // partID
                    Double.parseDouble(parts[5]) // totalAmount
                );
                transactions.add(transaction);
            }
        }
        System.out.println("Total transactions loaded: " + transactions.size());  // Debug output
    }

    // Method to save transactions back to the file
    private void saveTransactionsToFile() {
        List<String> transactionData = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionData.add(transaction.getTransactionID() + "|" + 
                                transaction.getDate() + "|" + 
                                transaction.getClientID() + "|" + 
                                transaction.getCarID() + "|" + 
                                transaction.getPartID() + "|" + 
                                transaction.getTotalAmount());
        }
        FileUtils.writeFile(TRANSACTION_FILE_PATH, transactionData);
    }

    // CRUD
    // Create: Add a new transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactionsToFile();  // Save after adding
    }

    // Update: Update an existing transaction by ID
    public void updateTransaction(String transactionID, Transaction updatedTransaction) {
        Transaction transaction = getTransactionByID(transactionID);
        if (transaction != null) {
            transaction.setDate(updatedTransaction.getDate());
            transaction.setClientID(updatedTransaction.getClientID());
            transaction.setCarID(updatedTransaction.getCarID());
            transaction.setPartID(updatedTransaction.getPartID());
            transaction.setTotalAmount(updatedTransaction.getTotalAmount());
            saveTransactionsToFile();  // Save after updating
        }
    }

    // Delete: Remove a transaction by ID
    public void deleteTransaction(String transactionID) {
        transactions.removeIf(transaction -> transaction.getTransactionID().equals(transactionID));
        saveTransactionsToFile();  // Save after deleting
    }

    // Retrieve a transaction by ID (helper function)
    public Transaction getTransactionByID(String transactionID) {
        return transactions.stream().filter(transaction -> transaction.getTransactionID().equals(transactionID)).findFirst().orElse(null);
    }

    // Method to print all transactions
    public void printAllTransactions() {
        transactions.forEach(System.out::println);
    }

    // Manager queries
    // Search transactions by clientID
    public List<Transaction> searchTransactionsByClientID(String clientID) {
        return transactions.stream()
                .filter(transaction -> transaction.getClientID().equals(clientID))
                .collect(Collectors.toList());
    }

    // Search transactions by carID
    public List<Transaction> searchTransactionsByCarID(String carID) {
        return transactions.stream()
                .filter(transaction -> transaction.getCarID().equals(carID))
                .collect(Collectors.toList());
    }

    // Search transactions by partID
    public List<Transaction> searchTransactionsByPartID(String partID) {
        return transactions.stream()
                .filter(transaction -> transaction.getPartID().equals(partID))
                .collect(Collectors.toList());
    }

    // Search transactions by date range
    public List<Transaction> searchTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(transaction -> !transaction.getDate().isBefore(startDate) && !transaction.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // Calculate total sales amount
    public double calculateTotalSales() {
        return transactions.stream()
                .mapToDouble(Transaction::getTotalAmount)
                .sum();
    }
}
