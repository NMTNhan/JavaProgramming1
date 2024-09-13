package main;


import managers.CarManager;
import models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CarCRUD {

    private CarManager carManager;
    private Scanner scanner;

    public CarCRUD(CarManager carManager, Scanner scanner) {
        this.carManager = carManager;
        this.scanner = scanner;  // Use the passed Scanner instance
    }

    public void createCar() {
        System.out.print("Enter car ID: ");
        String carID = scanner.nextLine();
        System.out.print("Enter car make: ");
        String make = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car year: ");
        int year = scanner.nextInt();
        System.out.print("Enter car mileage: ");
        double mileage = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter car color: ");
        String color = scanner.nextLine();
        System.out.print("Enter car status: ");
        String status = scanner.nextLine();
        System.out.print("Enter car price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Assuming historyServices and additionalNotes can be empty or default values
        List<String> historyServices = new ArrayList<>();
        String additionalNotes = "";

        // Create a new Car object with all required parameters
        Car newCar = new Car(carID, make, model, year, mileage, color, status, price, historyServices, additionalNotes);

        carManager.addCar(newCar);
        System.out.println("Car created successfully.");
    }


    public void updateCar() {
        System.out.print("Enter car ID to update: ");
        String carID = scanner.nextLine();

        Car car = carManager.findCarById(carID);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        System.out.print("Enter new model: ");
        String newModel = scanner.nextLine();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        car.setModel(newModel);
        car.setPrice(newPrice);
        System.out.println("Car updated successfully.");
    }

    public void deleteCar() {
        System.out.print("Enter car ID to delete: ");
        String carID = scanner.nextLine();

        Car car = carManager.findCarById(carID);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        carManager.removeCar(carID);
        System.out.println("Car deleted successfully.");
    }
}
