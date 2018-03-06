package lab07_12_18.shapes;

import static lab07_12_18.shapes.Shapes.*;

public class ShapeTest {
	private static void checkAll(Shape[] shapes, double totalPerimeter, double totalArea, Shape min, Shape max) {
		assert min(shapes) == min;
		assert max(shapes) == max;
		assert shapes[0].getCenter().coincidentWith(new Point(0, 0));
		assert shapes[1].getCenter().coincidentWith(new Point(-1, -1));
		assert shapes[2].getCenter().coincidentWith(new Point(1, 1));
		assert totalPerimeter(shapes) == totalPerimeter;
		assert totalArea(shapes) == totalArea;
	}

	public static void main(String[] args) {
		Shape[] shapes = { new Circle(2, new Point(1, 1)), new Square(1),
				Rectangle.newOfWidthHeight(1, 2, new Point(2, 2)) };
		double totalPerimeter = totalPerimeter(shapes);
		double totalArea = totalArea(shapes);
		Shape min = min(shapes);
		Shape max = max(shapes);
		moveAll(shapes, -1, -1);
		checkAll(shapes, totalPerimeter, totalArea, min, max);
		scaleAll(shapes, .5);
		checkAll(shapes, totalPerimeter * .5, totalArea * .25, min, max);
		for (int i = 0; i < shapes.length; i++)
			shapes[i].getCenter().move(1, 1);
		checkAll(shapes, totalPerimeter * .5, totalArea * .25, min, max);
	}
}
