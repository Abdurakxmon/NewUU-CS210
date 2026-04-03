import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PayrollSystem {
    public static void main(String[] args) {
        Employee[] employees = {
                new FullTimeEmployee("Abbos", "FT001", 60000),
                new PartTimeEmployee("Maqsud", "PT001", 20, 80),
                new CommissionEmployee("Baxodir", "CM001", 1000, 5000, 0.10),
                new FullTimeEmployee("Odil", "FT002", -24000), // invalid
                new PartTimeEmployee("Bobur", "PT002", 15, 60)
        };

        double totalPayroll = 0;
        StringBuilder report = new StringBuilder();
        report.append("Payroll Summary\n");
        report.append("========================\n");

        for (Employee employee : employees) {
            double salary = employee.printPaySlip();
            totalPayroll += salary;
            report.append(String.format("%s (%s): $%.2f%n", employee.name, employee.id, salary));
        }

        report.append(String.format("%nTotal Valid Monthly Payroll: $%.2f%n", totalPayroll));

        try (PrintWriter writer = new PrintWriter(new FileWriter("payroll_report.txt"))) {
            writer.print(report.toString());
            System.out.println("Payroll summary written to payroll_report.txt");
        } catch (IOException e) {
            System.out.println("Error writing payroll report: " + e.getMessage());
        }
    }
}