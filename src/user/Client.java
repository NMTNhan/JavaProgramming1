package user;

import java.time.LocalDate;
import java.util.Date;

public class Client extends User {
    private String membershipLevel; // Silver, Gold, Platinum
    private double totalSpending;

    public Client(String userID, String username, String password, LocalDate dateOfBirth, String address,
                  String phoneNumber, String email, String status, String membershipLevel) {
        super(userID, username, password, dateOfBirth, address, phoneNumber, email, "Client", status);
        this.membershipLevel = membershipLevel;
    }
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Add to total spending and check for membership upgrade
    public void addSpending(double amount) {
        this.totalSpending += amount;
        checkMembershipUpgrade();
    }

    // Check if the client qualifies for a membership upgrade
    private static final double SILVER_THRESHOLD = 30000000;
    private static final double GOLD_THRESHOLD = 100000000;
    private static final double PLATINUM_THRESHOLD = 250000000;

    private void checkMembershipUpgrade() {
        if (this.totalSpending > PLATINUM_THRESHOLD) {
            this.membershipLevel = "Platinum";
        } else if (this.totalSpending > GOLD_THRESHOLD) {
            this.membershipLevel = "Gold";
        } else if (this.totalSpending > SILVER_THRESHOLD) {
            this.membershipLevel = "Silver";
        }
    }
}
