package edu.yu.introtoalgs;

import java.util.List;

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
            // this method is O(logn) because as it resembles binary search.
            return ascendingList(list, key); 
        }
        else if (list.get(0) > list.get(lastElem) ) {
            // this method is O(logn) because as it resembles binary search.
            return descendingList(list, key);
        }
        else if (list.get(0) == key) {
            // this is constant time.
            return 0;
        }
        else {
            return -1;
        }
    }

    private static int ascendingList (final List<Integer> list , final int key) {
        // this method is O(logn) because as it resembles binary search.
        int low = 0;
        int high = list.size() - 1;
        int returnValue = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < list.get(mid) ){ high = mid - 1; }
            else if (key > list.get(mid) ){ low = mid + 1; }
            else { 
                // instead of returning, I let it keep looping through O(logn) times so that the first instance is ensured.
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
        // this method is O(logn) because as it resembles binary search.
        int low = 0;
        int high = list.size() - 1;
        int returnValue = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < list.get(mid) ){ low = mid + 1; }
            else if (key > list.get(mid) ){ high = mid - 1; }
            else { 
                // instead of returning, I let it keep looping through O(logn) times so that the first instance is ensured.
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
            // this method is O(logn) because as it resembles binary search.
            return equalToIndexAsceding(list);
        }
        else if (list.get(0) > list.get(lastElem) ) {
            // this method is O(logn) because as it resembles binary search.
            return equalToIndexDescending(list);
        }
        else if (list.get(0) <= lastElem && lastElem >= 0) {
            /** If the elem in the array is less than the array size and greater than zero,
             *  one of the indexes are certain to be equal to it's element, since all 
             *  the elements in the array are the same.
             */ 
            return list.get(0);
        }
        else {
            return -1;
        }
   }

   private static int equalToIndexAsceding (final List <Integer> list) {
        // this method is O(logn) because as it resembles binary search.
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
        // this method is O(logn) because as it resembles binary search.
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