package user;

import java.time.LocalDate;

public class Mechanic extends Employee {

    public Mechanic(String userId, String username, String password, LocalDate dateOfBirth, String address,
                    String phoneNumber, String email, String status) {
        super(userId, username, password, dateOfBirth, address, phoneNumber, email, status, "Mechanic");
    }
}
