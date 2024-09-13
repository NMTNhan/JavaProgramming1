package user;

import managers.AutoPartManager;
import managers.ServiceManager;
import managers.TransactionManager;
import managers.CarManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Manager extends User {
    public Manager(String userID, String username, String password, LocalDate dateOfBirth, String address,
                   String phoneNumber, String email, String status) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Manager", status);
    }



    // Implement the login method
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }


    // Calculate statistics for the manager
    public void calculateStatistics(TransactionManager transactionManager) {
        LocalDate now = LocalDate.now();
        System.out.println("Number of cars sold this month: " + transactionManager.calculateCarsSoldInMonth(now.getMonthValue(), now.getYear()));
        System.out.println("Total revenue for this month: " + transactionManager.calculateRevenue(now.withDayOfMonth(1), now));
    }


    // Search for entities within a date range
    public void searchEntities(TransactionManager transactionManager, LocalDate startDate, LocalDate endDate) {
        System.out.println("Cars sold between " + startDate + " and " + endDate + ":");
        List<String> carsSold = transactionManager.listCarsSold(startDate, endDate);
        carsSold.forEach(System.out::println);

        System.out.println("Services performed between " + startDate + " and " + endDate + ":");
        List<String> servicesPerformed = transactionManager.listServicesPerformed(startDate, endDate);
        servicesPerformed.forEach(System.out::println);

        System.out.println("Auto parts sold between " + startDate + " and " + endDate + ":");
        List<String> autoPartsSold = transactionManager.listAutoPartsSold(startDate, endDate);
        autoPartsSold.forEach(System.out::println);
    }

}
