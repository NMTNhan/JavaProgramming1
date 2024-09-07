package managers;

import models.AutoPart;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import utils.FileUtils;

public class AutoPartManager {

    // File path for the auto parts data file
    private static final String PART_FILE_PATH = "src/data/parts";

    // List to hold all parts
    private List<AutoPart> parts = new ArrayList<>();

    // Constructor: Load parts from file when the manager is initialized
    public AutoPartManager() {
        loadPartsFromFile();
    }

    // Method to load parts from the file
    private void loadPartsFromFile() {
        List<String> partData = FileUtils.readFile(PART_FILE_PATH);
        System.out.println("Total lines read from parts.txt: " + partData.size());  // Debug output
        for (String line : partData) {
            System.out.println("Reading line: " + line);  // Debug output
            String[] partsArray = line.split("\\|");
            if (partsArray.length == 6) {
                AutoPart part = new AutoPart(partsArray[0], partsArray[1], partsArray[2], partsArray[3], partsArray[4], Double.parseDouble(partsArray[5]));
                parts.add(part);
            }
        }
        System.out.println("Total parts loaded: " + parts.size());  // Debug output
    }

    // Method to save parts back to the file
    private void savePartsToFile() {
        List<String> partData = new ArrayList<>();
        for (AutoPart part : parts) {
            partData.add(part.getPartID() + "|" + part.getPartName() + "|" + part.getManufacturer() + "|" + part.getCondition() + "|" + part.getWarranty() + "|" + part.getCost());
        }
        FileUtils.writeFile(PART_FILE_PATH, partData);
    }

    // CRUD
    // Create: Add a new part
    public void addPart(AutoPart part) {
        parts.add(part);
        savePartsToFile();  // Save after adding
    }

    // Update: Update an existing part by ID
    public void updatePart(String partID, AutoPart updatedPart) {
        AutoPart part = getPartByID(partID);
        if (part != null) {
            part.setPartName(updatedPart.getPartName());
            part.setManufacturer(updatedPart.getManufacturer());
            part.setCondition(updatedPart.getCondition());
            part.setWarranty(updatedPart.getWarranty());
            part.setCost(updatedPart.getCost());
            savePartsToFile();  // Save after updating
        }
    }

    // Delete: Remove a part by ID
    public void deletePart(String partID) {
        parts.removeIf(part -> part.getPartID().equals(partID));
        savePartsToFile();  // Save after deleting
    }

    // Retrieve a part by ID (helper function)
    public AutoPart getPartByID(String partID) {
        return parts.stream().filter(part -> part.getPartID().equals(partID)).findFirst().orElse(null);
    }

    // Method to print all parts
    public void printAllParts() {
        parts.forEach(System.out::println);
    }

    // Manager queries
    // Search parts by manufacturer
    public List<AutoPart> searchPartsByManufacturer(String manufacturer) {
        return parts.stream()
                .filter(part -> part.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    // Search parts by condition
    public List<AutoPart> searchPartsByCondition(String condition) {
        return parts.stream()
                .filter(part -> part.getCondition().equalsIgnoreCase(condition))
                .collect(Collectors.toList());
    }

    // Search parts by price range
    public List<AutoPart> searchPartsByPriceRange(double minPrice, double maxPrice) {
        return parts.stream()
                .filter(part -> part.getCost() >= minPrice && part.getCost() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Search parts by warranty duration
    public List<AutoPart> searchPartsByWarranty(String warranty) {
        return parts.stream()
                .filter(part -> part.getWarranty().equalsIgnoreCase(warranty))
                .collect(Collectors.toList());
    }

    // Count parts by condition
    public long countPartsByCondition(String condition) {
        return parts.stream()
                .filter(part -> part.getCondition().equalsIgnoreCase(condition))
                .count();
    }

    //
}
