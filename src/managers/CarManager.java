package managers;

import models.Car;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarManager {

    // File path for the cars data file
    private static final String CAR_FILE_PATH = "data/cars";

    // List to hold all cars
    private List<Car> cars = new ArrayList<>();

    // Constructor: Load cars from file when the manager is initialized
    public CarManager() {
        loadCarsFromFile();
    }

    // Convert string line from file to a Car object
    private Car deserializeCar(String line) {
        String[] carData = line.split(",");

        // Check if the data has the correct number of fields
        if (carData.length != 10) {
            throw new IllegalArgumentException("Invalid car data format: " + line);
        }

        try {
            String carID = carData[0];
            String make = carData[1];
            String model = carData[2];
            int year = Integer.parseInt(carData[3]);
            double mileage = Double.parseDouble(carData[4]);
            String color = carData[5];
            String status = carData[6];
            double price = Double.parseDouble(carData[7]);

            List<String> serviceIDs = List.of(carData[8].split(";"));

            String additionalNotes = carData[9];

            return new Car(carID, make, model, year, mileage, color, status, price, serviceIDs, additionalNotes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing car data: " + line, e);
        }
    }

    // Convert a Car object to string for writing to file
    private String serializeCar(Car car) {
        String serviceIDs = String.join(";", car.getHistoryServices());
        return car.getCarID() + "," + car.getMake() + "," + car.getModel() + "," + car.getYear() + ","
                + car.getMileage() + "," + car.getColor() + "," + car.getStatus() + "," + car.getPrice() + ","
                + serviceIDs + "," + car.getAdditionalNotes();
    }

    // Load cars from file
    private void loadCarsFromFile() {
        List<String> lines = FileUtils.readFile(CAR_FILE_PATH);
        cars = lines.stream()
                .filter(line -> !line.trim().isEmpty()) // Ignore empty lines
                .map(this::deserializeCar)
                .collect(Collectors.toList());
    }

    // Add a new car
    public void addCar(Car car) {
        cars.add(car);
        List<String> serializedCars = cars.stream()
                .map(this::serializeCar)
                .collect(Collectors.toList());
        FileUtils.writeFile(CAR_FILE_PATH, serializedCars);
    }

    // Remove a car
    public void removeCar(String carId) {
        cars = cars.stream()
                .filter(car -> !car.getCarID().equals(carId))
                .collect(Collectors.toList());
        List<String> serializedCars = cars.stream()
                .map(this::serializeCar)
                .collect(Collectors.toList());
        FileUtils.writeFile(CAR_FILE_PATH, serializedCars);
    }

    // Update an existing car
    public void updateCar(String carId, Car updatedCar) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarID().equals(carId)) {
                cars.set(i, updatedCar);
                break;
            }
        }
        List<String> serializedCars = cars.stream()
                .map(this::serializeCar)
                .collect(Collectors.toList());
        FileUtils.writeFile(CAR_FILE_PATH, serializedCars);
    }

    public Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarID().equals(carId)) {
                return car;
            }
        }
        return null;  // Car not found
    }

    // Get all cars
    public List<Car> getAllCars() {
        return cars;
    }


}
