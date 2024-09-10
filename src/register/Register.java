package register;

import java.io.*;

public class Register {
    private static final String ACCOUNT_FILE = "src/data/account.txt"; // Path to account.txt file

    // Method to register a new account
    public static void register(String username, String password, String role) throws IOException {
        // Check if username already exists
        if (isUsernameExists(username)) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true));
        writer.write(username + " " + password + " " + role);
        writer.newLine();
        writer.close();

        System.out.println("Registration successful! You have registered as a " + role + ".");
    }

    // Check if username already exists
    private static boolean isUsernameExists(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] accountData = line.split(" ");
            if (accountData[0].equals(username)) {
                reader.close();
                return true;
            }
        }

        reader.close();
        return false;
    }


}
