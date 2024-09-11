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

    // Convert string line from file to a Car object
    private Car deserializeCar(String line) {
        // Assuming the car is stored as: carID, make, model, year, mileage, color, status, price, condition, warranty
        String[] carData = line.split(",");
        return new Car(carData[0], carData[1], carData[2], Integer.parseInt(carData[3]), Double.parseDouble(carData[4]),
                carData[5], carData[6], Double.parseDouble(carData[7]), carData[8], carData[9]);
    }

    // Convert a Car object to string for writing to file
    private String serializeCar(Car car) {
        return car.getCarID() + "," + car.getMake() + "," + car.getModel() + "," + car.getYear() + "," +
                car.getMileage() + "," + car.getColor() + "," + car.getStatus() + "," + car.getPrice() + "," +
                car.getCondition() + "," + car.getWarranty();
    }

    // Load cars from file
    private void loadCarsFromFile() {
        List<String> lines = FileUtils.readFile(CAR_FILE_PATH);
        cars = lines.stream()
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
}
