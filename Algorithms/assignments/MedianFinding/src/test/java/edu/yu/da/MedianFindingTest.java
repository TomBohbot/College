package edu.yu.da;

/** 
 * Median Finding Test Code 
 * @author Tom Bohbot
 * @version February 3, 2021
 */

import edu.yu.da.MedianFinding;
import edu.yu.da.MedianFinding.Account;
import org.junit.*;
import static org.junit.Assert.*;

public class MedianFindingTest {

	@Test
	public void ascendingAccounts () {
        Account [] set1 = new Account [5];
        Account [] set2 = new Account [5];
        for (int i = 0; i < 5; i ++) {
            set1[i] = new Account(i+1);
            set2[i] = new Account(i+6);
        }
		assertEquals ("Ascending Accounts" , 5.0 , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
    }
    
    @Test
	public void revAscendingAccounts () {
        Account [] set1 = new Account [5];
        Account [] set2 = new Account [5];
        for (int i = 0; i < 5; i ++) {
            set2[i] = new Account(i+1);
            set1[i] = new Account(i+6);
        }
		assertEquals ("Ascending Accounts" , 5.0 , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
    }
    
    @Test
	public void oddAndEven () {
        Account [] set1 = {new Account(1.0), new Account(3.0), new Account(5.0), new Account(7.0), new Account(9.0), };
        Account [] set2 = {new Account(2.0), new Account(4.0), new Account(6.0), new Account(8.0), new Account(10.0), };
		assertEquals ("Ascending Accounts" , new Account(5.0).getIncome() , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
	}

    @Test
	public void evenAndOdd () {
        Account [] set1 = {new Account(1.0), new Account(3.0), new Account(4.2), new Account(7.0), new Account(9.0), };
        Account [] set2 = {new Account(2.0), new Account(4.0), new Account(6.0), new Account(8.0), new Account(10.0), };
		assertEquals ("Ascending Accounts" , new Account(4.2).getIncome() , MedianFinding.findMedian(set2, set1).getIncome(), 0.01);
	}

    @Test
	public void oddAndEvenPlusOne () {
        Account [] set1 = {new Account(1.0), new Account(3.0), new Account(5.0), new Account(7.0), new Account(9.0), new Account(11.0) };
        Account [] set2 = {new Account(2.0), new Account(4.0), new Account(6.0), new Account(8.0), new Account(10.0),new Account(12.0)};
		assertEquals ("Ascending Accounts" , new Account(6.0).getIncome() , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
	}

    @Test
	public void evenAndOddPlusOne () {
        Account [] set1 = {new Account(1.0), new Account(3.0), new Account(4.2), new Account(7.0), new Account(9.0) };
        Account [] set2 = {new Account(2.0), new Account(4.0), new Account(6.0), new Account(8.0), new Account(10.0) };
		assertEquals ("Ascending Accounts" , new Account(4.2).getIncome() , MedianFinding.findMedian(set2, set1).getIncome(), 0.01);
	}

    @Test
	public void swallows () {
        Account [] set1 = {new Account(2.0), new Account(3.0), new Account(5.0), new Account(7.0), new Account(9.0)};
        Account [] set2 = {new Account(1.0), new Account(100.0), new Account(200.0), new Account(300.0), new Account(400.0)};
		assertEquals ("Ascending Accounts" , new Account(7.0).getIncome() , MedianFinding.findMedian(set2, set1).getIncome(), 0.01);
	}

    @Test
	public void revSwallows () {
        Account [] set1 = {new Account(2.0), new Account(3.0), new Account(5.0), new Account(7.0), new Account(9.0) };
        Account [] set2 = {new Account(1.0), new Account(100.0), new Account(200.0), new Account(300.0), new Account(400.0) };
		assertEquals ("Ascending Accounts" , new Account(7.0).getIncome() , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
	}

    @Test
	public void oddAndEvenLong () {
        Account [] set1 = {new Account(1.0), new Account(3.0), new Account(5.0), new Account(7.0), new Account(9.0), new Account(11.0), new Account(13.0), new Account(15.0), new Account(17.0), new Account(19.0), new Account(21.0), new Account(23.0), new Account(25.0), new Account(27.0)};
        Account [] set2 = {new Account(2.0), new Account(4.0), new Account(6.0), new Account(8.0), new Account(10.0),new Account(12.0), new Account(14.0), new Account(16.0), new Account(18.0), new Account(20.0), new Account(22.0), new Account(24.0), new Account(26.0), new Account(28.0)};
		assertEquals ("Ascending Accounts" , new Account(14.0).getIncome() , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
	}

    @Test
	public void oddAndEvenLongPlusOne () {
        Account [] set1 = {new Account(1.0), new Account(3.0), new Account(5.0), new Account(7.0), new Account(9.0), new Account(11.0), new Account(13.0), new Account(15.0), new Account(17.0), new Account(19.0), new Account(21.0), new Account(23.0), new Account(25.0), new Account(27.0), new Account(29.0)};
        Account [] set2 = {new Account(2.0), new Account(4.0), new Account(6.0), new Account(8.0), new Account(10.0),new Account(12.0), new Account(14.0), new Account(16.0), new Account(18.0), new Account(20.0), new Account(22.0), new Account(24.0), new Account(26.0), new Account(28.0), new Account(30.0)};
		assertEquals ("Ascending Accounts" , new Account(15.0).getIncome() , MedianFinding.findMedian(set1, set2).getIncome(), 0.01);
	}

    @Test
	public void swallows2() {
        Account [] set1 = {new Account(2.0), new Account(3.0), new Account(250.0), new Account(270.0), new Account(500.2) };
        Account [] set2 = {new Account(1.0), new Account(100.0), new Account(200.0), new Account(300.0), new Account(400.0) };
		assertEquals ("Ascending Accounts" , new Account(200.0).getIncome() , MedianFinding.findMedian(set2, set1).getIncome(), 0.01);
	}

    @Test
	public void swallows2PlusOne() {
        Account [] set1 = {new Account(2.0), new Account(3.0), new Account(250.0), new Account(270.0), new Account(500.2), new Account(520.2) };
        Account [] set2 = {new Account(1.0), new Account(100.0), new Account(200.0), new Account(300.0), new Account(400.0), new Account(600.2)};
		assertEquals ("Ascending Accounts" , new Account(250.0).getIncome() , MedianFinding.findMedian(set2, set1).getIncome(), 0.01);
	}
}