package main;

import managers.AutoPartManager;
import managers.ServiceManager;
import models.AutoPart;
import models.Service;
import register.Register;
import authentication.Authentication;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("COSC2081 GROUP ASSIGNMENT\n" +
                "AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM\n" +
                "Instructor: Mr. Minh Vu & Mr. Dung Nguyen\n" +
                "Group: Group Name\n" +
                "s3965654, Le Tuan Kiet\n" +
                "s3935748, Tran Nguyen Khang\n" +
                "s3924462, Nguyen Minh Trong Nhan\n" +
                "s3992133, Lu Duc Thinh");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please choose an option: ");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            Authentication.login(username, password);
        } else if (choice == 2) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Choose role (user, employee, manager): ");
            String role = scanner.nextLine().toLowerCase();
            Register.register(username, password, role);
        } else if (choice == 3) {
            Authentication.logout();
        } else {
            System.out.println("Invalid option!");
        }

        scanner.close();

        AutoPartManager partManager = new AutoPartManager();
        ServiceManager serviceManager = new ServiceManager();

        // --- AutoPartManager Tests ---
        // Print all parts initially
        System.out.println("Initial list of parts:");
        partManager.printAllParts();

        // Add a new part
        AutoPart newPart = new AutoPart("p031", "Clutch Kit", "Sachs", "new", "2 years", 220);
        partManager.addPart(newPart);

        // Print all parts after adding a new one
        System.out.println("\nAfter adding a new part:");
        partManager.printAllParts();

        // Update an existing part
        AutoPart updatedPart = new AutoPart("p001", "Brake Pad", "Brembo", "new", "3 years", 150);
        partManager.updatePart("p001", updatedPart);

        // Print all parts after updating
        System.out.println("\nAfter updating part p001:");
        partManager.printAllParts();

        // Delete a part
        partManager.deletePart("p002");

        // Print all parts after deleting one
        System.out.println("\nAfter deleting part p002:");
        partManager.printAllParts();

        // --- ServiceManager Tests ---
        // Print all services initially
        System.out.println("\nInitial list of services:");
        serviceManager.printAllServices();

        // Add a new service
        Service newService = new Service("s021", LocalDate.now(), "u003", "m001", "Brake Replacement", 180);
        serviceManager.addService(newService);

        // Print all services after adding a new one
        System.out.println("\nAfter adding a new service:");
        serviceManager.printAllServices();

        // Update an existing service
        Service updatedService = new Service("s001", LocalDate.of(2023, 9, 10), "u001", "m001", "Oil Change", 60);
        serviceManager.updateService("s001", updatedService);

        // Print all services after updating
        System.out.println("\nAfter updating service s001:");
        serviceManager.printAllServices();

        // Delete a service
        serviceManager.deleteService("s002");

        // Print all services after deleting one
        System.out.println("\nAfter deleting service s002:");
        serviceManager.printAllServices();
    }
}
