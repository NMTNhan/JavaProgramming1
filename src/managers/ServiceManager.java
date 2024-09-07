package managers;

import models.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;
import utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    // File path for the services data file
    private static final String SERVICE_FILE_PATH = "src/data/services";

    // List to hold all services
    private List<Service> services = new ArrayList<>();

    // Constructor: Load services from file when the manager is initialized
    public ServiceManager() {
        loadServicesFromFile();
    }

    // Method to load services from the file
    private void loadServicesFromFile() {
        List<String> serviceData = FileUtils.readFile(SERVICE_FILE_PATH);
        System.out.println("Total lines read from services.txt: " + serviceData.size());  // Debug output
        for (String line : serviceData) {
            System.out.println("Reading line: " + line);  // Debug output
            String[] serviceArray = line.split("\\|");
            if (serviceArray.length == 6) {
                Service service = new Service(serviceArray[0], LocalDate.parse(serviceArray[1]), serviceArray[2], serviceArray[3], serviceArray[4], Double.parseDouble(serviceArray[5]));
                services.add(service);
            }
        }
        System.out.println("Total services loaded: " + services.size());  // Debug output
    }

    // Method to save services back to the file
    private void saveServicesToFile() {
        List<String> serviceData = new ArrayList<>();
        for (Service service : services) {
            serviceData.add(service.getServiceID() + "|" + service.getServiceDate() + "|" + service.getClientID() + "|" + service.getMechanicID() + "|" + service.getServiceType() + "|" + service.getServiceCost());
        }
        FileUtils.writeFile(SERVICE_FILE_PATH, serviceData);
    }

    // CRUD
    // Create: Add a new service
    public void addService(Service service) {
        services.add(service);
        saveServicesToFile();  // Save after adding
    }

    // Update: Update an existing service by ID
    public void updateService(String serviceID, Service updatedService) {
        Service service = getServiceByID(serviceID);
        if (service != null) {
            service.setServiceDate(updatedService.getServiceDate());
            service.setClientID(updatedService.getClientID());
            service.setMechanicID(updatedService.getMechanicID());
            service.setServiceType(updatedService.getServiceType());
            service.setServiceCost(updatedService.getServiceCost());
            saveServicesToFile();  // Save after updating
        }
    }

    // Delete: Remove a service by ID
    public void deleteService(String serviceID) {
        services.removeIf(service -> service.getServiceID().equals(serviceID));
        saveServicesToFile();  // Save after deleting
    }

    // Retrieve a service by ID (helper function)
    public Service getServiceByID(String serviceID) {
        return services.stream().filter(service -> service.getServiceID().equals(serviceID)).findFirst().orElse(null);
    }

    // Method to print all services
    public void printAllServices() {
        services.forEach(System.out::println);
    }

    // Manager queries
    // Search services by clientID
    public List<Service> searchServicesByClientID(String clientID) {
        return services.stream()
                .filter(service -> service.getClientID().equals(clientID))
                .collect(Collectors.toList());
    }

    // Search services by mechanicID
    public List<Service> searchServicesByMechanicID(String mechanicID) {
        return services.stream()
                .filter(service -> service.getMechanicID().equals(mechanicID))
                .collect(Collectors.toList());
    }

    // Search services by service type
    public List<Service> searchServicesByType(String serviceType) {
        return services.stream()
                .filter(service -> service.getServiceType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
    }

    // Search services by date
    public List<Service> searchServicesByDateRange(LocalDate startDate, LocalDate endDate) {
        return services.stream()
                .filter(service -> !service.getServiceDate().isBefore(startDate) && !service.getServiceDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // Count services by mechanic
    public long countServicesByMechanicID(String mechanicID) {
        return services.stream()
                .filter(service -> service.getMechanicID().equals(mechanicID))
                .count();
    }
}
