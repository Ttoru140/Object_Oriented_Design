// Factory Pattern Example: Shapes

interface Shape {
    double getArea();
}

class Circle implements Shape {
    private double radius;

    public Circle() { // default constructor
        this.radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) { this.radius = radius; }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle() { // default constructor
        this.width = 1.0;
        this.height = 1.0;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }

    @Override
    public double getArea() {
        return width * height;
    }
}

class Triangle implements Shape {
    private double base;
    private double height;

    public Triangle() { // default constructor
        this.base = 1.0;
        this.height = 1.0;
    }

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public void setBase(double base) { this.base = base; }
    public void setHeight(double height) { this.height = height; }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;

        switch(shapeType.toUpperCase()) {
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "TRIANGLE":
                return new Triangle();
            default:
                return null;
        }
    }
}

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        // Circle
        Circle circle = (Circle) factory.getShape("CIRCLE");
        circle.setRadius(5);
        System.out.println("Circle Area: " + circle.getArea());

        // Rectangle
        Rectangle rectangle = (Rectangle) factory.getShape("RECTANGLE");
        rectangle.setWidth(4);
        rectangle.setHeight(6);
        System.out.println("Rectangle Area: " + rectangle.getArea());

        // Triangle
        Triangle triangle = (Triangle) factory.getShape("TRIANGLE");
        triangle.setBase(3);
        triangle.setHeight(5);
        System.out.println("Triangle Area: " + triangle.getArea());
    }
}
