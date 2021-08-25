package edu.yu.mdm;

/** 
 * @author Tom Bohbot - implementation
 * @author Dr. Leff   - skeleton code
 * @version May 7, 2021
 */

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gpaJDBC {

    private String databaseURL;
    private String dbName;
    private String userName;
    private String password;
    private Connection c = null;

    private void connectToServer() {
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection(databaseURL+dbName,userName, password);
           c.setAutoCommit(false);
        //    c.close();
        } catch (Exception e) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

  // A value class used by the studentGPAs() method
  public static class GPARecord implements Comparable<GPARecord> {
    /** Constructor: you may not change the signature or semantics of this
     * method.
     *
     * @param String studentId
     * @param double gpa
     */
    public GPARecord(final String studentId, final double gpa) {
      assert studentId != null : "studentId can't be null";
      assert studentId.length() > 0 : "studentId can't be empty: "+studentId;
      assert gpa >= 0.0 : "gpa must be non-negative: "+gpa;

      this.studentId = studentId;
      this.gpa = gpa;
    }

    @Override 
    public boolean equals (Object that) {
      if (this == that) {
        return true;
      }
      if (that == null) {
        return false;
      }
      if (this.getClass() != that.getClass() ) {
        return false;
      }
      GPARecord thatGpaRecord = (GPARecord) that;
      if (thatGpaRecord.studentId != this.studentId ) {
        return false;
      }
      return true;
    }

    @Override 
    public int hashCode () {
        return studentId.hashCode() + (int) gpa;
    }

    @Override 
    public String toString () {
        return "{" + this.studentId + ", " + this.gpa + "}";
    }

    @Override
    public int compareTo(final GPARecord b) {
        return this.studentId.compareTo(b.studentId);
    }

    // safe to expose because immutable
    public final String studentId;
    public final double gpa;
  }


    /**
     * Constructor specifying the JDBC "connection parameters" to use when
     * subsequentally invoking operations that require connecting to the
     * database.
     *
     * @param databaseURL the base JDBC url, does NOT contain either the database
     * name, the user name, or the password.  Example:
     * "jdbc:postgresql://localhost/"
     * @param dbName specifies the database to connect to
     * @param userName the user name credentials to use when connecting
     * @param password the password to use when connecting
     *
     * @see https://jdbc.postgresql.org/documentation/head/connect.html
     */
    public gpaJDBC(final String databaseURL, final String dbName, final String userName, final String password) {
        this.databaseURL = databaseURL;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        connectToServer();
    }

    /** 
     * Return a List of GPARecord, ordered in lexicographical order, by
     * ascending student id for all students who have taken a course.  GPAs
     * should be reported with a precision of 2 decimal points.  If a student
     * doesn't have a gpa because she hasn't taken courses or because the grade
     * earned on a course doesn't meet the criteria, the student should not be
     * included in the returned result.
     *
     * @return List of GPARecord per the above semantics.
     * @see GPARecord
     */
    public List<GPARecord> studentGPAs() {
        ArrayList <GPARecord> list = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT T.ID, " +
                                            "ROUND " +
                                            "(SUM " +
                                            "(CASE " + 
                                            "WHEN T.grade = 'A' THEN 4.0 * C.credits " +
                                            "WHEN T.grade = 'A-'THEN 3.7 * C.credits " +
                                            "WHEN T.grade = 'B+' THEN 3.3 * C.credits " +
                                            "WHEN T.grade = 'B' THEN 3.0 * C.credits " +
                                            "WHEN T.grade = 'B-' THEN 2.7 * C.credits " +
                                            "WHEN T.grade = 'C+' THEN 2.3 * C.credits " +
                                            "WHEN T.grade = 'C' THEN 2.0 * C.credits " +
                                            "WHEN T.grade = 'C-' THEN 1.7 * C.credits " +
                                            "WHEN T.grade = 'D+' THEN 1.3 * C.credits " +
                                            "WHEN T.grade = 'D' THEN 1.0 * C.credits " +
                                            "ELSE  NULL " +
                                            "END " +
                                            ") " +
                                            "/ SUM(C.credits) , 2) " +
                                            "AS GPA " +
                                            "FROM takes_s AS T, course_s AS C " +
                                            "WHERE C.course_id = T.course_id AND T.grade IN ('A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D') " +
                                            "GROUP BY T.ID " + 
                                            "ORDER BY T.ID " +
                                            ";");
            while ( rs.next() ) {
                GPARecord gpaRecord = new GPARecord(rs.getString("id"), rs.getDouble("gpa") );
                list.add(gpaRecord);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        Collections.sort(list); // need to sort b/c it is lexographical not numerical sorting.
        return list;
    }

  /** 
   * Returns the total number of credits points earned by that student across
   * all courses taken by the student
   *
   * @param ID the student's id
   * @return total grade points (not GPA) earned by that student across all
   * courses taken by that student.  If the student didn't take any courses,
   * return 0.  A grade that is less than a "D" doesn't contribute to the total
   * grade points.
   * @throws IllegalArgumentException if no student with that ID exists.
   */
    public int totalCredits(final String studentId) {
        if (doesStudentExist(studentId) == false) {
            throw new IllegalArgumentException("student does not exist.");
        }
        Statement stmt = null;
        int credits = 0;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT T.ID, SUM(C.credits) " +
                                            "FROM takes_s AS T, course_s AS C " +
                                            "WHERE T.ID = '"+studentId+"' AND C.course_id = T.course_id AND T.grade IN ('A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D') " +
                                            "GROUP BY T.ID;");
            while ( rs.next() ) {
                credits = rs.getInt("sum");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return credits;
    } 

    /** 
     * Returns the student's GPA.
     *
     * @param ID the student's id
     * @return The grade point average earned by that student across all courses
     * taken by that student.  GPAs must be calculated with a precision of 2
     * decimal points.  If the student didn't take any courses, return 0.0
     * @throws IllegalArgumentException if no student with that ID exists.
     */
    public double gpa (final String studentId) {
        if (doesStudentExist(studentId) == false) {
            throw new IllegalArgumentException("student does not exist.");
        }
        double gpa = 0.0;
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT T.ID, " +
                                            "ROUND " +
                                            "(SUM " +
                                            "(CASE " + 
                                            "WHEN T.grade = 'A' THEN 4.0 * C.credits " +
                                            "WHEN T.grade = 'A-'THEN 3.7 * C.credits " +
                                            "WHEN T.grade = 'B+' THEN 3.3 * C.credits " +
                                            "WHEN T.grade = 'B' THEN 3.0 * C.credits " +
                                            "WHEN T.grade = 'B-' THEN 2.7 * C.credits " +
                                            "WHEN T.grade = 'C+' THEN 2.3 * C.credits " +
                                            "WHEN T.grade = 'C' THEN 2.0 * C.credits " +
                                            "WHEN T.grade = 'C-' THEN 1.7 * C.credits " +
                                            "WHEN T.grade = 'D+' THEN 1.3 * C.credits " +
                                            "WHEN T.grade = 'D' THEN 1.0 * C.credits " +
                                            "ELSE  NULL " +
                                            "END " +
                                            ") " +
                                            "/ SUM(C.credits) , 2) " +
                                            "AS GPA " +
                                            "FROM takes_s AS T, course_s AS C " +
                                            "WHERE T.ID = '"+ studentId + "' AND C.course_id = T.course_id AND T.grade IN ('A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D') " +
                                            "GROUP BY T.ID " + 
                                            ";");
            while ( rs.next() ) {
                gpa = rs.getDouble("gpa");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return gpa;
    }

    private boolean doesStudentExist (String studentId) {
        boolean studentExists = false;
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT S.ID  " +
                                            "FROM student_s as S " +
                                            "WHERE S.ID = '"+studentId+"';");
            while ( rs.next() ) {
                studentExists = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return studentExists;
    }
} 