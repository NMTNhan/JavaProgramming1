import java.util.Date;

public class Employee extends User {
    private String jobPosition; // Salesperson, Mechanic

    public Employee(String userID, String username, String password, Date dateOfBirth, String address,
                    String phoneNumber, String email, String status, String jobPosition) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Employee", status);
        this.jobPosition = jobPosition;
    }

    // Calculate revenue generated by the employee (e.g., sales)
    public double calculateRevenue() {
        System.out.println("Calculating revenue for " + jobPosition + "...");
        // Logic to calculate revenue
        return 0.0;
    }

    // List transactions handled by the employee
    public void listTransactions() {
        System.out.println("Listing transactions handled by " + jobPosition + "...");
        // Logic to list transactions
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password) && this.status.equals("Active");
    }

    @Override
    public void viewProfile() {
        System.out.println("Employee Profile:");
        System.out.println("Username: " + username);
        System.out.println("Job Position: " + jobPosition);
        // Print other details
    }

    @Override
    public void modifyProfile() {
        System.out.println("Modifying employee profile...");
        // Logic for modifying profile
    }
}