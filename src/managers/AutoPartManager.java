package managers;

import models.AutoPart;
import user.Client;
import utils.FileUtils;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class AutoPartManager {

    // File path for the auto parts data file
    private static final String PART_FILE_PATH = "data/parts";

    // List to hold all parts
    private List<AutoPart> parts = new ArrayList<>();

    public void getAllParts() {
        if (parts.isEmpty()) {
            System.out.println("No parts available.");
        } else {
            for (AutoPart part : parts) {
                System.out.println(part);  // Ensure AutoPart class has a proper toString method
            }
        }
    }


    // Constructor: Load parts from file when the manager is initialized
    public AutoPartManager() {
        loadPartsFromFile();
    }

    // Convert string line from file to an AutoPart object
    private AutoPart deserializePart(String line) {
        String[] partData = line.split(","); // Use comma as delimiter

        // Check if the data has the correct number of fields
        if (partData.length != 8) {
            throw new IllegalArgumentException("Invalid auto part data format: " + line);
        }

        // Assuming the part is stored as: partID, partName, manufacturer, partNumber, condition, warranty, cost, additionalNotes
        try {
            String partID = partData[0];
            String partName = partData[1];
            String manufacturer = partData[2];
            String partNumber = partData[3];
            String condition = partData[4];
            String warranty = partData[5];
            double cost = Double.parseDouble(partData[6]);
            String additionalNotes = partData[7];

            return new AutoPart(partID, partName, manufacturer, partNumber, condition, warranty, cost, additionalNotes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing auto part data: " + line, e);
        }
    }

    // Convert an AutoPart object to string for writing to file
    private String serializePart(AutoPart part) {
        return part.getPartID() + "," + part.getPartName() + "," + part.getManufacturer() + "," + part.getPartNumber() + ","
                + part.getCondition() + "," + part.getWarranty() + "," + part.getCost() + "," + part.getAdditionalNotes();
    }

    // Load parts from file
    private void loadPartsFromFile() {
        List<String> partLines = FileUtils.readFile(PART_FILE_PATH);
        for (String line : partLines) {
            parts.add(deserializePart(line));  // Assuming deserializePart() exists and works
        }
    }

    // Add a new part
    public void addPart(AutoPart part) {
        parts.add(part);
        List<String> serializedParts = parts.stream()
                .map(this::serializePart)
                .collect(Collectors.toList());
        FileUtils.writeFile(PART_FILE_PATH, serializedParts);
    }

    // Remove a part
    public void removePart(String partId) {
        parts = parts.stream()
                .filter(part -> !part.getPartID().equals(partId))
                .collect(Collectors.toList());
        List<String> serializedParts = parts.stream()
                .map(this::serializePart)
                .collect(Collectors.toList());
        FileUtils.writeFile(PART_FILE_PATH, serializedParts);
    }

    // Update an existing part
    public void updatePart(String partId, AutoPart updatedPart) {
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getPartID().equals(partId)) {
                parts.set(i, updatedPart);
                break;
            }
        }
        List<String> serializedParts = parts.stream()
                .map(this::serializePart)
                .collect(Collectors.toList());
        FileUtils.writeFile(PART_FILE_PATH, serializedParts);
    }

    // Find a part by its ID and return the part object (not void)
    public AutoPart findPartById(String partId) {
        for (AutoPart part : parts) {
            if (part.getPartID().equals(partId)) {
                return part;  // Return the part if found
            }
        }
        return null;  // Return null if no part is found
    }
}
