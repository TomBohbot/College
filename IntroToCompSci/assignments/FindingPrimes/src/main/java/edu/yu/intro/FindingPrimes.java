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

	public static int [] primes ( final int N ) {
		Double squareRootDouble = 0.0;
		Double floorSquareRoot = 0.0;
		int prevSquareRoot = 1;
		int roundedSquareRoot = 1;
		String primeChecker = "Pending";
		int listOfPrimes [] = new int [9999];
		int arrayCounter = 0;
		int counter = 0;

		for (int i =2; i <= 10000*10000; i ++) {
			prevSquareRoot = roundedSquareRoot;										// Checking Previous square root to current square root.
			squareRootDouble = Math.sqrt(i);										// Process of fidning square root.
			floorSquareRoot = Math.floor(squareRootDouble);							// Process of fidning square root.
			roundedSquareRoot = (int) Math.round(floorSquareRoot);					// This is the square root of i.
			primeChecker = "True";
			// listOfPrimes [arrayCounter] = roundedSquareRoot;	
			if (prevSquareRoot == roundedSquareRoot) {								// If there is the same square root, then it is a repeating error so skip it.
				continue;									
			}
			else {																	// If not repeating then all is good.
				primeChecker = "True";
				// System.out.println(roundedSquareRoot);
				for (int j = 2; j < roundedSquareRoot; j ++) {	
					if (roundedSquareRoot % j == 0) {		// Check if square root modum every number until square root = 0, if it does then not prime.
						primeChecker = "False";
						break;		
					}
				}
			}
				if (primeChecker == "True" ){
					// System.out.println(roundedSquareRoot);
					listOfPrimes [counter] = roundedSquareRoot;
					counter = counter + 1;
				}
		}
		int finalListOfPrimes[] = new int [counter + 1];
		for (int k = 0; k <= counter; k++) {
			finalListOfPrimes[k] = listOfPrimes[k];
			// System.out.println(finalListOfPrimes[k]);
		}
		return finalListOfPrimes;

	}

	public static boolean isPrime ( final int N ) {
		int primeNumber = 0;
		boolean ifPrime = false;
		int allPrimes [] = primes(primeNumber);
		for (int i = 2; i <= 10000; i ++) {
			for (int j = 0; j <= 10000; j ++) {
				if (i == (allPrimes[j])){
					ifPrime = true;
					break;
				}
			}
		}
		return ifPrime;
	}

	public static void main (final String [] args) {
		int primeNumberMethod = 0;
		int allPrimesScanner [] = primes(primeNumberMethod);
		Scanner input = new Scanner(System.in);
		System.out.print("Nu? Enter the upper limit number that you want to search for primes: ");
		int maxPrimeNumber = input.nextInt ();
		int counter = 0;

		System.out.println("");
		System.out.println("*************************************************");
		System.out.println("INPUT ...");
		System.out.println("Searched for primes up to: " + maxPrimeNumber);
		System.out.println("OUTPUT ...");

		for (int i = 0; i <= maxPrimeNumber; i ++) {
			if (allPrimesScanner[i] <= maxPrimeNumber) {
				counter = counter + 1;
			}
			else {
				System.out.println("Found " + counter + " primes from 2.." + maxPrimeNumber);
				break;
			}
		}
		for (int i = 0; i <= maxPrimeNumber; i ++) {
			if (allPrimesScanner[i] <= maxPrimeNumber) {
				System.out.print (allPrimesScanner[i] + " ");
			}
			else {
				System.out.println("");
				System.out.println("*************************************************");
				break;
			}
		}

	}
}




