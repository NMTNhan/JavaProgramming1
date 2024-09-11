import java.util.Date;

public class Client extends User {
    private String membershipLevel; // Silver, Gold, Platinum
    private double totalSpending;

    public Client(String userID, String username, String password, Date dateOfBirth, String address,
                  String phoneNumber, String email, String status, String membershipLevel) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Client", status);
        this.membershipLevel = membershipLevel;
    }

    // Get discount rate based on membership level
    public double getDiscountRate() {
        switch (membershipLevel) {
            case "Silver":
                return 0.05; // 5% discount
            case "Gold":
                return 0.10; // 10% discount
            case "Platinum":
                return 0.15; // 15% discount
            default:
                return 0.0;  // No discount
        }
    }

    // Update the total spending and adjust membership level accordingly
    public void updateSpending(double amount) {
        this.totalSpending += amount;  // <-- Add to total spending
        // Update membership level based on new total spending
        if (totalSpending >= 250_000_000) {
            membershipLevel = "Platinum";
        } else if (totalSpending >= 100_000_000) {
            membershipLevel = "Gold";
        } else if (totalSpending >= 30_000_000) {
            membershipLevel = "Silver";
        }
    }

    // Override purchase method to include discount
    public void purchase(double amount) {
        double discountRate = getDiscountRate();  // <-- Get applicable discount rate
        double discountedAmount = amount * (1 - discountRate);  // <-- Apply discount
        updateSpending(discountedAmount);  // <-- Update spending with discounted amount
    }
    
    // Transaction
    public void viewTransaction(String transactionID, TransactionManager transactionManager) {
        Transaction transaction = transactionManager.getTransactionByID(transactionID);
        if (transaction != null) {
            System.out.println(transaction);
        } else {
            System.out.println("Transaction not found.");
        }
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password) && this.status.equals("Active");
    }

    @Override
    public void viewProfile() {
        System.out.println("Client Profile:");
        System.out.println("Username: " + username);
        System.out.println("Membership Level: " + membershipLevel);
        // Print other details
    }

    @Override
    public void modifyProfile() {
        System.out.println("Modifying client profile...");
        // Logic for modifying profile
    }
}
