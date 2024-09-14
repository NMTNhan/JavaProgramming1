package main;

import main.CarCRUD;
import main.PartCRUD;
import main.ServiceCRUD;
import managers.CarManager;
import managers.AutoPartManager;
import managers.ServiceManager;
import managers.TransactionManager;
import managers.UserManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ManagerMenu {

    private CarManager carManager;
    private AutoPartManager partManager;
    private ServiceManager serviceManager;
    private TransactionManager transactionManager;
    private UserManager userManager;
    private CarCRUD carCRUD;
    private PartCRUD partCRUD;
    private ServiceCRUD serviceCRUD;
    private Scanner scanner;

    public ManagerMenu(CarManager carManager, AutoPartManager partManager, ServiceManager serviceManager,
                       TransactionManager transactionManager, UserManager userManager, Scanner scanner) {
        this.carManager = carManager;
        this.partManager = partManager;
        this.serviceManager = serviceManager;
        this.transactionManager = transactionManager;
        this.userManager = userManager;
        this.scanner = scanner;

        // Instantiate the CRUD objects
        this.carCRUD = new CarCRUD(carManager, scanner);
        this.partCRUD = new PartCRUD(partManager, scanner);
        this.serviceCRUD = new ServiceCRUD(serviceManager, scanner);
    }

    // Manager menu
    public void displayMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\nManager Menu:");
            System.out.println("1. View/Search All Entities");
            System.out.println("2. Perform Statistics Operations");
            System.out.println("3. Perform CRUD Operations");
            System.out.println("4. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewOrSearchEntities();
                    break;
                case 2:
                    calculateStatistics();
                    break;
                case 3:
                    performCrudOperations();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // View and search for entities
    private void viewOrSearchEntities() {
        System.out.println("\nView and Search Entities:");
        System.out.println("1. Cars");
        System.out.println("2. Parts");
        System.out.println("3. Services");
        System.out.println("4. Transactions");
        System.out.println("5. Users");
        System.out.print("Choose an option: ");
        int entityChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (entityChoice) {
            case 1:
                carManager.getAllCars();  // View all cars
                break;
            case 2:
                partManager.getAllParts();  // View all parts
                break;
            case 3:
                serviceManager.getAllServices();  // View all services
                break;
            case 4:
                transactionManager.getAllTransactions();  // View all transactions
                break;
            case 5:
                userManager.getAllUsers();  // View all users
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }

        // Option to search entities by ID
        System.out.print("Would you like to search for a specific entity by ID? (yes/no): ");
        String searchOption = scanner.nextLine();
        if (searchOption.equalsIgnoreCase("yes")) {
            searchEntityById(entityChoice);
        }
    }

    // Search entities by ID
    private void searchEntityById(int entityChoice) {
        System.out.print("Enter the ID: ");
        String id = scanner.nextLine();

        switch (entityChoice) {
            case 1:  // Search cars
                var car = carManager.findCarById(id);
                if (car != null) {
                    System.out.println(car);
                } else {
                    System.out.println("Car not found.");
                }
                break;
            case 2:  // Search parts
                var part = partManager.findPartById(id);
                if (part != null) {
                    System.out.println(part);
                } else {
                    System.out.println("Part not found.");
                }
                break;
            case 3:  // Search services
                var service = serviceManager.findServiceById(id);
                if (service != null) {
                    System.out.println(service);
                } else {
                    System.out.println("Service not found.");
                }
                break;
            case 4:  // Search transactions
                var transaction = transactionManager.findTransactionById(id);
                if (transaction != null) {
                    System.out.println(transaction);
                } else {
                    System.out.println("Transaction not found.");
                }
                break;
            case 5:  // Search users
                var user = userManager.findUserById(id);
                if (user != null) {
                    System.out.println(user);
                } else {
                    System.out.println("User not found.");
                }
                break;
            default:
                System.out.println("Invalid entity choice.");
        }
    }

    // CRUD Menu
    private void performCrudOperations() {
        boolean back = false;

        while (!back) {
            System.out.println("\nCRUD Operations:");
            System.out.println("1. Cars");
            System.out.println("2. Parts");
            System.out.println("3. Services");
            System.out.println("4. Back");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    carCRUDMenu();
                    break;
                case 2:
                    partCRUDMenu();
                    break;
                case 3:
                    serviceCRUDMenu();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Car CRUD
    private void carCRUDMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("Car CRUD Menu:");
            System.out.println("1. Create car");
            System.out.println("2. Update car");
            System.out.println("3. Delete car");
            System.out.println("4. Return");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    carCRUD.createCar();
                    break;
                case 2:
                    System.out.print("Enter the car ID to update: ");
                    String carId = scanner.nextLine();
                    carCRUD.updateCar(carId);
                    break;
                case 3:
                    System.out.print("Enter the car ID to delete: ");
                    carId = scanner.nextLine();
                    carCRUD.deleteCar(carId);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Part CRUD
    private void partCRUDMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("Part CRUD Menu:");
            System.out.println("1. Create part");
            System.out.println("2. Update part");
            System.out.println("3. Delete part");
            System.out.println("4. Return");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    partCRUD.createPart();
                    break;
                case 2:
                    System.out.print("Enter the part ID to update: ");
                    String partId = scanner.nextLine();
                    partCRUD.updatePart(partId);
                    break;
                case 3:
                    System.out.print("Enter the part ID to delete: ");
                    partId = scanner.nextLine();
                    partCRUD.deletePart(partId);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Service CRUD
    private void serviceCRUDMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("Service CRUD Menu:");
            System.out.println("1. Create service");
            System.out.println("2. Update service");
            System.out.println("3. Delete service");
            System.out.println("4. Return");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    serviceCRUD.createService();
                    break;
                case 2:
                    System.out.print("Enter the service ID to update: ");
                    String serviceId = scanner.nextLine();
                    serviceCRUD.updateService(serviceId);
                    break;
                case 3:
                    System.out.print("Enter the service ID to delete: ");
                    serviceId = scanner.nextLine();
                    serviceCRUD.deleteService(serviceId);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    // Calculate statistics
    private void calculateStatistics() {
        boolean back = false;

        while (!back) {
            System.out.println("\nStatistics Menu:");
            System.out.println("1. Calculate number of cars sold in a month");
            System.out.println("2. Calculate revenue in a time period");
            System.out.println("3. Calculate revenue of services by a mechanic");
            System.out.println("4. Calculate revenue of cars sold by a salesperson");
            System.out.println("5. Return to main menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    calculateCarsSoldInMonth();
                    break;
                case 2:
                    calculateRevenueInTimePeriod();
                    break;
                case 3:
                    calculateMechanicRevenue();
                    break;
                case 4:
                    calculateSalespersonRevenue();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Calculate cars sold
    private void calculateCarsSoldInMonth() {
        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        long carsSold = transactionManager.calculateCarsSoldInMonth(month, year);  // Return type is now long
        System.out.println("Number of cars sold in " + month + "/" + year + ": " + carsSold);
    }


    // Calculate revenue in time period
    private void calculateRevenueInTimePeriod() {
        System.out.println("Enter the start date (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter the end date (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        double revenue = transactionManager.calculateRevenue(startDate, endDate);
        System.out.println("Total revenue from " + startDate + " to " + endDate + ": $" + revenue);
    }

    // Calculate mechanic revenue
    private void calculateMechanicRevenue() {
        System.out.print("Enter mechanic ID: ");
        String mechanicID = scanner.nextLine();

        System.out.println("Enter the start date (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter the end date (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        double revenue = transactionManager.calculateMechanicRevenue(mechanicID, startDate, endDate);
        System.out.println("Total revenue from services by mechanic " + mechanicID + ": $" + revenue);
    }


    // Calculate salesperson revenue
    private void calculateSalespersonRevenue() {
        System.out.print("Enter salesperson ID: ");
        String salespersonID = scanner.nextLine();

        System.out.println("Enter the start date (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter the end date (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        double revenue = transactionManager.calculateSalespersonRevenue(salespersonID, startDate, endDate);
        System.out.println("Total revenue from cars sold by " + salespersonID + ": $" + revenue);
    }
}
