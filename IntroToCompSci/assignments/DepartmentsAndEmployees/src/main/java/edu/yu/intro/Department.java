package edu.yu.intro;

/** " Department Class for Departments Assignment " Assignment #11
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
	
	private String empId;   			// constructor
	private double grossPay;			// constructor
	private String deptName;			// constructor
	private Employee empInstance = new Employee (empId , grossPay , deptName); // employee constructor
	private double totalGrossPay;		// sums up all employees gross pay per department
	private int empCounter;				// counts how many employees per department			
	private Employee [] arrayofEmp; 	// adds employees to accurate department
	private double currentGrossPay;		// takes current employees gross pay

	public Department (String deptName) {
		this.deptName = deptName;
		this.arrayofEmp = new Employee [MAX_EMPLOYEES];
		this.grossPay = grossPay;
	}

	protected Employee addEmployee (Employee emp) {
		try {
			for (int i = 0; i < arrayofEmp.length; i ++) {
				if (emp.getId().equals(arrayofEmp[i].getId() )) {
					throw new IllegalArgumentException("Duplicate employees in same department.");
				}
			}
		} catch (NullPointerException e) {}
		arrayofEmp [empCounter] = emp;
		currentGrossPay = emp.getGrossPay();
		empCounter ++;
		return emp;
	}

	protected double getGrossPay (double grossPayEmp) {
		totalGrossPay = totalGrossPay + grossPayEmp;
		return totalGrossPay;
	}

	public Employee [] getEmployees () {
		String empsDeptId = empInstance.getDeptName();

		for (int i = 0; i < MAX_EMPLOYEES; i ++) {
			if (empsDeptId.equals(this.deptName) ) {
			empInstance = arrayofEmp[i];
			}
		}
		return arrayofEmp;
	}

	public int getNEmployees () {
		return empCounter;
	}

	public String getName () {
		return this.deptName;
	}
	
	public double getTotalGrossPay () {
		return totalGrossPay;
	}

	protected double getAverageGrossPay () {
		double averageGrossPay = totalGrossPay / empCounter;
		if (empCounter==0) {
			return 0;
		}
		return averageGrossPay;
	}
}