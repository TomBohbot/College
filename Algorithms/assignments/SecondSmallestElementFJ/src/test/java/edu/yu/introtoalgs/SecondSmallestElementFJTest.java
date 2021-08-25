package edu.yu.introtoalgs;

/**
 * @author Tom Bohbot
 * @version Novemebr 17, 2020
 * MergeAnInterval SecondSmallestElementFJTestCode - Intro To Algorithms
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import edu.yu.introtoalgs.SecondSmallestElementFJ;

import java.util.*;



public class SecondSmallestElementFJTest {

    @Test (expected = IllegalArgumentException.class)
	public void illegalCutOff () {
        int [] array = {1 , 2 , 3 , 4 , 5};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , -0.1);
        int one = obj.secondSmallest();
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalCutOffTooBig () {
        int [] array = {1 , 2 , 3 , 4 , 5};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 1.1);
        int one = obj.secondSmallest();
    }

    @Test (expected = IllegalArgumentException.class)
	public void emptyArray () {
        int [] array = new int [3];
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 1.0);
        int one = obj.secondSmallest();
    }

    @Test (expected = IllegalArgumentException.class)
	public void arrayOfSizeOne () {
        int [] array = {0};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 1.0);
        int one = obj.secondSmallest();
    }

    @Test (expected = IllegalArgumentException.class)
	public void onlyDuplicates () {
        int [] array = {1 , 1 , 1 , 1 , 1};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.6);
        int one = obj.secondSmallest();
    }

    @Test (expected = IllegalArgumentException.class)
	public void onlyDuplicatesTwo () {
        int [] array = {2147483647 , 2147483647 , 2147483647 , 2147483647 , 2147483647};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.4);
        int one = obj.secondSmallest();
    }

    @Test 
	public void ascendingNumbers () {
        int [] array = {0 , 1 , 2  , 3 , 5};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.331);
        assertEquals(1 , obj.secondSmallest() );
    }

    @Test 
	public void ascendingNumbersTwo () {
        int [] array = {-1 , 0 , 2147483647};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.331);
        assertEquals(0 , obj.secondSmallest() );
    }

    @Test 
	public void descendingNumbers () {
        int [] array = {10 , 4 , 3  , 1 , 0};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.245);
        assertEquals(1 , obj.secondSmallest() );
    }

    @Test 
	public void randomOrder () {
        int [] array = {-10 , 820 , 25  , 236 , -100000};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.984);
        assertEquals(-10 , obj.secondSmallest() );
    }

    @Test 
	public void minElementsInArray () {
        int [] array = {1 , 2};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.3);
        assertEquals(2 , obj.secondSmallest() );
    }

    @Test 
	public void minElementsInArrayTwo () {
        int [] array = {-1 , -22};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.5);
        assertEquals(-1 , obj.secondSmallest() );
    }

    @Test 
	public void onlyDups () {
        int [] array = {-1 , -1 , 0 , 0 , 2 , 2 , -1 , -1 , 0 , 0 , 6 , 6 , 5 , 5};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.5);
        assertEquals(0 , obj.secondSmallest() );
    }

    @Test 
	public void onlyDups2 () {
        int [] array = {1 , 1 , 1 , 1 , 1 , 2 , 2 , 2 , 2 , 2};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.5);
        assertEquals(2 , obj.secondSmallest() );
    }

    @Test 
	public void oneUniqueElem () {
        int [] array = {1 , 1 , 1  , 1 , 2};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 1.0);
        assertEquals(2 , obj.secondSmallest() );
        int [] arrayTwp = {1 , 1 , 1  , 2 , 1};
        obj = new SecondSmallestElementFJ(arrayTwp , 1.0);
        assertEquals(2 , obj.secondSmallest() );
        int [] arrayThree = {1 , 1 , 2  , 1 , 1};
        obj = new SecondSmallestElementFJ(arrayThree , 0.9);
        assertEquals(2 , obj.secondSmallest() );
        int [] arrayFour = {1 , 2 , 1  , 1 , 1};
        obj = new SecondSmallestElementFJ(arrayFour , 0.8);
        assertEquals(2 , obj.secondSmallest() );
        int [] arrayFive = {2 , 1 , 1  , 1 , 1};
        obj = new SecondSmallestElementFJ(arrayFive , 0.7);
        assertEquals(2 , obj.secondSmallest() );
        int [] arraySix = {2 , 2 , 2  , 1};
        obj = new SecondSmallestElementFJ(arraySix , 0.6);
        assertEquals(2 , obj.secondSmallest() );
        int [] arraySeven = {2 , 2 , 1  , 2};
        obj = new SecondSmallestElementFJ(arraySeven , 0.3);
        assertEquals(2 , obj.secondSmallest() );
        int [] arrayEigth = {2 , 1 , 2  , 2};
        obj = new SecondSmallestElementFJ(arrayEigth , 0.1);
        assertEquals(2 , obj.secondSmallest() );
        int [] arrayNine = {1 , 2 , 2  , 2};
        obj = new SecondSmallestElementFJ(arrayNine , 0.0);
        assertEquals(2 , obj.secondSmallest() );
    }

    @Test 
	public void leffTestOne () {
        int [] array = {1 , 7 , 4  , 3 , 6};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.2);
        assertEquals(3 , obj.secondSmallest() );
    }

    @Test 
	public void leffTestTwo () {
        int [] array = {6 , 1 , 4  , 3 , 5 , 2 , 1};
        SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.9);
        assertEquals(2 , obj.secondSmallest() );
    }

    @Test
    public void doublingTest() {
        Random random = new Random ();
        int x = 1048576 ;
        for (int i = 20; i < 31; i ++) {
            int min = 2147483647;
            int secondMin = 2147483647;
            int [] array = new int [x];
            for (int j = 0; j < x; j ++) {
                if (j == 1) {
                    array[j] = array[0] + 1; // ensures two distinct elements. 
                }
                else {
                    array[j] = random.nextInt (10);
                }
                if (array[j] == secondMin || array[j] == min) {
                    continue;
                }
                if (array[j] < min) {
                    secondMin = min;
                    min = array[j];
                }
                else if (array[j] < secondMin) {
                    secondMin = array[j];
                }
            }
            SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 0.9);
            Stopwatch stopwatch = new Stopwatch();
            assertEquals(secondMin , obj.secondSmallest() );
            System.out.println("Stopwatch " + array.length + ": " + stopwatch.elapsedTime() );
            x = x * 2;
        }
    }

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

    // private int proveSequentialAlgorithmIsOn(int [] array) {
    //     int min = 2147483647;
    //     int secondMin = 2147483647;
    //     HashSet <Integer> seenNumbers = new HashSet <Integer>();
    //     if (array.length <= 1) {
    //         throw new IllegalArgumentException("Input must contain at least two distinct integers.");
    //     } 
    //     for (int number : array) {
    //         Integer numberObj = (Integer) number;
    //         if (seenNumbers.contains(numberObj) ) {
    //             continue;
    //         }
    //         if (number < min) {
    //             secondMin = min;
    //             min = number;
    //         }
    //         else if (number < secondMin) {
    //             secondMin = number;
    //         }
    //         seenNumbers.add(numberObj);
    //     }
    //     if (seenNumbers.size() == 1) {
    //         throw new IllegalArgumentException("Input must contain at least two distinct integers.");
    //     } 
    //     return secondMin;
    // }

    // // // @Test
    // // // public void doublingTestSequentialAlgorithm() {
    // // //     System.out.println("HEYYY");
    // // //     Random random = new Random ();
    // // //     int x = 2;
    // // //     for (int i = 0; i < 25; i ++) {
    // // //         int min = 2147483647;
    // // //         int secondMin = 2147483647;
    // // //         int [] array = new int [x];
    // // //         for (int j = 0; j < x; j ++) {
    // // //             if (j == 1) {
    // // //                 array[j] = array[0] + 1; // ensures two distinct elements. 
    // // //             }
    // // //             else {
    // // //                 array[j] = random.nextInt (2147483647);
    // // //             }
    // // //             if (array[j] < min) {
    // // //                 secondMin = min;
    // // //                 min = array[j];
    // // //             }
    // // //             else if (array[j] < secondMin) {
    // // //                 secondMin = array[j];
    // // //             }
    // // //         }
    // // //         SecondSmallestElementFJ obj = new SecondSmallestElementFJ(array , 1.0);
    // // //         Stopwatch stopwatch = new Stopwatch();
    // // //         assertEquals(secondMin , proveSequentialAlgorithmIsOn(array) );
    // // //         System.out.println("Stopwatch " + i + ": " + stopwatch.elapsedTime() );
    // // //         x = x * 2;
    // // //     }
    // // // }
}