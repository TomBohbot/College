package edu.yu.intro ;

/** " Dozens Of Eggs Variables " for Intro To Comp Sci
*
* @author Tom Bohbot
*/
public class DozensOfEggsVariables {
	public static void main(final String [] args) {
		double EggsToPack = 18972;
		double EggBoxes = EggsToPack / 12;
		double RemaindingEggs = EggsToPack % 12;
		double CostPerBox = 0.72;
		double TotalCost = EggsToPack * CostPerBox;

		System.out.println("Your number of eggs (" + EggsToPack + ") gets packed into " + EggBoxes + "  boxes and " + RemaindingEggs + " extra eggs");	
		System.out.println("Total packaging cost: $" + TotalCost);	
	
	}
}