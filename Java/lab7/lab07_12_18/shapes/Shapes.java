package lab07_12_18.shapes;

public class Shapes {

	private static Shape bound(Shape[] shapes, int comp) {
		if (shapes.length == 0)
			return null;
		Shape acc = shapes[0];
		for (int i = 1; i < shapes.length; i++) {
			Shape s = shapes[i];
			if (comp * s.compareTo(acc) > 0)
				acc = s;
		}
		return acc;
	}

	public static Shape max(Shape[] shapes) {
		return bound(shapes, 1);
	}

	public static Shape min(Shape[] shapes) {
		return bound(shapes, -1);
	}

	public static void moveAll(Shape[] shapes, double dx, double dy) {
		for (Shape s : shapes)
			s.move(dx, dy);
	}

	public static void scaleAll(Shape[] shapes, double factor) {
		for (Shape s : shapes)
			s.scale(factor);
	}

	public static double totalPerimeter(Shape[] shapes) {
		double acc = 0;
		for (Shape s : shapes)
			acc += s.perimeter();
		return acc;
	}

	public static double totalArea(Shape[] shapes) {
		double acc = 0;
		for (Shape s : shapes)
			acc += s.area();
		return acc;
	}

}
