import java.util.*;

interface Shape {
    double calculateArea(); // 计算面积
    double calculatePerimeter(); // 计算周长
    String getShapeName(); // 获取图形名称
    void displayInfo(); // 显示图形信息
}

class Circle implements Shape {
    private double r;

    public Circle(double r) {
        this.r = r;
    }
    public double calculateArea() {
        return Math.PI * r * r;
    }
    public double calculatePerimeter() {
        return 2 * Math.PI * r;
    }
    public String getShapeName() {
        return "圆形";
    }
    public void displayInfo() {
        System.out.println("名称: " + getShapeName());
        System.out.println("半径: " + r);
        System.out.println("面积: " + String.format("%.2f", calculateArea()));
        System.out.println("周长: " + String.format("%.2f", calculatePerimeter()));
    }
}

class Rectangle implements Shape {
    private double a,b;

    public Rectangle(double a,double b) {
        this.a = a;
        this.b = b;
    }
    public double calculateArea() {
        return a * b;
    }
    public double calculatePerimeter() {
        return 2 * (a + b);
    }
    public String getShapeName() {
        return "矩形";
    }
    public void displayInfo() {
        System.out.println("名称: " + getShapeName());
        System.out.println("宽度: " + a);
        System.out.println("高度: " + b);
        System.out.println("面积: " + String.format("%.2f", calculateArea()));
        System.out.println("周长: " + String.format("%.2f", calculatePerimeter()));
    }
}

class Triangle implements Shape {
    private double a,b,c;

    public Triangle(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("无法构成三角形");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double calculateArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double calculatePerimeter() {
        return a + b + c;
    }
    public String getShapeName() {
        return "三角形";
    }
    public void displayInfo() {
        System.out.println("名称: " + getShapeName());
        System.out.println("边长: " + a+","+b+","+c);
        System.out.println("面积: " + String.format("%.2f", calculateArea()));
        System.out.println("周长: " + String.format("%.2f", calculatePerimeter()));
    }
}

class ShapeManager {
    private List<Shape> shapes;
    public ShapeManager() {
        shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Triangle(3, 4, 5));
    }
    public void displayShapes() {
        for(Shape shape : shapes) {
            shape.displayInfo();
            System.out.println();
        }
    }
}

public class Test{
    public static void main(String[] args){
        new ShapeManager().displayShapes();
    }
}