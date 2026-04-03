import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentFileReader {
    public static void main(String[] args) {
        File file = new File("students.txt");
        int cnt = 0;

        try (Scanner scanner = new Scanner(file)) {
            System.out.printf("%-20s %-8s %-20s%n", "Name", "GPA", "Major");
            System.out.println("--------------------------------------------------");

            while (scanner.hasNext()) {
                String firstName = scanner.next();
                String lastName = scanner.next();
                double gpa = scanner.nextDouble();
                String major = scanner.next();

                String fullName = firstName + " " + lastName;
                System.out.printf("%-20s %-8.2f %-20s%n", fullName, gpa, major);
                cnt++;
            }

            System.out.println("Total students: " + cnt);
        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt was not found.");
        }
    }
}