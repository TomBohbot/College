package edu.yu.intro;

/** " JUNIT4 " Assignment #8
*
* @author Tom Bohbot
*/

import java.util.Arrays;

public class Factorial {

	public static void main (final String [] args) {
		long factorialTableMethod[] = factorialTable();

		System.out.println("********************");
		// System.out.println("Factorial " + Arrays.toString(factorialTableMethod));
		int counter = 0;
		while (counter <= 20) {
			System.out.println("Factorial ( " + counter + ") = " + factorialTableMethod[counter]);
			counter = counter + 1;
		}
		System.out.println("********************");
	}

	public static long factorial (int n) { 

		int counter = 0;
		int passedCounter = 0;
		long totalFactorialValue = 0;
		long factorValue[] = new long [21];

		if (n < 0 || n > 20) {
			throw new IllegalArgumentException ("Factorial Value must be an interger inbetween 0 and 20.");
		}
		while (counter <= n) {
			if (counter == 0) {
				factorValue[0] = 1;
				totalFactorialValue = 1;
				counter = 1;
			}
			else if (counter >= 1 && counter <= 9) {
				totalFactorialValue = counter * factorValue[passedCounter];
				factorValue [counter] = totalFactorialValue;
				counter = counter + 1;
				passedCounter = counter - 1;
			}
			else if (counter >= 10) {
				totalFactorialValue = counter * factorValue[passedCounter];
				factorValue [counter] = totalFactorialValue;
				counter = counter + 1;
				passedCounter = counter - 1;
			}
		}
		return totalFactorialValue;
	}

	public static long[] factorialTable() {
		// System.out.println("********************");
		int counter = 0;
		int passedCounter = 0;
		long totalFactorialValue = 0;
		long factorValue [] = new long [21];

		while (counter <= 20) {
			if (counter == 0) {
				factorValue[0] = 1;
				totalFactorialValue = 0;
				// System.out.println("Factorial ( " + counter + ") = " + factorValue[counter]);
				counter = 1;
			}
			if (counter >= 1 && counter <= 9) {
				totalFactorialValue = counter * factorValue[passedCounter];
				factorValue [counter] = totalFactorialValue;
				// System.out.println("Factorial ( " + counter + ") = " + factorValue[counter]);
				counter = counter + 1;
				passedCounter = counter - 1;
			}
			if (counter >= 10) {
				totalFactorialValue = counter * factorValue[passedCounter];
				factorValue [counter] = totalFactorialValue;
				// System.out.println("Factorial (" + counter + ") = " + factorValue[counter]);
				counter = counter + 1;
				passedCounter = counter - 1;
			}
		}
		// System.out.println("********************");
		return factorValue;
		//return factorValue;
	}
}