package edu.yu.intro.test;

/** " FindingRationalNumbersTest " Assignment #8
*
* @author Tom Bohbot
*/

import edu.yu.intro.Rational;
import org.junit.*;
import static org.junit.Assert.*;

public class FindingRationalNumbersTest {

// BEGINNING: TESTING GETTERS METHOD
	@Test
	public void testingGetNumeratorFirstConstructor() {
		Rational rationalInstance = new Rational();
		assertEquals("testing Get Numerator for the first constructor." , 0 , rationalInstance.getNumerator() );
	}
	@Test
	public void testingGetNumeratorSecondConstructor() {
		Rational rationalInstance = new Rational(10 , 2);
		assertEquals("testing Get Numerator for the second constructor." , 10 , rationalInstance.getNumerator() );
	}
	@Test
	public void testingGetDenominator() {
		Rational rationalInstance = new Rational();
		assertEquals("testing Get Denominator for the first constructor." , 1 , rationalInstance.getDenominator() );
	}
	@Test
	public void testingGetDenominatorSecondConstructor() {
		Rational rationalInstance = new Rational(10 , 2);
		assertEquals("testing Get Numerator for the second constructor." , 2 , rationalInstance.getDenominator() );
	}
// END: TESTING GETTERS METHOD

// BEGINNING: TESTING SECOND CONSTRUCTOR
	@Test (expected = IllegalArgumentException.class)
	public void testingSecondConstructor () {
		Rational rationalInstance = new Rational(1 , 0);
		rationalInstance.getDenominator();
	}
// END: TESTING SECOND CONSTRUCTOR	

//BEGGINING: TESTING NEGATE METHOD
	@Test
	public void testingNegateNumeratorFirstConstructor() {
		Rational rationalInstance = new Rational();
		rationalInstance.negate();
		assertEquals("testing negation of numerator for the first constructor." , 0 , rationalInstance.getNumerator());
	}	
	@Test
	public void testingNegateNumeratorSecondConstructor() {
		Rational rationalInstance = new Rational(1 , 2);
		rationalInstance.negate();
		assertEquals("testing negation of numerator for the second constructor." , -1 , rationalInstance.getNumerator());
	}	
	@Test
	public void testingNegateDenominatorFirstConstructor() {
		Rational rationalInstance = new Rational();
		rationalInstance.negate();
		assertEquals("testing negation of denominator for the first constructor." , 1 , rationalInstance.getDenominator());
	}	
	@Test
	public void testingNegateDenominatorSecondConstructor() {
		Rational rationalInstance = new Rational(1 , 2);
		rationalInstance.negate();
		assertEquals("testing negation of denominator for the second constructor." , 2 , rationalInstance.getDenominator());
	}	
// END: TESTING NEGATE METHOD

// BEGINNING: TESTING INVERT METHOD	
	@Test (expected = UnsupportedOperationException.class)
	public void testingInvertNumeratorFirstConstructor () {
		Rational rationalInstance = new Rational();
		rationalInstance.invert();
		rationalInstance.getNumerator();
	}
	@Test
	public void testingInvertNumeratorSecondConstructor() {
		Rational rationalInstance = new Rational(23, 5);
		rationalInstance.invert();
		assertEquals("testing inverted numerator for the second constructor." , 5 , rationalInstance.getNumerator());
	}	
	@Test (expected = UnsupportedOperationException.class)
	public void testingInvertDenominatorFirstConstructor () {
		Rational rationalInstance = new Rational();
		rationalInstance.invert();
		rationalInstance.getDenominator();
	}	
	@Test
	public void testingInvertDenominatorSecondConstructor() {
		Rational rationalInstance = new Rational(23 , 5);
		rationalInstance.invert();
		assertEquals("testing inverted denominator for the second constructor." , 23 , rationalInstance.getDenominator());
	}	
// END: TESTING INVERT METHOD	

// BEGINNING: TESTING TODOUBLE METHOD	
	@Test
	public void testingToDoubleFirstContructor() {
		Rational rationalInstance = new Rational();
		assertEquals("testing rational number as a type double for the first constructor." , 0.0 , rationalInstance.toDouble(), 0 );
	}
	@Test
	public void testingToDoubleSecondContructor() {
		Rational rationalInstance = new Rational(16 , 2);
		assertEquals("testing rational number as a type double for the second constructor." , 8.0 , rationalInstance.toDouble(), 0 );
	}
	@Test
	public void testingToDoubleSecondContructorFivaAndTwo() {
		Rational rationalInstance = new Rational(5 , 2);
		assertEquals("testing rational number as a type double for the second constructor." , 2.5 , rationalInstance.toDouble(), 0 );
	}
// END: TESTING TODOUBLE METHOD	

//BEGINNING: TESTING REDUCE METHOD	
	@ Test 
	public void testingReduce () {
		Rational rationalInstance = new Rational (24 , 9);
		Rational answer = rationalInstance.reduce();

		assertEquals("testing rationalReduce Numerator" , 8 , answer.getNumerator() );
		assertEquals("testing rationalReduce Denominator." , 3 , answer.getDenominator() );
	}
	@ Test 
	public void testingReduce2 () {
		Rational rationalInstance = new Rational (9 , 3);
		Rational answer = rationalInstance.reduce();

		assertEquals("testing rationalReduce Numerator" , 3 , answer.getNumerator() );
		assertEquals("testing rationalReduce Denominator." , 1 , answer.getDenominator() );
	}
	@ Test 
	public void testingReduce3 () {
		Rational rationalInstance = new Rational (5 , 3);
		Rational answer = rationalInstance.reduce();

		assertEquals("testing rationalReduce Numerator" , 5 , answer.getNumerator() );
		assertEquals("testing rationalReduce Denominator." , 3 , answer.getDenominator() );
	}

//END: TESTING REDUCE METHOD	

//BEGINNING: TESTING ADDING FRACTIONS METHOD		
	@Test
	public void testingAddMethod() {
		Rational rationalInstance = new Rational(5 , 2);
		Rational rationalAdded = new Rational (10 , 2);
		Rational answer = rationalInstance.add(rationalAdded);
		assertEquals("testing numerator of added fractions." , 15 , answer.getNumerator() );
		assertEquals("testing denominator of added fractions." , 2 , answer.getDenominator() );
	}
	@Test
	public void testingAddMethodTwo() {
		Rational rationalInstance = new Rational(5 , 2);
		Rational rationalAdded = new Rational (10 , 3);
		Rational answer = rationalInstance.add(rationalAdded);
		assertEquals("testing numerator of added fractions." , 35 , answer.getNumerator() );
		assertEquals("testing denominator of added fractions." , 6 , answer.getDenominator() );
	}
	@Test
	public void testingAddMethodThree() {
		Rational rationalInstance = new Rational(5 , 4);
		Rational rationalAdded = new Rational (11 , 4);
		Rational answer = rationalInstance.add(rationalAdded);
		assertEquals("testing numerator of added fractions." , 4 , answer.getNumerator() );
		assertEquals("testing denominator of added fractions." , 1 , answer.getDenominator() );
	}

//END: TESTING ADDING FRACTIONS METHOD	
}