package edu.yu.intro;

/** " Employee for Departments Assignment " Assignment #11
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

public class Employee {
	
	private String empId;
	private double grossPay;
	private String deptName;
	private double totalGrossPay;

	public Employee (String empId , double grossPay , String deptName) {
		this.empId = empId;
		this.grossPay = grossPay;
		this.deptName = deptName;
	}

	public String getId () {
		return empId;
	}

	public double getGrossPay () {
		totalGrossPay = totalGrossPay + grossPay;
		return grossPay;
	}

	protected String getDeptName () {
		return deptName;
	}
}
