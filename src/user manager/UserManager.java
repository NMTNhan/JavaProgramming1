import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();

    // Soft delete user
    public void removeUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
            }
        }
    }

    // Update user
    public void updateUser(String userId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.set(i, updatedUser);
            }
        }
    }

    // Retrieve user by username
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.isActive()) {
                return user;
            }
        }
        return null;
    }

    // Retrieve all users (for manager)
    public List<User> getAllUsers() {
        return users;
    }

    // Update membership level
    public void updateMembershipLevel(String userId, double totalSpending) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.isActive()) {
                if (totalSpending >= 250000000) {
                    user.setMembershipLevel("Platinum");
                } else if (totalSpending >= 100000000) {
                    user.setMembershipLevel("Gold");
                } else if (totalSpending >= 30000000) {
                    user.setMembershipLevel("Silver");
                } else {
                    user.setMembershipLevel("None");
                }
            }
        }
    }

}
