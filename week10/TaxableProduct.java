public class TaxableProduct extends Product implements Taxable {

    public TaxableProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculateTax() {
        return price * TAX_RATE;
    }

    @Override
    public void print() {
        double tax = calculateTax();
        System.out.printf("Taxable Product: %sn", name);
        System.out.printf("Price: $%.2f%n", price);
        System.out.printf("Tax: $%.2f%n", tax);
        System.out.printf("Total: $%.2f%n", price + tax);
    }
}