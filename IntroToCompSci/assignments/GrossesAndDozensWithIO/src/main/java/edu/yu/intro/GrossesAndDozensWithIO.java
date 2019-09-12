package edu.yu.intro;

/** " Grosses And Dozens With IO " Assignment 3a
*
* @author Tom Bohbot
*/

import java.util.Scanner; // Allows me too use scanner for later use.

public class GrossesAndDozensWithIO {
	public static void main(final String [] args) {

		Scanner input = new Scanner(System.in); // Allows computer to read input

		System.out.printf("Nu? Enter number of eggs to be converted: ");



		int eggsToPack = input.nextInt ();                                 // equal to number of input
		int grossEggs = eggsToPack / 144;                                  // Total amount of gross eggs
		int remaindingGrossEggs = grossEggs % 12;                          // Remainder of gross eggs
		int remaindingDozensOfEggs = eggsToPack - (grossEggs * 144);        // How many eggs are left after the gross eggs
		int dozenOfEggs = remaindingDozensOfEggs / 12;                     // Remainding eggs post gross divided into dozens
		int remaindingEggs = remaindingGrossEggs %12;                      // Total remainding eggs

		System.out.println("*************************************************");

		
		System.out.println("INPUT ...");
		System.out.printf("%-20s %10d %n" , "Number of eggs:" , eggsToPack);

		System.out.println("OUTPUT ..."); 
		System.out.printf("%-20s %10d %n" , "# of gross:     " , grossEggs);
		System.out.printf("%-20s %10d %n" , "# of dozens:    " , dozenOfEggs);
		System.out.printf("%-20s %10d %n" , "# of extras:    " , remaindingEggs);	


		System.out.println("*************************************************");
	
	}
}