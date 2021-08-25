package edu.yu.introtoalgs;

/**
 * Testing MergeAnInterval.
 * @author  Tom Bohbot
 * @version November 8, 2020
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import edu.yu.introtoalgs.MergeAnInterval;
import edu.yu.introtoalgs.MergeAnInterval.Interval;

import java.util.*;


public class MergeAndUnionTest {

    private HashSet <Interval> intervals = new HashSet <Interval>();

    public HashSet<Interval> fillIntervals () {
        intervals.add(new Interval (0 , 2) );
        intervals.add(new Interval (3 , 4) );
        intervals.add(new Interval (7 , 10) );
        intervals.add(new Interval (12 , 18) );
        intervals.add(new Interval (19 , 20) );
        return intervals;
    }

    @Test (expected = IllegalArgumentException.class)
	public void nullSet () {
        intervals = null;
        Interval newIntervalOne = new Interval(1 , 2); 
        MergeAnInterval.merge(intervals, newIntervalOne); 
    }

    @Test (expected = IllegalArgumentException.class)
	public void nullInterval () {
		intervals = fillIntervals();
        Interval newIntervalOne = null;
        MergeAnInterval.merge(intervals, newIntervalOne); 
        intervals.clear();
    }

    @Test (expected = IllegalArgumentException.class)
	public void nullParamaters () {
		intervals = null;
        Interval newIntervalOne = null;
        MergeAnInterval.merge(intervals, newIntervalOne); 
        intervals.clear();
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalNewInterval () {
		intervals = fillIntervals();
        Interval newIntervalOne = new Interval (2 , 1);
        MergeAnInterval.merge(intervals, newIntervalOne); 
        intervals.clear();
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalNewIntervalTwo () {
		intervals = fillIntervals();
        Interval newIntervalOne = new Interval (2 , 2);
        MergeAnInterval.merge(intervals, newIntervalOne); 
        intervals.clear();
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalSetOfIntervals () {
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (0 , 2) );
        testInterval.add(new Interval (3 , 4) );
        testInterval.add(new Interval (11 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        Interval newIntervalOne = new Interval (0 , 2);
        MergeAnInterval.merge(testInterval, newIntervalOne); 
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalSetOfIntervalsTwo () {
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (0 , 2) );
        testInterval.add(new Interval (4 , 4) );
        testInterval.add(new Interval (9 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        Interval newIntervalOne = new Interval (0 , 2);
        MergeAnInterval.merge(testInterval, newIntervalOne); 
    }

    @Test (expected = IllegalArgumentException.class)
	public void IllegalIntervalsEverywhere () {
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (0 , 2) );
        testInterval.add(new Interval (4 , 4) );
        testInterval.add(new Interval (9 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        Interval newIntervalOne = new Interval (8 , 2);
        MergeAnInterval.merge(testInterval, newIntervalOne); 
    }

    @Test 
	public void negInterval () {
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (0 , 2) );
        testInterval.add(new Interval (4 , 6) );
        testInterval.add(new Interval (9 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        Interval newIntervalOne = new Interval(-1 , 2); 
        MergeAnInterval.merge(testInterval, newIntervalOne); 
    }

    @Test 
	public void negIntervalSet () {
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (-1 , 2) );
        testInterval.add(new Interval (4 , 6) );
        testInterval.add(new Interval (9 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        Interval newIntervalOne = new Interval(0 , 2); 
        MergeAnInterval.merge(testInterval, newIntervalOne); 
    }

    @Test 
	public void negIntervalSeBoth () {
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (-10 , -8) );
        testInterval.add(new Interval (4 , 6) );
        testInterval.add(new Interval (9 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        Interval newIntervalOne = new Interval(-6 , 1); 
        HashSet <Interval> compareInterval = new HashSet <Interval>();
        compareInterval.add(new Interval (-10 , -8) );
        compareInterval.add(new Interval (-6 , 1) );
        compareInterval.add(new Interval (4 , 6) );
        compareInterval.add(new Interval (9 , 10) );
        compareInterval.add(new Interval (12 , 18) );
        compareInterval.add(new Interval (19 , 20) );
        HashSet <Interval> testOneInterval = (HashSet <Interval>) MergeAnInterval.merge(testInterval, newIntervalOne);
        assertEquals("Testing if first union works" , compareInterval , testOneInterval);
        // Test 2:
        Interval newIntervalTwo = new Interval(-4 , 13); 
        compareInterval.clear();
        compareInterval.add(new Interval (-10 , -8) );
        compareInterval.add(new Interval (-6 , 18) );
        compareInterval.add(new Interval (19 , 20) );
        HashSet <Interval> testTwoInterval = (HashSet <Interval>) MergeAnInterval.merge(testOneInterval, newIntervalTwo);
        assertEquals("Testing if first union works" , compareInterval , testTwoInterval);
        // Test 3: Nothing same interval added:
        assertEquals("Testing if first union works" , compareInterval , MergeAnInterval.merge(testOneInterval, newIntervalTwo));
        // Test 4:
        compareInterval.clear();
        Interval newIntervalThree = new Interval (-100 , 100);
        compareInterval.add(new Interval (-100 , 100) );
        HashSet <Interval> testFourInterval = (HashSet <Interval>) MergeAnInterval.merge(testOneInterval, newIntervalThree);
        assertEquals("Testing if first union works" , compareInterval , testFourInterval);
    }
    
    @Test
    public void findFirstInstanceDoesExist() { // confirm this one..
        // filling intervals set:
        intervals = fillIntervals();
        // Making the new Interval:
        Interval newIntervalOne = new Interval(1 , 2);  
        // Making expected return value:
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (0 , 2) );
        testInterval.add(new Interval (3 , 4) );
        testInterval.add(new Interval (7 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        intervals.clear();
    }

    @Test
    public void addAnIntervalInMiddle() {
        // filling intervals set:
        intervals = fillIntervals();
        // Making the new Interval:
        Interval newIntervalOne = new Interval(5 , 6);  
        // Making expected return value:
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval (0 , 2) );
        testInterval.add(new Interval (3 , 4) );
        testInterval.add(new Interval (5 , 6) );
        testInterval.add(new Interval (7 , 10) );
        testInterval.add(new Interval (12 , 18) );
        testInterval.add(new Interval (19 , 20) );
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        intervals.clear();
    }

    @Test
    public void findFirstInstanceDoesNotExist() {
        // filling intervals set:
        intervals = fillIntervals();
        // Making the new Interval:
        Interval newIntervalOne = new Interval(25 , 26);  
        // Making expected return value:
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add (new Interval (0 , 2) );
        testInterval.add (new Interval (3 , 4) );
        testInterval.add (new Interval (7 , 10) );
        testInterval.add (new Interval (12 , 18) );
        testInterval.add (new Interval (19 , 20) );
        testInterval.add(newIntervalOne);
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        intervals.clear();
    }

    @Test
    public void mergeOneSet() {
        // Instantiaing intervals:
        Interval intervalOne = new Interval (0 , 2);
        Interval intervalTwo = new Interval (3 , 4);
        Interval intervalThree = new Interval (7 , 10);
        Interval intervalFour = new Interval (12 , 18);
        Interval intervalFive = new Interval (19 , 20);
        // filling intervals set:
        intervals.add(intervalOne);
        intervals.add(intervalTwo);
        intervals.add(intervalThree);
        intervals.add(intervalFour);
        intervals.add(intervalFive);      
        // Making the new Interval:
        Interval newIntervalOne = new Interval(9 , 13);  
        Interval returnedNewInterval = new Interval(7 , 18);
        // Making expected return value:
        LinkedHashSet <Interval> testInterval = new LinkedHashSet <Interval>();
        testInterval.add(intervalOne);
        testInterval.add(intervalTwo);
        testInterval.add(returnedNewInterval);
        testInterval.add(intervalFive);
        //Test Case 1:
        HashSet <Interval> returnedSet = (HashSet <Interval>) MergeAnInterval.merge(intervals, newIntervalOne);
        assertEquals("Testing if first union works" , testInterval.size() , returnedSet.size() );
        assertEquals("Testing if first union works" , testInterval , returnedSet );
    }

    @Test
    public void mergeOneSetUnsorted() {
        // Instantiaing intervals:
        Interval intervalOne = new Interval (0 , 2);
        Interval intervalTwo = new Interval (3 , 4);
        Interval intervalThree = new Interval (7 , 10);
        Interval intervalFour = new Interval (12 , 18);
        Interval intervalFive = new Interval (19 , 20);
        // filling intervals set:
        intervals.add(intervalOne);
        intervals.add(intervalTwo);
        intervals.add(intervalThree);
        intervals.add(intervalFour);
        intervals.add(intervalFive);      
        // Making the new Interval:
        Interval newIntervalOne = new Interval(9 , 13);  
        // Making expected return value:
        Interval returnedInterval = new Interval(7 , 18);  
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(intervalOne);
        testInterval.add(intervalTwo);
        testInterval.add(returnedInterval);
        testInterval.add(intervalFive);
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        intervals.clear();
    }

    @Test
    public void mergeTwoUnions() {
        // filling intervals set:
        intervals = fillIntervals();
        // Making the new Interval:
        Interval newIntervalOne = new Interval(1 , 9);  
        // Making expected return value:
        Interval returnedIntervalOne = new Interval(0 , 10);  
        HashSet <Interval> testInterval = new HashSet <Interval>();
        Interval intervalFour = new Interval (12 , 18);
        Interval intervalFive = new Interval (19 , 20);
        testInterval.add(returnedIntervalOne);
        testInterval.add(intervalFour);
        testInterval.add(intervalFive);
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        Interval newIntervalTwo = new Interval(12 , 30);  
        // Interval returnedIntervalTwo = new Interval(12 , 30);  
        testInterval.clear();
        testInterval.add(returnedIntervalOne);
        testInterval.add(newIntervalTwo);
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalTwo).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalTwo) );
        Interval newIntervalThree = new Interval(3 , 1000);  
        Interval returnIntervalThree = new Interval(0 , 1000);  
        testInterval.clear();
        testInterval.add(returnIntervalThree);
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalThree).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalThree) );
        intervals.clear();
    }

    @Test
    public void mergeAllUnions() {
        // filling intervals set:
        intervals = fillIntervals();
        // Making the new Interval:
        Interval newIntervalOne = new Interval(0 , 1000);  
        // Making expected return value:
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(newIntervalOne);
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        intervals.clear();
    }

    @Test
    public void mergeAllUnionsWithinBounds() {
        // filling intervals set:
        intervals = fillIntervals();
        // Making the new Interval:
        Interval newIntervalOne = new Interval(1 , 19);  
        Interval returnIntervalOne = new Interval(0 , 20);  
        // Making expected return value:
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(returnIntervalOne);
        //Test Case 1:
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
        intervals.clear();
    }

    @Test
    public void leffTestOne() {
        intervals.add(new Interval(3 , 4) );
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval(3 , 4) );
        testInterval.add(new Interval(0 , 2) );
        Interval newIntervalOne = new Interval(0 , 2);
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
    }

    @Test
    public void leffTestTwo() {
        intervals.add(new Interval(1 , 4) );
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval(0 , 4) );
        Interval newIntervalOne = new Interval(0 , 2);
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
    }

    @Test
    public void leffTestThree() {
        intervals.add(new Interval(1 , 2) );
        intervals.add(new Interval(3 , 4) );
        HashSet <Interval> testInterval = new HashSet <Interval>();
        testInterval.add(new Interval(0 , 4) );
        Interval newIntervalOne = new Interval(0 , 3);
        assertEquals("Testing if first union works" , testInterval.size() , MergeAnInterval.merge(intervals, newIntervalOne).size() );
        assertEquals("Testing if first union works" , testInterval , MergeAnInterval.merge(intervals, newIntervalOne) );
    }

}