package managers;

import models.Service;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceManager {

    private static final String SERVICE_FILE_PATH = "src/data/services";
    private List<Service> services = new ArrayList<>();
    private int nextServiceID = 1;

    // Constructor: Load services from file when the manager is initialized
    public ServiceManager() {
        loadServicesFromFile();
        setNextServiceID(); // Set the nextServiceID after loading services
    }


    // Convert string line from file to a Service object
    private Service deserializeService(String line) {
        String[] serviceData = line.split("\\|");

        if (serviceData.length != 8) {
            throw new IllegalArgumentException("Invalid service data format: " + line);
        }

        try {
            String serviceID = serviceData[0];
            LocalDate serviceDate = LocalDate.parse(serviceData[1]);
            String clientID = serviceData[2];
            String mechanicID = serviceData[3];
            String serviceType = serviceData[4];

            // Split replaced parts by semicolon
            List<String> replacedParts = List.of(serviceData[5].split(";"));

            double serviceCost = Double.parseDouble(serviceData[6]);
            String additionalNotes = serviceData[7];

            return new Service(serviceID, serviceDate, clientID, mechanicID, serviceType, replacedParts, serviceCost, additionalNotes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing service data: " + line, e);
        }
    }


    // Convert a Service object to string for writing to file
    private String serializeService(Service service) {
        String replacedParts = String.join(";", service.getReplacedParts());
        return service.getServiceID() + "," + service.getServiceDate() + "," + service.getClientID() + "," +
                service.getMechanicID() + "," + service.getServiceType() + "," + replacedParts + "," +
                service.getServiceCost() + "," + service.getAdditionalNotes();
    }

    // Load services from file
    private void loadServicesFromFile() {
        List<String> serviceLines = FileUtils.readFile(SERVICE_FILE_PATH);
        for (String line : serviceLines) {
            services.add(deserializeService(line));
        }
    }

    // Add a new service
    public void addService(Service service) {
        services.add(service);
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

    // Find a service by its ID
    public Service findServiceById(String serviceId) {
        for (Service service : services) {
            if (service.getServiceID().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    // Get all services
    public void getAllServices() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (Service service : services) {
                System.out.println(service);
            }
        }
    }

    // Method to generate the next service ID with the format sXXX
    public String generateServiceID() {
        String serviceID = "s" + String.format("%03d", nextServiceID);
        nextServiceID++;
        return serviceID;
    }

    // Method to determine the next available service ID based on the highest existing ID
    private void setNextServiceID() {
        int maxID = 0;
        for (Service service : services) {
            String serviceID = service.getServiceID();
            if (serviceID.startsWith("s")) {
                int currentID = Integer.parseInt(serviceID.substring(1)); // Extract the numeric part of the ID
                if (currentID > maxID) {
                    maxID = currentID;
                }
            }
        }
        nextServiceID = maxID + 1;  // Set nextServiceID to one higher than the highest existing ID
    }

}
