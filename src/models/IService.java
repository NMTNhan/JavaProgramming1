package models;

import java.time.LocalDate;

public interface IService {
    String getServiceID();

    void setServiceID(String serviceID);

    LocalDate getServiceDate();

    void setServiceDate(LocalDate serviceDate);

    String getClientID();

    void setClientID(String clientID);

    String getMechanicID();

    void setMechanicID(String mechanicID);

    String getServiceType();

    void setServiceType(String serviceType);

    double getServiceCost();

    void setServiceCost(double serviceCost);
}
