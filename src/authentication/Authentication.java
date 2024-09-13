package authentication;

import user.*;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.*;

public class Authentication {
    private Map<String, User> userDatabase = new HashMap<>();
    private User loggedInUser = null;
    private static final String ACCOUNT_FILE = "data/users"; // Correct CSV file path

    public Authentication() {
        loadUsersFromFile();
    }

    // Load users from file
    private void loadUsersFromFile() {
        List<String> lines = FileUtils.readFile(ACCOUNT_FILE);

        for (String line : lines) {
            String[] userData = line.split(",");

            // Ensure that only exactly 9 or 10 fields are processed
            if (userData.length < 9 || userData.length > 10) {
                System.out.println("Invalid user data format: " + line);
                continue;
            }

            String userID = userData[0];
            String username = userData[1];
            String password = userData[2];
            LocalDate dateOfBirth = LocalDate.parse(userData[3]);
            String address = userData[4];
            String phoneNumber = userData[5];
            String email = userData[6];
            String role = userData[7];
            String status = userData[8];

            User user = null;

            if (role.equalsIgnoreCase("client")) {
                user = new Client(userID, username, password, dateOfBirth, address, phoneNumber, email, status, "Silver");
            } else if (role.equalsIgnoreCase("manager")) {
                user = new Manager(userID, username, password, dateOfBirth, address, phoneNumber, email, status);
            } else if (role.equalsIgnoreCase("employee")) {
                // Assign job position (either "salesperson" or "mechanic" depending on data)
                String jobPosition = userData.length == 10 ? userData[9] : "unknown";
                user = new Employee(userID, username, password, dateOfBirth, address, phoneNumber, email, status, jobPosition);
            }

            if (user != null) {
                userDatabase.put(username, user);  // Store the user in the map based on their username
            }
        }
    }



    // Register new user
    public void registerUser(User newUser) {
        if (!userDatabase.containsKey(newUser.getUsername())) {  // Use username as key
            userDatabase.put(newUser.getUsername(), newUser);
            saveUsersToFile();  // Save the new user to the file
            System.out.println("User " + newUser.getUsername() + " registered successfully.");
        } else {
            System.out.println("User with username " + newUser.getUsername() + " already exists.");
        }
    }


    // Login method
    public boolean login(String username, String password) {
        for (User user : userDatabase.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;  // Set the logged-in user
                return true;  // Login successful
            }
        }
        return false;  // Login failed
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }


    // Save users to the file
    private void saveUsersToFile() {
        List<String> lines = new ArrayList<>();
        for (User user : userDatabase.values()) {
            lines.add(user.getUserId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getDateOfBirth()
                    + "," + user.getAddress() + "," + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getRole() + "," + user.getStatus());
        }
        FileUtils.writeFile(ACCOUNT_FILE, lines);
    }

    // Logout method
    public void logout() {
        if (loggedInUser != null) {
            System.out.println("User " + loggedInUser.getUsername() + " logged out.");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}
