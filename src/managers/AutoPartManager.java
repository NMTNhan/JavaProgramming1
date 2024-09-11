package managers;

import models.AutoPart;
import utils.FileUtils;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class AutoPartManager {

    // File path for the auto parts data file
    private static final String PART_FILE_PATH = "src/data/parts";

    // List to hold all parts
    private List<AutoPart> parts = new ArrayList<>();

    // Constructor: Load parts from file when the manager is initialized
    public AutoPartManager() {
        loadPartsFromFile();
    }

    // Convert string line from file to an AutoPart object
    private AutoPart deserializePart(String line) {
        // Assuming the part is stored as: partID, partName, manufacturer, condition, warranty, cost
        String[] partsData = line.split(",");
        return new AutoPart(partsData[0], partsData[1], partsData[2], partsData[3], partsData[4], Double.parseDouble(partsData[5]));
    }

    // Convert an AutoPart object to string for writing to file
    private String serializePart(AutoPart part) {
        return part.getPartID() + "," + part.getPartName() + "," + part.getManufacturer() + "," + part.getCondition() + "," +
                part.getWarranty() + "," + part.getCost();
    }

    // Load parts from file
    private void loadPartsFromFile() {
        List<String> lines = FileUtils.readFile(PART_FILE_PATH);
        parts = lines.stream()
                .map(this::deserializePart)
                .collect(Collectors.toList());
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
}
