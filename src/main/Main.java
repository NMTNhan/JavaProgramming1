package main;

import authentication.Authentication;
import managers.*;
import user.Client;
import user.Employee;
import user.Manager;
import user.User;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    // Manager objects
    private static CarManager carManager;
    private static AutoPartManager partManager;
    private static ServiceManager serviceManager;
    private static TransactionManager transactionManager;
    private static UserManager userManager;

    private static Authentication authentication;  // Declare but don’t initialize

    public static void main(String[] args) {
        try {
            // Print welcome screen
            printWelcomeScreen();

            // Initialize the authentication system here, after the program has fully started
            authentication = new Authentication();
            initializeManagersAndAuthentication();  // Initialize the managers

            boolean exit = false;

            while (!exit) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Welcome Screen
    private static void printWelcomeScreen() {
        System.out.println("============================================");
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Mr. Dung Nguyen");
        System.out.println("Group: Group 11");
        System.out.println("s3935748, Tran Nguyen Khang");
        System.out.println("s3965654, Le Tuan Kiet");
        System.out.println("s3992133, Lu Duc Thinh");
        System.out.println("s3924462, Nguyen Minh Trong Nhan");
        System.out.println("============================================");
    }

    // Initialize managers and authentication
    private static void initializeManagersAndAuthentication() {
        try {
            carManager = new CarManager();
            partManager = new AutoPartManager();
            serviceManager = new ServiceManager();
            transactionManager = new TransactionManager();
            userManager = new UserManager();
            authentication = new Authentication();
        } catch (Exception e) {
            System.out.println("Error initializing managers or authentication: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void login() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Validate credentials with the Authentication class
            if (authentication.login(username, password)) {
                User loggedInUser = authentication.getLoggedInUser();
                System.out.println("Login successful! Welcome, " + loggedInUser.getUsername() + ".");

                // Redirect to the appropriate menu based on role
                switch (loggedInUser.getRole().toLowerCase()) {
                    case "manager":
                        ManagerMenu managerMenu = new ManagerMenu(carManager, partManager, serviceManager, transactionManager, userManager, scanner);
                        managerMenu.displayMenu();
                        break;
                    case "employee":
                        EmployeeMenu employeeMenu = new EmployeeMenu(transactionManager, scanner);
                        employeeMenu.displayMenu();
                        break;
                    case "client":
                        ClientMenu clientMenu = new ClientMenu(carManager, partManager, serviceManager, transactionManager, scanner);
                        clientMenu.displayMenu();
                        break;
                    default:
                        System.out.println("Invalid role. Access denied.");
                        break;
                }
            } else {
                System.out.println("Login failed. Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void register() {
        try {
            System.out.print("Enter a username: ");
            String username = scanner.nextLine();
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();
            System.out.print("Enter your date of birth (yyyy-MM-dd): ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your status (Active/Inactive): ");
            String status = scanner.nextLine();
            System.out.print("Enter your role (employee, client): ");
            String role = scanner.nextLine().toLowerCase();

            // Generate a unique user ID
            String userID = "user" + System.currentTimeMillis();

            // Create a new user based on the role
            User newUser;
            switch (role) {
                case "employee":
                    System.out.print("Enter job position (mechanic, salesperson): ");
                    String jobPosition = scanner.nextLine();
                    newUser = new Employee(userID, username, password, dateOfBirth, address, phoneNumber, email, status, jobPosition);
                    break;
                case "client":
                    newUser = new Client(userID, username, password, dateOfBirth, address, phoneNumber, email, status, "Silver");
                    break;
                default:
                    System.out.println("Invalid role. Registration failed.");
                    return;
            }

            // Register the new user using Authentication class
            authentication.registerUser(newUser);
            System.out.println("Registration successful! You can now log in.");
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
