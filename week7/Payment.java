import java.util.Date;

class Payment {
    private String paymentId;
    private double amount;
    private Date paymentDate;
    private PaymentStatus status;
    private String transactionDetails;

    public Payment(String paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = new Date();
        this.status = PaymentStatus.PENDING;
    }

    public boolean process() {
        System.out.println("Processing payment of $" + amount + " for ID: " + paymentId);
        this.status = PaymentStatus.COMPLETED;
        this.transactionDetails = "Transaction approved. Ref: " + System.currentTimeMillis();
        return true;
    }

    public boolean refund() {
        if (status == PaymentStatus.COMPLETED) {
            status = PaymentStatus.REFUNDED;
            System.out.println("Payment refunded: " + paymentId);
            return true;
        }
        return false;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getPaymentDate() { return paymentDate; }
    public PaymentStatus getStatus() { return status; }
    public String getTransactionDetails() { return transactionDetails; }
    public void setTransactionDetails(String transactionDetails) { this.transactionDetails = transactionDetails; }
}