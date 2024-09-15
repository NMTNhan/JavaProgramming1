package main;

import managers.CarManager;
import models.Car;
import utils.ActivityLogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarCRUD {
    private CarManager carManager;
    private Scanner scanner;

    public CarCRUD(CarManager carManager, Scanner scanner) {
        this.carManager = carManager;
        this.scanner = scanner;
    }

    // Create Car
    public void createCar() {
        System.out.print("Enter car maker: ");
        String make = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car year: ");
        int year = scanner.nextInt();
        System.out.print("Enter car mileage: ");
        double mileage = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter car color: ");
        String color = scanner.nextLine();
        System.out.print("Enter car price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        List<String> historyServices = new ArrayList<>();  // Initialize empty service history
        System.out.print("Enter any additional notes: ");
        String additionalNotes = scanner.nextLine();
        String carId = carManager.generateCarID();
        Car newCar = new Car(carId, make, model, year, mileage, color, "available", price, historyServices, additionalNotes);
        carManager.addCar(newCar);
        System.out.println("Car created successfully with ID: " + carId);
    }

    // Update car
    public void updateCar(String carID) {
        Car car = carManager.findCarById(carID);
        if (car != null) {
            System.out.print("Update car maker (" + car.getMake() + "): ");
            String make = scanner.nextLine();
            System.out.print("Update car model (" + car.getModel() + "): ");
            String model = scanner.nextLine();
            System.out.print("Update car year (" + car.getYear() + "): ");
            int year = scanner.nextInt();
            System.out.print("Update car mileage (" + car.getMileage() + "): ");
            double mileage = scanner.nextDouble();
            System.out.print("Update car color (" + car.getColor() + "): ");
            scanner.nextLine(); // Consume the newline
            String color = scanner.nextLine();
            System.out.print("Update car price (" + car.getPrice() + "): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            car.setMake(make);
            car.setModel(model);
            car.setYear(year);
            car.setMileage(mileage);
            car.setColor(color);
            car.setPrice(price);

            carManager.updateCar(carID, car);
            System.out.println("Car updated successfully!");
        } else {
            System.out.println("Car with ID " + carID + " not found.");
        }
    }

    // Delete car
    public void deleteCar(String carID) {
        carManager.removeCar(carID);
        System.out.println("Car deleted successfully!");
    }
}
