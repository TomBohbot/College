package edu.yu.intro;

/** " Shape With Interface " Assignment #12
*
* @author Tom Bohbot
*/

import java.lang.Math.*;

public class Triangle implements Shape , Polygon {
	private double s1;
	private double s2;
	private double s3;
	private double perimeter;

	public Triangle (double s1, double s2, double s3) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
	}
	@Override
	public double perimeter() {
		double perimeter = (this.s1 + this.s2 + this.s3);
		return perimeter;
	}
	@Override
	public double area() {
		double p = perimeter()/2;
		double area = Math.sqrt(p*(p-this.s1)*(p-this.s2)*(p-this.s3) );
		return area;
	}
	@Override
	public int nSides() {
		int nSides = 3;
		return nSides;
	}
}