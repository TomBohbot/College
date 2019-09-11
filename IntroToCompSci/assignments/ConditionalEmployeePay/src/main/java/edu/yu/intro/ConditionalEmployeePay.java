package edu.yu.intro;

/** " Conditional Employee Pay " Assignment #3b
*
* @author Tom Bohbot
*/

import java.util.Scanner; // Allows me too use scanner for later use.

public class ConditionalEmployeePay {
	public static void main (final String [] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("What is your employee id? ");
		System.out.println("How many hours have you worked? ");
		System.out.println("What is your wage rate? Please enter as a number without the $ sign. Ex. 15.25");
		System.out.println("How many deductions do you have? ");

		int empId = input.nextInt ();
		double hoursWorked = input.nextDouble ();
		double wageRate = input.nextDouble ();
		int deductions = input.nextInt ();

		double taxRate = 0.15;

		double grossPay = hoursWorked * wageRate;	
		double taxes = grossPay * taxRate;

		double pension = 0.05 * grossPay;

		double netPay = grossPay - taxes - deductions - pension;
		double averagePay = netPay / hoursWorked;

			if (netPay < 0) {
			System.out.println("Your net pay is negative. Please advise.");
			System.exit (0);
		}	
		if (hoursWorked < 1) {
			System.out.println("You have not worked enough hours. Come back when you have worked at least one hour.");
			System.exit (0);
		}
		if (wageRate <= 15.00) {
			System.out.println("You must make at least $15.00 per hour. You must input without a dollar sign ($), and please use two decimal places (ex. 15.00).");
			System.exit (0);
		}
		if (deductions < 0) {
			System.out.println("Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
			System.exit (0);
		}
		if (deductions > 35) {
			System.out.println("Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
			System.exit (0);
		}


		System.out.println("*************************************************");
		System.out.println("");
		
		System.out.printf("%s %n" , "INPUT...");                                     // Input Line
		System.out.printf("%-20s %-20s %n" , "Employee Id:" , empId);                // Line 1
		System.out.printf("%-20s %-20.2f %n" , "Hours Worked:" , hoursWorked);       // Line 2
		System.out.printf("%-20s %-20.2f %n" , "Wage Rate:" , wageRate);             // Line 3
		System.out.printf("%-20s %-20d %n" , "Deductions" , deductions);             // Line 4

		System.out.println("");                                                     // Empty Space
		System.out.println("");                                                     // Empty Space
		System.out.println("");                                                     // Empty Space

		System.out.printf("%s %n" , "OUTPUT...");                                   // Output Line
		System.out.printf("%-20s %-20.2f %n" , "Gross Pay:" , grossPay);
		System.out.printf("%-20s %-20.2f %n" , "Taxes:" , taxes);
		System.out.printf("%-20s %-20.2f %n" , "Net pay:" , netPay);
		System.out.printf("%-20s %-20.2f %n" , "Average pay:" , averagePay);

		System.out.println("*************************************************");
		
	}
}











