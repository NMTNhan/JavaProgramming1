package main;

import managers.ServiceManager;
import models.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceCRUD {

    private ServiceManager serviceManager;
    private Scanner scanner;

    public ServiceCRUD(ServiceManager serviceManager, Scanner scanner) {
        this.serviceManager = serviceManager;
        this.scanner = scanner;
    }

    public void createService() {
        System.out.print("Enter service ID: ");
        String serviceID = scanner.nextLine();
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine();
        System.out.print("Enter mechanic ID: ");
        String mechanicID = scanner.nextLine();
        System.out.print("Enter service type: ");
        String serviceType = scanner.nextLine();
        System.out.print("Enter service cost: ");
        double serviceCost = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Assuming the service date is today and replacedParts and additionalNotes are empty for now
        LocalDate serviceDate = LocalDate.now();
        List<String> replacedParts = new ArrayList<>();
        String additionalNotes = "";

        // Create a new Service object with all required parameters
        Service newService = new Service(serviceID, serviceDate, clientID, mechanicID, serviceType, replacedParts, serviceCost, additionalNotes);

        serviceManager.addService(newService);
        System.out.println("Service created successfully.");
    }


    public void updateService() {
        System.out.print("Enter service ID to update: ");
        String serviceID = scanner.nextLine();

        Service service = serviceManager.findServiceById(serviceID);
        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        service.setServiceType(newName);
        service.setServiceCost(newPrice);
        System.out.println("Service updated successfully.");
    }

    public void deleteService() {
        System.out.print("Enter service ID to delete: ");
        String serviceID = scanner.nextLine();

        Service service = serviceManager.findServiceById(serviceID);
        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        serviceManager.removeService(serviceID);
        System.out.println("Service deleted successfully.");
    }
}
