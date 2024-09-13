package user;

import java.time.LocalDate;

public class Employee extends User {
    private String jobPosition;

    public Employee(String userID, String username, String password, LocalDate dateOfBirth, String address,
                    String phoneNumber, String email, String status, String jobPosition) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Employee", status);
        this.jobPosition = jobPosition;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}
