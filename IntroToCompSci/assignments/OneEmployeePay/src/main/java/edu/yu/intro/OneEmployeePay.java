package edu.yu.intro;

/** " OneEmployeePay " for Optional Assignment #1 in Intro To Comp Sci
*
* @author Tom Bohbot
*/

import java.util.Scanner; // Allows me too use scanner for later use.

public class OneEmployeePay {
	public static void main (final String [] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println("What is your employee id? ");
		System.out.println("How many hours have you worked? ");
		System.out.println("What is your wage rate? ");
		System.out.println("How many deductions do you have? ");

		String empId = input.next ();
		double hoursWorked = input.nextDouble ();
		double wageRate = input.nextDouble ();
		int deductions = input.nextInt ();

		double taxRate = 0.15;

		double grossPay = hoursWorked * wageRate;	
		double taxes = grossPay * taxRate;

		double pension = 0.05 * grossPay;

		double netPay = grossPay - taxes - deductions - pension;
		double averagePay = netPay / hoursWorked;

		System.out.println("*************************************************");

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
