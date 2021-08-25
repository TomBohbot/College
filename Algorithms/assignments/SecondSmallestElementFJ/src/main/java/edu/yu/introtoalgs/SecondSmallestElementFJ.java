package edu.yu.introtoalgs;

/**
 * @author Tom Bohbot
 * @version Novemebr 17, 2020
 * MergeAnInterval SecondSmallestElementFJ - Intro To Algorithms
 */

import java.util.*;
import java.util.concurrent.*;

public class SecondSmallestElementFJ {

    private class SecondSmallestParallelProgramming extends RecursiveTask<int []> {
        private int [] array;
        private double sequentialCutOff;
        private int beginning;
        private int end;
        private int [] smallestTwo;

        SecondSmallestParallelProgramming (final int [] array , final double fractionToApplySequentialCutoff , int beginning , int end) {
            if (array == null || fractionToApplySequentialCutoff < 0.0 || fractionToApplySequentialCutoff > 1.0) { throw new IllegalArgumentException("Fraction to apply sequential cutoff cannot be less than zero."); }
            if (array.length <= 1) { throw new IllegalArgumentException("Input must contain at least two distinct integers."); } 
            this.array = array;
            this.sequentialCutOff = fractionToApplySequentialCutoff;
            this.beginning = beginning;
            this.end = end;
            this.smallestTwo = new int [3];
        }

        @Override
        protected int [] compute() {
            // Parallel Method:
            double threshold = this.array.length * this.sequentialCutOff;
            int arraySize = this.end - this.beginning;
            if (threshold <= 1) { threshold = 1; }
            if (this.sequentialCutOff == 1 || threshold >= arraySize) { return computeDirectly(); }
            int mid = (beginning + end) / 2;
            SecondSmallestParallelProgramming subTaskOne = new SecondSmallestParallelProgramming(this.array , this.sequentialCutOff , beginning , mid);
            SecondSmallestParallelProgramming subTaskTwo = new SecondSmallestParallelProgramming(this.array , this.sequentialCutOff , mid , end);
            invokeAll(subTaskOne , subTaskTwo);
            int [] subOne = subTaskOne.join();
            int [] subTwo = subTaskTwo.join();
            findSecondSmallest(subOne, subTwo);
            return smallestTwo;
        }

        private int [] findSecondSmallest (int [] subOne , int [] subTwo) {
            if (subOne[2] == 1 || subTwo[2] == 1) {
                this.smallestTwo[2] = 1;
            }
            if (subOne[0] <= subTwo[0]) {
                this.smallestTwo[0] = subOne[0];
                if (subOne[1] <= subTwo[0] && subOne[1] != subOne[0]) {
                    this.smallestTwo[2] = 1;
                    this.smallestTwo[1] = subOne[1];
                    return smallestTwo;
                }
                if (subTwo[0] < subOne[1] && subTwo[0] != subOne[0]) {
                    this.smallestTwo[2] = 1;
                    this.smallestTwo[1] = subTwo[0];
                    return smallestTwo;
                }
            }
            else {
                this.smallestTwo[0] = subTwo[0];
                if (subTwo[1] <= subOne[0] && subTwo[1] != subTwo[0]) {
                    this.smallestTwo[2] = 1;
                    this.smallestTwo[1] = subTwo[1];
                    return smallestTwo;
                }
                if (subOne[0] < subTwo[1] && subOne[0] != subTwo[0]) {
                    this.smallestTwo[2] = 1;
                    this.smallestTwo[1] = subOne[0];
                    return smallestTwo;
                }
            }    
            if (subOne[1] < subTwo[1] && subOne[1] != smallestTwo[0]) {
                this.smallestTwo[2] = 1;
                this.smallestTwo[1] = subOne[1];
                return smallestTwo;
            }
            this.smallestTwo[1] = subTwo[1];
            return smallestTwo;
        }

        private int [] computeDirectly() {
            int min = 2147483647;
            int secondMin = 2147483647;
            int distinctElem = 0;
            if (array.length == 1) {
                min = array[0];
                int [] smallestTwo = {min , secondMin , 0};
                return smallestTwo;
            } 
            for (int i = beginning; i < end; i ++) {
                int number = this.array[i];
                if (array[0] != array[i] ) {
                    distinctElem = 1;
                }
                if (number == min || number == secondMin) {
                    continue;
                }
                if (number < min) {
                    secondMin = min;
                    min = number;
                }
                else if (number < secondMin) {
                    secondMin = number;
                }
            }
            int [] smallestTwo = {min , secondMin , distinctElem};
            return smallestTwo;
        }
    }

    private int [] array;
    private double sequentialCutOff;

    /** Constructor .
    *
    * @param array input that we ’ll search
    * for the second smallest element ,
    * cannot be null .
    * @param fractionToApplySequentialCutoff
    * a fraction of the original number of
    * array elements : when the remaining
    * elements dip below this fraction , the
    * fork - join algorithm will process using
    * a sequential algorithm . Cannot be
    * less than 0.0 (fork - join processing
    * for all but arrays of size 1) and
    * cannot exceed 1.0 (no fork - join
    * processing will take place at all).
    */
    public SecondSmallestElementFJ (final int [] array , final double fractionToApplySequentialCutoff ) {
        if (array == null || fractionToApplySequentialCutoff < 0.0 || fractionToApplySequentialCutoff > 1.0) {
            throw new IllegalArgumentException("Fraction to apply sequential cutoff cannot be less than zero.");
        }
        this.array = array;
        this.sequentialCutOff = fractionToApplySequentialCutoff;
    }

    /** Returns the second smallest
    * unique element of the input array .
    *
    * Example : if array is [1 , 7 , 4 , 3 , 6] ,
    * then secondSmallest ( array ) == 3.
    *
    * Example : if array is [6 , 1 , 4 , 3 , 5 ,
    * 2 , 1] , secondSmallest ( array ) == 2.
    *
    *
    * @return second smallest unique element
    * of the input
    * @throws IllegalArgumentException if
    * the input doesn ’t contain a minimum of
    * two unique elements .
    */
    public int secondSmallest() {
        SecondSmallestParallelProgramming obj = new SecondSmallestParallelProgramming(this.array, this.sequentialCutOff , 0 , this.array.length);
        ForkJoinPool pool = new ForkJoinPool();
        int [] secondSmallest = pool.invoke(obj);
        if (secondSmallest[2] == 0) { 
            throw new IllegalArgumentException("Must have at least two distinct integers.");
        }
        return secondSmallest[1];
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
        SecondSmallestElementFJ thatObj = (SecondSmallestElementFJ) that;
        if (this.sequentialCutOff != thatObj.sequentialCutOff ) {
            return false;
        }
        if (!(this.array.equals(thatObj.array) ) ) {
            return false;
        }
        return true;
    }
    
    @Override 
    public int hashCode () {
        Integer secondSmallestElement = secondSmallest();
        return secondSmallestElement.hashCode();
    }

    @Override
    public String toString() {
        Integer secondSmallestElement = secondSmallest();
        return secondSmallestElement.toString();
    }
}