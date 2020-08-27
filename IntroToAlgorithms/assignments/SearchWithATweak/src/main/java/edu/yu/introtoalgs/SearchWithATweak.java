package edu.yu.introtoalgs;

import java.util.List;
import java.util.ArrayList;

/**
 * SearchWithATweak
 * Assignment #1
 * @author Tom Bohbot
 */

public class SearchWithATweak {

    /** Searches the specified sorted list of Integers for
    * the specified key. The list must be sorted prior to 1
    * making this call: otherwise , the results are
    * undefined. If the list contains multiple elements
    * with the specified value , will return the index of
    * the first instance of that value.
    *
    * @param list the list to be searched: the list is
    * assumed to be sorted
    * @param key the value to be searched for
    * @return Index of the search key, if it is contained
    * in the list; otherwise , returns -1.
    */

    private static int findFirstInstanceBruteForce (final List<Integer> list , final int key) {
        // The following code is O(n):
        ArrayList <Integer> newList = (ArrayList <Integer>) list;
        for (int i = 0; i < newList.size(); i ++) {
            if (newList.get(i) == key) {
                return newList.indexOf(key);
            }
        }
        return -1;
    }

    public static int findFirstInstance (final List<Integer> list , final int key) {
        // The following code is O(logn):
        ArrayList <Integer> newList = (ArrayList <Integer>) list;
        int low = 0;
        int high = newList.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < newList.get(mid) ){ high = mid - 1; }
            else if (key > newList.get(mid) ){ low = mid + 1; }
            else { 
                // In the case of duplicates:
                while (mid != 0 && newList.get(mid - 1) == key) {
                    if (mid == 0) {
                        return mid;
                    }
                    mid -= 1;
                }
                return mid;
            }
        }
        return -1;
    }


    /** Searched the specified sorted list of distinct
    * Integers and returns an index i with the property
    * that the value of the ith element is itself i.
    *
    *
    * @param list the list to be searched: the list is
    * assumed to be sorted and contains distinct values
    * @return Index satisfying the specified property if any
    * such elements exist; otherwise , return -1
    */
   public static int elementEqualToItsIndex (final List <Integer> list) {
        // just for now:
        return elementEqualToItsIndexBruteForce(list);


        // ArrayList <Integer> newList = (ArrayList <Integer>) list;
        // int low = 0;
        // int high = newList.size() - 1;
        // while (low <= high) {
        //     for (int i = 0; i < high; i ++) {
        //         int mid = low + (high - low) / 2;
        //         if (newList.get(i) < mid ){ high = mid - 1; }
        //         else if (newList.get(i) > mid ){ low = mid + 1; }
        //         else { return mid; }
        //     }
        //     low = 0;
        //     high = newList.size() - 1;
        // }
        // return -1;
   }

   private static int elementEqualToItsIndexBruteForce (final List <Integer> list) {
        // Following code is brute force:
        ArrayList <Integer> newList = (ArrayList<Integer>) list;
        for (int i = 0; i < newList.size(); i ++) {
            for (int j = 0; j < newList.size(); j ++ ){
                if (newList.get(i) == i ) {
                    return i;
                }
            }       
        }
        return -1;
   }
}