package user;

import java.util.Date;

public abstract class User {
    protected String userID;
    protected String username;
    protected String password;
    protected Date dateOfBirth;
    protected String address;
    protected String phoneNumber;
    protected String email;
    protected String role; // Manager, Employee, Client
    protected String status; // Active/Inactive

    // Constructor
    public User(String userID, String username, String password, Date dateOfBirth, String address, String phoneNumber, String email, String role, String status) {
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

    // Method to get the user ID
    public String getUserId() {
        return this.userID;
    }

    // Method to set the user's active/inactive status
    public void setActive(boolean isActive) {
        this.status = isActive ? "Active" : "Inactive";
    }

    // Abstract login method to be implemented by subclasses
    public abstract boolean login(String username, String password);
}
