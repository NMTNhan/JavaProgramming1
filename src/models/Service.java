package models;

import java.time.LocalDate;

public class Service {
    private String serviceID;
    private LocalDate serviceDate;
    private String clientID;
    private String mechanicID;
    private String serviceType;
    private double serviceCost;

    // Constructor
    public Service(String serviceID, LocalDate serviceDate, String clientID, String mechanicID, String serviceType, double serviceCost) {
        this.serviceID = serviceID;
        this.serviceDate = serviceDate;
        this.clientID = clientID;
        this.mechanicID = mechanicID;
        this.serviceType = serviceType;
        this.serviceCost = serviceCost;
    }

    // Getters and Setters
    public String getServiceID() { return serviceID; }
    public void setServiceID(String serviceID) { this.serviceID = serviceID; }

    public LocalDate getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }

    public String getClientID() { return clientID; }
    public void setClientID(String clientID) { this.clientID = clientID; }

    public String getMechanicID() { return mechanicID; }
    public void setMechanicID(String mechanicID) { this.mechanicID = mechanicID; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public double getServiceCost() { return serviceCost; }
    public void setServiceCost(double serviceCost) { this.serviceCost = serviceCost; }

    @Override
    public String toString() {
        return serviceID + " - " + serviceType + " (Cost: " + serviceCost + "), Service Date: " + serviceDate;
    }
}
