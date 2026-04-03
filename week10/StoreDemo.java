public class StoreDemo {
    public static void main(String[] args) {
        Printable[] items = {
                new Product("Notebook", 5.50),
                new TaxableProduct("Headphones", 40.00),
                new DigitalProduct("Java Course", 25.00, "https://download.example.com/java-course"),
                new Product("Pen", 1.20),
                new TaxableProduct("Keyboard", 60.00),
                new DigitalProduct("E-Book", 10.00, "https://download.example.com/ebook")
        };

        double totalTax = 0;

        for (Printable item : items) {
            item.print();

            if (item instanceof Taxable) {
                totalTax += ((Taxable) item).calculateTax();
            }
        }

        System.out.printf("Total tax collected: $%.2f%n", totalTax);
    }
}