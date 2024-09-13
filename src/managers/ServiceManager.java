package managers;

import models.Service;
import user.Client;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceManager {

    // File path for the services data file
    private static final String SERVICE_FILE_PATH = "data/services";

    // List to hold all services
    private List<Service> services = new ArrayList<>();

    public void getAllServices() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (Service service : services) {
                System.out.println(service);  // Ensure Service class has a proper toString method
            }
        }
    }

    // Constructor: Load services from file when the manager is initialized
    public ServiceManager() {
        loadServicesFromFile();
    }

    // Convert string line from file to a Service object
    private Service deserializeService(String line) {
        String[] serviceData = line.split("\\|");  // Use '|' as the delimiter

        // Check if the data has the correct number of fields (should be 8 based on the data format)
        if (serviceData.length != 8) {
            throw new IllegalArgumentException("Invalid service data format: " + line);
        }

        String serviceID = serviceData[0];
        LocalDate serviceDate = LocalDate.parse(serviceData[1]);  // Parse the date
        String clientID = serviceData[2];
        String mechanicID = serviceData[3];
        String serviceType = serviceData[4];

        // Split parts by ';' if there are multiple parts
        List<String> replacedParts = Arrays.asList(serviceData[5].split(";"));

        double serviceCost = Double.parseDouble(serviceData[6]);
        String additionalNotes = serviceData[7];

        // Return a new Service object with the parsed data
        return new Service(serviceID, serviceDate, clientID, mechanicID, serviceType, replacedParts, serviceCost, additionalNotes);
    }

    // Convert a Service object to string for writing to file
    private String serializeService(Service service) {
        String replacedParts = String.join(";", service.getReplacedParts());
        return service.getServiceID() + "," + service.getServiceDate() + "," + service.getClientID() + ","
                + service.getMechanicID() + "," + service.getServiceType() + "," + replacedParts + ","
                + service.getServiceCost() + "," + service.getAdditionalNotes();
    }

    // Load services from file
    private void loadServicesFromFile() {
        List<String> serviceLines = FileUtils.readFile(SERVICE_FILE_PATH);
        for (String line : serviceLines) {
            services.add(deserializeService(line));  // Assuming deserializeService() exists and works
        }
    }

    // Add a new service and update client's total spending
    public void addService(Service service) {
        services.add(service);
        // No need to update client spending in this method

        // Serialize services to save them to the file
        List<String> serializedServices = services.stream()
                .map(this::serializeService)
                .collect(Collectors.toList());
        FileUtils.writeFile(SERVICE_FILE_PATH, serializedServices);
    }


    // Remove a service
    public void removeService(String serviceId) {
        services = services.stream()
                .filter(service -> !service.getServiceID().equals(serviceId))
                .collect(Collectors.toList());
        List<String> serializedServices = services.stream()
                .map(this::serializeService)
                .collect(Collectors.toList());
        FileUtils.writeFile(SERVICE_FILE_PATH, serializedServices);
    }

    // Update an existing service
    public void updateService(String serviceId, Service updatedService) {
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getServiceID().equals(serviceId)) {
                services.set(i, updatedService);
                break;
            }
        }
        List<String> serializedServices = services.stream()
                .map(this::serializeService)
                .collect(Collectors.toList());
        FileUtils.writeFile(SERVICE_FILE_PATH, serializedServices);
    }

    // Find a service by its ID and return the service object (not void)
    public Service findServiceById(String serviceId) {
        for (Service service : services) {
            if (service.getServiceID().equals(serviceId)) {
                return service;  // Return the service if found
            }
        }
        return null;  // Return null if no service is found
    }
}
