package edu.yu.da;

import java.util.HashMap;

/**
 * Dynamicaly solve - What is the minimal number of coins needed to make change for a specified amount?
 * @author Avraham Leff - Skeleton Code
 * @author Tom Bohbot   - Implementor
 * @version April, 2021
 */

public class DPMakingChange {

    public static final int PSEUDO_INFINITY = 100000;

    /** 
     * Given a set of coin denominations and an amount of change that must be
     * made from these denominations, determines the optimal (as defined by
     * "minimal number") coins that should be used to make change.  There are an
     * unlimited number of coins available for each of the specified
     * denominations.  If change cannot be made given these parameters, this fact
     * must be reflected by appropriate use of the PSEUDO_INFINITY constant (see
     * below).
     *
     * @param denominations A non-null array of coin denominations (units are
     * assumed to be cents), of size n+1, indexed from 1..n.
     *
     * Note: denominations[0] is only a place-holder element, and should be
     * ignored by the method implementation.  The denominations need not be
     * sorted in any way.  The parameter must have length >= 2.
     *
     * The semantics are undefined if the client supplies a parameter containing
     * non-unique denominations.
     * @param N A non-negative amount of change to be calculated (same units as
     * the denominations parameter)
     * @return an int[][] array c such that c[i, j] holds the minimum number of
     * coins required to make change for an amount "j" using only coins d_1,
     * ... d_i.  The optimal number of coins must be available at
     * c[denominations.length -1][N].
     *
     * Note: denominations c[i][0] = 0 for all denominations i.
     *
     * Note: if change cannot be made change for amount "j" using coins d_1, ...,
     * d_i, the implementation must set c[i, j] = PSEUDO_INFINITY.
     */
    public static int[][] makeChange(final int denominations[], final int N) {
        if (denominations == null) {
            throw new IllegalArgumentException("param(s) cannot be null.");
        }
        HashMap <Integer, Integer> map = new HashMap <>();
        int [][] change = new int [denominations.length][N+1];
        for (int d = 1; d < change.length; d++) {         // O(N)
            for (int n = 1; n < change[d].length; n++) {  // O(denominations.length)
                // exact amount found, must be most optimal solution :)
                if (denominations[d] == n) {
                    map.put(n, 1);
                }
                // a new elem can be made through combo of current and prev elems:
                if (denominations[d] < n && map.get(n) == null && map.get(n-denominations[d]) != 0 && map.get(n-denominations[d]) != PSEUDO_INFINITY) {
                    int betterFit = map.get(n-denominations[d])+1;
                    map.put(n, betterFit);
                }
                // potential that a combo of current denomination and previous found denomination will be more optimal:
                if (denominations[d] < n && map.get(n) != null && map.get(n-denominations[d]) != 0 && map.get(n-denominations[d]) != PSEUDO_INFINITY) {
                    int betterFit = map.get(n-denominations[d])+1;
                    if (map.get(n) != null) {
                        betterFit = Integer.min(betterFit, map.get(n) );
                        map.replace(n, betterFit);
                    }
                }
                // If the value is still zero then no solution was found. Set to no solution/pseudo infinity:
                if (map.get(n) == null || map.get(n) == PSEUDO_INFINITY + 1) {
                    map.put(n, PSEUDO_INFINITY);
                }
                // set the values of this iteration to my final return array:
                change[d][n] = map.get(n);
            }
        }
        return change;    
    } 

    /** 
     * Calculates the actual "payout" of coins returned for a given makeChange()
     * problem.
     *
     * The semantics of this method are undefined if the client doesn't supply
     * parameter values passed to, and returned from, a valid invocation of
     * makeChange().
     * 
     * @param c, the matrix returned by makeChange().
     * @param denominations the corresponding parameter to makeChange().
     * @param N the corresponding parameter to makeChange().
     * @return An array a_1, ... a_n of non-negative integers such that a_1 x
     * d_1, ..., + a_n x d_n = N.  The values of a_1, + ... + a_n are minimal for
     * the given denominations and amount of change that needs to be made.
     * @see #makeChange
     */
    public static int[] payOut(final int c[][], final int[] denominations, final int N) {
        if (c == null || denominations == null) {
            throw new IllegalArgumentException("param(s) cannot be null.");
        }
        if (c[denominations.length-1][N] == PSEUDO_INFINITY) {
            throw new IllegalStateException("No payout possible"); 
        }
        int [] payOutVar = new int [denominations.length];
        int currN = N;
        int i = c.length-1;
        while (i > 0 && currN > 0) {
            // the last change means that this elem must be used:
            if (c[i][currN] != c[i-1][currN]) {
                payOutVar[i] += 1;
                currN -= denominations[i];
                i = c.length-1;
            }
            else {
                i -= 1;    
            }
        }
        return payOutVar;
    }
}