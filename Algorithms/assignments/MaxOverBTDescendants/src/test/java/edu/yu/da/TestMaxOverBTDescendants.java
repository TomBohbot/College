package edu.yu.da;

 
/** 
 * Test MaxOverBTDescendants
 * @author Tom Bohbot
 * @version February 24, 2021
 */

import edu.yu.da.MaxOverBTDescendants;
import org.junit.*;
import static org.junit.Assert.*;

public class TestMaxOverBTDescendants {

    // Constructor Illegal Paramaters:
    @Test(expected = IllegalArgumentException.class)
	public void vUnderZero() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(-1, 0, 1.1);
	}

    @Test(expected = IllegalArgumentException.class)
	public void rootUnderZero() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(10, -1, 1.1);
	}

    @Test(expected = IllegalArgumentException.class)
	public void rootEqualsV() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(10, 10, 1.1);
	}

    @Test(expected = IllegalArgumentException.class)
	public void rootGreaterThanV() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(10, 11, 1.1);
	}

    @Test(expected = IllegalArgumentException.class)
	public void exceptionsEverywhere() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(-1, 100, 1.1);
	}

    // addChild Illegal Paramaters:
    @Test(expected = IllegalArgumentException.class)
	public void parentDoesntExistVanilla() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(6, 0, 1.1);
        obj.addChild(2, 4, 2.2);
        obj.addChild(4, 0, 3.3);
        obj.addChild(0, 1, 4.4);
        obj.addChild(0, 3, 5.5);
        obj.addChild(10, 5, 6.6);
	}

    @Test(expected = IllegalArgumentException.class)
	public void parentDoesntExistComplicated() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(6, 2, 1.1);
        obj.addChild(2, 4, 2.2);
        obj.addChild(4, 0, 3.3);
        obj.addChild(0, 1, 4.4);
        obj.addChild(0, 3, 5.5);
        obj.addChild(10, 5, 6.6);
	}

    @Test(expected = IllegalArgumentException.class)
	public void tooManyKidsPerParent() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(7, 2, 1.1);
        obj.addChild(2, 4, 2.2);
        obj.addChild(4, 0, 3.3);
        obj.addChild(0, 1, 4.4);
        obj.addChild(0, 3, 5.5);
        obj.addChild(10, 5, 6.6);
        obj.addChild(0, 6, 5.8);
	}

    @Test(expected = IllegalArgumentException.class)
	public void illegalChild() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(7, 2, 1.1);
        obj.addChild(2, -1, 2.2);
	}

    @Test(expected = IllegalArgumentException.class)
	public void illegalChildTooBig() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(7, 2, 1.1);
        obj.addChild(2, 7, 2.2);
	}

    @Test(expected = IllegalArgumentException.class)
	public void duplicateChildID() {          
        MaxOverBTDescendants obj = new MaxOverBTDescendants(7, 2, 1.1);
        obj.addChild(2, 3, 2.2);
        obj.addChild(4, 0, 3.3);
        obj.addChild(0, 1, 4.4);
        obj.addChild(0, 3, 5.5);
        obj.addChild(10, 5, 6.6);
        obj.addChild(0, 6, 5.8);
	}

    // Vanilla Tests:
    @Test
	public void ascendingValues () {
        MaxOverBTDescendants obj = new MaxOverBTDescendants(6, 2, 1.1);
        obj.addChild(2, 4, 2.2);
        obj.addChild(4, 0, 3.3);
        obj.addChild(0, 1, 4.4);
        obj.addChild(0, 3, 5.5);
        obj.addChild(3, 5, 6.6);
        double [] values = obj.maxOverAllBTDescendants();
        // check if each element in the array is correct:
        assertEquals("first element",  3.3,  values[0], 0.001);
        assertEquals("second element", 4.4,   values[1], 0.001);
        assertEquals("third element",  1.1,  values[2], 0.001);
        assertEquals("fourth element", 5.5, values[3], 0.001);
        assertEquals("fifth element",  2.2,  values[4], 0.001);
        assertEquals("sixth element",  6.6, values[5], 0.001);
    }

    @Test
	public void mixedValues () {
        MaxOverBTDescendants obj = new MaxOverBTDescendants(6, 2, 10.1);
        obj.addChild(2, 4, 2.2);
        obj.addChild(4, 0, 30.3);
        obj.addChild(0, 1, 14.4);
        obj.addChild(0, 3, 50.5);
        obj.addChild(3, 5, 6.6);
        double [] values = obj.maxOverAllBTDescendants();
        // check if each element in the array is correct:
        assertEquals("first element",  30.3,  values[0], 0.001);
        assertEquals("second element", 30.3,   values[1], 0.001);
        assertEquals("third element",  10.1,  values[2], 0.001);
        assertEquals("fourth element", 50.5, values[3], 0.001);
        assertEquals("fifth element",  10.1,  values[4], 0.001);
        assertEquals("sixth element",  50.5, values[5], 0.001);
    }

    // Doubling Test:
    private static class Stopwatch {

        private final long start;

        private Stopwatch() {
            start = System.currentTimeMillis();
        }
    
        private double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }    
    } 

    // @Test
    // public void doublingTestSequentialAlgorithm() {
    //     int x = 4;
    //     // 25 is the amount of time I will double:
    //     for (int i = 0; i < 25; i ++) {
    //         Stopwatch stopwatch = new Stopwatch();
    //         MaxOverBTDescendants obj = new MaxOverBTDescendants(x, 1, 1.0);
    //         // add n elements to the list:
    //         for (int j = 2; j < x; j ++ ) {
    //             obj.addChild(j-1, j, j*1.1);
    //         }
    //         // get the array:
    //         double [] values = obj.maxOverAllBTDescendants();
    //         System.out.println("Stopwatch " + x + ": " + stopwatch.elapsedTime() );
    //         // now loop through them to make sure it's actually correct, shouldn't be included within stopwatch:
    //         for (int j = 2; j < x; j ++) {
    //             assertEquals("Verifying the doubling test" , j*1.1, values[j], 0.001);
    //         }
    //         x *= 2;
    //     }
    // }

    // @Test
    // public void alwaysEqualToRoot() {
    //     int x = 4;
    //     // 25 is the amount of time I will double:
    //     for (int i = 0; i < 25; i ++) {
    //         Stopwatch stopwatch = new Stopwatch();
    //         MaxOverBTDescendants obj = new MaxOverBTDescendants(x, 0, Integer.MAX_VALUE);
    //         // add n elements to the list:
    //         for (int j = 1; j < x; j ++ ) {
    //             obj.addChild(j-1, j, j*1.1);
    //         }
    //         // get the array:
    //         double [] values = obj.maxOverAllBTDescendants();
    //         System.out.println("Stopwatch " + x + ": " + stopwatch.elapsedTime() );
    //         // now loop through them to make sure it's actually correct, shouldn't be included within stopwatch:
    //         for (int j = 0; j < x; j ++) {
    //             assertEquals("Verifying the doubling test" , Integer.MAX_VALUE, values[j], 0.001);
    //         }
    //         x *= 2;
    //     }
    // }
}
