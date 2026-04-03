public class DigitalProduct extends TaxableProduct {
    private String downloadUrl;

    public DigitalProduct(String name, double price, String downloadUrl) {
        super(name, price);
        this.downloadUrl = downloadUrl;
    }

    @Override
    public void print() {
        double tax = calculateTax();

        System.out.printf("Digital Product: %s%n", name);
        System.out.printf("Price: $%.2f%n", price);
        System.out.printf("Tax: $%.2f%n", tax);
        System.out.printf("Total: $%.2f%n", price + tax);
        System.out.printf("Digital Product: %s%n", downloadUrl);

    }
}