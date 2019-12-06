package edu.yu.intro;

/** " Shapes With Inheritance " Assignment #11
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
}