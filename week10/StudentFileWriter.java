import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class StudentFileWriter {
    public static void main(String[] args) {
        File file = new File("students.txt");

        if (file.exists()) {
            System.out.println("Warning: students.txt already exists and will be overwritten.");
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Shamshod Karimov 3.85 Software Engineering");
            writer.println("Malika Tursunova 3.90 Mathematics");
            writer.println("Bekzod Salimov 3.45 Physics");
            writer.println("Aziza Nurmatova 3.70 Chemical Engineering");
            writer.println("Jasur Rakhimov 3.60 Economics");

            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}