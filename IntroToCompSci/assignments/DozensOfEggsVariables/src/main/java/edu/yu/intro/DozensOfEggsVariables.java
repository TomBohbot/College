package edu.yu.intro;

/** " Dozens Of Eggs Variables " for Intro To Comp Sci
*
* @author Tom Bohbot
*/
public class DozensOfEggsVariables {
	public static void main(final String [] args) {
		int EggsToPack = 18972;
		int EggBoxes = EggsToPack / 12;
		int RemaindingEggs = EggsToPack % 12;
		double CostPerBox = 0.72;
		double TotalCost = EggBoxes * CostPerBox;

		System.out.println("Your number of eggs (" + EggsToPack + ") gets packed into " + EggBoxes + " boxes and " + RemaindingEggs + " extras");	
		System.out.println("Total packaging cost: $" + TotalCost);	
	
	}
}