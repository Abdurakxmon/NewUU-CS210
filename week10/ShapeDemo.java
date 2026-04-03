public class ShapeDemo {

    public static Shape findLargest(Shape[] shapes) {
        Shape largest = shapes[0];

        for (Shape shape : shapes) {
            if (shape.getArea() > largest.getArea()) {
                largest = shape;
            }
        }

        return largest;
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle("Red", 3),
                new Rectangle("Blue", 4, 5),
                new Triangle("Green", 3, 4, 5),
                new Circle("Yellow", 2.5),
                new Rectangle("Black", 6, 2),
                new Triangle("White", 5, 5, 6)
        };

        double totalArea = 0;

        for (Shape shape : shapes) {
            shape.displayInfo();
            totalArea += shape.getArea();
        }

        Shape largest = findLargest(shapes);
        System.out.println("Largest shape by area:");
        largest.displayInfo();

        System.out.printf("Total combined area: %.2f%n", totalArea);
    }
}