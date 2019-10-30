package edu.yu.intro.test;

/** " ExceptionalEmployeePay " Assignment #6
*
* @author Tom Bohbot
*/

import edu.yu.intro.Factorial;
import org.junit.*;
import static org.junit.Assert.*;

public class FactorialTest {

	@Test
	public void factorial0 () {
		assertEquals ("testing factorial 0" , 1L , Factorial.factorial(0));
	}
	@Test
	public void factorial1 () {
		assertEquals ("testing factorial 1" , 1L , Factorial.factorial(1));
	}
	@Test
	public void factorial3 () {
		assertEquals ("testing factorial 9" , 362880L , Factorial.factorial(9));
	}
	@Test
	public void factorial10 () {
		assertEquals ("testing factorial 10" , 3628800L , Factorial.factorial(10));
	}
	@Test
	public void factorial19 () {
		assertEquals ("testing factorial 19" , 121645100408832000L , Factorial.factorial(19));
	}
	@Test
	public void factorial20 () {
		assertEquals ("testing factorial 20" , 2432902008176640000L , Factorial.factorial(20));
	}
	@Test(expected = IllegalArgumentException.class)
	public void invalidInputTooSmall () {
		Factorial.factorial(-1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void invalidInputTooLarge () {
		Factorial.factorial(21);
	}
	@Test 
	public void factorialArray() {
		assertArrayEquals ("testing factorial array" , new long []{1L , 1L , 2L , 6L , 24L , 120L , 720L , 5040L , 40320L , 362880L , 3628800L , 39916800L , 479001600L , 6227020800L , 87178291200L , 1307674368000L , 20922789888000L , 355687428096000L , 6402373705728000L , 121645100408832000L , 2432902008176640000L} , Factorial.factorialTable());
	}

}