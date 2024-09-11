import java.util.Date;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public abstract class Manager extends User {

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
        System.out.println("User ID: " + userID);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
    }

    @Override
    public void modifyProfile() {
        Scanner scanner = new Scanner(System.in);

        // Display current profile
        viewProfile();

        // Ask the user which field they want to modify
        System.out.println("Which field would you like to modify?");
        System.out.println("1. Address");
        System.out.println("2. Phone Number");
        System.out.println("3. Email");
        System.out.print("Enter the number corresponding to the field: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new address: ");
                this.address = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new phone number: ");
                this.phoneNumber = scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter new email: ");
                this.email = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice. No changes made.");
                return;
        }

        System.out.println("Profile updated successfully.");
        saveProfileToFile(); // Save updated profile to file
    }

    // Save profile changes to the file
    public void saveProfileToFile() {
        File file = new File("src/data/user"); // Your actual file path

        try {
            // Load all users from the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(" ");

                // Check if this is the current user (based on username)
                if (accountData[0].equals(this.username)) {
                    // Replace the user's data with the updated profile
                    String newLine = String.format("%s %s %s %s %s %s %s",
                            this.userID, this.username, this.password,
                            this.address, this.phoneNumber, this.email,
                            "Manager", this.status);
                    fileContent.append(newLine).append(System.lineSeparator());
                } else {
                    // Keep other user data unchanged
                    fileContent.append(line).append(System.lineSeparator());
                }
            }
            reader.close();

            // Write the updated content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("Profile changes saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving profile to file: " + e.getMessage());
        }
    }
}