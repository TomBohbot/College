package edu.yu.da;

/** 
 * Pay out Test Code 
 * @author Tom Bohbot
 * @version February 3, 2021
 */

import edu.yu.da.DPMakingChange;
import org.junit.*;
import static org.junit.Assert.*;

public class TestPayOut {

	@Test
	public void givenTest () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 15;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,2,0};
        assertEquals("Testing Case 1", coins[0], DPMakingChange.payOut(change, denominations, n)[0] );
        assertEquals("Testing Case 2", coins[1], DPMakingChange.payOut(change, denominations, n)[1] );
        assertEquals("Testing Case 3", coins[2], DPMakingChange.payOut(change, denominations, n)[2] );
        assertEquals("Testing Case 4", coins[3], DPMakingChange.payOut(change, denominations, n)[3] );
    }

    @Test
	public void givenTestExtended () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 16;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,2,2,0};
        assertEquals("Testing Case 1", coins[0], DPMakingChange.payOut(change, denominations, n)[0] );
        assertEquals("Testing Case 2", coins[1], DPMakingChange.payOut(change, denominations, n)[1] );
        assertEquals("Testing Case 3", coins[2], DPMakingChange.payOut(change, denominations, n)[2] );
        assertEquals("Testing Case 4", coins[3], DPMakingChange.payOut(change, denominations, n)[3] );
    }

    @Test
	public void givenTestThree () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 17;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,1,1};
        assertEquals("Testing Case 1", coins[0], DPMakingChange.payOut(change, denominations, n)[0] );
        assertEquals("Testing Case 2", coins[1], DPMakingChange.payOut(change, denominations, n)[1] );
        assertEquals("Testing Case 3", coins[2], DPMakingChange.payOut(change, denominations, n)[2] );
        assertEquals("Testing Case 4", coins[3], DPMakingChange.payOut(change, denominations, n)[3] );
    }

    @Test
	public void givenTestFour () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 18;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,1,1};
        assertEquals("Testing Case 1", coins[0], DPMakingChange.payOut(change, denominations, n)[0] );
        assertEquals("Testing Case 2", coins[1], DPMakingChange.payOut(change, denominations, n)[1] );
        assertEquals("Testing Case 3", coins[2], DPMakingChange.payOut(change, denominations, n)[2] );
        assertEquals("Testing Case 4", coins[3], DPMakingChange.payOut(change, denominations, n)[3] );
    }

    @Test
	public void givenTestFive () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 19;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,2,1,1};
        assertEquals("Testing Case 1", coins[0], DPMakingChange.payOut(change, denominations, n)[0] );
        assertEquals("Testing Case 2", coins[1], DPMakingChange.payOut(change, denominations, n)[1] );
        assertEquals("Testing Case 3", coins[2], DPMakingChange.payOut(change, denominations, n)[2] );
        assertEquals("Testing Case 4", coins[3], DPMakingChange.payOut(change, denominations, n)[3] );
    }

    @Test
	public void givenTestSix () {
        int [] denominations = new int []{0, 1, 7, 10};
        int n = 20;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,0,2};
        assertEquals("Testing Case 1", coins[0], DPMakingChange.payOut(change, denominations, n)[0] );
        assertEquals("Testing Case 2", coins[1], DPMakingChange.payOut(change, denominations, n)[1] );
        assertEquals("Testing Case 3", coins[2], DPMakingChange.payOut(change, denominations, n)[2] );
        assertEquals("Testing Case 4", coins[3], DPMakingChange.payOut(change, denominations, n)[3] );
    }

    @Test
    public void newSeriesTwo() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 2;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,0,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesThree() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 3;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,1,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesFour() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 4;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,2,0,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesFive() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 5;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,0,1};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesSix() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 6;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,2,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesSeven() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 7;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,0,1};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesEight() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 8;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,1,1};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesNine() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 9;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,2,0,1};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertTrue("Testing Case 4", (1 == returnedCoins[3] && 2 == returnedCoins[1]) ^ 3 == returnedCoins[2]);
    }

    @Test
    public void newSeriesNineV2() {
        int [] denominations = new int []{0, 5, 2, 3};
        int n = 9;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,2,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertTrue("Testing Case 4", (1 == returnedCoins[1] && 2 == returnedCoins[2]) ^ 3 == returnedCoins[3]);
    }

    @Test
    public void newSeriesNineV3() {
        int [] denominations = new int []{0, 3, 5, 2};
        int n = 9;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,1,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertTrue("Testing Case 4", (1 == returnedCoins[2] && 2 == returnedCoins[3]) ^ 3 == returnedCoins[1]);
    }

    @Test
    public void newSeriesTen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 10;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,0,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesEleven() { // this test breaks build b/c an xor is needed when counting how many coins are made!
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 11;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,2,1};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        // assertTrue("Testing Case 4", (1 == returnedCoins[3] && 3 == returnedCoins[1]) ^ (3 == returnedCoins[2] && 1 == returnedCoins[1]) );
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesElevenV2() { // this test breaks build b/c an xor is needed when counting how many coins are made!
        int [] denominations = new int []{0, 5, 3, 2};
        int n = 11;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,2,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesElevenV3() { // this test breaks build b/c an xor is needed when counting how many coins are made!
        int [] denominations = new int []{0, 3, 5, 2};
        int n = 11;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,2,1,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesTwelve() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 12;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,0,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesThirteen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 13;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,1,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test // error here ?
    public void newSeriesFourteen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 14;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,2,0,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertTrue((returnedCoins[0]==0 && returnedCoins[1]==2 && returnedCoins[2]==0 && returnedCoins[3]==2) ^ (returnedCoins[0]==0 && returnedCoins[1]==0 && returnedCoins[2]==3 && returnedCoins[3]==1) );
    }

    @Test
    public void newSeriesFifteen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 15;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,0,3};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test 
    public void newSeriesSixteen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 16;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,2,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesSeventeen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 17;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,1,0,3};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesEighteen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 18;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,1,3};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    // @Test // error?
    public void newSeriesNineteen() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 19;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,3,2};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesTwenty() {
        int [] denominations = new int []{0, 2, 3, 5};
        int n = 20;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,0,4};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesTwentyVTwo() {
        int [] denominations = new int []{0, 5, 3, 2};
        int n = 20;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,4,0,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesTwentyVThree() {
        int [] denominations = new int []{0, 3, 5, 2};
        int n = 20;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,0,4,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }

    @Test
    public void newSeriesTwentyVFour() {
        int [] denominations = new int []{0, 5, 2, 3};
        int n = 20;
        int [][] change = DPMakingChange.makeChange(denominations, n);
        int [] coins = new int []{0,4,0,0};
        int [] returnedCoins = DPMakingChange.payOut(change, denominations, n);
        assertEquals("Testing Case 1", coins[0], returnedCoins[0] );
        assertEquals("Testing Case 2", coins[1], returnedCoins[1] );
        assertEquals("Testing Case 3", coins[2], returnedCoins[2] );
        assertEquals("Testing Case 4", coins[3], returnedCoins[3] );
    }
}