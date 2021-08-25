package edu.yu.da;

/**
 * @author Avraham Leff - Skeleton Code
 * @author Tom Bohbot   - Implementor
 * @version February 3, 2021
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedianFinding {

    private final static Logger logger = LogManager.getLogger(MedianFinding.class);

    /** 
     * Immutable account class for pedagogic purposes only since it's not very
     * useful.
     */
    public static class Account implements Comparable<Account> {
        
        private final double income;

        /** Initializes the account with an income field.
         */
        public Account(final double accountIncome) {
            income = accountIncome;
        }

        public double getIncome() {
            return income; 
        }

        public int compareTo(final Account that) {
            if (this.income > that.income) {
                return 1;
            }
            else {
                return -1;
            }
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
          Account thatAccount = (Account) that;
          if (thatAccount.getIncome() != this.getIncome() ) {
            return false;
          }
          return true;
        }

        @Override 
        public String toString () {
            return this.getIncome() + "";
        }
    } 

    /** 
     * Finds the median account (with respect to account income) over two sets
     * of accounts. The two sets are of the same length.
     *
     * @param set1 A sorted, in ascending order, with respect to account
     * income, non-null array of accounts. If the array is not sorted correctly,
     * the results of the method are undefined.
     * @param set2 A sorted, in ascending order, with respect to account
     * income, non-null array of accounts. If the array is not sorted correctly,
     * the results of the method are undefined.
     * @return Account record that has the median income with respect to
     * all accounts in the union of set1 and set2.
     */
    public static Account findMedian(final Account[] set1, final Account[] set2) {
        if (set1 == null || set2 == null) {
            throw new IllegalArgumentException("The set(s) cannot be null.");
        }
        return recursiveHelper(set1, set2, 0, set1.length-1, 0, set2.length-1);
    } 

    private static Account recursiveHelper(Account[] set1, Account[] set2, int min1, int max1, int min2, int max2) {
        // Base Case:
        if (max1 == min1 && max2 == min2) {
            if (set1[min1].getIncome() < set2[min2].getIncome()){
                return set1[min1];
            } 
            else{
                return set2[min2];
            }
        }
        // Recursive calls:
        else {
            // Calculate medians of each set:
            double medianOne = set1[(min1 + max1)/2].getIncome();
            double medianTwo = set2[(min2 + max2)/2].getIncome();
            // Since each Account is unique there is no chance of medians equaling each other:
            if (medianOne < medianTwo) {
                return recursiveHelper(set1, set2, (max1 + min1 + 1)/2, max1, min2, (max2 + min2)/2);
            }
            else {
                return recursiveHelper(set2, set1, (max2 + min2 + 1)/2, max2, min1, (max1 + min1)/2);
            }
        }
    }
}