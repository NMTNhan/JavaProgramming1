package main;

import authentication.Authentication;
import authentication.Register;
import user.Client;
import user.Employee;
import user.Manager;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // Test registering new users
            System.out.println("Registering users:");
            Register.register("clientUser", "clientPass", "Client");
            Register.register("employeeUser", "employeePass", "Employee");
            Register.register("managerUser", "managerPass", "Manager");
            System.out.println("Registration completed.\n");

            // Test login functionality for different users
            System.out.println("Testing login:");
            Authentication.login("clientUser", "clientPass"); // Expect successful login for Client
            Authentication.login("employeeUser", "employeePass"); // Expect successful login for Employee
            Authentication.login("managerUser", "managerPass"); // Expect successful login for Manager

            // Test failed login
            System.out.println("\nTesting failed login:");
            Authentication.login("clientUser", "wrongPass"); // Expect login failure

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
