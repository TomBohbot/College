package edu.yu.intro;

/** " Shapes With Inheritance " Assignment #11
*
* @author Tom Bohbot
*/

public abstract class Shape {
	
	public abstract double area ();
	public abstract double perimeter ();
}

class Circle extends Shape {
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

class Rectangle extends Shape {
	private double width;
	private double height;

	public Rectangle (double width , double height) {
		this.width = width;
		this.height = height;
	}
	@Override
	public double area () {
		double area = this.width * this.height;
		return area;
	}
	@Override
	public double perimeter () {
		double perimeter = (this.width * 2) + (this.height * 2);
		return perimeter;
	}
}

class Triangle extends Shape {
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
	public double perimeter () {
		double perimeter = (this.s1 + this.s2 + this.s3);
		return perimeter;
	}
	@Override
	public double area () {
		double p = perimeter()/2;
		double area = Math.sqrt(p*(p-this.s1)*(p-this.s2)*(p-this.s3) );
		return area;
	}
}