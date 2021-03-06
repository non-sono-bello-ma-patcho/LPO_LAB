package lab07_12_18.shapes;

public class Square extends AbstractShape {
    /* da modificare */    
	private double side = 1.0;
	private final Point center = new Point();

	private static void checkPositive(double arg) {
		if (arg < 0)
			throw new IllegalArgumentException("Argument must be positive");
	}

	public Square() {
	}

	public Square(double side, Point center) {
		this(side);
		this.center.move(center.getX(), center.getY());
	}

	public Square(double side) {
		checkPositive(side);
		this.side = side;
	}
	@Override

	public void move(double dx, double dy) {
		center.move(dx, dy);
	}
	@Override

	public void scale(double factor) {
		checkPositive(factor);
		side *= factor;
	}
	@Override
	public Point getCenter() {
		return new Point(center);
	}
	@Override
	public double perimeter() {
		return 4 * side;
	}
	@Override
	public double area() {
		return side * side;
	}
	@Override
	public int compareTo(Shape shape) {
		double thisArea = area(), shapeArea = shape.area();
		return thisArea < shapeArea ? -1 : thisArea == shapeArea ? 0 : 1;
	}

}
