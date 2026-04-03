import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileLogger {

    public static void writeLog(String filename, String message) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(filename, true));
            writer.println(new Date() + " - " + message);

            throw new RuntimeException("Simulated write error");
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        } finally {
            // finally matters because it guarantees the writer is closed.
            if (writer != null) {
                writer.close();
            }
            System.out.println("Logger closed.");
        }
    }


}