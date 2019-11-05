package edu.yu.intro;

/** " AckermannFn " Assignment #9
*
* @author Tom Bohbot
*/

import java.util.*;

public class AckermannFn {
	public static void main (final String [] args) {
		int m = 0;
		int n = 0;
		System.out.println("********************");
		while (m<=3) {
			long ackermannMethod = ackermann(m , n);
			System.out.println("Ackermann's function (" + m + "," + n + ") is equal to " + ackermannMethod);
			m = m + 1;

			if (m == 4 ){
				n = n + 1;
				m = 0;
			} if (n == 4){
				break;
			}
		}
		System.out.println("********************");

	}

	public static long ackermann ( long m , long n ) {
		long ackResult = 0;

		if (m < 0L || n < 0L) {
			throw new IllegalArgumentException ("Ackermanm's function only works with nonnegative integers. Please input nonnegative integers.");
		} else if (m == 0L) {
			ackResult = n + 1L;
		} else if (n == 0L) {
			m = m - 1L;
			n = 1L;
			ackResult = ackermann (m , n);
		} else {
			n = n - 1;
			Long newAckermann = ackermann(m , n);
			m = m - 1;
			ackResult = ackermann (m , newAckermann);
		}
		return ackResult;

	}

	// public static long ackermann1 ( long m , long n ) {
	// 	long ackResult = 0;

	// 	if (m < 0L || n < 0L) {
	// 		throw new IllegalArgumentException ("Ackermanm's  function only works with nonnegative integers. Please input nonnegative integers.");
	// 	} else if ( m > 3L || n > 3L) {
	// 		throw new StackOverflowError ("This code isn't made to handle numbers greater than three.");
	// 	} else if (m == 0L) {
	// 		ackResult = n + 1L;
	// 	} else if (m == 1L) {
	// 		ackResult = n + 2L;
	// 	} else if (m == 2L) {
	// 		ackResult = 2L*n + 3L;
	// 	} else if (m == 3L && n == 0) {
	// 		ackResult = 2L * 2L * 2L - 3L;
	// 	} else if (m == 3L && n == 1) {
	// 		ackResult = 2L * 2L * 2L * 2L - 3L;
	// 	} else if (m == 3L && n == 2) {
	// 		ackResult = 2L * 2L * 2L * 2L * 2L - 3L;
	// 	} else if (m == 3L && n == 3) {
	// 		ackResult = 2L * 2L * 2L * 2L *2L * 2L - 3L;
	// 	}
	// 	return ackResult; 
	// }
	
}








