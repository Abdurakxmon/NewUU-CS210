public abstract class Employee {
    protected String name;
    protected String id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract double calculateMonthlySalary() throws InvalidSalaryException;

    public double printPaySlip() {
        try {
            double salary = calculateMonthlySalary();
            System.out.printf("Employee: %s (%s) | Monthly Salary: $%.2f%n", name, id, salary);
            return salary;
        } catch (InvalidSalaryException e) {
            System.out.printf("Employee: %s (%s) | Error: %s%n", name, id, e.getMessage());
            return 0;
        }
    }
}