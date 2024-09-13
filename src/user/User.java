package user;

import java.time.LocalDate;

public abstract class User implements IUser {  // Implement IUser here
    protected String userID;
    protected String username;
    protected String password;
    protected LocalDate dateOfBirth;
    protected String address;
    protected String phoneNumber;
    protected String email;
    protected String role; // Manager, Employee, Client
    protected String status; // Active/Inactive

    // Constructor
    public User(String userID, String username, String password, LocalDate dateOfBirth, String address,
                String phoneNumber, String email, String role, String status) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    // Implementing IUser methods
    @Override
    public String getUserId() { return this.userID; }

    @Override
    public void setUserId(String userID) { this.userID = userID; }

    @Override
    public String getUsername() { return this.username; }

    @Override
    public void setUsername(String username) { this.username = username; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public void setPassword(String password) { this.password = password; }

    @Override
    public String getAddress() { return this.address; }

    @Override
    public void setAddress(String address) { this.address = address; }

    @Override
    public String getPhoneNumber() { return this.phoneNumber; }

    @Override
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String getEmail() { return this.email; }

    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getRole() { return this.role; }

    @Override
    public void setRole(String role) { this.role = role; }

    @Override
    public String getStatus() { return this.status; }

    @Override
    public void setStatus(String status) { this.status = status; }

    // New Getters and Setters for dateOfBirth
    public LocalDate getDateOfBirth() { return this.dateOfBirth; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public void setActive(boolean isActive) {
        this.status = isActive ? "Active" : "Inactive";
    }


    public String getUserType() {
        return this.role;  // Assuming `userType` is a field in `User`
    }


    // This method remains abstract to be implemented by subclasses
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }


    // Optional: toString() for better debugging/logging
    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
