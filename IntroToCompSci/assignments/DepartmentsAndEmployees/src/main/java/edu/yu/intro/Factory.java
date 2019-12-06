package edu.yu.intro;

/** " Factory Class for Departments Assignment " Assignment #11
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

	private final static int MAX_EMPLOYEES = 100;		// Requirements Document.
	public final static int MAX_DEPARTMENTS = 10;		// Requirements Document.
	private String [] deptNames; 						// Required to check if duplicte departments.
	private Department [] departments; 					// Required to return an array of object departments.
	private int deptLineNumber; 						// check line number of department file
	private int lineNumber; 							// for the employee file.
	private int empIdCounter; 							// for the employee duplicate part.
	private String empIdArray [] = new String [1000]; 	// for the employee duplicate part.
	private int counter = 4;							// for the resized array I made to get aroung the nullpointer exception.
	private int deptCounter;
	private int empToDeptCounter;

	public Factory() {
		this.deptNames = new String [MAX_DEPARTMENTS];
		this.departments = new Department [MAX_DEPARTMENTS];
	}

	public Department [] getDepartments () {	
		// attempting to get rid of null pointer exception which is what my muted return line of code returns if less than ten departments. 
		Department [] resizeDeptArray = new Department [deptCounter];
		for (int i = 0; i < deptCounter; i ++) {
			resizeDeptArray[i] = departments[i];
		}
		return resizeDeptArray;
		// return this.departments;
	}

	public int getNDepartments () {
		return deptCounter;
	}

	public Department newDepartment (final String input){
		Factory factoryInstance = new Factory();
		Department deptInstance = new Department ("hi");
		String deptName = "Sample";
		String preTokenizedLine = input;
		StringTokenizer tokenizerDept = new StringTokenizer(preTokenizedLine);
		int allTokensDept = tokenizerDept.countTokens();
		deptLineNumber ++;

		if (allTokensDept != 1) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + deptLineNumber + ": Incorrect amount of departments per line.");
		}	 
		else {
			deptName = tokenizerDept.nextToken();
		}
		// Looking for a duplicate department via the loop.
		for (int i = 0; i < this.deptNames.length; i ++) {
			if (deptName.equals(deptNames[i]) ) {
				throw new IllegalArgumentException("ERROR: Problem at line # " + deptLineNumber + ": Duplicate department.");
			}
		}

		// not throwing the error because I don't want to show the stack. The client should not see that. Prof. Leff said either is fine on Dec. 5th in class. 
		if(deptCounter == 10) {
			System.out.println("ERROR: Problem at line # " + deptLineNumber + ": Over ten departments were entered. Discarding file and exiting program.");
			System.exit(1); 
		}

		// Creating departments that passed my tests.
		this.deptNames[this.getNDepartments()] = deptName;
		deptInstance = new Department(deptName);
		this.departments[this.getNDepartments()] = deptInstance;
		deptCounter ++;
			
		return deptInstance;
	}

	public Employee newEmployee (final String input) {

		Employee empInstance = new Employee ("sampleEmp" , 100 , "sampleDept");
		String empId = "";
		double hoursWorked = 0;
		double wageRate = 0;
		int deductions = 0;	
		String deptName = "";
		String hoursWorkedString = "";
		String wageRateString = "";
		String deductionsString = "";
		double grossPay = 0;
		boolean passedTests = true;

		String preTokenizedLine = input;
		StringTokenizer tokenizerDept = new StringTokenizer(preTokenizedLine);
		int allTokensDept = tokenizerDept.countTokens();
		StringTokenizer tokenizer = new StringTokenizer(preTokenizedLine);
		int allTokens = tokenizer.countTokens();
		lineNumber ++;

		if (allTokens != 5) {
			passedTests = false;
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file: Incorrect amount of tokens");
		} 

		empId = tokenizer.nextToken();
		hoursWorkedString = tokenizer.nextToken();
		wageRateString = tokenizer.nextToken();
		deductionsString = tokenizer.nextToken();
		deptName = tokenizer.nextToken();

		// Department [] arrayOfDepts = this.getDepartments();
		// Employee [] arrayOfEmp;
		// for (int i = 0; i < deptCounter; i ++) {
		// 	if (deptName.equals(arrayOfDepts[i].getName() ) ) {
		// 	// if (deptName.equals(this.deptNames[i]) ) {	
		// 		System.out.println(arrayOfDepts[i].getName() );
		// 		arrayOfEmp = arrayOfDepts[i].getEmployees();
		// 		for (int j = 0; j < arrayOfDepts[i].getNEmployees(); j ++) {
		// 			if (empId.equals(arrayOfEmp[j].getId() ) ) {
		// 				throw new IllegalArgumentException ("ERROR: Problem at line # " + lineNumber + " of employee file: This employee has already been accounted for.");
		// 			}
		// 		}
		// 	}
		// }

		// Department deptInstance = new Department(deptName); // To pull in the array of employees
		// Employee [] empInDeptArray = deptInstance.getEmployees();

		// Ensuring there are no duplicate employee ids. Modifying this one to only catch duplicates in the same department. If same id in different departments then it is okay according to Leff's Piazza response on 11/23/19.
		// for (int i = 0; i <= empIdCounter; i ++) {
		// 	if (empId.equals(empIdArray[i]) ) {
		// 	// if (empId.equals(empInDeptArray[i].getId() ) ) {
		// 		passedTests = false;
		// 		throw new IllegalArgumentException ("ERROR: Problem at line # " + lineNumber + " of employee file: This employee has already been accounted for.");
		// 	}
		// }
		// empIdArray [empIdCounter] = empId;
		// empIdCounter ++;	
		// I throw Illegal Arguement exceptions instead of System.out.println per Prof. Leff's comment to me in class on December 5, 2019. 
		try {
			hoursWorked = Double.parseDouble(hoursWorkedString);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file: Could not parse Hours Worked [" + hoursWorkedString + "] into valid input");
		} try {
			wageRate = Double.parseDouble(wageRateString);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file: Could not parse Wage Rate [" + wageRateString + "] into valid input");
		} try {
			deductions = Integer.parseInt(deductionsString);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file: Could not parse Deductions [" + deductionsString + "] into valid input");
		}

		grossPay = hoursWorked * wageRate;
		double taxes = grossPay * 0.15; // 0.15 is the tax rate.
		double pension = 0.05 * grossPay;
		double netPay = grossPay - taxes - deductions - pension;

		if (hoursWorked < 1) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file. You have not worked enough hours. Come back when you have worked at least one hour.");
		}
		else if (wageRate < 15.00) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file. Your wage rate must be at least $15.00 per hour. You must input without a dollar sign ($), and please use two decimal places (ex. 15.00).");
		}
		else if (deductions <= 0) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file. Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
		}
		else if (deductions >= 35) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file. Your deductions must be greater than 0 and less than 35. Please verify your deductions and re-enter.");
		}
		else if (netPay < 0) {
			throw new IllegalArgumentException("ERROR: Problem at line # " + lineNumber + " of employee file. Your net pay is negative. Please advise.");
		}	
		else if (passedTests == false) {
		}
		else {
			empInstance = new Employee (empId , grossPay , deptName);
			for (int i = 0; i < 10; i ++) {
				if (empInstance.getDeptName().equals(deptNames[i]) ){
				if (departments[i].getNEmployees() == 100) {
					// not throwing the error becuase I don't want the stack to show as a client should not see the stack.
					System.out.println("ERROR: Too many employees entered. Discardig file and exiting program.");
					System.exit(1);
				}
				departments[i].addEmployee(empInstance);
				departments[i].getGrossPay(grossPay);
				return empInstance;
				}
			}
			throw new IllegalArgumentException ("ERROR: Problem at line # " + lineNumber + " of employee file. Employee has an unknown department.");
		}		
	return empInstance;
	}

	public static void main (final String [] args) throws NumberFormatException , NoSuchElementException {
		//Prof. Leff's code to read the file:
		Factory factoryInstance = new Factory();
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

			System.out.println("*************************************************");
			// calculating employee data:
				while (deptScanner.hasNextLine() ) {
					try {
						Department deptOutput = factoryInstance.newDepartment(deptScanner.nextLine() );
						double totalGrossPay = deptOutput.getTotalGrossPay();
						String getDeptName = deptOutput.getName();
						int getNDept = factoryInstance.getNDepartments();
						int nDepartments = factoryInstance.getNDepartments();
						int nEmployeesperDept = deptOutput.getNEmployees();
						double averageGrossPay = deptOutput.getAverageGrossPay();
						// System.out.println(getNDept);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage() );
					}	
				} 
			// calculating department data:
				while (empScanner.hasNextLine() ) {
					try {
						Employee empOutput = factoryInstance.newEmployee(empScanner.nextLine());
						String id = empOutput.getId();	
						double grossPay = empOutput.getGrossPay();
						String gettingDeptId = empOutput.getDeptName();
						// System.out.println(gettingDeptId);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} 
					
				}
			// printing out the results:
			System.out.printf("%s %20s %20s %20s %n" , "Department", "# Employees" , "Total Gross Pay" , "Average Gross Pay");
			Department [] deptArray = factoryInstance.getDepartments();
			for (int i = 0; i < deptArray.length; i ++) {
					String getDeptName = deptArray[i].getName();
					int nEmployeesperDept = deptArray[i].getNEmployees();
					double totalGrossPay = deptArray[i].getTotalGrossPay();
					double averageGrossPay = deptArray[i].getAverageGrossPay();
		
					System.out.printf("%-19s %-16d %-18.2f %-20.2f %n" , getDeptName , nEmployeesperDept , totalGrossPay , averageGrossPay);
			}
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found. " + e);
			System.exit(0);
		}
	System.out.println("*************************************************");
	}
}