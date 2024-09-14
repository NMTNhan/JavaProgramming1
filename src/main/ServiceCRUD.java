package main;

import managers.ServiceManager;
import models.Service;

import java.util.Scanner;

public class ServiceCRUD {
    private ServiceManager serviceManager;
    private Scanner scanner;

    public ServiceCRUD(ServiceManager serviceManager, Scanner scanner) {
        this.serviceManager = serviceManager;
        this.scanner = scanner;
    }

    // Create service
    public void createService() {
        System.out.print("Enter service type: ");
        String serviceType = scanner.nextLine();
        System.out.print("Enter mechanic ID: ");
        String mechanicID = scanner.nextLine();
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine();
        System.out.print("Enter service cost: ");
        double serviceCost = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Service service = new Service(serviceManager.generateServiceID(), java.time.LocalDate.now(), clientID, mechanicID, serviceType, null, serviceCost, "");
        serviceManager.addService(service);
        System.out.println("Service added successfully!");
    }

    // Update service
    public void updateService(String serviceID) {
        Service service = serviceManager.findServiceById(serviceID);
        if (service != null) {
            System.out.print("Update service type (" + service.getServiceType() + "): ");
            String serviceType = scanner.nextLine();
            System.out.print("Update mechanic ID (" + service.getMechanicID() + "): ");
            String mechanicID = scanner.nextLine();
            System.out.print("Update client ID (" + service.getClientID() + "): ");
            String clientID = scanner.nextLine();
            System.out.print("Update service cost (" + service.getServiceCost() + "): ");
            double serviceCost = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            service.setServiceType(serviceType);
            service.setMechanicID(mechanicID);
            service.setClientID(clientID);
            service.setServiceCost(serviceCost);

            serviceManager.updateService(serviceID, service);
            System.out.println("Service updated successfully!");
        } else {
            System.out.println("Service with ID " + serviceID + " not found.");
        }
    }

    // Delete service
    public void deleteService(String serviceID) {
        serviceManager.removeService(serviceID);
        System.out.println("Service deleted successfully!");
    }
}
