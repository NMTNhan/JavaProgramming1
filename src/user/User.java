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
    protected String status;

    //Constructor
    public User(String userID, String username, String password, Date dateOfBirth, String address,
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

    //Getters and Setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return role;
    }

    public void setUserType(String userType) {
        this.role = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userID + '\'' +
                ", fullName='" + username + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", status=" + status +
                '}';
    }

    // Abstract methods
    public abstract boolean login(String username, String password);
    public abstract void viewProfile();
    public abstract void modifyProfile();

    public abstract Object getUserId();
    public abstract void setActive(boolean b);
    public abstract void setMembershipLevel(String membershipLevel);
    public abstract boolean isActive();
}
