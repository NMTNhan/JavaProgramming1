package managers;

import user.Client;
import user.Employee;
import user.Manager;
import user.User;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String USER_FILE_PATH = "data/users";
    private List<User> users = new ArrayList<>();
    public UserManager() {
        loadUsersFromFile();  // Load users when UserManager is instantiated
    }

    // Add a new user
    public void addUser(User user) {
        users.add(user);
    }

    // Soft delete user
    public void removeUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);  // Assuming setActive(boolean) exists in the User class
            }
        }
    }

    // Update user
    public void updateUser(String userId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.set(i, updatedUser);
                break;
            }
        }
    }

    // Find a user by their ID and print the result
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public Employee findEmployeeById(String employeeId) {
        for (User user : users) {
            if (user instanceof Employee && user.getUserId().equals(employeeId)) {
                return (Employee) user;
            }
        }
        return null;  // Employee not found
    }

    // Method to convert a CSV line to a User object
    private User deserializeUser(String line) {
        String[] fields = line.split(",");  // Split by comma, assuming CSV format

        // Common fields
        String userID = fields[0];
        String username = fields[1];
        String password = fields[2];
        LocalDate dateOfBirth = LocalDate.parse(fields[3]);  // Assuming yyyy-MM-dd format
        String address = fields[4];
        String phoneNumber = fields[5];
        String email = fields[6];
        String role = fields[7];
        boolean isActive = fields[8].equalsIgnoreCase("active");  // Convert "active" or "inactive" to boolean

        // Create and return the appropriate subclass based on the role
        switch (role.toLowerCase()) {
            case "manager":
                return new Manager(userID, username, password, dateOfBirth, address, phoneNumber, email, isActive ? "Active" : "Inactive");
            case "employee":
                return new Employee(userID, username, password, dateOfBirth, address, phoneNumber, email, isActive ? "Active" : "Inactive", "Employee");
            case "client":
                return new Client(userID, username, password, dateOfBirth, address, phoneNumber, email, isActive ? "Active" : "Inactive", "Standard");  // Assuming "Standard" membership level for simplicity
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    private void loadUsersFromFile() {
        List<String> userLines = FileUtils.readFile(USER_FILE_PATH);
        for (String line : userLines) {
            users.add(deserializeUser(line));  // Deserialize each line into a User object
        }
    }

    // Print all users
    public void getAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.println(user);  // Ensure User class has a proper toString() method
            }
        }
    }
}
