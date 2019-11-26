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



public class Department {
	public final static int MAX_EMPLOYEES = 100;
	
	private String empId;
	private double grossPay;
	private String deptName;
	private double totalGrossPay;
	private int empCounter;

	Employee empInstance = new Employee (empId , grossPay , deptName);

	public Department (String deptName) {
		this.deptName = deptName;
	}

	// public Employee [] getEmployees () {
	// 	String empsDeptId = empInstance.getDeptName();


	// 	empCounter ++;
	// 	return something;
	// }

	public int getNEmployees () {
		return empCounter;
	}

	public String getName () {
		return deptName;
	}
	
	public double getTotalGrossPay () {
		double totalGrossPay = empInstance.getTotalGrossPay();
		return totalGrossPay;
	}


}