package edu.yu.da;

/** 
 * Median Finding Test Code 
 * @author Tom Bohbot
 * @version February 3, 2021
 */

import edu.yu.da.DPMakingChange;
import org.junit.*;
import static org.junit.Assert.*;

public class TestDPMakingChange {

	@Test
	public void givenTest () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 15;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);
        assertEquals ("standard test" , 0, change[0][4]);
        assertEquals ("standard test" , 0, change[0][5]);
        assertEquals ("standard test" , 0, change[0][6]);
        assertEquals ("standard test" , 0, change[0][7]);
        assertEquals ("standard test" , 0, change[0][8]);
        assertEquals ("standard test" , 0, change[0][9]);
        assertEquals ("standard test" , 0, change[0][10]);
        assertEquals ("standard test" , 0, change[0][11]);
        assertEquals ("standard test" , 0, change[0][12]);
        assertEquals ("standard test" , 0, change[0][13]);
        assertEquals ("standard test" , 0, change[0][14]);
        assertEquals ("standard test" , 0, change[0][15]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 1, change[1][1]);
        assertEquals ("standard test" , 2, change[1][2]);
        assertEquals ("standard test" , 3, change[1][3]);
        assertEquals ("standard test" , 4, change[1][4]);
        assertEquals ("standard test" , 5, change[1][5]);
        assertEquals ("standard test" , 6, change[1][6]);
        assertEquals ("standard test" , 7, change[1][7]);
        assertEquals ("standard test" , 8, change[1][8]);
        assertEquals ("standard test" , 9, change[1][9]);
        assertEquals ("standard test" , 10, change[1][10]);
        assertEquals ("standard test" , 11, change[1][11]);
        assertEquals ("standard test" , 12, change[1][12]);
        assertEquals ("standard test" , 13, change[1][13]);
        assertEquals ("standard test" , 14, change[1][14]);
        assertEquals ("standard test" , 15, change[1][15]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 1, change[2][1]);
        assertEquals ("standard test" , 2, change[2][2]);
        assertEquals ("standard test" , 3, change[2][3]);
        assertEquals ("standard test" , 4, change[2][4]);
        assertEquals ("standard test" , 5, change[2][5]);
        assertEquals ("standard test" , 6, change[2][6]);
        assertEquals ("standard test" , 1, change[2][7]);
        assertEquals ("standard test" , 2, change[2][8]);
        assertEquals ("standard test" , 3, change[2][9]);
        assertEquals ("standard test" , 4, change[2][10]);
        assertEquals ("standard test" , 5, change[2][11]);
        assertEquals ("standard test" , 6, change[2][12]);
        assertEquals ("standard test" , 7, change[2][13]);
        assertEquals ("standard test" , 2, change[2][14]);
        assertEquals ("standard test" , 3, change[2][15]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 1, change[3][1]);
        assertEquals ("standard test" , 2, change[3][2]);
        assertEquals ("standard test" , 3, change[3][3]);
        assertEquals ("standard test" , 4, change[3][4]);
        assertEquals ("standard test" , 5, change[3][5]);
        assertEquals ("standard test" , 6, change[3][6]);
        assertEquals ("standard test" , 1, change[3][7]);
        assertEquals ("standard test" , 2, change[3][8]);
        assertEquals ("standard test" , 3, change[3][9]);
        assertEquals ("standard test" , 1, change[3][10]);
        assertEquals ("standard test" , 2, change[3][11]);
        assertEquals ("standard test" , 3, change[3][12]);
        assertEquals ("standard test" , 4, change[3][13]);
        assertEquals ("standard test" , 2, change[3][14]);
        assertEquals ("standard test" , 3, change[3][15]);
    }

	@Test
	public void givenTestExtended () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 20;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);
        assertEquals ("standard test" , 0, change[0][4]);
        assertEquals ("standard test" , 0, change[0][5]);
        assertEquals ("standard test" , 0, change[0][6]);
        assertEquals ("standard test" , 0, change[0][7]);
        assertEquals ("standard test" , 0, change[0][8]);
        assertEquals ("standard test" , 0, change[0][9]);
        assertEquals ("standard test" , 0, change[0][10]);
        assertEquals ("standard test" , 0, change[0][11]);
        assertEquals ("standard test" , 0, change[0][12]);
        assertEquals ("standard test" , 0, change[0][13]);
        assertEquals ("standard test" , 0, change[0][14]);
        assertEquals ("standard test" , 0, change[0][15]);
        assertEquals ("standard test" , 0, change[0][16]);
        assertEquals ("standard test" , 0, change[0][17]);
        assertEquals ("standard test" , 0, change[0][18]);
        assertEquals ("standard test" , 0, change[0][19]);
        assertEquals ("standard test" , 0, change[0][20]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 1, change[1][1]);
        assertEquals ("standard test" , 2, change[1][2]);
        assertEquals ("standard test" , 3, change[1][3]);
        assertEquals ("standard test" , 4, change[1][4]);
        assertEquals ("standard test" , 5, change[1][5]);
        assertEquals ("standard test" , 6, change[1][6]);
        assertEquals ("standard test" , 7, change[1][7]);
        assertEquals ("standard test" , 8, change[1][8]);
        assertEquals ("standard test" , 9, change[1][9]);
        assertEquals ("standard test" , 10, change[1][10]);
        assertEquals ("standard test" , 11, change[1][11]);
        assertEquals ("standard test" , 12, change[1][12]);
        assertEquals ("standard test" , 13, change[1][13]);
        assertEquals ("standard test" , 14, change[1][14]);
        assertEquals ("standard test" , 15, change[1][15]);
        assertEquals ("standard test" , 16, change[1][16]);
        assertEquals ("standard test" , 17, change[1][17]);
        assertEquals ("standard test" , 18, change[1][18]);
        assertEquals ("standard test" , 19, change[1][19]);
        assertEquals ("standard test" , 20, change[1][20]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 1, change[2][1]);
        assertEquals ("standard test" , 2, change[2][2]);
        assertEquals ("standard test" , 3, change[2][3]);
        assertEquals ("standard test" , 4, change[2][4]);
        assertEquals ("standard test" , 5, change[2][5]);
        assertEquals ("standard test" , 6, change[2][6]);
        assertEquals ("standard test" , 1, change[2][7]);
        assertEquals ("standard test" , 2, change[2][8]);
        assertEquals ("standard test" , 3, change[2][9]);
        assertEquals ("standard test" , 4, change[2][10]);
        assertEquals ("standard test" , 5, change[2][11]);
        assertEquals ("standard test" , 6, change[2][12]);
        assertEquals ("standard test" , 7, change[2][13]);
        assertEquals ("standard test" , 2, change[2][14]);
        assertEquals ("standard test" , 3, change[2][15]);
        assertEquals ("standard test" , 4, change[2][16]);
        assertEquals ("standard test" , 5, change[2][17]);
        assertEquals ("standard test" , 6, change[2][18]);
        assertEquals ("standard test" , 7, change[2][19]);
        assertEquals ("standard test" , 8, change[2][20]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 1, change[3][1]);
        assertEquals ("standard test" , 2, change[3][2]);
        assertEquals ("standard test" , 3, change[3][3]);
        assertEquals ("standard test" , 4, change[3][4]);
        assertEquals ("standard test" , 5, change[3][5]);
        assertEquals ("standard test" , 6, change[3][6]);
        assertEquals ("standard test" , 1, change[3][7]);
        assertEquals ("standard test" , 2, change[3][8]);
        assertEquals ("standard test" , 3, change[3][9]);
        assertEquals ("standard test" , 1, change[3][10]);
        assertEquals ("standard test" , 2, change[3][11]);
        assertEquals ("standard test" , 3, change[3][12]);
        assertEquals ("standard test" , 4, change[3][13]);
        assertEquals ("standard test" , 2, change[3][14]);
        assertEquals ("standard test" , 3, change[3][15]);
        assertEquals ("standard test" , 4, change[3][16]);
        assertEquals ("standard test" , 2, change[3][17]);
        assertEquals ("standard test" , 3, change[3][18]);
        assertEquals ("standard test" , 4, change[3][19]);
        assertEquals ("standard test" , 2, change[3][20]);
    }

    @Test
	public void newInOrderTest () {
        int [] denominations = new int []{0, 2, 7, 10};
        int n = 15;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);
        assertEquals ("standard test" , 0, change[0][4]);
        assertEquals ("standard test" , 0, change[0][5]);
        assertEquals ("standard test" , 0, change[0][6]);
        assertEquals ("standard test" , 0, change[0][7]);
        assertEquals ("standard test" , 0, change[0][8]);
        assertEquals ("standard test" , 0, change[0][9]);
        assertEquals ("standard test" , 0, change[0][10]);
        assertEquals ("standard test" , 0, change[0][11]);
        assertEquals ("standard test" , 0, change[0][12]);
        assertEquals ("standard test" , 0, change[0][13]);
        assertEquals ("standard test" , 0, change[0][14]);
        assertEquals ("standard test" , 0, change[0][15]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 100000, change[1][1]);
        assertEquals ("standard test" , 1, change[1][2]);
        assertEquals ("standard test" , 100000, change[1][3]);
        assertEquals ("standard test" , 2, change[1][4]);
        assertEquals ("standard test" , 100000, change[1][5]);
        assertEquals ("standard test" , 3, change[1][6]);
        assertEquals ("standard test" , 100000, change[1][7]);
        assertEquals ("standard test" , 4, change[1][8]);
        assertEquals ("standard test" , 100000, change[1][9]);
        assertEquals ("standard test" , 5, change[1][10]);
        assertEquals ("standard test" , 100000, change[1][11]);
        assertEquals ("standard test" , 6, change[1][12]);
        assertEquals ("standard test" , 100000, change[1][13]);
        assertEquals ("standard test" , 7, change[1][14]);
        assertEquals ("standard test" , 100000, change[1][15]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 100000, change[2][1]);
        assertEquals ("standard test" , 1, change[2][2]);
        assertEquals ("standard test" , 100000, change[2][3]);
        assertEquals ("standard test" , 2, change[2][4]);
        assertEquals ("standard test" , 100000, change[2][5]);
        assertEquals ("standard test" , 3, change[2][6]);
        assertEquals ("standard test" , 1, change[2][7]);
        assertEquals ("standard test" , 4, change[2][8]);
        assertEquals ("standard test" , 2, change[2][9]);
        assertEquals ("standard test" , 5, change[2][10]);
        assertEquals ("standard test" , 3, change[2][11]);
        assertEquals ("standard test" , 6, change[2][12]);
        assertEquals ("standard test" , 4, change[2][13]);
        assertEquals ("standard test" , 2, change[2][14]);
        assertEquals ("standard test" , 5, change[2][15]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 100000, change[3][1]);
        assertEquals ("standard test" , 1, change[3][2]);
        assertEquals ("standard test" , 100000, change[3][3]);
        assertEquals ("standard test" , 2, change[3][4]);
        assertEquals ("standard test" , 100000, change[3][5]);
        assertEquals ("standard test" , 3, change[3][6]);
        assertEquals ("standard test" , 1, change[3][7]);
        assertEquals ("standard test" , 4, change[3][8]);
        assertEquals ("standard test" , 2, change[3][9]);
        assertEquals ("standard test" , 1, change[3][10]);
        assertEquals ("standard test" , 3, change[3][11]);
        assertEquals ("standard test" , 2, change[3][12]);
        assertEquals ("standard test" , 4, change[3][13]);
        assertEquals ("standard test" , 2, change[3][14]);
        assertEquals ("standard test" , 5, change[3][15]);
    }

    @Test
	public void newInOrderTestTwo () {
        int [] denominations = new int []{0, 1, 2, 3};
        int n = 15;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);
        assertEquals ("standard test" , 0, change[0][4]);
        assertEquals ("standard test" , 0, change[0][5]);
        assertEquals ("standard test" , 0, change[0][6]);
        assertEquals ("standard test" , 0, change[0][7]);
        assertEquals ("standard test" , 0, change[0][8]);
        assertEquals ("standard test" , 0, change[0][9]);
        assertEquals ("standard test" , 0, change[0][10]);
        assertEquals ("standard test" , 0, change[0][11]);
        assertEquals ("standard test" , 0, change[0][12]);
        assertEquals ("standard test" , 0, change[0][13]);
        assertEquals ("standard test" , 0, change[0][14]);
        assertEquals ("standard test" , 0, change[0][15]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 1, change[1][1]);
        assertEquals ("standard test" , 2, change[1][2]);
        assertEquals ("standard test" , 3, change[1][3]);
        assertEquals ("standard test" , 4, change[1][4]);
        assertEquals ("standard test" , 5, change[1][5]);
        assertEquals ("standard test" , 6, change[1][6]);
        assertEquals ("standard test" , 7, change[1][7]);
        assertEquals ("standard test" , 8, change[1][8]);
        assertEquals ("standard test" , 9, change[1][9]);
        assertEquals ("standard test" , 10, change[1][10]);
        assertEquals ("standard test" , 11, change[1][11]);
        assertEquals ("standard test" , 12, change[1][12]);
        assertEquals ("standard test" , 13, change[1][13]);
        assertEquals ("standard test" , 14, change[1][14]);
        assertEquals ("standard test" , 15, change[1][15]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 1, change[2][1]);
        assertEquals ("standard test" , 1, change[2][2]);
        assertEquals ("standard test" , 2, change[2][3]);
        assertEquals ("standard test" , 2, change[2][4]);
        assertEquals ("standard test" , 3, change[2][5]);
        assertEquals ("standard test" , 3, change[2][6]);
        assertEquals ("standard test" , 4, change[2][7]);
        assertEquals ("standard test" , 4, change[2][8]);
        assertEquals ("standard test" , 5, change[2][9]);
        assertEquals ("standard test" , 5, change[2][10]);
        assertEquals ("standard test" , 6, change[2][11]);
        assertEquals ("standard test" , 6, change[2][12]);
        assertEquals ("standard test" , 7, change[2][13]);
        assertEquals ("standard test" , 7, change[2][14]);
        assertEquals ("standard test" , 8, change[2][15]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 1, change[3][1]);
        assertEquals ("standard test" , 1, change[3][2]);
        assertEquals ("standard test" , 1, change[3][3]);
        assertEquals ("standard test" , 2, change[3][4]);
        assertEquals ("standard test" , 2, change[3][5]);
        assertEquals ("standard test" , 2, change[3][6]);
        assertEquals ("standard test" , 3, change[3][7]);
        assertEquals ("standard test" , 3, change[3][8]);
        assertEquals ("standard test" , 3, change[3][9]);
        assertEquals ("standard test" , 4, change[3][10]);
        assertEquals ("standard test" , 4, change[3][11]);
        assertEquals ("standard test" , 4, change[3][12]);
        assertEquals ("standard test" , 5, change[3][13]);
        assertEquals ("standard test" , 5, change[3][14]);
        assertEquals ("standard test" , 5, change[3][15]);
    }

    @Test
	public void scrambleGivenTest () {
        int [] denominations = new int []{0, 10, 7, 1};
        int n = 15;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);
        assertEquals ("standard test" , 0, change[0][4]);
        assertEquals ("standard test" , 0, change[0][5]);
        assertEquals ("standard test" , 0, change[0][6]);
        assertEquals ("standard test" , 0, change[0][7]);
        assertEquals ("standard test" , 0, change[0][8]);
        assertEquals ("standard test" , 0, change[0][9]);
        assertEquals ("standard test" , 0, change[0][10]);
        assertEquals ("standard test" , 0, change[0][11]);
        assertEquals ("standard test" , 0, change[0][12]);
        assertEquals ("standard test" , 0, change[0][13]);
        assertEquals ("standard test" , 0, change[0][14]);
        assertEquals ("standard test" , 0, change[0][15]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 100000, change[1][1]);
        assertEquals ("standard test" , 100000, change[1][2]);
        assertEquals ("standard test" , 100000, change[1][3]);
        assertEquals ("standard test" , 100000, change[1][4]);
        assertEquals ("standard test" , 100000, change[1][5]);
        assertEquals ("standard test" , 100000, change[1][6]);
        assertEquals ("standard test" , 100000, change[1][7]);
        assertEquals ("standard test" , 100000, change[1][8]);
        assertEquals ("standard test" , 100000, change[1][9]);
        assertEquals ("standard test" , 1, change[1][10]);
        assertEquals ("standard test" , 100000, change[1][11]);
        assertEquals ("standard test" , 100000, change[1][12]);
        assertEquals ("standard test" , 100000, change[1][13]);
        assertEquals ("standard test" , 100000, change[1][14]);
        assertEquals ("standard test" , 100000, change[1][15]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 100000, change[2][1]);
        assertEquals ("standard test" , 100000, change[2][2]);
        assertEquals ("standard test" , 100000, change[2][3]);
        assertEquals ("standard test" , 100000, change[2][4]);
        assertEquals ("standard test" , 100000, change[2][5]);
        assertEquals ("standard test" , 100000, change[2][6]);
        assertEquals ("standard test" , 1, change[2][7]);
        assertEquals ("standard test" , 100000, change[2][8]);
        assertEquals ("standard test" , 100000, change[2][9]);
        assertEquals ("standard test" , 1, change[2][10]);
        assertEquals ("standard test" , 100000, change[2][11]);
        assertEquals ("standard test" , 100000, change[2][12]);
        assertEquals ("standard test" , 100000, change[2][13]);
        assertEquals ("standard test" , 2, change[2][14]);
        assertEquals ("standard test" , 100000, change[2][15]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 1, change[3][1]);
        assertEquals ("standard test" , 2, change[3][2]);
        assertEquals ("standard test" , 3, change[3][3]);
        assertEquals ("standard test" , 4, change[3][4]);
        assertEquals ("standard test" , 5, change[3][5]);
        assertEquals ("standard test" , 6, change[3][6]);
        assertEquals ("standard test" , 1, change[3][7]);
        assertEquals ("standard test" , 2, change[3][8]);
        assertEquals ("standard test" , 3, change[3][9]);
        assertEquals ("standard test" , 1, change[3][10]);
        assertEquals ("standard test" , 2, change[3][11]);
        assertEquals ("standard test" , 3, change[3][12]);
        assertEquals ("standard test" , 4, change[3][13]);
        assertEquals ("standard test" , 2, change[3][14]);
        assertEquals ("standard test" , 3, change[3][15]);
    }

    @Test
	public void modifyGivenTest () {
        int [] denominations = new int []{0, 3, 5, 4};
        int n = 10;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);
        assertEquals ("standard test" , 0, change[0][4]);
        assertEquals ("standard test" , 0, change[0][5]);
        assertEquals ("standard test" , 0, change[0][6]);
        assertEquals ("standard test" , 0, change[0][7]);
        assertEquals ("standard test" , 0, change[0][8]);
        assertEquals ("standard test" , 0, change[0][9]);
        assertEquals ("standard test" , 0, change[0][10]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 100000, change[1][1]);
        assertEquals ("standard test" , 100000, change[1][2]);
        assertEquals ("standard test" , 1, change[1][3]);
        assertEquals ("standard test" , 100000, change[1][4]);
        assertEquals ("standard test" , 100000, change[1][5]);
        assertEquals ("standard test" , 2, change[1][6]);
        assertEquals ("standard test" , 100000, change[1][7]);
        assertEquals ("standard test" , 100000, change[1][8]);
        assertEquals ("standard test" , 3, change[1][9]);
        assertEquals ("standard test" , 100000, change[1][10]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 100000, change[2][1]);
        assertEquals ("standard test" , 100000, change[2][2]);
        assertEquals ("standard test" , 1, change[2][3]);
        assertEquals ("standard test" , 100000, change[2][4]);
        assertEquals ("standard test" , 1, change[2][5]);
        assertEquals ("standard test" , 2, change[2][6]);
        assertEquals ("standard test" , 100000, change[2][7]);
        assertEquals ("standard test" , 2, change[2][8]);
        assertEquals ("standard test" , 3, change[2][9]);
        assertEquals ("standard test" , 2, change[2][10]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 100000, change[3][1]);
        assertEquals ("standard test" , 100000, change[3][2]);
        assertEquals ("standard test" , 1, change[3][3]);
        assertEquals ("standard test" , 1, change[3][4]);
        assertEquals ("standard test" , 1, change[3][5]);
        assertEquals ("standard test" , 2, change[3][6]);
        assertEquals ("standard test" , 2, change[3][7]);
        assertEquals ("standard test" , 2, change[3][8]);
        assertEquals ("standard test" , 2, change[3][9]);
        assertEquals ("standard test" , 2, change[3][10]);
    }

	@Test
	public void givenTestIllegal () {
        int [] denominations = new int []{0, 4, 7, 10};
        int n = 3;
        int [][] change = DPMakingChange.makeChange(denominations, n);
		assertEquals ("standard test" , 0, change[0][0]);
        assertEquals ("standard test" , 0, change[0][1]);
        assertEquals ("standard test" , 0, change[0][2]);
        assertEquals ("standard test" , 0, change[0][3]);

        assertEquals ("standard test" , 0, change[1][0]);
        assertEquals ("standard test" , 100000, change[1][1]);
        assertEquals ("standard test" , 100000, change[1][2]);
        assertEquals ("standard test" , 100000, change[1][3]);

        assertEquals ("standard test" , 0, change[2][0]);
        assertEquals ("standard test" , 100000, change[2][1]);
        assertEquals ("standard test" , 100000, change[2][2]);
        assertEquals ("standard test" , 100000, change[2][3]);

        assertEquals ("standard test" , 0, change[3][0]);
        assertEquals ("standard test" , 100000, change[3][1]);
        assertEquals ("standard test" , 100000, change[3][2]);
        assertEquals ("standard test" , 100000, change[3][3]);
    }
}