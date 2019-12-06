package edu.yu.intro.test;

/** " FindingRationalNumbersTest " Assignment #8
*
* @author Tom Bohbot
*/
import java.util.Scanner;
import edu.yu.intro.Factory;
import edu.yu.intro.Department;
import edu.yu.intro.Employee;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DepartmentsTest {

// Test the employee file.
	@Test
	public void employeeHappyPath() {
		Factory factoryInstance = new Factory();
		Department deptInstance = new Department("Dept1");
		Employee empInstance = new Employee("Tom" , 100 , "Dept1");

		assertTrue ("testing id of employee." , "Tom".equals(empInstance.getId() ) );
		assertEquals ("testing gross pay of employee" , 100.0, empInstance.getGrossPay() , 0.01 );
	}

	@Test
	public void newDeptHappyPath () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");
		Department dept2 = factoryInstance.newDepartment("Dept2");
		Department dept3 = factoryInstance.newDepartment("Dept3");
		Department dept4 = factoryInstance.newDepartment("Dept4");
		Department dept5 = factoryInstance.newDepartment("Dept5");
		assertEquals("Testing Get N Departments." , 5 , factoryInstance.getNDepartments() );
		// assertEquals("Testing Get N Departments Happy Path." , 5 , factoryInstance.getNDepartments() );
		// assertEquals("Testing Get N Departments Happy Path." , 5 , factoryInstance.getNDepartments() );
	}
	@Test (expected = IllegalArgumentException.class)
	public void newDeptDuplicatePath () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");
		Department dept2 = factoryInstance.newDepartment("Dept2");
		Department dept1Copy = factoryInstance.newDepartment("Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newDeptTooManyTokensPath () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");
		Department dept2 = factoryInstance.newDepartment("Dept2");
		Department dept3 = factoryInstance.newDepartment("Dept3");
		Department dept4 = factoryInstance.newDepartment("Dept4");
		Department dept1Copy = factoryInstance.newDepartment("Dept1 4");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpTooManyTokensPath () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 25 8 Dept1 2");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpDuplicate () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Tom 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 25 8 Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpParseError1 () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom Yaakov 25 8 Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpParseError2 () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 Yaakov 8 Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpParseError3 () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 25 Yaakov Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpHoursWorkedError () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 0 25 8 Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpWageRateError () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 14 8 Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpDeductionsError () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 15 35 Dept1");
	}


	@Test (expected = IllegalArgumentException.class) 
	public void newEmpDeductionsError2 () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 15 0 Dept1");
	}


	@Test (expected = IllegalArgumentException.class) 
	public void newEmpUnknownDept () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept2");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 8 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 8 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 15 18 Dept1");
	}

	@Test 
	public void getNEmployees () {
		Factory factoryInstance = new Factory();
		Department dept1 = factoryInstance.newDepartment("Dept1");
		Department dept2 = factoryInstance.newDepartment("Dept2");

		Employee emp1 = factoryInstance.newEmployee("Harry 15 25 18 Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff 15 25 18 Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom 15 15 18 Dept1");
		Employee emp4 = factoryInstance.newEmployee("David 15 15 18 Dept2");

		assertEquals("Testing GetNDepts for Dept1" , 3 , dept1.getNEmployees() );
		assertEquals("Testing GetDeptName for Dept1" , "Dept1" , dept1.getName() );
		assertEquals("Testing GetTotalGrossPay for Dept1" , 975.0 , dept1.getTotalGrossPay() , 0 );
	}

}