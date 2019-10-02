package edu.yu.intro;

/** " ExceptionalEmployeePay " Assignment #3b
*
* @author Tom Bohbot
*/

import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class ExceptionalEmployeePay {
	public static void main (final String [] args) throws NumberFormatException , NoSuchElementException{


// BEGINNING: Code Prof. Leff said to use:													*********************************************************
		if (args.length != 1) {
			final String msg = "Usage: ExceptionalEmployeePay name_of_input file";
			System.err.println(msg);
			throw new IllegalArgumentException(msg);
		}

		final String inputFileName = args[0];
		final File input = new File(inputFileName);

// END: Code Prof. Leff said to use:														*********************************************************	

//BEGINNING: Variables needed for Scanner 													*********************************************************
		String empId = ("FakeName");
		double hoursWorked = 0;
		double wageRate = 0;
		int deductions = 0;	

		String hoursWorkedstring = "Sample";
 		String wageRatestring = "Sample";
 		String deductionsstring = "Sample";

		String lineFromNextLine = "Sample";	
// END: Variables needed for Scanner   														*********************************************************

//BEGINNING: Variables needed for Output 													*********************************************************
		double taxRate = 0.15;
		double grossPay = hoursWorked * wageRate;	
		double taxes = grossPay * taxRate;
		double pension = 0.05 * grossPay;
		double netPay = grossPay - taxes - deductions - pension;
		double averagePay = netPay / hoursWorked;
		int totalTokenPerLine = 0;

		double totalGrossPay = 0;
		double totalTaxes = 0;
		double highestPay = 0;
		String highestPayEmp= "Noone";
		int lineCounter = 0;
		String grossPayConditional = "Don't Break";
		String taxConditional = "Don't Break";
// END: Variables needed for output   														*********************************************************

		System.out.println("*************************************************");
		System.out.printf("%20s %-20s %-20s %-20s %-20s %n" , "Id", "Gross Pay" , "Taxes" , "Net Pay" , "Average Pay");
// Beginning: Attempt I made to make scanning files work and variables for scanner:			xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		try {
			final Scanner scanner = new Scanner(input);

			int lineNumber = 1;

			while (scanner.hasNext() ){										
				lineFromNextLine = scanner.nextLine ();
				lineCounter = lineCounter + 1;
// END: Attempt I made to make scanning files work and variables for scanner:				xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

// BEGINNING: String Tokenizer into seperate strings and converting to appropiate type		*********************************************************
				StringTokenizer tokenizer = new StringTokenizer(lineFromNextLine);
		 	// 	while (tokenizer.hasMoreTokens()) {
		 	// 		try {
			 // 			empId = tokenizer.nextToken();
			 // 			hoursWorkedstring = tokenizer.nextToken();
			 // 			wageRatestring = tokenizer.nextToken();
			 // 			deductionsstring = tokenizer.nextToken();
		 	// 		} catch (NoSuchElementException e){
				// 		System.out.println("Problem at line # " + lineCounter + ": Expected 4 tokens per line, found (token counter).  Discarding input & advancing.");
				// 		grossPayConditional = "Break";
				// 		taxConditional = "Break";
				// 	}
		 	// 	}
		 	// 	try {
				// 	hoursWorked = Double.parseDouble(hoursWorkedstring);
				// } catch (NumberFormatException e) {
				// System.out.println("Problem at line # " + lineCounter + ": Could not parse Hours Worked [" + hoursWorkedstring + "] into valid input");
				// } try {
				// 	wageRate = Double.parseDouble(wageRatestring);
				// } catch (NumberFormatException e) {
				// System.out.println("Problem at line # " + lineCounter + ": Could not parse Wage Rate [" + wageRatestring + "] into valid input");
				// } try {
				// 	deductions = Integer.parseInt(deductionsstring);
				// } catch (NumberFormatException e) {
				// System.out.println("Problem at line # " + lineCounter + ": Could not parse Deductions [" + deductionsstring + "] into valid input");
				// }


				try {
//**********************//**********************//**********************//**********************//**********************//**********************//**********************//**********************					
					while (tokenizer.hasMoreTokens()) {
				 			empId = tokenizer.nextToken();
				 				totalTokenPerLine = totalTokenPerLine + 1;
				 			hoursWorkedstring = tokenizer.nextToken();
				 				totalTokenPerLine = totalTokenPerLine + 1;
				 			wageRatestring = tokenizer.nextToken();
				 				totalTokenPerLine = totalTokenPerLine + 1;
				 			deductionsstring = tokenizer.nextToken();
				 				totalTokenPerLine = totalTokenPerLine + 1;
		 			}


			 		try {
						hoursWorked = Double.parseDouble(hoursWorkedstring);
					} catch (NumberFormatException e) {
					System.out.println("Problem at line # " + lineCounter + ": Could not parse Hours Worked [" + hoursWorkedstring + "] into valid input");
					} try {
						wageRate = Double.parseDouble(wageRatestring);
					} catch (NumberFormatException e) {
					System.out.println("Problem at line # " + lineCounter + ": Could not parse Wage Rate [" + wageRatestring + "] into valid input");
					} try {
						deductions = Integer.parseInt(deductionsstring);
					} catch (NumberFormatException e) {
					System.out.println("Problem at line # " + lineCounter + ": Could not parse Deductions [" + deductionsstring + "] into valid input");
					}
//**********************//**********************//**********************//**********************//**********************//**********************//**********************//**********************//**********************
					hoursWorked = Double.parseDouble(hoursWorkedstring);
					wageRate = Double.parseDouble(wageRatestring);
					deductions = Integer.parseInt(deductionsstring);

					grossPay = hoursWorked * wageRate;
					taxes = grossPay * taxRate;
					pension = 0.05 * grossPay;
					netPay = grossPay - taxes - deductions - pension;
					averagePay = netPay / hoursWorked;

// BEGINNING: Conditionals That Test For Errors That Java Will Still Run					*********************************************************	
					if (hoursWorked < 1) {
						System.out.println("ERROR: Line Number " + lineCounter + ". You have not worked enough hours. Come back when you have worked at least one hour.");
					}
					else if (wageRate < 15.00) {
						System.out.println("ERROR: Line Number " + lineCounter + ". Your wage rate must be at least $15.00 per hour. You must input without a dollar sign ($), and please use two decimal places (ex. 15.00).");
					}
					else if (deductions <= 0) {
						System.out.println("ERROR: Line Number " + lineCounter + ". Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
					}
					else if (deductions >= 35) {
						System.out.println("ERROR: Line Number " + lineCounter + ". Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
					}
					else if (netPay < 0) {
						System.out.println("ERROR: Line Number " + lineCounter + ". Your net pay is negative. Please advise.");
					}	
					else {
						System.out.printf("%20s %-20.2f %-20.2f %-20.2f %-20.2f %n" ,empId , grossPay , taxes , netPay , averagePay);
					}
// END: Conditionals That Test For Errors That Java Will Still Run							*********************************************************		

				} catch (NumberFormatException e) {
					// System.out.println("Problem at line # " + lineCounter + ": Could not parse (Variable) [XXX] into valid input");
					totalTokenPerLine = 0;
					grossPayConditional = "Break";
					taxConditional = "Break";
				 } catch (NoSuchElementException e) {
					System.out.println("Problem at line # " + lineCounter + ": Expected 4 tokens per line, found " + totalTokenPerLine + ").  Discarding input & advancing.");
					totalTokenPerLine = 0;
					grossPayConditional = "Break";
					taxConditional = "Break";
				}
				
				if (grossPayConditional == "Don't Break"){
					if (hoursWorked >=1 && wageRate >= 15){
						totalGrossPay = totalGrossPay + grossPay;
						if (grossPay > highestPay) {
							highestPay = grossPay;
							highestPayEmp = empId;
						}
					}
				}
				if (taxConditional == "Don't Break"){
					if (hoursWorked >=1 && wageRate >= 15){
						totalTaxes = totalTaxes + taxes;
						}
					}
			grossPayConditional = "Don't Break";
			taxConditional = "Don't Break";
			}   

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.printf("%42s %-22.2f %n" , "Total Gross Pay: " , totalGrossPay);
			System.out.printf("%42s %-22.2f %n" , "Total Taxes: " , totalTaxes);
			System.out.printf("%42s %-22s %n", "Employee with Largest Gross Pay: " , highestPayEmp);
			System.out.println("*************************************************");


			} catch (java.io.FileNotFoundException e) {
				System.out.println("File not found" + e);
				System.exit(0);

		}
	}
	
}

		