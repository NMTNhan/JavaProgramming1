package authentication;

import java.io.*;

public class Authentication {
    private static final String ACCOUNT_FILE = "C:\\Users\\ADMIN\\Desktop\\New folder (2)\\JavaProgramming1\\src\\data\\account.txt"; // Path to account.txt file

    // Method for login
    public static void login(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE));
        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            String[] accountData = line.split(" ");
            if (accountData[0].equals(username) && accountData[1].equals(password)) {
                found = true;
                String role = accountData[2];
                System.out.println("Login successful! Role: " + role);
                // Call appropriate interface based on role
                switch (role.toLowerCase()) {
                    case "manager":
                        managerInterface();
                        break;
                    case "employee":
                        employeeInterface();
                        break;
                    case "client":
                        clientInterface();
                        break;
                    default:
                        System.out.println("Unknown role!");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Login failed! Invalid username or password.");
        }

        reader.close();
    }

    // Interface for manager
    private static void managerInterface() {
        System.out.println("Welcome to Manager Interface!");
        // Implement manager-specific operations here
    }

    // Interface for employee
    private static void employeeInterface() {
        System.out.println("Welcome to Employee Interface!");
        // Implement employee-specific operations here
    }

    // Interface for client
    private static void clientInterface() {
        System.out.println("Welcome to Client Interface!");
        // Implement customer-specific operations here
    }

    // Method to log out
    public static void logout() {
        System.out.println("Logout successful!");
    }
}