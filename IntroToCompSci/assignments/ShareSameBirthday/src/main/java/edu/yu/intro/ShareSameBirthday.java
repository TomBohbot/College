package edu.yu.intro;

/** " ShareSameBirthday " Assignment
*
* @author Tom Bohbot
*/

import java.util.Random;
import java.util.Arrays;


public class ShareSameBirthday {
	public static void main (final String [] args) {

		Random random = new Random ();

		int [] bdayRangeTen = new int [10];
		int [] bdayRangeTwenty = new int [23];
		int [] bdayRangeSeventy = new int [70];

		Double matchTen = 0.0;
		Double matchTwenty = 0.0;
		Double matchSeventy = 0.0;

		String breakLoopTen = "Don't break.";
		String breakLoopTwenty = "Don't break.";
		String breakLoopSeventy = "Don't break.";

		for (int birthdayTen = 0; birthdayTen <= 10000; birthdayTen ++)	{

// 10 PEOPLE:

			for (int tenCase = 0; tenCase <= 9; tenCase ++) {
				bdayRangeTen[tenCase] = random.nextInt (365) + 1;

			}
				for (int i = 0; i <= 9; i++) {

					for (int matchingBirthdayTen = i + 1; matchingBirthdayTen <= 9; matchingBirthdayTen++) {
						if (bdayRangeTen[i]==(bdayRangeTen[matchingBirthdayTen])){
							matchTen = matchTen +1;
							breakLoopTen = "BREAK";
							break;
						}

					}
					if (breakLoopTen == "BREAK") {
						breakLoopTen = "Don't break.";
						break;
					}
						
						
				}
			
// 23 PEOPLE:

			for (int twentyCase = 0; twentyCase <= 22; twentyCase ++) {
				bdayRangeTwenty[twentyCase] = random.nextInt (365) + 1;

			}
				for (int iTwenty = 0; iTwenty <= 22; iTwenty++){

					for (int matchingBirthdayTwenty = iTwenty + 1; matchingBirthdayTwenty <= 22; matchingBirthdayTwenty++){
						if (bdayRangeTwenty[iTwenty]==(bdayRangeTwenty[matchingBirthdayTwenty])){
							matchTwenty = matchTwenty +1;
							breakLoopTwenty = "BREAK";
							break;

						}

					}
					if (breakLoopTwenty == "BREAK") {
							breakLoopTwenty = "Don't break.";
							break;
					}
							
						
				}

// 70 PEOPLE:

			for (int seventyCase = 0; seventyCase <= 69; seventyCase ++) {
				bdayRangeSeventy[seventyCase] = random.nextInt (365) + 1;

			}
				for (int iSeventy = 0; iSeventy <= 69; iSeventy++){

					for (int matchingBirthdaySeventy = iSeventy + 1; matchingBirthdaySeventy <= 69; matchingBirthdaySeventy++){
						if (bdayRangeSeventy[iSeventy] == bdayRangeSeventy[matchingBirthdaySeventy]){
							matchSeventy = matchSeventy +1;
							breakLoopSeventy = "BREAK";
							break;

						}
					}
					if (breakLoopSeventy == "BREAK") {
						breakLoopSeventy = "Don't break.";
						break;
					}
							
							
						breakLoopSeventy = "Don't break.";
				}
		}
		
		


		// System.out.println("Matching Birthdays For 10 People: " + matchTen);
		// System.out.println("Matching Birthdays For 23 People: " + matchTwenty);
		// System.out.println("Matching Birthdays For 70 People: " + matchSeventy);

		double expirementsRun = 10000.0;
		double tenBdayStat = (matchTen / expirementsRun);
		double twentyBdayStat = (matchTwenty / expirementsRun);
		double seventyBdayStat = (matchSeventy/expirementsRun);


		System.out.println("");
		System.out.println("***********************************************");
		System.out.printf("%-10s %n" , "Number of expirements run: 10,000");
		System.out.printf("%-10s %5.3f %n" , "(For population size 10): simulated 'shared birthday'frequency is " , tenBdayStat);
		System.out.printf("%-10s %5.3f %n" ,"(For population size 23): simulated 'shared birthday'frequency is " , twentyBdayStat);
		System.out.printf("%-10s %5.3f %n" ,"(For population size 70): simulated 'shared birthday'frequency is " , seventyBdayStat);
		System.out.println("***********************************************");
	}
}