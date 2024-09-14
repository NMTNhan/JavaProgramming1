package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import managers.CarManager;
import managers.AutoPartManager;
import managers.ServiceManager;
import managers.TransactionManager;
import models.Transaction;
import models.Car;
import models.AutoPart;
import models.Service;
import utils.ActivityLogManager;

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

    // Client menu
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

    // View Cars
    private void viewCars() {
        System.out.println("Available Cars:");
        carManager.getAllCars();  // Display all cars
        System.out.print("Select a car by ID: ");
        String carID = scanner.nextLine();

        Car selectedCar = carManager.findCarById(carID);
        if (selectedCar != null) {
            createTransaction(carID, selectedCar.getPrice(), "Car");
        } else {
            System.out.println("Invalid Car ID. Please try again.");
        }
        ActivityLogManager.logActivity("View Cars");
    }

    // View Service
    private void viewServices() {
        System.out.println("Available Services:");
        serviceManager.getAllServices();  // Display all services
        System.out.print("Select a service by ID: ");
        String serviceID = scanner.nextLine();

        Service selectedService = serviceManager.findServiceById(serviceID);
        if (selectedService != null) {
            createTransaction(serviceID, selectedService.getServiceCost(), "Service");
        } else {
            System.out.println("Invalid Service ID. Please try again.");
        }
    }

    // View Parts
    private void viewParts() {
        System.out.println("Available Parts:");
        partManager.getAllParts();  // Display all parts
        System.out.print("Select a part by ID: ");
        String partID = scanner.nextLine();

        AutoPart selectedPart = partManager.findPartById(partID);
        if (selectedPart != null) {
            createTransaction(partID, selectedPart.getCost(), "Part");
        } else {
            System.out.println("Invalid Part ID. Please try again.");
        }
    }

    // Create Transaction
    private void createTransaction(String itemID, double totalAmount, String itemType)
    {
        // Doesnt work so just leave it here
        }
}

