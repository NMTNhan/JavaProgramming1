package managers;

import models.AutoPart;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AutoPartManager {

    private static final String PART_FILE_PATH = "src/data/parts";
    private List<AutoPart> autoParts = new ArrayList<>();

    private int nextPartID = 1;

    // Constructor: Load parts from file when the manager is initialized
    public AutoPartManager() {
        loadPartsFromFile();
    }

    // Convert string line from file to an AutoPart object
    private AutoPart deserializePart(String line) {
        String[] partData = line.split(",");

        // Check if the data has the correct number of fields
        if (partData.length != 8) {
            throw new IllegalArgumentException("Invalid part data format: " + line);
        }

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
            throw new IllegalArgumentException("Error parsing part data: " + line, e);
        }
    }

    // Convert an AutoPart object to string for writing to file
    private String serializePart(AutoPart part) {
        return part.getPartID() + "," + part.getPartName() + "," + part.getManufacturer() + "," +
                part.getPartNumber() + "," + part.getCondition() + "," + part.getWarranty() + "," +
                part.getCost() + "," + part.getAdditionalNotes();
    }

    // Load parts from file
    private void loadPartsFromFile() {
        List<String> partLines = FileUtils.readFile(PART_FILE_PATH);
        for (String line : partLines) {
            autoParts.add(deserializePart(line));
        }
        System.out.println("Parts loaded: " + autoParts.size());
    }

    // Add a new part
    public void addPart(AutoPart part) {
        autoParts.add(part);
        List<String> serializedParts = autoParts.stream()
                .map(this::serializePart)
                .collect(Collectors.toList());
        FileUtils.writeFile(PART_FILE_PATH, serializedParts);
    }

    // Remove a part
    public void removePart(String partId) {
        autoParts = autoParts.stream()
                .filter(part -> !part.getPartID().equals(partId))
                .collect(Collectors.toList());
        List<String> serializedParts = autoParts.stream()
                .map(this::serializePart)
                .collect(Collectors.toList());
        FileUtils.writeFile(PART_FILE_PATH, serializedParts);
    }

    // Update an existing part
    public void updatePart(String partId, AutoPart updatedPart) {
        for (int i = 0; i < autoParts.size(); i++) {
            if (autoParts.get(i).getPartID().equals(partId)) {
                autoParts.set(i, updatedPart);
                break;
            }
        }
        List<String> serializedParts = autoParts.stream()
                .map(this::serializePart)
                .collect(Collectors.toList());
        FileUtils.writeFile(PART_FILE_PATH, serializedParts);
    }

    // Find a part by its ID
    public AutoPart findPartById(String partId) {
        for (AutoPart part : autoParts) {
            if (part.getPartID().equals(partId)) {
                return part;
            }
        }
        return null;
    }

    // Get all parts
    public void getAllParts() {
        if (autoParts.isEmpty()) {
            System.out.println("No parts available.");
        } else {
            for (AutoPart part : autoParts) {
                System.out.println(part);
            }
        }
    }

    // Method to generate the next part ID with the format pXXX
    public String generatePartID() {
        String partID = "p" + String.format("%03d", nextPartID);
        nextPartID++;
        return partID;
    }

}
