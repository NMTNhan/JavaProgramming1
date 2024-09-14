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

    // Create part
    public void createPart() {
        System.out.print("Enter part name: ");
        String partName = scanner.nextLine();
        System.out.print("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter part number: ");
        String partNumber = scanner.nextLine();
        System.out.print("Enter condition: ");
        String condition = scanner.nextLine();
        System.out.print("Enter warranty: ");
        String warranty = scanner.nextLine();
        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        AutoPart part = new AutoPart(partManager.generatePartID(), partName, manufacturer, partNumber, condition, warranty, cost, "");
        partManager.addPart(part);
        System.out.println("Part added successfully!");
    }

    // Update part
    public void updatePart(String partID) {
        AutoPart part = partManager.findPartById(partID);
        if (part != null) {
            System.out.print("Update part name (" + part.getPartName() + "): ");
            String partName = scanner.nextLine();
            System.out.print("Update manufacturer (" + part.getManufacturer() + "): ");
            String manufacturer = scanner.nextLine();
            System.out.print("Update part number (" + part.getPartNumber() + "): ");
            String partNumber = scanner.nextLine();
            System.out.print("Update condition (" + part.getCondition() + "): ");
            String condition = scanner.nextLine();
            System.out.print("Update warranty (" + part.getWarranty() + "): ");
            String warranty = scanner.nextLine();
            System.out.print("Update cost (" + part.getCost() + "): ");
            double cost = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            part.setPartName(partName);
            part.setManufacturer(manufacturer);
            part.setPartNumber(partNumber);
            part.setCondition(condition);
            part.setWarranty(warranty);
            part.setCost(cost);

            partManager.updatePart(partID, part);
            System.out.println("Part updated successfully!");
        } else {
            System.out.println("Part with ID " + partID + " not found.");
        }
    }

    // Delete part
    public void deletePart(String partID) {
        partManager.removePart(partID);
        System.out.println("Part deleted successfully!");
    }
}
