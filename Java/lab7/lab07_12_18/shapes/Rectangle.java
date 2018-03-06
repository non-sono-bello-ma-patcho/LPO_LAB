package lab07_12_18.shapes;

public class Rectangle extends AbstractShape {
    /* da modificare */
	private double width = 1.0;
	private double height = 1.0;

	private final Point center = new Point();

	private static void checkPositive(double arg) {
		if (arg < 0)
			throw new IllegalArgumentException("Argument must be positive");
	}

	private Rectangle(double width, double height, Point center) {
		this(width, height);
		this.center.move(center.getX(), center.getY());
	}

	private Rectangle(double width, double height) {
		checkPositive(width);
		checkPositive(height);
		this.width = width;
		this.height = height;
	}

	public Rectangle() {
	}

	public static Rectangle newOfWidthHeight(double width, double height) {
		return new Rectangle(width, height);
	}

	public static Rectangle newOfWidthHeight(double width, double height, Point center) {
		return new Rectangle(width, height, center);
	}

	public void move(double dx, double dy) {
		center.move(dx, dy);
	}

	public void scale(double factor) {
		checkPositive(factor);
		width *= factor;
		height *= factor;
	}

	public Point getCenter() {
		return new Point(center);
	}

	public double perimeter() {
		return 2 * (width + height);
	}

	public double area() {
		return width * height;
	}

	public int compareTo(Shape shape) {
		double thisArea = area(), shapeArea = shape.area();
		return thisArea < shapeArea ? -1 : thisArea == shapeArea ? 0 : 1;
	}

}
