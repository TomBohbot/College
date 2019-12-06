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

	// Factory factoryInstance = new Factory();
	// Department deptInstance = new Department("Dept1");
	// Employee empInstance = new Employee("Tom" , 100 , "Dept1");

// Test the employee file.
	@Test
	public void employeeHappyPath() {
		Factory factoryInstance = new Factory();
		Department deptInstance = new Department("Dept1");
		Employee empInstance = new Employee("Tom" , 100 , "Dept1");

		assertTrue ("testing id of employee." , "Tom".equals(empInstance.getId() ) );
		assertEquals ("testing gross pay of employee" , 100.0, empInstance.getGrossPay() , 0.01 );
	}

	// @Test (expected = IllegalArgumentException.class)
	// public void employeeSadPath() {
	// 	Factory factoryInstance = new Factory();
	// 	Department deptInstance = new Department("Dept1");
	// 	// Employee empInstance = new Employee("Tom" , "tom" , "Dept1");

	// 	assertTrue ("testing id of employee." , "Tom".equals(empInstance.getId() ) );
	// 	assertEquals ("testing gross pay of employee" , 100.0, empInstance.getGrossPay() , 0.01 );		
	// }

// Test the department file. 
	// @Test
	// public void employeeHappyPath() {
	// 	Factory factoryInstance = new Factory();
	// 	Department deptInstance = new Department("Dept1");
	// 	Employee empInstance = new Employee("Tom" , 100 , "Dept1");
	// 	Employee empInstance2 = new Employee("Harry" , 100 , "Dept1");

	// 	assertEquals ("testing total gross pay of departments." ,  ,);
	// 	assertEquals ("testing gross pay of employee" , 100.0, empInstance.getGrossPay() , 0.01 );
	// }

// Test the Factory file. 
	// @Test
	// public void newEmployeeHappyPath() {
	// 	Factory factoryInstance = new Factory();
	// 	Department deptInstance = new Department("Dept1");
	// 	Employee empInstance = new Employee("Tom" , 100 , "Dept1");

	// 	assertTrue ("testing id of employee." , "Tom".equals(empInstance.getId() ) );
	// 	assertEquals ("testing gross pay of employee" , 100.0, empInstance.getGrossPay() , 0.01 );
	// }
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

		Employee emp1 = factoryInstance.newEmployee("Harry , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , 15 , 25, 8 , Dept1 , 2");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpDuplicatePath () {
		Factory factoryInstance = new Factory();

		Employee emp1 = factoryInstance.newEmployee("Tom , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , 15 , 25, 8 , Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpParseError1 () {
		Factory factoryInstance = new Factory();

		Employee emp1 = factoryInstance.newEmployee("Harry , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , Yaakov , 25, 8 , Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpParseError2 () {
		Factory factoryInstance = new Factory();

		Employee emp1 = factoryInstance.newEmployee("Harry , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , 15 , Yaakov, 8 , Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpParseError3 () {
		Factory factoryInstance = new Factory();

		Employee emp1 = factoryInstance.newEmployee("Harry , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , 15 , 25, Yaakov , Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpHoursWorkedError () {
		Factory factoryInstance = new Factory();

		Employee emp1 = factoryInstance.newEmployee("Harry , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , 0 , 25, 8 , Dept1");
	}

	@Test (expected = IllegalArgumentException.class) 
	public void newEmpWageRateError () {
		Factory factoryInstance = new Factory();

		Employee emp1 = factoryInstance.newEmployee("Harry , 15 , 25, 8 , Dept1");
		Employee emp2 = factoryInstance.newEmployee("Leff , 15 , 25, 8 , Dept1");	
		Employee emp3 = factoryInstance.newEmployee("Tom , 15 , 15 , 8 , Dept1");
	}


	// @Test
	// public void newDeptHappyPath1 () {
	// 	Factory factoryInstance = new Factory();
	// 	Department deptInstance = new Department("Dept1");
	// 	Employee empInstance = new Employee("Tom" , 100 , "Dept1");

	// 	Department dept1 = factoryInstance.newDepartment("Dept1");
	// 	Department dept2 = factoryInstance.newDepartment("Dept2");
	// 	Department dept3 = factoryInstance.newDepartment("Dept3");
	// 	Department dept4 = factoryInstance.newDepartment("Dept4");
	// 	Department dept5 = factoryInstance.newDepartment("Dept5");
	// 	Employee empInstance1  = new Employee("1 " , 100 , "Dept1");
	// 	Employee empInstance2  = new Employee("2 " , 100 , "Dept2");
	// 	Employee empInstance3  = new Employee("3 " , 100 , "Dept1");
	// 	Employee empInstance4  = new Employee("4 " , 100 , "Dept3");
	// 	Employee empInstance5  = new Employee("5 " , 100 , "Dept1");
	// 	Employee empInstance6  = new Employee("6 " , 100 , "Dept4");
	// 	Employee empInstance7  = new Employee("7 " , 100 , "Dept1");
	// 	Employee empInstance8  = new Employee("8 " , 100 , "Dept5");
	// 	Employee empInstance9  = new Employee("9 " , 100 , "Dept1");
	// 	Employee empInstance10 = new Employee("10" , 100 , "Dept6");

	// 	Department [] deptArray = factoryInstance.getDepartments();

	// 	assertEquals("Testing Get N Departments." , 5 , factoryInstance.getNDepartments() );
	// 	assertEquals("Testing Get N Employees in dept." , 5 , deptArray[1].getNEmployees() );
	// 	// assertEquals("Testing Get N Departments Happy Path." , 5 , factoryInstance.getNDepartments() );
	// }
}