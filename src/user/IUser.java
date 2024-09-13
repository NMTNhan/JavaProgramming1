package user;

public interface IUser {
    String getUserId();

    void setUserId(String userID);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getAddress();

    void setAddress(String address);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getEmail();

    void setEmail(String email);

    String getRole();

    void setRole(String role);

    String getStatus();

    void setStatus(String status);

    boolean login(String username, String password);
}
