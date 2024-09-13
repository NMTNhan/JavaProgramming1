package models;

import java.time.LocalDateTime;

public class ActivityLog {
    private String userId;
    private LocalDateTime timestamp;
    private String action;
    private String details;

    public ActivityLog(String userId, String action, String details) {
        this.userId = userId;
        this.timestamp = LocalDateTime.now();  // Log current time
        this.action = action;
        this.details = details;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return timestamp + " | User: " + userId + " | Action: " + action + " | Details: " + details;
    }
}
