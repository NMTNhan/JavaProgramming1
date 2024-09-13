package main;

import managers.CarManager;
import managers.AutoPartManager;
import managers.ServiceManager;
import managers.TransactionManager;
import managers.UserManager;
import main.CarCRUD;
import main.PartCRUD;
import main.ServiceCRUD;
import models.AutoPart;
import models.Car;
import models.Service;
import models.Transaction;
import user.User;

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

    public void displayMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\nManager Menu:");
            System.out.println("1. View all entities");
            System.out.println("2. Search entities by ID");
            System.out.println("3. Perform CRUD operations");
            System.out.println("4. Calculate statistics");
            System.out.println("5. List items");
            System.out.println("6. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllEntities();
                    break;
                case 2:
                    searchEntitiesById();
                    break;
                case 3:
                    performCrudOperations();
                    break;
                case 4:
                    calculateStatistics();
                    break;
                case 5:
                    listItems();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewAllEntities() {
        System.out.println("View all entities:");
        System.out.println("1. Cars");
        System.out.println("2. Parts");
        System.out.println("3. Services");
        System.out.println("4. Transactions");
        System.out.println("5. Users");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                carManager.getAllCars();
                break;
            case 2:
                partManager.getAllParts();
                break;
            case 3:
                serviceManager.getAllServices();
                break;
            case 4:
                transactionManager.getAllTransactions();
                break;
            case 5:
                userManager.getAllUsers();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void searchEntitiesById() {
        System.out.println("Search entities by ID:");
        System.out.println("1. Cars");
        System.out.println("2. Parts");
        System.out.println("3. Services");
        System.out.println("4. Transactions");
        System.out.println("5. Users");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter the ID: ");
        String id = scanner.nextLine();

        switch (choice) {
            case 1:  // Cars
                Car car = carManager.findCarById(id);
                if (car != null) {
                    System.out.println(car);  // Print car details
                } else {
                    System.out.println("Car with ID " + id + " not found.");
                }
                break;
            case 2:  // Parts
                AutoPart part = partManager.findPartById(id);
                if (part != null) {
                    System.out.println(part);  // Print part details
                } else {
                    System.out.println("Part with ID " + id + " not found.");
                }
                break;
            case 3:  // Services
                Service service = serviceManager.findServiceById(id);
                if (service != null) {
                    System.out.println(service);  // Print service details
                } else {
                    System.out.println("Service with ID " + id + " not found.");
                }
                break;
            case 4:  // Transactions
                Transaction transaction = transactionManager.findTransactionById(id);
                if (transaction != null) {
                    System.out.println(transaction);  // Print transaction details
                } else {
                    System.out.println("Transaction with ID " + id + " not found.");
                }
                break;
            case 5:  // Users
                User user = userManager.findUserById(id);
                if (user != null) {
                    System.out.println(user);  // Print user details
                } else {
                    System.out.println("User with ID " + id + " not found.");
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void performCrudOperations() {
        System.out.println("CRUD operations:");
        System.out.println("1. Cars");
        System.out.println("2. Parts");
        System.out.println("3. Services");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                carCRUD.createCar();
                break;
            case 2:
                partCRUD.createPart();
                break;
            case 3:
                serviceCRUD.createService();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void carCRUDMenu() {
        boolean back = false;

        while (!back) {
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
                    carCRUD.updateCar();
                    break;
                case 3:
                    carCRUD.deleteCar();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void partCRUDMenu() {
        boolean back = false;

        while (!back) {
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
                    partCRUD.updatePart();
                    break;
                case 3:
                    partCRUD.deletePart();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void serviceCRUDMenu() {
        boolean back = false;

        while (!back) {
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
                    serviceCRUD.updateService();
                    break;
                case 3:
                    serviceCRUD.deleteService();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void calculateStatistics() {
        boolean back = false;

        while (!back) {
            System.out.println("Calculate statistics:");
            System.out.println("1. Number of cars sold in a month");
            System.out.println("2. Revenue in a time period");
            System.out.println("3. Services done by a mechanic");
            System.out.println("4. Revenue of cars sold by a salesperson");
            System.out.println("5. Return");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    transactionManager.calculateCarsSoldInMonth(month, year);
                    break;
                case 2:
                    LocalDate[] dateRange = promptForDateRange();
                    transactionManager.calculateRevenue(dateRange[0], dateRange[1]);
                    break;
                case 3:
                    System.out.print("Enter mechanic ID: ");
                    String mechanicId = scanner.nextLine();
                    dateRange = promptForDateRange();
                    transactionManager.calculateMechanicRevenue(mechanicId, dateRange[0], dateRange[1]);
                    break;
                case 4:
                    System.out.print("Enter salesperson ID: ");
                    String salespersonId = scanner.nextLine();
                    dateRange = promptForDateRange();
                    transactionManager.calculateSalespersonRevenue(salespersonId, dateRange[0], dateRange[1]);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private LocalDate[] promptForDateRange() {
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        return new LocalDate[] { startDate, endDate };
    }

    private void listItems() {
        boolean back = false;

        while (!back) {
            System.out.println("List items:");
            System.out.println("1. Cars sold");
            System.out.println("2. Transactions");
            System.out.println("3. Services");
            System.out.println("4. Parts sold");
            System.out.println("5. Return");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            LocalDate[] dateRange = promptForDateRange();

            switch (choice) {
                case 1:
                    transactionManager.listCarsSold(dateRange[0], dateRange[1]);
                    break;
                case 2:
                    transactionManager.listTransactions(dateRange[0], dateRange[1]);
                    break;
                case 3:
                    transactionManager.listServicesPerformed(dateRange[0], dateRange[1]);
                    break;
                case 4:
                    transactionManager.listAutoPartsSold(dateRange[0], dateRange[1]);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
