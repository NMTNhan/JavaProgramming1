package managers;

import models.Transaction;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {

    // File paths for transactions, services, and parts data
    private static final String TRANSACTION_FILE_PATH = "data/transactions";
    private static final String SERVICE_FILE_PATH = "data/services";
    private static final String PART_FILE_PATH = "data/parts";

    // Lists to hold transactions, services, and parts
    private List<Transaction> transactions = new ArrayList<>();
    private List<String[]> services;
    private List<String[]> parts;

    // Constructor: Load data from files
    public TransactionManager() {
        loadServicesFromFile();
        loadPartsFromFile();
        loadTransactionsFromFile();
    }

    // Load services from file
    private void loadServicesFromFile() {
        List<String> serviceLines = FileUtils.readFile(SERVICE_FILE_PATH);
        services = serviceLines.stream().map(line -> line.split("\\|")).collect(Collectors.toList());
    }

    // Load parts from file
    private void loadPartsFromFile() {
        List<String> partLines = FileUtils.readFile(PART_FILE_PATH);
        parts = partLines.stream().map(line -> line.split(",")).collect(Collectors.toList());
    }

    // Load transactions from file
    private void loadTransactionsFromFile() {
        List<String> transactionLines = FileUtils.readFile(TRANSACTION_FILE_PATH);
        for (String line : transactionLines) {
            transactions.add(deserializeTransaction(line));  // Assuming deserializeTransaction() exists
        }
    }

    private Transaction deserializeTransaction(String line) {
        // Use a regex to split by commas but ignore commas inside quotes
        String[] transactionData = line.split(",");

        // Check if the data has the correct number of fields (should be 8)
        if (transactionData.length != 9) {
            throw new IllegalArgumentException("Invalid transaction data format: " + line);
        }

        String transactionID = transactionData[0];
        LocalDate transactionDate = LocalDate.parse(transactionData[1]);  // Parse the date
        String clientID = transactionData[2];
        String salespersonID = transactionData[3];
        String mechanicID = transactionData[4];

        // Split purchased items by ';'
        List<String> purchasedItems = Arrays.asList(transactionData[5].split(";"));

        double discount = Double.parseDouble(transactionData[6]);
        double totalAmount = Double.parseDouble(transactionData[7].replaceAll("\"", ""));  // Remove quotes from totalAmount
        String additionalNotes = transactionData[8].replaceAll("^\"|\"$", "");  // Remove surrounding quotes from additionalNotes

        // Return a new Transaction object with the parsed data
        return new Transaction(transactionID, transactionDate, clientID, salespersonID, mechanicID, purchasedItems, discount, totalAmount, additionalNotes);
    }


    // Add a new transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction added successfully.");
    }

    // Save transactions list to file after any changes
    private void saveTransactionsToFile() {
        FileUtils.writeFile(TRANSACTION_FILE_PATH, transactions.stream()
                .map(this::serializeTransaction)
                .collect(Collectors.toList()));
    }

    // Convert a Transaction object to string for writing to file
    private String serializeTransaction(Transaction transaction) {
        return transaction.getTransactionID() + "," + transaction.getTransactionDate() + "," + transaction.getClientID() + ","
                + transaction.getSalespersonID() + "," + transaction.getMechanicID() + "," + String.join(";", transaction.getPurchasedItems()) + ","
                + transaction.getDiscount() + "," + transaction.getTotalAmount() + "," + transaction.getAdditionalNotes();
    }

    // Calculate total revenue in a given time period (day/week/month)
    public double calculateRevenue(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .mapToDouble(Transaction::getTotalAmount)
                .sum();
    }

    // Calculate total revenue generated by a specific mechanic
    public double calculateMechanicRevenue(String mechanicID, LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .filter(t -> t.getMechanicID().equals(mechanicID)) // Filter by mechanic ID
                .mapToDouble(Transaction::getTotalAmount)
                .sum();
    }

    // Calculate total revenue generated by a specific salesperson
    public double calculateSalespersonRevenue(String salespersonID, LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .filter(t -> t.getSalespersonID().equals(salespersonID)) // Filter by salesperson ID
                .mapToDouble(Transaction::getTotalAmount)
                .sum();
    }

    // Method to list all transactions
    public List<Transaction> listTransactions(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // Method to calculate cars sold in a month
    public long calculateCarsSoldInMonth(int month, int year) {
        return transactions.stream()
                .filter(t -> t.getTransactionDate().getMonthValue() == month && t.getTransactionDate().getYear() == year)
                .flatMap(t -> t.getPurchasedItems().stream())
                .filter(item -> item.startsWith("C"))  // Assuming car IDs start with 'C'
                .count();
    }

    // Method to list cars sold
    public List<String> listCarsSold(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .flatMap(t -> t.getPurchasedItems().stream())
                .filter(item -> item.startsWith("C"))
                .collect(Collectors.toList());
    }

    // Method to list services performed
    public List<String> listServicesPerformed(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .flatMap(t -> t.getPurchasedItems().stream())
                .filter(item -> item.startsWith("S"))  // Assuming service IDs start with 'S'
                .collect(Collectors.toList());
    }

    // Method to list auto parts sold
    public List<String> listAutoPartsSold(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate) && !t.getTransactionDate().isAfter(endDate))
                .flatMap(t -> t.getPurchasedItems().stream())
                .filter(item -> item.startsWith("P"))  // Assuming part IDs start with 'P'
                .collect(Collectors.toList());
    }

    // Print all transactions
    public void getAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);  // Ensure Transaction class has a proper toString() method
            }
        }
    }

    // Find a transaction by its ID and return the transaction object (not void)
    public Transaction findTransactionById(String transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionID().equals(transactionId)) {
                return transaction;  // Return the transaction if found
            }
        }
        return null;  // Return null if no transaction is found
    }
}
