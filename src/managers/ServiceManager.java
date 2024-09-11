package managers;

import models.Service;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceManager {

    // File path for the services data file
    private static final String SERVICE_FILE_PATH = "src/data/services";

    // List to hold all services
    private List<Service> services = new ArrayList<>();

    // Constructor: Load services from file when the manager is initialized
    public ServiceManager() {
        loadServicesFromFile();
    }

    // Convert string line from file to a Service object
    private Service deserializeService(String line) {
        // Assuming the service is stored as: serviceID, serviceDate, clientID, mechanicID, serviceType, serviceCost
        String[] serviceData = line.split(",");
        return new Service(serviceData[0], LocalDate.parse(serviceData[1]), serviceData[2], serviceData[3],
                serviceData[4], Double.parseDouble(serviceData[5]));
    }

    // Convert a Service object to string for writing to file
    private String serializeService(Service service) {
        return service.getServiceID() + "," + service.getServiceDate() + "," + service.getClientID() + "," +
                service.getMechanicID() + "," + service.getServiceType() + "," + service.getServiceCost();
    }

    // Load services from file
    private void loadServicesFromFile() {
        List<String> lines = FileUtils.readFile(SERVICE_FILE_PATH);
        services = lines.stream()
                .map(this::deserializeService)
                .collect(Collectors.toList());
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
}
