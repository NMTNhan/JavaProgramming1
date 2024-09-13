public class ActivityLog {
    private String userId;
    private String activity;
    private String timestamp;

    // Constructor
    public ActivityLog(String userId, String timestamp, String activity) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.activity = activity;
    }

    //Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
