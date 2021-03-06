package edu.yu.intro.test;

/** " FindingRationalNumbersTest " Assignment #8
*
* @author Tom Bohbot
*/

import edu.yu.intro.Shapes;
import edu.yu.intro.Shape;
import org.junit.*;
import static org.junit.Assert.*;

public class ShapesTest {

	Shapes shapesInstance = new Shapes();

	@Test
	public void CheckCircleMethod () {
		Shape circle = shapesInstance.newCircle(5.5);
		assertEquals ("testing area of circle." , 95.03317 , circle.area() , 0.01 );
		assertEquals ("testing perimeter of circle." , 34.557519 , circle.perimeter() , 0.01 );
	}
	@Test (expected = IllegalArgumentException.class)
	public void negativeInputCircle () {
		Shape circle = shapesInstance.newCircle(-5);
	}
	@Test (expected = IllegalArgumentException.class)
	public void nonPositiveInputCircle () {
		Shape circle = shapesInstance.newCircle(0);
	}

	@Test
	public void CheckRectangleMethod () {
		Shape rectangle = shapesInstance.newRectangle(2 , 2);
		assertEquals ("testing area of rectangle." , 4.0 , rectangle.area() , 0 );
		assertEquals ("testing perimeter of rectangle." , 8.0 , rectangle.perimeter() , 0 );
	}
	@Test (expected = IllegalArgumentException.class)
	public void negativeInputRectangle () {
		Shape rectangle = shapesInstance.newRectangle(2 , -2);
	}
	@Test (expected = IllegalArgumentException.class)
	public void nonPositiveInputRectangle () {
		Shape rectangle = shapesInstance.newRectangle(0 , 2);
	}

	@Test
	public void CheckTriangleMethod () {
		Shape triangle = shapesInstance.newTriangle(18 , 30 , 24);
		assertEquals ("testing area of triangle." , 216.0 , triangle.area() , 0 );
		assertEquals ("testing perimeter of triangle." , 72.0 , triangle.perimeter() , 0 );
	}
	@Test (expected = IllegalArgumentException.class)
	public void negativeInputTriangle () {
		Shape triangle = shapesInstance.newTriangle(18 , -30 , 24);
	}
	@Test (expected = IllegalArgumentException.class)
	public void nonPositiveInputTriangle () {
		Shape triangle = shapesInstance.newTriangle(18 , 30 , 0);
	}
}