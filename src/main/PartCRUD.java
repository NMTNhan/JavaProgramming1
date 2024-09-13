package main;

import managers.AutoPartManager;
import models.AutoPart;

import java.util.Scanner;

public class PartCRUD {

    private AutoPartManager partManager;
    private Scanner scanner;

    public PartCRUD(AutoPartManager partManager, Scanner scanner) {
        this.partManager = partManager;
        this.scanner = scanner;
    }

    public void createPart() {
        System.out.print("Enter part ID: ");
        String partID = scanner.nextLine();
        System.out.print("Enter part name: ");
        String partName = scanner.nextLine();
        System.out.print("Enter part manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter part number: ");
        String partNumber = scanner.nextLine();
        System.out.print("Enter part condition (e.g., new/used): ");
        String condition = scanner.nextLine();
        System.out.print("Enter part warranty: ");
        String warranty = scanner.nextLine();
        System.out.print("Enter part cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter additional notes: ");
        String additionalNotes = scanner.nextLine();

        // Create a new AutoPart object with all required parameters
        AutoPart newPart = new AutoPart(partID, partName, manufacturer, partNumber, condition, warranty, cost, additionalNotes);

        partManager.addPart(newPart);
        System.out.println("Part created successfully.");
    }



    public void updatePart() {
        System.out.print("Enter part ID to update: ");
        String partID = scanner.nextLine();

        AutoPart part = partManager.findPartById(partID);
        if (part == null) {
            System.out.println("Part not found.");
            return;
        }

        System.out.print("Enter new part name: ");
        String newPartName = scanner.nextLine();
        System.out.print("Enter new manufacturer: ");
        String newManufacturer = scanner.nextLine();
        System.out.print("Enter new part number: ");
        String newPartNumber = scanner.nextLine();
        System.out.print("Enter new condition: ");
        String newCondition = scanner.nextLine();
        System.out.print("Enter new warranty: ");
        String newWarranty = scanner.nextLine();
        System.out.print("Enter new cost: ");
        double newCost = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new additional notes: ");
        String newAdditionalNotes = scanner.nextLine();

        // Update the part's details
        part.setPartName(newPartName);
        part.setManufacturer(newManufacturer);
        part.setPartNumber(newPartNumber);
        part.setCondition(newCondition);
        part.setWarranty(newWarranty);
        part.setCost(newCost);
        part.setAdditionalNotes(newAdditionalNotes);

        System.out.println("Part updated successfully.");
    }


    public void deletePart() {
        System.out.print("Enter part ID to delete: ");
        String partID = scanner.nextLine();

        AutoPart part = partManager.findPartById(partID);
        if (part == null) {
            System.out.println("Part not found.");
            return;
        }

        partManager.removePart(partID);
        System.out.println("Part deleted successfully.");
    }
}
