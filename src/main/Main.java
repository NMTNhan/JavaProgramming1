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

    // Initialize manager objects
    private static CarManager carManager = new CarManager();
    private static AutoPartManager partManager = new AutoPartManager();
    private static ServiceManager serviceManager = new ServiceManager();
    private static TransactionManager transactionManager = new TransactionManager();
    private static UserManager userManager = new UserManager();

    // Authentication instance
    private static Authentication authentication = new Authentication();

    public static void main(String[] args) {
        try {
            boolean exit = false;

            while (!exit) {
                try {
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
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayWelcomeScreen() {
        System.out.println("*************************************");
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Mr. Dung Nguyen");
        System.out.println("Group: Please give us HD");
        System.out.println("s3965654, Le Tuan Kiet\n");
        System.out.println("s3935748, Tran Nguyen Khang\n");
        System.out.println("s3992133, Lu Duc Thinh\n");
        System.out.println("s3924462, Nguyen Minh Trong Nhan\n");
        System.out.println("*************************************\n");

        System.out.println("Welcome to the Auto136 Car Dealership Management System!");
        System.out.println("Please choose from the following options:");
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
            System.out.print("Enter your role (manager, employee, client): ");
            String role = scanner.nextLine().toLowerCase();

            // Generate a unique user ID
            String userID = "user" + System.currentTimeMillis();

            // Create a new user based on the role
            User newUser;
            switch (role) {
                case "manager":
                    newUser = new Manager(userID, username, password, dateOfBirth, address, phoneNumber, email, status);
                    break;
                case "employee":
                    System.out.print("Enter job position: ");
                    String jobPosition = scanner.nextLine();
                    newUser = new Employee(userID, username, password, dateOfBirth, address, phoneNumber, email, status, jobPosition);
                    break;
                case "client":
                    System.out.print("Enter membership level (Silver, Gold, Platinum): ");
                    String membershipLevel = scanner.nextLine();
                    newUser = new Client(userID, username, password, dateOfBirth, address, phoneNumber, email, status, membershipLevel);
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
