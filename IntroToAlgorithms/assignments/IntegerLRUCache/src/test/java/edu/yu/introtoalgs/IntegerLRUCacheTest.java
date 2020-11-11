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
	public void negativeCapacity () {
        IntegerLRUCache cache = new IntegerLRUCache(-5);
    }    

    @Test (expected = IllegalArgumentException.class)
	public void putNullKey () {
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

    @Test
    public void testPutTwo() {
        IntegerLRUCache cache = new IntegerLRUCache(5);
        Integer putOne = cache.put(1 , 10);
        Integer putTwo = cache.put(2 , 20);
        Integer putThree= cache.put(3 , 30);
        Integer putFour = cache.put(4 , 40);
        Integer putFive = cache.put(5 , 50);
        Integer putOneTwice = cache.put(1 , 100);
        assertEquals(putOne , null);
        assertEquals(putTwo , null);
        assertEquals(putThree , null);
        assertEquals(putFour , null);
        assertEquals(putFive , null);
        assertEquals((Integer) 10 , putOneTwice);
        // add an aditional node to put it over the limit:
        Integer putSix = cache.put(6 , 60);
        assertEquals(putSix , null);
        assertEquals(null , cache.get(2) );
        // Testing that the gets work correctly:
        assertEquals( (Integer) 100 , cache.get(1) );
        assertEquals(null , cache.get(2));
        assertEquals((Integer) 30 , cache.get(3));
        assertEquals((Integer) 40 , cache.get(4));
        assertEquals((Integer) 50 , cache.get(5));
        assertEquals((Integer) 60 , cache.get(6));
        // Now I know that the gets work properly/
        // Time to test the remove:
        assertEquals(null , cache.remove( (Integer) 2) );
        assertEquals((Integer) 100 , cache.remove( (Integer) 1) );
        assertEquals((Integer) 50 , cache.remove( (Integer) 5) );
        assertEquals( null , cache.get(1) );
        assertEquals(null , cache.get(2));
        assertEquals((Integer) 30 , cache.get(3));
        assertEquals((Integer) 40 , cache.get(4));
        assertEquals(null , cache.get(5));
        assertEquals((Integer) 60 , cache.get(6));
        // Removing elements works properly. I currently have 3 elements out of 5 max in cache.
        assertEquals(null , cache.put(8 , 80) );
        assertEquals(null , cache.put(9 , 90) );
        assertEquals((Integer) 80 , cache.get(8));
        assertEquals((Integer) 90 , cache.get(9));
        // update all the elements:
        assertEquals((Integer) 90 , cache.put(9 , 900));
        assertEquals((Integer) 60 , cache.put(6 , 600));
        assertEquals((Integer) 30 , cache.put(3 , 300));
        assertEquals((Integer) 40 , cache.put(4 , 400));
        assertEquals((Integer) 80 , cache.put(8 , 800));
        assertEquals((Integer) 900 , cache.get(9));
        assertEquals((Integer) 600 , cache.get(6));
        assertEquals((Integer) 300 , cache.get(3));
        assertEquals((Integer) 400 , cache.get(4));
        assertEquals((Integer) 800 , cache.get(8));
        // updating elements causes no problem. Now replace all alements except most recently used 2:
        cache.put(100 , 1);
        cache.put(200 , 2);
        cache.put(300 , 3);
        assertEquals((Integer) null , cache.get(9));
        assertEquals((Integer) null , cache.get(6));
        assertEquals((Integer) null , cache.get(3));
        assertEquals((Integer) 400 , cache.get(4));
        assertEquals((Integer) 800 , cache.get(8));
        assertEquals((Integer) 1 , cache.get(100));
        assertEquals((Integer) 2 , cache.get(200));
        assertEquals((Integer) 3 , cache.get(300));
        cache.put(400 , 4);
        cache.put(500 , 5);
        assertEquals((Integer) null , cache.get(1));
        assertEquals((Integer) null , cache.get(2));
        assertEquals((Integer) null , cache.get(3));
        assertEquals((Integer) null , cache.get(4));
        assertEquals((Integer) null , cache.get(5));
        assertEquals((Integer) null , cache.get(6));
        assertEquals((Integer) null , cache.get(8));
        assertEquals((Integer) null , cache.get(9));
        assertEquals((Integer) 1 , cache.get(100));
        assertEquals((Integer) 2 , cache.get(200));
        assertEquals((Integer) 3 , cache.get(300));
        assertEquals((Integer) 4 , cache.get(400));
        assertEquals((Integer) 5 , cache.get(500));
    }

    @Test
    public void oneCapacity() {
        IntegerLRUCache cache = new IntegerLRUCache(1);
        assertEquals((Integer) null , cache.put(1 , 10));
        assertEquals((Integer) 10 , cache.put(1 , 100));
        assertEquals((Integer) 100 , cache.put(1 , 1000));
        assertEquals((Integer) null , cache.put(2 , 20));
        assertEquals((Integer) null , cache.get(1));
        assertEquals((Integer) 20 , cache.get(2));
        assertEquals((Integer) null , cache.put(3 , 30) );
        assertEquals((Integer) null , cache.put(4 , 40) );
        assertEquals((Integer) null , cache.get(1));
        assertEquals((Integer) null , cache.get(2));
        assertEquals((Integer) null , cache.get(3));
        assertEquals((Integer) 40 , cache.get(4));
        assertEquals((Integer) 40 , cache.put(4 , 400) );
        assertEquals((Integer) 400 , cache.get(4));
        assertEquals((Integer) 400 , cache.put(4 , 500) );
        assertEquals((Integer) 500 , cache.get(4));
        assertEquals((Integer) 500 , cache.put(4 , 500) );
        assertEquals((Integer) 500 , cache.get(4));
        assertEquals((Integer) null , cache.remove( (Integer) 1) );
        assertEquals((Integer) null , cache.remove( (Integer) 2) );
        assertEquals((Integer) null , cache.remove( (Integer) 3) );
        assertEquals((Integer) 500 , cache.remove( (Integer) 4) );
        assertEquals((Integer) null , cache.get(1));
        assertEquals((Integer) null , cache.get(2));
        assertEquals((Integer) null , cache.get(3));
        assertEquals((Integer) null , cache.get(4));
        assertEquals((Integer) null , cache.put(4 , 40) );
        assertEquals((Integer) 40 , cache.get(4));
    }

    @Test 
	public void zeroIsCapacity () {
        IntegerLRUCache cache = new IntegerLRUCache(0);
        assertEquals((Integer) null , cache.put(1 , 10) );
        assertEquals((Integer) null , cache.put(1 , 100) );
        assertEquals((Integer) null , cache.get(1));
        assertEquals((Integer) null , cache.remove( (Integer) 1) );
    }    
}