package models;

import java.util.List;

public class Car {
    private String carID;
    private String make;
    private String model;
    private int year;
    private double mileage;
    private String color;
    private String status; // available/sold
    private double price;
    private List<String> historyServices; // History of services
    private String additionalNotes;

    // Constructor with all fields
    public Car(String carID, String make, String model, int year, double mileage,
               String color, String status, double price,
               List<String> historyServices, String additionalNotes) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.status = status;
        this.price = price;
        this.historyServices = historyServices;
        this.additionalNotes = additionalNotes;
    }

    // Getters and Setters
    public String getCarID() { return carID; }
    public void setCarID(String carID) { this.carID = carID; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getMileage() { return mileage; }
    public void setMileage(double mileage) { this.mileage = mileage; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public List<String> getHistoryServices() { return historyServices; }
    public void setHistoryServices(List<String> historyServices) { this.historyServices = historyServices; }

    public String getAdditionalNotes() { return additionalNotes; }
    public void setAdditionalNotes(String additionalNotes) { this.additionalNotes = additionalNotes; }

    @Override
    public String toString() {
        return carID + " " + make + " " + model + " (" + year + ") - " + status +
                ", Mileage: " + mileage + "km, Price: $" + price + ", History Services: " + historyServices;
    }
}
