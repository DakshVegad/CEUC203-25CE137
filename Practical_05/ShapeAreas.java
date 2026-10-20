abstract class Shape {
    abstract double area();
}
class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double area() { return Math.PI * radius * radius; }
}
class Rectangle extends Shape {
    private final double length, width;
    Rectangle(double length, double width) { this.length = length; this.width = width; }
    @Override double area() { return length * width; }
}
class Triangle extends Shape {
    private final double base, height;
    Triangle(double base, double height) { this.base = base; this.height = height; }
    @Override double area() { return 0.5 * base * height; }
}
public class ShapeAreas {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(2), new Rectangle(4, 3), new Triangle(4, 5)};
        double total = 0, largest = -1;
        for (Shape shape : shapes) {
            double area = shape.area();
            System.out.printf("%s area: %.2f%n", shape.getClass().getSimpleName(), area);
            total += area;
            largest = Math.max(largest, area);
        }
        System.out.printf("Total area: %.2f%nLargest area: %.2f%n", total, largest);
    }
}