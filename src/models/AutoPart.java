package models;

public class AutoPart {
    private String partID;
    private String partName;
    private String manufacturer;
    private String condition; // new, used, refurbished
    private String warranty;
    private double cost;

    // Constructor
    public AutoPart(String partID, String partName, String manufacturer, String condition, String warranty, double cost) {
        this.partID = partID;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.condition = condition;
        this.warranty = warranty;
        this.cost = cost;
    }

    // Getters and Setters
    public String getPartID() { return partID; }
    public void setPartID(String partID) { this.partID = partID; }

    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public String toString() {
        return partID + " " + partName + " by " + manufacturer + " - " + condition +
                ", Warranty: " + warranty + ", Cost: $" + cost;
    }
}
