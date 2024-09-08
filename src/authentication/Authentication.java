public class Authentication {
    private UserManager userManager;
    private User loggedInUser;

    public Authentication(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean login(String username, String password) {
        User user = userManager.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            logActivity("Login");
            return true;
        }
        return false;
    }

    public void logout() {
        if (loggedInUser != null) {
            logActivity("Logout");
            loggedInUser = null;
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    private void logActivity(String activity) {
        // Implement activity logging here
        System.out.println(loggedInUser.getUsername() + " has " + activity + ".");
    }
}
