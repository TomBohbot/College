package edu.yu.intro;

/** " Verify Random " Assignment
*
* @author Tom Bohbot
*/

import java.util.Random;

public class VerifyRandom {
	public static void main (final String [] args) {
		
		double snakeEyes = 0;

		int i = 0;
		Random random = new Random ();
		int diceOne = 0;
		int diceTwo = 0;
		double rollsUntilSnakeEyes = 0;
		double totalCombinedRolls = 0;


		for (i =1; i <=10000; i++){

			while (snakeEyes < 10000) {
		
				diceOne = random.nextInt (6) + 1;
				diceTwo = random.nextInt (6) + 1;	

				rollsUntilSnakeEyes = rollsUntilSnakeEyes + 1;

				if (diceOne == 1 && diceTwo == 1)
					snakeEyes = (snakeEyes + 1);
				
			}	

	}

		int totalLoops = 10000;
		double averageSimProb = 10000 / rollsUntilSnakeEyes;
		double numOfFrac = 1;  									// Due to setting variable equal to "1/36" being zero.
		double denOfFrac = 36;								    // Due to setting variable equal to "1/36" being zero.
		double projectedRatio = numOfFrac / denOfFrac;
		double difAsARatio = Math.abs((averageSimProb - projectedRatio) / projectedRatio);

		System.out.printf("%-65s %5.3f %n" , "Average simulated probability: " , averageSimProb);
		System.out.printf("%-65s %5d %n" , "Number of simulations: " , totalLoops);
		System.out.printf("%-65s %5.3f %n" , "Difference (as ratio) with respect to predicted probability: " , difAsARatio);

	} 
}