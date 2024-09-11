import java.util.Date;
import java.util.List;

public class Manager extends User {

    public Manager(String userID, String username, String password, Date dateOfBirth, String address,
                   String phoneNumber, String email, String status) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Manager", status);
    }

    // View all entities (Cars, Services, AutoParts, etc.)
    public void viewAllEntities() {
        System.out.println("Viewing all entities...");
        // Logic to view cars, services, parts, etc.
    }

    // View membership levels and discounts
    public void viewMembershipDiscountPolicies() {
        System.out.println("Silver: 5% discount for spending over 30 million VND");
        System.out.println("Gold: 10% discount for spending over 100 million VND");
        System.out.println("Platinum: 15% discount for spending over 250 million VND");
    }

    // Adjust membership policies (example)
    public void adjustMembershipPolicy(String level, double newThreshold, double newDiscountRate) {
        // Logic to adjust membership policies
    }
    
    // Perform statistics, such as sales, revenue, etc.
    public void performStatistics() {
        System.out.println("Performing statistics...");
        // Logic for calculating statistics
    }

    // Manage employees
    public void manageEmployees(List<Employee> employees) {
        System.out.println("Managing employees...");
        // Logic to manage employees (e.g., assigning tasks, reviewing performance, etc.)
    }

    // Transaction
    public void manageEmployees(List<Employee> employees) {
        System.out.println("Managing employees...");
        // Logic to manage employees (e.g., assigning tasks, reviewing performance, etc.)
        // Example: List all employees
        employees.forEach(System.out::println);
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password) && this.status.equals("Active");
    }

    @Override
    public void viewProfile() {
        System.out.println("Manager Profile:");
        System.out.println("Username: " + username);
        // Print other details
    }

    @Override
    public void modifyProfile() {
        System.out.println("Modifying manager profile...");
        // Logic for modifying profile
    }
}