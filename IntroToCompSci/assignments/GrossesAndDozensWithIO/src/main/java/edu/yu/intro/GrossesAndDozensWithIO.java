package edu.yu.intro;

/** " Grosses And Dozens With IO " Assignment 3a
*
* @author Tom Bohbot
*/

import java.util.Scanner; // Allows me too use scanner for later use.

public class GrossesAndDozensWithIO {
	public static void main(final String [] args) {

		Scanner input = new Scanner(System.in); // Allows computer to read input

		System.out.println("Nu? Enter number of eggs to be converted: ");



		int EggsToPack = input.nextInt ();
		int EggBoxes = EggsToPack / 12;
		int RemaindingEggs = EggsToPack % 12;
		int grossEggs = EggsToPack / 144;

		System.out.println("*************************************************");

		
		System.out.println("INPUT ...");
		System.out.printf("%-20s %10d %n" , "Number of eggs:" , EggsToPack);

		System.out.println("OUTPUT ..."); 
		System.out.printf("%-20s %10d %n" , "# of gross:     " , grossEggs);
		System.out.printf("%-20s %10d %n" , "# of dozens:    " , EggBoxes);
		System.out.printf("%-20s %10d %n" , "# of extras:    " , RemaindingEggs);	


		System.out.println("*************************************************");
	
	}
}