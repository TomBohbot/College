package edu.yu.intro;

/** " Shape With Interface " Assignment #12
*
* @author Tom Bohbot
*/

import java.lang.Math.*;

public class Rectangle implements Shape , Polygon{
	private double width;
	private double height;

	public Rectangle (double width , double height) {
		this.width = width;
		this.height = height;
	}
	@Override
	public double area() {
		double area = width * height;
		return area;
	}
	@Override
	public double perimeter() {
		double perimeter = (width * 2) + (height * 2);
		return perimeter;
	}
	@Override
	public int nSides() {
		int nSides = 4;
		return nSides;
	}
}