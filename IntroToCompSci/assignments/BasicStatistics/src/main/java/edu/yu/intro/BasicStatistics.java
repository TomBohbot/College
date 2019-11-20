package edu.yu.intro;

/** " FindingRationalNumbers " Assignment #11
*
* @author Tom Bohbot
*/

public class BasicStatistics {

	private int nDatums;
	private double sum;
	private double mean;
	private double standardDeviation; 
	private double min = Double.POSITIVE_INFINITY;
	private double max = Double.NEGATIVE_INFINITY;

	private double sumOfStepTwoSDV;
	private double [] sdvArray = new double [1000000];

	public void enter (double num) {
		sdvArray[nDatums] = num;
		nDatums = nDatums + 1;
		sum = sum + num;
		mean = sum / nDatums;
		if (min > num) {
			min = num;
		}
		if (max < num) {
			max = num;
		}
	}

	public void enter (double[] data) {
		int j = 0;
		int lengthOfArray = data.length + nDatums;
		for (int i = nDatums; i < lengthOfArray; i ++) {
			sdvArray[i] = data[j];
			j ++;
		}
		nDatums = nDatums + data.length;
		for (int i = 0; i < data.length; i ++) {
			sum = sum + data[i];
			mean = sum / nDatums;
			if (min > data[i]) {
				min = data[i];
			}
			if (max < data[i]) {
				max = data[i];
			}
		}
	}

	public int getNDatums () {
		return nDatums;
	}

	public double getSum () {
		return sum;
	}

	public double getMean () {
		if (nDatums == 0) {
			return 0/0.0;
		}
		return mean;
	}

	public double getStandardDeviation () {
		if (nDatums == 0) {
			return 0/0.0;
		}
		for (int i = 0; i < nDatums; i ++) {
			double stepOneSDV = (sdvArray[i] - mean);
			double stepTwoSDV = stepOneSDV * stepOneSDV;
			sumOfStepTwoSDV = sumOfStepTwoSDV + stepTwoSDV;
			double stepThreeSDV = sumOfStepTwoSDV / (nDatums);
			double finalStepSDV = Math.sqrt(stepThreeSDV);
			standardDeviation = finalStepSDV;
		}
		return standardDeviation;
	}

	public double getMin () {
		return min;
	}

	public double getMax () {
		return max;
	}

	public static void main ( final String [] args ) {
		BasicStatistics statisticsInstance = new BasicStatistics();

		statisticsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		// statisticsInstance.enter(22.3);
		// statisticsInstance.enter(872.1);
		// statisticsInstance.enter(39.8);
		// statisticsInstance.enter(47.6);
		
		int printGetNDatumsMethod = statisticsInstance.getNDatums();
		double printGetMinMethod = statisticsInstance.getMin();
		double printGetMaxMethod = statisticsInstance.getMax();
		double printGetSumMethod = statisticsInstance.getSum();
		double printGetMeanMethod = statisticsInstance.getMean();
		double printGetSDMethod = statisticsInstance.getStandardDeviation();

		System.out.println("*************************************************");	
		System.out.printf ("%-20s %-15d %n" , "Number of datums: " , printGetNDatumsMethod);
		System.out.printf("%-20s %-15.3f %n" ,"Min of datums: " , printGetMinMethod);
		System.out.printf("%-20s %-15.3f %n" ,"Max of datums: " , printGetMaxMethod);
		System.out.printf("%-20s %-15.3f %n" , "Sum of datums: " , printGetSumMethod);
		System.out.printf("%-20s %-15.3f %n" ,"Mean of datums: " , printGetMeanMethod);
		System.out.printf("%-20s %-15.3f %n" ,"StdDev of datums: " , printGetSDMethod);
		System.out.println("*************************************************");
	}
}