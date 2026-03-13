import java.util.Date;

class Notification {
    private String notificationId;
    private String recipientEmail;
    private String message;
    private Date sentDate;

    public Notification(String notificationId, String recipientEmail, String message) {
        this.notificationId = notificationId;
        this.recipientEmail = recipientEmail;
        this.message = message;
        this.sentDate = new Date();
    }

    public void send() {
        System.out.println("Sending notification to " + recipientEmail + ": " + message);
    }

    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }
    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Date getSentDate() { return sentDate; }
}