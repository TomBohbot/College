package edu.yu.intro;

/** " FindingRationalNumbers " Assignment #11
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

public class Factory {

	public final static int MAX_DEPARTMENTS = 10;
	private String [] deptNames;
	private String [] employees;

	private String empId;
	private double grossPay;
	private String deptName;
	private Department deptNameForArray;
	private int deptCounter;

	public Factory() {
		this.deptNames = new String [MAX_DEPARTMENTS];
	}

	// public Department [] getDepartments () {
	 	// Department [] getDepartments = new Department(deptName) [10];
	// 	for (int i = 0; i < 10; i ++) {
	// 		getDepartments[i] = deptNameForArray;
	// 	}
	// 	deptCounter ++;
	// 	return getDepartments;
	// }

	public int getNDepartments () {
		return deptCounter;
	}

	public Department newDepartment (final String input){
		Department deptInstance = new Department (input);
		String deptName = "SAMPLE";
		String preTokenizedLine = input;
		StringTokenizer tokenizerDept = new StringTokenizer(preTokenizedLine);
		int allTokensDept = tokenizerDept.countTokens();
		try {
			if (allTokensDept != 1) {
				throw new IllegalArgumentException("Incorrect amount of departments per line.");
			}	 
			else {
				deptName = tokenizerDept.nextToken();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Expected 1 token per line, found " + allTokensDept + ".  Discarding input & advancing.");
		}	
		// deptName = tokenizerDept.nextToken();
		// String [] deptNames = new String [MAX_DEPARTMENTS];
		// TRYING TO MAKE THE .EQUALS SHTICK
		// try {
		// 	for (int i = 0; i < deptNames.length; i ++) {
		// 		if (deptName.equals(deptNames[i]) ) {
		// 			throw new IllegalArgumentException("ERROR: Duplicate department.");
		// 		}
		// 		else {
		// 			deptNames[i] = deptName;
		// 		}
		// 	}
		// } catch (IllegalArgumentException e) {
		// 	System.out.println("ERROR: Duplicate department.");
		// }

		deptInstance = new Department (deptName);
		return deptInstance;
	}

	public Employee newEmployee (final String input) {

		Employee empInstance = new Employee (empId , grossPay , deptName);
		String empId = ("FakeName");
		double hoursWorked = 0;
		double wageRate = 0;
		int deductions = 0;	
		String deptName = ("FakeDepartment");
		String hoursWorkedString = "Sample";
		String wageRateString = "Sample";
		String deductionsString = "Sample";
		double grossPay = 0;
		double taxes = 0;
		double pension = 0;
		double netPay = 0;
		double taxRate = 0;
		double totalGrossPay = 0;
		int lineNumber = 0;

		String preTokenizedLine = input;
		StringTokenizer tokenizerDept = new StringTokenizer(preTokenizedLine);
		int allTokensDept = tokenizerDept.countTokens();
		StringTokenizer tokenizer = new StringTokenizer(preTokenizedLine);
		int allTokens = tokenizer.countTokens();
		lineNumber ++;

		try {
				if (allTokens != 5) {
					throw new IllegalArgumentException("Incorrect amount of tokens");
				} 

				empId = tokenizer.nextToken();
				hoursWorkedString = tokenizer.nextToken();
	 			wageRateString = tokenizer.nextToken();
	 			deductionsString = tokenizer.nextToken();
	 			deptName = tokenizer.nextToken();

	 			try {
					hoursWorked = Double.parseDouble(hoursWorkedString);
				} catch (NumberFormatException e) {
					System.out.println("Problem at line # " + lineNumber + ": Could not parse Hours Worked [" + hoursWorkedString + "] into valid input");
				} try {
					wageRate = Double.parseDouble(wageRateString);
				} catch (NumberFormatException e) {
					System.out.println("Problem at line # " + lineNumber + ": Could not parse Wage Rate [" + wageRateString + "] into valid input");
				} try {
					deductions = Integer.parseInt(deductionsString);
				} catch (NumberFormatException e) {
					System.out.println("Problem at line # " + lineNumber + ": Could not parse Deductions [" + deductionsString + "] into valid input");
				}

				grossPay = hoursWorked * wageRate;
				taxes = grossPay * taxRate;
				pension = 0.05 * grossPay;
				netPay = grossPay - taxes - deductions - pension;

				if (hoursWorked < 1) {
					System.out.println("ERROR: Line Number " + lineNumber + ". You have not worked enough hours. Come back when you have worked at least one hour.");
				}
				else if (wageRate < 15.00) {
					System.out.println("ERROR: Line Number " + lineNumber + ". Your wage rate must be at least $15.00 per hour. You must input without a dollar sign ($), and please use two decimal places (ex. 15.00).");
				}
				else if (deductions <= 0) {
					System.out.println("ERROR: Line Number " + lineNumber + ". Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
				}
				else if (deductions >= 35) {
					System.out.println("ERROR: Line Number " + lineNumber + ". Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
				}
				else if (netPay < 0) {
					System.out.println("ERROR: Line Number " + lineNumber + ". Your net pay is negative. Please advise.");
					totalGrossPay = totalGrossPay - grossPay;
				}	
				else {
					empInstance = new Employee (empId , grossPay , deptName);
				}

			} catch (IllegalArgumentException e) {
				System.out.println("Problem at line # " + lineNumber + ": Expected 4 tokens per line, found " + allTokens + ".  Discarding input & advancing.");
			}		
		return empInstance;
	}

	public static void main (final String [] args) throws NumberFormatException , NoSuchElementException {

		Factory factoryInstance = new Factory();
	
		System.out.println("*************************************************");
		System.out.printf("%s %10s %10s %20s %20s %n" , "Department", "#" , "Employees" , "Total Gross Pay" , "Average Gross Pay");

		if (args.length != 2) {
			final String msg = "Usage: DepartmentsAndEmployees employeeInputFile deptInputFile ";
			System.err.println (msg);
			throw new IllegalArgumentException (msg);
		}
		final String employeeInputFile = args[0];
		final String deptInputFile = args[1];
		final File empInput = new File (employeeInputFile);
		final File deptInput = new File (deptInputFile);

		try {
			final Scanner empScanner = new Scanner (empInput);
			final Scanner deptScanner = new Scanner (deptInput);

		int lineNumber = 1;
		while (empScanner.hasNextLine() ) {
			Employee empOutput = factoryInstance.newEmployee(empScanner.nextLine());
			String id = empOutput.getId();	
			double grossPay = empOutput.getGrossPay();
			double totalGrossPay = empOutput.getTotalGrossPay();

			// System.out.println(id);
			// System.out.println(grossPay);
			System.out.println(totalGrossPay);
		}
		while (deptScanner.hasNextLine() ) {
			Department deptOutput = factoryInstance.newDepartment(deptScanner.nextLine() );
			double totalGrossPay = deptOutput.getTotalGrossPay();
			String getDeptName = deptOutput.getName();

			System.out.println(getDeptName);
			// System.out.println(totalGrossPay);
		}
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found. " + e);
			System.exit(0);
		}
	System.out.println("STILL WORKING :)");
	System.out.println("*************************************************");
	}








}