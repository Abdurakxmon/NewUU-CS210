public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, String id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateMonthlySalary() throws InvalidSalaryException {
        if (hourlyRate <= 0 || hoursWorked < 0) {
            throw new InvalidSalaryException("Hourly rate and hours worked must be valid.");
        }
        return hourlyRate * hoursWorked;
    }
}