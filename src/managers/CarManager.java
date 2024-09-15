package managers;

import models.Car;
import utils.FileUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarManager {

    // File path for the cars data file
    private static final String CAR_FILE_PATH = "src/data/cars";

    // List to hold all cars
    private List<Car> cars = new ArrayList<>();
    private int nextCarID = 1;

    // Constructor: Load cars from file when the manager is initialized
    public CarManager() {
        File carFile = new File(CAR_FILE_PATH);
        if (!carFile.exists()) {
            System.out.println("Car file not found. Creating a new one.");
            FileUtils.writeFile(CAR_FILE_PATH, new ArrayList<>());  // Create an empty file
        } else {
            loadCarsFromFile();
            setNextCarID(); // Set the nextCarID after loading cars
        }
    }

    // Convert string line from file to a Car object
    // Convert string line from file to a Car object
    private Car deserializeCar(String line) {
        String[] carData = line.split(",");

        // Check if the data has the correct number of fields (allowing optional fields to be empty)
        if (carData.length < 8 || carData.length > 10) {
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

            // Check if serviceIDs field exists, if not, assign an empty list
            List<String> serviceIDs = carData.length > 8 && !carData[8].isEmpty()
                    ? List.of(carData[8].split(";"))
                    : new ArrayList<>();

            // Check if additionalNotes field exists, if not, assign an empty string
            String additionalNotes = carData.length > 9 ? carData[9] : "";

            return new Car(carID, make, model, year, mileage, color, status, price, serviceIDs, additionalNotes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing car data: " + line, e);
        }
    }


    // Convert a Car object to string for writing to file
    private String serializeCar(Car car) {
        List<String> historyServices = car.getHistoryServices();
        String serviceIDs = (historyServices != null) ? String.join(";", historyServices) : ""; // Handle null historyServices
        return car.getCarID() + "," + car.getMake() + "," + car.getModel() + "," + car.getYear() + ","
                + car.getMileage() + "," + car.getColor() + "," + car.getStatus() + "," + car.getPrice() + ","
                + serviceIDs + "," + car.getAdditionalNotes();
    }


    // Load cars from file
    private void loadCarsFromFile() {
        // Assuming FileUtils.readFile() reads the car data file correctly
        List<String> carLines = FileUtils.readFile(CAR_FILE_PATH);
        for (String line : carLines) {
            cars.add(deserializeCar(line));
        }
    }

    // Create car
    public void addCar(Car car) {
        cars.add(car);
        saveCarsToFile();
        System.out.println("Car added: " + car);
    }

    // Find car by ID
    public Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarID().trim().equalsIgnoreCase(carId.trim())) {
                System.out.println("Car found: " + car);
                return car;
            }
        }
        System.out.println("Car not found: " + carId);
        return null;
    }

    // Update car
    public void updateCar(String carId, Car updatedCar) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarID().equals(carId)) {
                cars.set(i, updatedCar);
                System.out.println("Car updated: " + updatedCar);
                break;
            }
        }
        saveCarsToFile();
    }

    // Delete car
    public void removeCar(String carId) {
        cars = cars.stream()
                .filter(car -> !car.getCarID().equals(carId))
                .collect(Collectors.toList());
        saveCarsToFile();
        System.out.println("Car removed with ID: " + carId);
    }

    // Save the list of cars to the file
    private void saveCarsToFile() {
        List<String> serializedCars = cars.stream()
                .map(this::serializeCar)
                .collect(Collectors.toList());
        FileUtils.writeFile(CAR_FILE_PATH, serializedCars);
    }

    // Get all cars
    public void getAllCars() {
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    // Method to generate the next car ID
    public String generateCarID() {
        String carID = "c" + String.format("%03d", nextCarID);
        nextCarID++;
        return carID;
    }


    // Method to determine the next available car ID based on the highest existing ID
    private void setNextCarID() {
        int maxID = 0;
        for (Car car : cars) {
            String carID = car.getCarID();
            if (carID.startsWith("c")) {
                int currentID = Integer.parseInt(carID.substring(1)); // Extract the numeric part of the ID
                if (currentID > maxID) {
                    maxID = currentID;
                }
            }
        }
        nextCarID = maxID + 1;  // Set nextCarID to one higher than the highest existing ID
    }

}
