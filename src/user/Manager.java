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
}
