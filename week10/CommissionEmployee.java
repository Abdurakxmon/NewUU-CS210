public class CommissionEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public CommissionEmployee(String name, String id, double baseSalary, double salesAmount, double commissionRate) {
        super(name, id);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculateMonthlySalary() throws InvalidSalaryException {
        if (commissionRate < 0 || commissionRate > 1) {
            throw new InvalidSalaryException("Commission rate must be between 0 and 1.");
        }
        return baseSalary + (salesAmount * commissionRate);
    }
}