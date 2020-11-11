package edu.yu.introtoalgs;

/**
 * Testing: IntegerLRUCache.
 * @author  Tom Bohbot
 * @version November 11, 2020
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import edu.yu.introtoalgs.IntegerLRUCache;

import java.util.*;


public class IntegerLRUCacheTest {

    // IntegerLRUCacheTest cache = new IntegerLRUCache(5);

    @Test (expected = IllegalArgumentException.class)
	public void ptuNullKey () {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        cache.put(null , 1);
    }

    @Test (expected = IllegalArgumentException.class)
	public void putNullValue () {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        cache.put(1 , null);
    }

    @Test (expected = IllegalArgumentException.class)
	public void putNullBoth () {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        cache.put(null , null);
    }

    @Test (expected = IllegalArgumentException.class)
	public void getNullKey () {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        cache.get(null);
    }

    @Test (expected = IllegalArgumentException.class)
	public void removeNullKey () {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        cache.remove(null);
    }

    @Test
    public void testPut() {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        Integer putOne = cache.put(1 , 1);
        Integer putTwo = cache.put(2 , 1);
        Integer putThree= cache.put(3 , 1);
        Integer putFour = cache.put(4 , 1);
        Integer putFive = cache.put(5 , 1);
        Integer putOneTwice = cache.put(1 , 2);
        assertEquals(putOne , null);
        assertEquals(putTwo , null);
        assertEquals(putThree , null);
        assertEquals(putFour , null);
        assertEquals(putFive , null);
        assertEquals((Integer) 1 , putOneTwice);
        // add an aditional node to put it over the limit:
        Integer putSix = cache.put(6 , 1);
        assertEquals(putSix , null);
        assertEquals(null , cache.get(2) );
        

    }

}