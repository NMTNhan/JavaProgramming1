package models;

import java.time.LocalDate;
import java.util.List;

public interface ITransaction {
    String getTransactionID();

    void setTransactionID(String transactionID);

    LocalDate getTransactionDate();

    void setTransactionDate(LocalDate transactionDate);

    String getClientID();

    void setClientID(String clientID);

    String getSalespersonID();

    void setSalespersonID(String salespersonID);

    String getMechanicID();

    void setMechanicID(String mechanicID);

    List<String> getPurchasedItems();

    void setPurchasedItems(List<String> purchasedItems);

    double getDiscount();

    void setDiscount(double discount);

    double getTotalAmount();

    void setTotalAmount(double totalAmount);

    String getAdditionalNotes();

    void setAdditionalNotes(String additionalNotes);
}
