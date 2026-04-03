public class FullTimeEmployee extends Employee {
    private double annualSalary;

    public FullTimeEmployee(String name, String id, double annualSalary) {
        super(name, id);
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateMonthlySalary() throws InvalidSalaryException {
        if (annualSalary <= 0) {
            throw new InvalidSalaryException("Annual salary must be greater than 0.");
        }
        return annualSalary / 12.0;
    }
}