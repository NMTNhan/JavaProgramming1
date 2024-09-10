import java.util.Date;

public class Client extends User {
    private String membershipLevel; // Silver, Gold, Platinum

    public Client(String userID, String username, String password, Date dateOfBirth, String address,
                  String phoneNumber, String email, String status, String membershipLevel) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Client", status);
        this.membershipLevel = membershipLevel;
    }

    // Get discount rate based on membership level
    public double getDiscountRate() {
        switch (membershipLevel) {
            case "Silver":
                return 0.05; // 5% discount
            case "Gold":
                return 0.10; // 10% discount
            case "Platinum":
                return 0.15; // 15% discount
            default:
                return 0.0;  // No discount
        }
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password) && this.status.equals("Active");
    }

    @Override
    public void viewProfile() {
        System.out.println("Client Profile:");
        System.out.println("Username: " + username);
        System.out.println("Membership Level: " + membershipLevel);
        // Print other details
    }

    @Override
    public void modifyProfile() {
        System.out.println("Modifying client profile...");
        // Logic for modifying profile
    }
}
