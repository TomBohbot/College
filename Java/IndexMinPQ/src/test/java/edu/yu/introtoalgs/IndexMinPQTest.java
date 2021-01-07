package edu.yu.introtoalgs;

/**
 * Testing Graphs And Mazes.
 * @author  Tom Bohbot
 * @version December 2, 2020
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import edu.yu.introtoalgs.IndexMinPQ;

import java.util.*;


public class IndexMinPQTest <Key> {

    @Test (expected = RuntimeException.class)
    public void maxInput() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.insert(0, 1);
        obj.insert(1, 2);
        obj.insert(2, 3);
        obj.insert(4, 5);
    }

    @Test (expected = RuntimeException.class)
    public void negativeMaxN() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(-1);
    }

    @Test (expected = RuntimeException.class)
    public void duplicateIndexes() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.insert(0, 1);
        obj.insert(1, 2);
        obj.insert(2, 3);
        obj.insert(1, 4);
    }

    @Test (expected = RuntimeException.class)
    public void nullKey() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.insert(0, 1);
        obj.insert(1, 2);
        obj.insert(2, 3);
        obj.insert(1, null);
    }

    @Test (expected = RuntimeException.class)
    public void negativeIndex() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.insert(-1, 1);
    }

    @Test (expected = RuntimeException.class)
    public void indexTooLarge() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.insert(5, 1);
    }

    @Test (expected = RuntimeException.class)
    public void negativeIndexTwo() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.keyOf(-1);
    }

    @Test (expected = RuntimeException.class)
    public void indexTooLargeTwo() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(4);
        obj.keyOf(5);
    }

    @Test
    public void vanilla() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(5);
        obj.insert(0, 1);
        obj.insert(1, 2);
        obj.insert(2, 3);
        obj.insert(3, 4);
        assertEquals((Integer) 1 , obj.keyOf(0) );
        assertEquals((Integer) 2 , obj.keyOf(1) );
        assertEquals((Integer) 3 , obj.keyOf(2) );
        assertEquals((Integer) 4 , obj.keyOf(3) );
        assertEquals((Integer) 4 , (Integer) obj.size() );
        assertEquals(false , obj.isEmpty() );
        assertEquals((Integer) 1 , obj.minKey() );
        assertEquals((Integer) 0 , (Integer) obj.minIndex() );
    }    

    @Test
    public void iteratorTest() {
        IndexMinPQ <Integer> obj = new IndexMinPQ<Integer>(10);
        obj.insert(0, 1);
        obj.insert(1, 2);
        obj.insert(2, 3);
        obj.insert(3, 4);
        Iterator <Integer> iterator = obj.iterator();
        while (iterator.hasNext() ) {
            System.out.println(iterator.next() );
        }
        assertEquals((Integer) 1 , obj.keyOf(0) );
        assertEquals((Integer) 2 , obj.keyOf(1) );
        assertEquals((Integer) 3 , obj.keyOf(2) );
        assertEquals((Integer) 4 , obj.keyOf(3) );
        assertEquals((Integer) 4 , (Integer) obj.size() );
        assertEquals(false , obj.isEmpty() );
        assertEquals((Integer) 1 , obj.minKey() );
        assertEquals((Integer) 0 , (Integer) obj.minIndex() );
    }

    @Test
    public void iteratorTestTwo() {
        IndexMinPQ <String> obj = new IndexMinPQ<String>(5);
        obj.insert(0, "z");
        obj.insert(1, "c");
        obj.insert(2, "a");
        obj.insert(3, "b");
        obj.insert(4, "m");
        Iterator <Integer> iterator = obj.iterator();
        while (iterator.hasNext() ) {
            System.out.println(iterator.next() );
        }

    }

    @Test
    public void vanillaWithString() {
        IndexMinPQ <String> obj = new IndexMinPQ<String>(10);
        obj.insert(0, "z");
        obj.insert(1, "c");
        obj.insert(2, "a");
        obj.insert(3, "b");
        obj.insert(4, "m");
        assertEquals( "z" , obj.keyOf(0) );
        assertEquals( "c" , obj.keyOf(1) );
        assertEquals( "a" , obj.keyOf(2) );
        assertEquals( "b" , obj.keyOf(3) );
        assertEquals( "m" , obj.keyOf(4) );
        assertEquals( (Integer) 5 , (Integer) obj.size() );
        assertEquals(false , obj.isEmpty() );
        assertEquals("a" , obj.minKey() );
        assertEquals((Integer) 2 , (Integer) obj.minIndex() );
        assertEquals((Integer) 2 , (Integer) obj.delMin() );
        assertEquals((Integer) 3 , (Integer) obj.delMin() );
        assertEquals((Integer) 1 , (Integer) obj.delMin() );
        assertEquals((Integer) 4 , (Integer) obj.delMin() );
        assertEquals((Integer) 0 , (Integer) obj.delMin() );
        assertEquals(true , obj.isEmpty() );
        assertEquals( (Integer) 0 , (Integer) obj.size() );
    }    
}
