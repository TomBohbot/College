package edu.yu.intro;

/** " Shape With Interface " Assignment #12
*
* @author Tom Bohbot
*/

import java.lang.Math.*;

public class Circle implements Shape {
	private double radius;
	private double pi = Math.PI;

	public Circle (double radius) {
		this.radius = radius;
	}
	@Override
	public double area () {
		double area = pi * this.radius * this.radius;
		return area;
	}
	@Override
	public double perimeter () {
		double perimeter = 2 * pi * this.radius;
		return perimeter;
	}
}