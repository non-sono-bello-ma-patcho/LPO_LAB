package lab07_12_18.shapes;

public class Circle extends AbstractShape {
    /* da modificare */
	private double radius = 1.0;
	private final Point center = new Point();

	private static void checkPositive(double arg) {
		if (arg < 0)
			throw new IllegalArgumentException("Argument must be positive");
	}

	public Circle() {
	}

	public Circle(double radius, Point center) {
		this(radius);
		this.center.move(center.getX(), center.getY());
	}

	public Circle(double radius) {
		checkPositive(radius);
		this.radius = radius;
	}

	public void move(double dx, double dy) {
		center.move(dx, dy);
	}

	public void scale(double factor) {
		checkPositive(factor);
		radius *= factor;
	}

	public Point getCenter() {
		return new Point(center);
	}

	public double perimeter() {
		return 2 * Math.PI * radius;
	}

	public double area() {
		return Math.PI * radius * radius;
	}

	public int compareTo(Shape shape) {
		double thisArea = area(), shapeArea = shape.area();
		return thisArea < shapeArea ? -1 : thisArea == shapeArea ? 0 : 1;
	}

}
