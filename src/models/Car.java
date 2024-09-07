package models;

public class Car {
    private String carID;
    private String make;
    private String model;
    private int year;
    private double mileage;
    private String color;
    private String status; // available, sold
    private double price;
    private String condition;
    private String warranty;

    // Constructor
    public Car(String carID, String make, String model, int year, double mileage,
               String color, String status, double price, String condition, String warranty) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.status = status;
        this.price = price;
        this.condition = condition;
        this.warranty = warranty;
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

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }

    @Override
    public String toString() {
        return carID + " " + make + " " + model + " (" + year + ") - " + status +
                ", Mileage: " + mileage + "km, Price: $" + price;
    }
}
