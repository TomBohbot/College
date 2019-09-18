package edu.yu.intro;

/** " Verify Random " Assignment
*
* @author Tom Bohbot
*/

import java.util.Random;

public class VerifyRandom {
	public static void main (final String [] args) {
		
		double snakeEyes = 0;

		for (int i = 1; i <= 10000; i++) {
		
			Random random = new Random ();
			int diceOne = random.nextInt (6) + 1;
			int diceTwo = random.nextInt (6) + 1;

			if (diceOne == 1 && diceTwo == 1)
				snakeEyes = snakeEyes + 1;

		}

		int totalLoops = 10000;
		double averageSimProb = snakeEyes / totalLoops;
		double projectedRatio = 0.02777777777777777777777778;
		double difAsARatio = Math.abs((averageSimProb - projectedRatio) / projectedRatio);
		double infitinityTest = (0.1 - (1/3)) / (1/3);

		System.out.printf("%-65s %5.3f %n" , "Average simulated probability: " , averageSimProb);
		System.out.printf("%-65s %5d %n" , "Number of simulations: " , totalLoops);
		System.out.printf("%-65s %5.3f %n" , "Difference (as ratio) with respect to predicted probability: " , difAsARatio);

	} 
}