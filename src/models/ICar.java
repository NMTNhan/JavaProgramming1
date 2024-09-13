package models;

import java.util.List;

public interface ICar {
    String getCarID();
    void setCarID(String carID);

    String getMake();
    void setMake(String make);

    String getModel();
    void setModel(String model);

    int getYear();
    void setYear(int year);

    double getMileage();
    void setMileage(double mileage);

    String getColor();
    void setColor(String color);

    String getStatus();
    void setStatus(String status);

    double getPrice();
    void setPrice(double price);

    String getAdditionalNotes();
    void setAdditionalNotes(String additionalNotes);

    // New methods for managing history services
    List<String> getHistoryServices();
    void setHistoryServices(List<String> historyServices);
}
