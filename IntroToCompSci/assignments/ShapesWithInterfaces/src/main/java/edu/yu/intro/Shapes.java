package edu.yu.intro;

/** " Shapes With Interface " Assignment #12
*
* @author Tom Bohbot
*/

public class Shapes {

	public static Shape newCircle (final double radius) {

		if (radius <= 0) {
			throw new IllegalArgumentException ("ERROR: The radius must be positive.");
		}
		Shape circle = new Circle (radius);
		return circle;
	}

	public static Shape newRectangle (final double width , final double height) {

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException ("ERROR: The radius must be positive.");
		}
		Shape rectangle = new Rectangle (width , height);
		return rectangle;
	}

	public static Shape newTriangle (final double s1 , final double s2 , final double s3) {

		if (s1 <= 0 || s2 <= 0 || s3 <= 0) {
			throw new IllegalArgumentException ("ERROR: The radius must be positive.");
		}
		Shape triangle = new Triangle (s1 , s2 , s3);
		return triangle;
	}

	public static void main (final String [] args) {

		Shapes shapesInstance = new Shapes();

		Shape circle = shapesInstance.newCircle(5);
		Shape rectangle = shapesInstance.newRectangle(2 , 2);
		Shape triangle = shapesInstance.newTriangle(18 , 30 , 24);

		Rectangle rectangleNSides = (Rectangle) rectangle;
		Triangle triangleNSides = (Triangle) triangle;

		System.out.println("*************************************************");
		System.out.println("circle's area is " + circle.area() );
		System.out.println("circle's perimeter is " + circle.perimeter() );
		System.out.println();
		System.out.println("rectangle's area is " + rectangle.area() );
		System.out.println("rectangle's perimeter is " + rectangle.perimeter() );
		System.out.println("rectangle's number of sides is " + rectangleNSides.nSides() );
		System.out.println();
		System.out.println("triangle's area is " + triangle.area() );
		System.out.println("triangle's perimeter is " + triangle.perimeter() );
		System.out.println("triangle's number of sides is " + triangleNSides.nSides() );
		System.out.println("*************************************************");
	}
}