public class BankApp {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.00);
        System.out.printf("Balance: $%.2f%n", account.getBalance());

        double[] banknoteList = {200.00, 400.00, 100.00};

        for (double banknote : banknoteList) {
            try {
                account.withdraw(banknote);
                System.out.printf("Withdrew $%.2f. Remaining balance: $%.2f%n", banknote, account.getBalance());
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}