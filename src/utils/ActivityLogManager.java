package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActivityLogManager {

    private static final String LOG_FILE_PATH = "data/activity_log";  // Path to log file

    // Method to log activity
    public static void logActivity(String activityType) {
        try (FileWriter logWriter = new FileWriter(LOG_FILE_PATH, true)) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            logWriter.write(timeStamp + " - " + activityType + ": " + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to activity log file: " + e.getMessage());
        }
    }

    // Method to clear the log
    public static void clearLog() {
        try (FileWriter logWriter = new FileWriter(LOG_FILE_PATH)) {
            logWriter.write("");  // Overwrite the log file with an empty string
        } catch (IOException e) {
            System.out.println("Error clearing activity log file: " + e.getMessage());
        }
    }

    // Method to read the log
    public static String readLog() {
        StringBuilder logContent = new StringBuilder();
        try {
            List<String> logLines = Files.readAllLines(Paths.get(LOG_FILE_PATH));
            for (String line : logLines) {
                logContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading activity log file: " + e.getMessage());
        }
        return logContent.toString();
    }
}
