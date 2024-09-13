package user;

import java.time.LocalDate;
import java.util.Date;

public class Salesperson extends Employee {
    public Salesperson(String userID, String username, String password, LocalDate dateOfBirth, String address,
                       String phoneNumber, String email, String status) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, status, "Salesperson");
    }


    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
