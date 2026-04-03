import java.util.Scanner;

public class SafeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter first number (or 'exit'): ");
                String usrinp = scanner.nextLine();

                if (usrinp.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                int firstNum = Integer.parseInt(usrinp);

                System.out.print("Enter second number: ");
                String secondInput = scanner.nextLine();
                int secondNum = Integer.parseInt(secondInput);

                int result = firstNum / secondNum;
                System.out.println("Result: " + firstNum + " / " + secondNum + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: Cannot divide by zero.");
                System.out.println("Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter valid integers only.");
                System.out.println("Try again.");
            }
        }

        scanner.close();
    }
}