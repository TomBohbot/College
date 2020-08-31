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
    public static int findFirstInstance (final List<Integer> list , final int key) {
        // The following code is O(logn):
        if (list == null || list.size() == 0) {
            return -1;
        }
        int lastElem = list.size() - 1;
        if (list.get(0) < list.get(lastElem) ) {
            return ascendingList(list, key);
        }
        else if (list.get(0) > list.get(lastElem) ) {
            return descendingList(list, key);
        }
        else if (list.get(0) == key) {
            return 0;
        }
        else {
            return -1;
        }
    }

    private static int ascendingList (final List<Integer> list , final int key) {
        int low = 0;
        int high = list.size() - 1;
        int returnValue = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < list.get(mid) ){ high = mid - 1; }
            else if (key > list.get(mid) ){ low = mid + 1; }
            else { 
                returnValue = mid;
                high = mid - 1;
            }
        }
        if (returnValue != -1) {
            return returnValue;
        }
        return -1;
    }

    private static int descendingList (final List<Integer> list , final int key) {
        int low = 0;
        int high = list.size() - 1;
        int returnValue = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < list.get(mid) ){ low = mid + 1; }
            else if (key > list.get(mid) ){ high = mid - 1; }
            else { 
                returnValue = mid;
                high = mid - 1;
            }
        }
        if (returnValue != -1) {
            return returnValue;
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
        // The following code is O(logn):
        if (list == null || list.size() == 0) {
            return -1;
        }
        int lastElem = list.size() - 1;
        if (list.get(0) < list.get(lastElem) ) {
            return equalToIndexAsceding(list);
        }
        else if (list.get(0) > list.get(lastElem) ) {
            return equalToIndexDescending(list);
        }
        else if (list.get(0) <= lastElem) {
            return list.get(0);
        }
        else {
            return -1;
        }
   }

   private static int equalToIndexAsceding (final List <Integer> list) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid < list.get(mid) ){ high = mid - 1; }
            else if (mid > list.get(mid) ){ low = mid + 1; }
            else { 
                return mid; 
            }
        }
        return -1; 
   }

   private static int equalToIndexDescending (final List <Integer> list) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid < list.get(mid) ){ low = mid + 1; }
            else if (mid > list.get(mid) ){ high = mid - 1; }
            else { 
                return mid; 
            }
        }
        return -1; 
    }
}