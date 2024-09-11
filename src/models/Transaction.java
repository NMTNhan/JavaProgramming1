package models;

import java.util.List;

public class Transaction {

    private String transactionID;
    private String transactionDate;
    private String clientID;
    private String salespersonID;
    private List<String> purchasedItems; // List of car/part IDs
    private double discount;
    private double totalAmount;
    private String additionalNotes;

    // Constructor
    public Transaction(String transactionID, String transactionDate, String clientID, String salespersonID,
            List<String> purchasedItems, double discount, double totalAmount, String additionalNotes) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salespersonID = salespersonID;
        this.purchasedItems = purchasedItems;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.additionalNotes = additionalNotes;
    }

    // Getters and Setters
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getSalespersonID() {
        return salespersonID;
    }

    public void setSalespersonID(String salespersonID) {
        this.salespersonID = salespersonID;
    }

    public List<String> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<String> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionID
                + ", Date: " + transactionDate
                + ", Client ID: " + clientID
                + ", Salesperson ID: " + salespersonID
                + ", Purchased Items: " + String.join(", ", purchasedItems)
                + ", Discount: " + discount + "%"
                + ", Total Amount: $" + totalAmount
                + ", Notes: " + additionalNotes;
    }
}
