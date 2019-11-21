package edu.yu.intro;

/** " FindingRationalNumbers " Assignment #11
*
* @author Tom Bohbot
*/

import java.util.Arrays;
import java.util.*;
import java.lang.Math.*;

public class Rational {

	private int numerator;
	private int denominator;

	public Rational(){
		this.numerator = 0;
		this.denominator = 1;
	}

	public String printRational() {

		String isPositiveOrNegative = "pending";
		String numeratorString = Integer.toString(numerator);
		String denominatorString = Integer.toString(denominator);
		if ((numerator > 0 && denominator > 0) || (numerator < 0 && denominator < 0)) {
			isPositiveOrNegative = "positive";
		}
		else if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
			isPositiveOrNegative = "negative";
		}
		else if (numerator == 0) {
			isPositiveOrNegative = "neither positive or negative";
		}
		else if (denominator == 0) {
			isPositiveOrNegative = "not rational";
		}

		String printRational = " The numerator is " + numeratorString + ". The denominator is " + denominatorString + ". The rational number is " + isPositiveOrNegative + ".";
		return printRational;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public static void main (final String [] args) {
		Rational rationalInstance = new Rational(10, 5);
		String printRationalMethod = rationalInstance.printRational();
		System.out.println (printRationalMethod);
	}

	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("The denominator cannot be zero.");
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public void negate() {
			numerator = -1 * numerator;
	}

	public void invert() {
		if (numerator == 0) {
			throw new UnsupportedOperationException("The numerator cannot be equal to zero, as it will invert and become the denominator.");
		}
		int invertedNumerator = denominator;
		int invertedDenominator = numerator;
		numerator = invertedNumerator;
		denominator = invertedDenominator;
	}

	public double toDouble() {
		Double numeratorAsDouble = new Double(numerator);
		Double rationalNumberAsDouble = numeratorAsDouble / denominator;
		return rationalNumberAsDouble;
	}

	public Rational reduce() {
		int numeratorInstanceFinal = numerator;
		int denominatorInstanceFinal = denominator;
		int numeratorInstance = numerator;
		int denominatorInstance = denominator;
			while (numeratorInstance != denominatorInstance) {
				if (numeratorInstance > denominatorInstance) {
					numeratorInstance = numeratorInstance - denominatorInstance;
				}
				else {
					denominatorInstance = denominatorInstance - numeratorInstance;
				}
			}
		Rational rationalReduce = new Rational(numeratorInstanceFinal / numeratorInstance, denominatorInstanceFinal / numeratorInstance );
		return rationalReduce;
	}
	

	public Rational add (final Rational that) {
		int numeratorOne = this.numerator;
		int denominatorOne = this.denominator;
		int numeratorTwo = that.numerator;
		int denominatorTwo = that.denominator;

		int addedNumeratorOne = this.numerator * that.denominator;
		int addedNumeratorTwo = that.numerator * this.denominator;
		int finalNumerator = addedNumeratorTwo + addedNumeratorOne;
		int commonDenominator = this.denominator * that.denominator;

		Rational rationalAdd = new Rational(finalNumerator , finalDenominator);
		Rational answer = rationalAdd.reduce();
		return answer;

	}


}