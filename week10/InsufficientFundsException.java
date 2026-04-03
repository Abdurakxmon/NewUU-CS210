public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount) {
        super(String.format("Insufficient funds. You are short by $%.2f", amount));
    }
}