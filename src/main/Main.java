package main;

import managers.AutoPartManager;
import managers.ServiceManager;
import models.AutoPart;
import models.Service;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AutoPartManager partManager = new AutoPartManager();
        ServiceManager serviceManager = new ServiceManager();

        // --- AutoPartManager Tests ---
        // Print all parts initially
        System.out.println("Initial list of parts:");
        partManager.printAllParts();

        // Add a new part
        AutoPart newPart = new AutoPart("p031", "Clutch Kit", "Sachs", "new", "2 years", 220);
        partManager.addPart(newPart);

        // Print all parts after adding a new one
        System.out.println("\nAfter adding a new part:");
        partManager.printAllParts();

        // Update an existing part
        AutoPart updatedPart = new AutoPart("p001", "Brake Pad", "Brembo", "new", "3 years", 150);
        partManager.updatePart("p001", updatedPart);

        // Print all parts after updating
        System.out.println("\nAfter updating part p001:");
        partManager.printAllParts();

        // Delete a part
        partManager.deletePart("p002");

        // Print all parts after deleting one
        System.out.println("\nAfter deleting part p002:");
        partManager.printAllParts();

        // --- ServiceManager Tests ---
        // Print all services initially
        System.out.println("\nInitial list of services:");
        serviceManager.printAllServices();

        // Add a new service
        Service newService = new Service("s021", LocalDate.now(), "u003", "m001", "Brake Replacement", 180);
        serviceManager.addService(newService);

        // Print all services after adding a new one
        System.out.println("\nAfter adding a new service:");
        serviceManager.printAllServices();

        // Update an existing service
        Service updatedService = new Service("s001", LocalDate.of(2023, 9, 10), "u001", "m001", "Oil Change", 60);
        serviceManager.updateService("s001", updatedService);

        // Print all services after updating
        System.out.println("\nAfter updating service s001:");
        serviceManager.printAllServices();

        // Delete a service
        serviceManager.deleteService("s002");

        // Print all services after deleting one
        System.out.println("\nAfter deleting service s002:");
        serviceManager.printAllServices();
    }
}
