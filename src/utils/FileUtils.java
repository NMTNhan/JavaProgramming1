package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // Method to read lines from a file
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return lines;
    }

    // Method to write lines to a file
    public static void writeFile(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
}
