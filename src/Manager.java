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