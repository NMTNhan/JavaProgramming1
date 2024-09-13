package models;

import java.time.LocalDate;
import java.util.List;


public class Service implements IService{
    private String serviceID;
    private LocalDate serviceDate;
    private String clientID;
    private String mechanicID;
    private String serviceType;
    private List<String> replacedParts; // Newly added field
    private double serviceCost;
    private String additionalNotes; // Newly added field

    // Constructor
    public Service(String serviceID, LocalDate serviceDate, String clientID, String mechanicID, String serviceType, List<String> replacedParts, double serviceCost, String additionalNotes) {
        this.serviceID = serviceID;
        this.serviceDate = serviceDate;
        this.clientID = clientID;
        this.mechanicID = mechanicID;
        this.serviceType = serviceType;
        this.replacedParts = replacedParts;
        this.serviceCost = serviceCost;
        this.additionalNotes = additionalNotes;
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

    public List<String> getReplacedParts() { return replacedParts; }
    public void setReplacedParts(List<String> replacedParts) { this.replacedParts = replacedParts; }

    public double getServiceCost() { return serviceCost; }
    public void setServiceCost(double serviceCost) { this.serviceCost = serviceCost; }

    public String getAdditionalNotes() { return additionalNotes; }
    public void setAdditionalNotes(String additionalNotes) { this.additionalNotes = additionalNotes; }

    @Override
    public String toString() {
        return serviceID + " - " + serviceType + " (Cost: " + serviceCost + "), Service Date: " + serviceDate + ", Notes: " + additionalNotes;
    }
}
