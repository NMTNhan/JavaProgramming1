package managers;

import models.Car;
import utils.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarManager {

    // File path for the cars data file
    private static final String CAR_FILE_PATH = "src/data/cars";

    // List to hold all cars
    private List<Car> cars = new ArrayList<>();

    // Constructor: Load cars from file when the manager is initialized
    public CarManager() {
        loadCarsFromFile();
    }

    // Method to load cars from the file
    private void loadCarsFromFile() {
        List<String> carData = FileUtils.readFile(CAR_FILE_PATH);
        System.out.println("Total lines read from cars.txt: " + carData.size());  // Debug output
        for (String line : carData) {
            System.out.println("Reading line: " + line);  // Debug output
            String[] parts = line.split("\\|");
            if (parts.length == 10) {
                Car car = new Car(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]), parts[5], parts[6],
                        Double.parseDouble(parts[7]), parts[8], parts[9]);
                cars.add(car);
            }
        }
        System.out.println("Total cars loaded: " + cars.size());  // Debug output
    }

    // Method to save cars back to the file
    private void saveCarsToFile() {
        List<String> carData = new ArrayList<>();
        for (Car car : cars) {
            carData.add(car.getCarID() + "|" + car.getMake() + "|" + car.getModel() + "|" + car.getYear() + "|" +
                    car.getMileage() + "|" + car.getColor() + "|" + car.getStatus() + "|" + car.getPrice() + "|" +
                    car.getCondition() + "|" + car.getWarranty());
        }
        FileUtils.writeFile(CAR_FILE_PATH, carData);
    }

    // CRUD
    // Create: Add a new car
    public void addCar(Car car) {
        cars.add(car);
        saveCarsToFile();  // Save after adding
    }

    // Update: Update an existing car by ID
    public void updateCar(String carID, Car updatedCar) {
        Car car = getCarByID(carID);
        if (car != null) {
            car.setMake(updatedCar.getMake());
            car.setModel(updatedCar.getModel());
            car.setPrice(updatedCar.getPrice());
            car.setMileage(updatedCar.getMileage());
            car.setColor(updatedCar.getColor());
            car.setStatus(updatedCar.getStatus());
            car.setCondition(updatedCar.getCondition());
            car.setWarranty(updatedCar.getWarranty());
            saveCarsToFile();  // Save after updating
        }
    }

    // Delete: Remove a car by ID
    public void deleteCar(String carID) {
        cars.removeIf(car -> car.getCarID().equals(carID));
        saveCarsToFile();  // Save after deleting
    }

    // Method to print all cars
    public void printAllCars() {
        cars.forEach(System.out::println);
    }

    // Retrieve a car by ID (helper function)
    public Car getCarByID(String carID) {
        return cars.stream().filter(car -> car.getCarID().equals(carID)).findFirst().orElse(null);
    }

    // Manager queries
    // Search cars by price range
    public List<Car> searchCarsByPriceRange(double minPrice, double maxPrice) {
        return cars.stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Search cars by year
    public List<Car> searchCarsByYear(int year) {
        return cars.stream()
                .filter(car -> car.getYear() >= year)
                .collect(Collectors.toList());
    }

    // Search cars by condition
    public List<Car> searchCarsByCondition(String condition) {
        return cars.stream()
                .filter(car -> car.getCondition().equalsIgnoreCase(condition))
                .collect(Collectors.toList());
    }

    // List of all available cars
    public List<Car> listAllAvailableCars() {
        return cars.stream()
                .filter(car -> car.getStatus().equalsIgnoreCase("available"))
                .collect(Collectors.toList());
    }

    // Count cars by status
    public long countCarsByStatus(String status) {
        return cars.stream()
                .filter(car -> car.getStatus().equalsIgnoreCase(status))
                .count();
    }
}
