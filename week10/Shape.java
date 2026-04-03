public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public void displayInfo() {
        System.out.printf("%s | Color: %s | Area: %.2f | Perimeter: %.2f%n", getClass().getSimpleName(), color, getArea(), getPerimeter());
    }
}