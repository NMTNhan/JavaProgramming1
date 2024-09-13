package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import managers.CarManager;
import managers.AutoPartManager;
import managers.ServiceManager;
import managers.TransactionManager;
import models.Transaction;

public class ClientMenu {
    private CarManager carManager;
    private AutoPartManager partManager;
    private ServiceManager serviceManager;
    private TransactionManager transactionManager;
    private Scanner scanner;

    public ClientMenu(CarManager carManager, AutoPartManager partManager, ServiceManager serviceManager,
                      TransactionManager transactionManager, Scanner scanner) {
        this.carManager = carManager;
        this.partManager = partManager;
        this.serviceManager = serviceManager;
        this.transactionManager = transactionManager;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nClient Menu:");
            System.out.println("1. View Cars");
            System.out.println("2. View Services");
            System.out.println("3. View Parts");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewCars();
                    break;
                case 2:
                    viewServices();
                    break;
                case 3:
                    viewParts();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewCars() {
        System.out.println("Available Cars:");
        carManager.getAllCars();  // Display all cars
        System.out.print("Select a car by ID: ");
        String carID = scanner.nextLine();

        if (carManager.findCarById(carID) != null) {
            System.out.println("Thank you for your patronage!");
            createTransaction(carID);
        } else {
            System.out.println("Invalid Car ID. Please try again.");
        }
    }

    private void viewServices() {
        System.out.println("Available Services:");
        serviceManager.getAllServices();  // Display all services
        System.out.print("Select a service by ID: ");
        String serviceID = scanner.nextLine();

        if (serviceManager.findServiceById(serviceID) != null) {
            System.out.println("Thank you for your patronage!");
            createTransaction(serviceID);
        } else {
            System.out.println("Invalid Service ID. Please try again.");
        }
    }

    private void viewParts() {
        System.out.println("Available Parts:");
        partManager.getAllParts();  // Display all parts
        System.out.print("Select a part by ID: ");
        String partID = scanner.nextLine();

        if (partManager.findPartById(partID) != null) {
            System.out.println("Thank you for your patronage!");
            createTransaction(partID);
        } else {
            System.out.println("Invalid Part ID. Please try again.");
        }
    }

    private void createTransaction(String itemID) {
        // Create a new transaction for the selected item (car/part/service)
        LocalDate transactionDate = LocalDate.now();
        String clientID = "client001";  // For demonstration purposes, using a static client ID
        String salespersonID = "sales001";  // For demo purposes, use static salesperson ID
        String mechanicID = "";  // Use empty or assign a mechanic if needed
        List<String> purchasedItems = List.of(itemID);  // Adding the item to the purchase list
        double discount = 0.0;  // Assuming no discount
        double totalAmount = 1000.0;  // Example total amount (replace with actual logic)
        String additionalNotes = "Thank you for your purchase!";

        // Create the new transaction with the required fields
        Transaction newTransaction = new Transaction("T123", transactionDate, clientID, salespersonID, mechanicID,
                purchasedItems, discount, totalAmount, additionalNotes);

        transactionManager.addTransaction(newTransaction);  // Save the new transaction
        System.out.println("Transaction has been successfully created.");
    }

}
