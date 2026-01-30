import java.util.Scanner;

public class AllProblems {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect a problem to run (3-9) or 0 to exit:");
            System.out.println("3: Area and Perimeter of a Rectangle");
            System.out.println("4: Volume of a Cylinder");
            System.out.println("5: Volume of a Triangular Prism");
            System.out.println("6: Tax and Tip");
            System.out.println("7: Cost of Driving");
            System.out.println("8: Sum of Digits of a Four-Digit Number");
            System.out.println("9: Sort Three Integers");

            int choice = input.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 3:
                    rectangleProblem();
                    break;
                case 4:
                    cylinderProblem(input);
                    break;
                case 5:
                    triangularPrismProblem(input);
                    break;
                case 6:
                    taxAndTipProblem(input);
                    break;
                case 7:
                    drivingCostProblem(input);
                    break;
                case 8:
                    sumOfDigitsProblem(input);
                    break;
                case 9:
                    sortThreeNumbersProblem(input);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Program exited.");
    }

    // Problem 3
    public static void rectangleProblem() {
        double width = 4.5;
        double height = 7.9;
        double area = width * height;
        double perimeter = 2 * (width + height);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }

    // Problem 4
    public static void cylinderProblem(Scanner input) {
        System.out.print("Enter the radius and length of a cylinder: ");
        double radius = input.nextDouble();
        double length = input.nextDouble();
        double area = Math.PI * radius * radius;
        double volume = area * length;
        System.out.printf("The area is %.4f%n", area);
        System.out.printf("The volume is %.4f%n", volume);
    }

    // Problem 5
    public static void triangularPrismProblem(Scanner input) {
        System.out.print("Enter the length of the side and height of the equilateral triangle: ");
        double side = input.nextDouble();
        double height = input.nextDouble();
        double area = (Math.sqrt(3) / 4) * side * side;
        double volume = area * height;
        System.out.printf("The area is %.2f%n", area);
        System.out.printf("The volume of the triangular prism is %.2f%n", volume);
    }

    // Problem 6
    public static void taxAndTipProblem(Scanner input) {
        System.out.print("Enter the cost of the meal: ");
        double mealCost = input.nextDouble();
        double taxRate = 0.07; // change to your local tax
        double tax = mealCost * taxRate;
        double tip = mealCost * 0.18;
        double total = mealCost + tax + tip;
        System.out.printf("Tax: $%.2f%n", tax);
        System.out.printf("Tip: $%.2f%n", tip);
        System.out.printf("Total: $%.2f%n", total);
    }

    // Problem 7
    public static void drivingCostProblem(Scanner input) {
        System.out.print("Enter the driving distance: ");
        double distance = input.nextDouble();
        System.out.print("Enter miles per gallon: ");
        double mpg = input.nextDouble();
        System.out.print("Enter price per gallon: ");
        double price = input.nextDouble();
        double cost = (distance / mpg) * price;
        System.out.printf("The cost of driving is $%.2f%n", cost);
    }

    // Problem 8
    public static void sumOfDigitsProblem(Scanner input) {
        System.out.print("Enter a four-digit integer: ");
        int number = input.nextInt();
        int d1 = number / 1000;
        int d2 = (number / 100) % 10;
        int d3 = (number / 10) % 10;
        int d4 = number % 10;
        int sum = d1 + d2 + d3 + d4;
        System.out.printf("%d + %d + %d + %d = %d%n", d1, d2, d3, d4, sum);
    }

    // Problem 9
    public static void sortThreeNumbersProblem(Scanner input) {
        System.out.print("Enter three integers: ");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        int middle = a + b + c - min - max;
        System.out.println("Sorted order: " + min + " " + middle + " " + max);
    }
}
