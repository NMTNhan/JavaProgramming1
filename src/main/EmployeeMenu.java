package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import managers.TransactionManager;

public class EmployeeMenu {
    private TransactionManager transactionManager;
    private Scanner scanner;

    public EmployeeMenu(TransactionManager transactionManager, Scanner scanner) {
        this.transactionManager = transactionManager;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Calculate revenue in a time period");
            System.out.println("2. List the number of cars sold in a time period");
            System.out.println("3. List the number of services sold in a time period");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    calculateRevenue();
                    break;
                case 2:
                    listCarsSold();
                    break;
                case 3:
                    listServicesSold();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void calculateRevenue() {
        LocalDate[] dateRange = promptForDateRange();
        double revenue = transactionManager.calculateRevenue(dateRange[0], dateRange[1]);
        System.out.println("Revenue between " + dateRange[0] + " and " + dateRange[1] + ": $" + revenue);
    }

    private void listCarsSold() {
        LocalDate[] dateRange = promptForDateRange();
        System.out.println("Cars sold between " + dateRange[0] + " and " + dateRange[1] + ":");
        transactionManager.listCarsSold(dateRange[0], dateRange[1]);
    }

    private void listServicesSold() {
        LocalDate[] dateRange = promptForDateRange();
        System.out.println("Services performed between " + dateRange[0] + " and " + dateRange[1] + ":");
        transactionManager.listServicesPerformed(dateRange[0], dateRange[1]);
    }


    private LocalDate[] promptForDateRange() {
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        return new LocalDate[]{startDate, endDate};
    }
}
