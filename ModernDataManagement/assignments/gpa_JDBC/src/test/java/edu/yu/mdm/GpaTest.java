// package edu.yu.mdm;

// /**
//  * Test Code
//  * @author Tom Bohbot
//  */

// import edu.yu.mdm.gpaJDBC;
// import edu.yu.mdm.gpaJDBC.GPARecord;
// import java.util.*;
// import org.junit.*;
// import static org.junit.Assert.*;

// public class GpaTest {

//     gpaJDBC obj = new gpaJDBC("jdbc:postgresql://localhost/", "final", "tombohbot", "");

//     @Test
//     public void studentOne() {
//         assertEquals("00128 current gpa", 3.87, obj.gpa("00128"), 0.0001);
//         assertEquals("00128 credits earned", 7, obj.totalCredits("00128"));        
//     }

//     @Test
//     public void studentTwo() {
//         assertEquals("12345 current gpa", 3.43, obj.gpa("12345"), 0.0001);
//         assertEquals("12345 credits earned", 14, obj.totalCredits("12345"));        
//     }

//     @Test
//     public void studentThree() {
//         assertEquals("19991 current gpa", 3.0, obj.gpa("19991"), 0.0001);
//         assertEquals("19991 credits earned", 3, obj.totalCredits("19991"));        
//     }

//     @Test
//     public void studentFour() {
//         assertEquals("23121 current gpa", 2.3, obj.gpa("23121"), 0.0001);
//         assertEquals("23121 credits earned", 3, obj.totalCredits("23121"));        
//     }

//     @Test
//     public void studentFive() {
//         assertEquals("44553 current gpa", 2.7, obj.gpa("44553"), 0.0001);
//         assertEquals("44553 credits earned", 4, obj.totalCredits("44553"));        
//     }

//     @Test
//     public void studentSix() {
//         assertEquals("45678 current gpa", 3.17, obj.gpa("45678"), 0.0001);
//         assertEquals("45678 credits earned", 7, obj.totalCredits("45678"));
//     }

//     @Test
//     public void studentSeven() {
//         assertEquals("54321 current gpa", 3.5, obj.gpa("54321"), 0.0001);
//         assertEquals("54321 credits earned", 8, obj.totalCredits("54321"));
//     }

//     @Test
//     public void studentEight() {
//         assertEquals("55739 current gpa", 3.7, obj.gpa("55739"), 0.0001);
//         assertEquals("55739 credits earned", 3, obj.totalCredits("55739"));
//     }

//     @Test
//     public void studentNineNoClassesTaken() {
//         assertEquals("70557 current gpa", 0.0, obj.gpa("70557"), 0.0001);
//         assertEquals("70557 credits earned", 0, obj.totalCredits("70557"));
//     }

//     @Test
//     public void studentTen() {
//         assertEquals("76543 current gpa", 4.0, obj.gpa("76543"), 0.0001);
//         assertEquals("76543 credits earned", 7, obj.totalCredits("76543"));
//     }

//     @Test
//     public void studentEleven() {
//         assertEquals("76653 current gpa", 2.0, obj.gpa("76653"), 0.0001);
//         assertEquals("76653 credits earned", 3, obj.totalCredits("76653"));
//     }

//     @Test
//     public void studentTwelve() {
//         assertEquals("98765 current gpa", 2.26, obj.gpa("98765"), 0.0001);
//         assertEquals("98765 credits earned", 7, obj.totalCredits("98765"));
//     }

//     @Test
//     public void studentThirteen() {
//         assertEquals("98988 current gpa", 4.0, obj.gpa("98988"), 0.0001);
//         assertEquals("98988 credits earned", 4, obj.totalCredits("98988"));
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void fakeStudentCredits() {
//         obj.totalCredits("11");
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void fakeStudentGPA() {
//         obj.gpa("1");
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void fakeStudentGPATwo() {
//         obj.gpa("a");
//     }

//     @Test
//     public void compareTo() {
//         GPARecord a = new GPARecord("123", 4.0);
//         GPARecord b = new GPARecord("1122", 4.0);
//         GPARecord c = new GPARecord("123", 1.0);
//         GPARecord d = new GPARecord("1122", 1.0);
//         assertEquals("ensuring compare to works", 1, a.compareTo(b) );
//         assertEquals("ensuring compare to works", -1, b.compareTo(a) );
//         assertEquals("ensuring compare to works", 0, b.compareTo(d) );
//         assertEquals("ensuring compare to works", 0, c.compareTo(a) );
//         GPARecord as = new GPARecord("taul", 4.0);
//         GPARecord bs = new GPARecord("tom", 4.0);
//         GPARecord cs = new GPARecord("taul", 1.0);
//         GPARecord ds = new GPARecord("tom", 1.0);
//         assertEquals("ensuring compare to works", -14, as.compareTo(bs) );
//         assertEquals("ensuring compare to works", 14, bs.compareTo(as) );
//         assertEquals("ensuring compare to works", 0, bs.compareTo(ds) );
//         assertEquals("ensuring compare to works", 0, cs.compareTo(as) );
//     }

//     // muting this test in case he has it diff. dont want to have array index out of bounds for him.
//     // @Test 
//     // public void listOfGPAs() {
//     //     List <GPARecord> list = obj.studentGPAs();
//     //     assertEquals("student 1 ID", "00128", list.get(0).studentId);
//     //     assertEquals("student 1 GPA", 3.87, list.get(0).gpa, 0.0001);
//     //     assertEquals("student 2 ID", "12345", list.get(1).studentId);
//     //     assertEquals("student 2 GPA", 3.43, list.get(1).gpa, 0.0001);
//     //     assertEquals("student 3 ID", "19991", list.get(2).studentId);
//     //     assertEquals("student 3 GPA", 3.00, list.get(2).gpa, 0.0001);
//     //     assertEquals("student 4 ID", "23121", list.get(3).studentId);
//     //     assertEquals("student 4 GPA", 2.30, list.get(3).gpa, 0.0001);
//     //     assertEquals("student 5 ID", "44553", list.get(4).studentId);
//     //     assertEquals("student 5 GPA", 2.7, list.get(4).gpa, 0.0001);
//     //     assertEquals("student 6 ID", "45678", list.get(5).studentId);
//     //     assertEquals("student 6 GPA", 3.17, list.get(5).gpa, 0.0001);
//     //     assertEquals("student 7 ID", "54321", list.get(6).studentId);
//     //     assertEquals("student 7 GPA", 3.50, list.get(6).gpa, 0.0001);
//     //     assertEquals("student 8 ID", "55739", list.get(7).studentId);
//     //     assertEquals("student 8 GPA", 3.7, list.get(7).gpa, 0.0001);
//     //     assertEquals("student 10 ID", "76543", list.get(8).studentId);
//     //     assertEquals("student 10 GPA", 4.00, list.get(8).gpa, 0.0001);
//     //     assertEquals("student 11 ID", "76653", list.get(9).studentId);
//     //     assertEquals("student 11 GPA", 2.00, list.get(9).gpa, 0.0001);
//     //     assertEquals("student 12 ID", "98765", list.get(10).studentId);
//     //     assertEquals("student 12 GPA", 2.26, list.get(10).gpa, 0.0001);
//     //     assertEquals("student 13 ID", "98988", list.get(11).studentId);
//     //     assertEquals("student 13 GPA", 4.00, list.get(11).gpa, 0.0001);
//     // }
// }
