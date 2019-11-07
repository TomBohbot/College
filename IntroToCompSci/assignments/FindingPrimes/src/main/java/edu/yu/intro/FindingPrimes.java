package edu.yu.intro;

/** " FindingPrimes " Assignment #10
*
* @author Tom Bohbot
*/

import java.util.Arrays;
import java.util.*;
import java.lang.Math.*;

public class FindingPrimes {

	public final static int MAX_LIMIT = 10000;

	public static boolean isPrime ( final int N ) {
		// Double squareRootDouble = 0.0;												// Required -- Nope
		// Double floorSquareRoot = 0.0;												// Required -- Nope
		// int prevSquareRoot = 1;														// Required -- Nope
		// int roundedSquareRoot = 1;													// Required -- Nope
		// boolean isPrime = false; 													// Required -- Nope
		// // int listOfPrimes [] = new int [9999];										// Not Required -- Nope
		// // int arrayCounter = 0;														// Not Required -- Nope
		// int counter = 0;																// Required -- Nope

		// for (int i =2; i <= 10000*10000; i ++) {										// When I was testing in main.
		if (N < 2 || N > MAX_LIMIT) {
			throw new IllegalArgumentException ("Please input an integer greater than one and less than the max limit.");
		}
		for (int i =2; i <= Math.round(Math.sqrt(N)); i ++) {							// Most of these notes are for when I tested in main. Will try to never test in main again!
			// prevSquareRoot = roundedSquareRoot;										// Checking Previous square root to current square root.
			// squareRootDouble = Math.sqrt(i);											// Process of finding square root as a Double.
			// System.out.println("squareRootDouble=" + roundedSquareRoot);
			// floorSquareRoot = Math.floor(squareRootDouble);							// Process of finding square root and converting to int.
			// System.out.println("floorSquareRoot=" + roundedSquareRoot);
			// roundedSquareRoot = (int) Math.round(floorSquareRoot);					// This is the square root of i as an int.
			// System.out.println("roundedSquareRoot=" + roundedSquareRoot);
			// primeChecker = true;
			// // listOfPrimes [arrayCounter] = roundedSquareRoot;	
			// if (prevSquareRoot == roundedSquareRoot) {								// If there is the same square root, then it is a repeating error so skip it.
			// 	primeChecker = false;
			// 	continue;									
			// }
			// else {
			if ( N % i == 0){
				return false;
			}	
		}
		return true;																// If not repeating then all is good.
			// 	primeChecker = true;
			// 	for (int j = 2; j < roundedSquareRoot; j ++) {						// Doesn't include sqrt b/c anything modum itself is zero, so does not help to conclude if prime.
			// 		// roundedSquareRoot = N;
			// 		if (roundedSquareRoot % j == 0) {	
			// 			primeChecker = false;
			// 			return primeChecker;		
			// 		}
			// 	}
			// // }
			// 	if (roundedSquareRoot < 2 || roundedSquareRoot > MAX_LIMIT) {		// Assignment requirements. Moved up in code due to not requiing two loops. Required two loops because I tried doing everything om one method originally.
			// 		throw new IllegalArgumentException ("ERROR: Number must be greater than one or smaller than 10,000.");
			// 	}
			// 	if (isPrime == true ){
			// 		// listOfPrimes [counter] = roundedSquareRoot;
			// 		// System.out.println(roundedSquareRoot);						 // When I used this as main.!!!
			// 		counter = counter + 1;
			// 		return primeChecker;
			// 		// return primeChecker;
			// 	}
		// int finalListOfPrimes[] = new int [counter + 1];
		// for (int k = 0; k <= counter; k++) {
		// 	finalListOfPrimes[k] = listOfPrimes[k];
		// }
		// primeChecker = false;
		// return primeChecker;

	}

	public static int [] primes ( final int N ) {
		int primeChecker;
		boolean isPrimeMethod;
		//System.out.println("is prime num?" + primeChecker)
		int [] primeNumberArray = new int [MAX_LIMIT];
		int counter = 0;

		if (N < 2 || N > MAX_LIMIT) {
			throw new IllegalArgumentException ("Please input an integer greater than one and less than the max limit.");
		}
		for (primeChecker = 2; primeChecker <= N; primeChecker ++) {
			isPrimeMethod = isPrime(primeChecker);
			if (isPrimeMethod) {

				primeNumberArray[counter] = primeChecker;
				counter = counter + 1;
				// System.out.println(primeChecker); // Checking when this was part of the main.
			}	
		}
		int finalListOfPrimes[] = new int [counter];
		for (int k = 0; k < counter; k++) {
			finalListOfPrimes[k] = primeNumberArray[k];
			// System.out.println(finalListOfPrimes[k]); // Checking when this was part of the main.
		}
		return finalListOfPrimes;

	}


	public static void main (final String [] args) {

		//int primeNumberMethod = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("Nu? Enter the upper limit number that you want to search for primes: ");
		int maxPrimeNumber = input.nextInt ();
		// int searchPrimeNumber = maxPrimeNumber + 1;

		// if (maxPrimeNumber < 2 || maxPrimeNumber > MAX_LIMIT) {
		// 	throw new IllegalArgumentException ("Please input an integer greater than one and less than the max limit.");
		// }
		// else {

			int allPrimesScanner [] = primes(maxPrimeNumber);
			int primeArrayLength = allPrimesScanner.length;
			System.out.println("");
			System.out.println("*************************************************");
			System.out.println("INPUT ...");
			System.out.println("Searched for primes up to: " + maxPrimeNumber);
			System.out.println("OUTPUT ...");
			System.out.println("Found " + primeArrayLength + " primes from 2.." + maxPrimeNumber);

			for (int i = 0; i < primeArrayLength; i ++) {
				System.out.print (allPrimesScanner[i] + ", ");
			}
			System.out.println("");
			System.out.println("*************************************************");
			// 	if (allPrimesScanner[i] <= maxPrimeNumber) {
					
			// 	}
			// 	else {
			
			// 		break;
			// 	}
			// }
		// }
	}
}

