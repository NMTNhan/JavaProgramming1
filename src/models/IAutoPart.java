package models;

public interface IAutoPart {
    String getPartID();

    void setPartID(String partID);

    String getPartName();

    void setPartName(String partName);

    String getManufacturer();

    void setManufacturer(String manufacturer);

    String getCondition();

    void setCondition(String condition);

    String getWarranty();

    void setWarranty(String warranty);

    double getCost();

    void setCost(double cost);
}
