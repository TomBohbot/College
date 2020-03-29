package edu.yu.cs.com1320.project.stage3.impl;

/**
 * Stage 3:
 * Trie Test Code
 * @author Tom Bohbot
 */

import edu.yu.cs.com1320.project.impl.TrieImpl;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TrieTest {

    TrieImpl trie = new TrieImpl();

    @Test
    public void TestPutNoDups() {
        trie.put("she " , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        List <Integer> testValue = new ArrayList <Integer> ();
        testValue.add(0);
        testValue.add(6);
        testValue.add(7);
        assertEquals("Testing if get works" , testValue , trie.getAllSorted("she") );
    }

    @Test
    public void TestGetNothing() {
        trie.put("she " , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        List <Integer> testValue = new ArrayList <Integer> ();
        assertEquals("Testing if get works" , testValue , trie.getAllSorted("fashlb") );
    }

    // @Test
    // public void TestPutWithDups() {
    //     trie.put("she " , 0);
    //     trie.put("sells" , 1);
    //     trie.put("sea" , 2);
    //     trie.put("shells" , 3);
    //     trie.put("by" , 4);
    //     trie.put("the" , 5);
    //     trie.put("she" , 6);
    //     trie.put("she says she" , 7);
    //     List <Integer> testValue = new ArrayList <Integer> ();
    //     testValue.add(7);
    //     testValue.add(6);
    //     testValue.add(0);
    //     assertEquals("Testing if get works" , testValue , trie.getAllSorted("she") );
    // }

    @Test
    public void TestGetPrefixNoDups() {
        trie.put("she " , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        trie.put("shell" , 8);
        List <Integer> testValue = new ArrayList <Integer> ();
        testValue.add(0);
        testValue.add(6);
        testValue.add(7);
        testValue.add(8);
        testValue.add(3);
        assertEquals("Testing if get works" , testValue , trie.getAllWithPrefixSorted("sh") );
    }

    @Test
    public void TestDeleteAll() {
        trie.put("she " , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        List <Integer> testValue = new ArrayList <Integer> ();
        testValue.add(0);
        testValue.add(6);
        testValue.add(7);
        assertEquals("Testing if get works" , testValue , trie.getAllSorted("she") );
        Set <Integer> deletedVals = new HashSet<Integer>(testValue); 
        assertEquals("Testing if delete works" , deletedVals , trie.deleteAll("she") );
        // Set <Integer> deletedValsEmpty = new HashSet<Integer>(); 
        // assertEquals("Testing getting docs after deleting all docs" , deletedValsEmpty , trie.getAllSorted("she")); // NEED TO FIX THIS PROBLEM. DELETE UNECESSARY NODES!!
    }

    @Test
    public void TestDeleteAllGotNothing() {
        trie.put("she " , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        List <Integer> testValue = new ArrayList <Integer> ();
        testValue.add(0);
        testValue.add(6);
        testValue.add(7);
        assertEquals("Testing if get works" , testValue , trie.getAllSorted("she") );
        Set <Integer> deletedVals = new HashSet<Integer>(); 
        assertEquals("Testing if delete works" , deletedVals , trie.deleteAll("hi") );
    }

    @Test
    public void TestDeleteSingleNode() {
        trie.put("she" , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        List <Integer> testValue = new ArrayList <Integer> ();
        testValue.add(0);
        testValue.add(6);
        testValue.add(7);
        assertEquals("Testing if get works" , testValue , trie.getAllSorted("she") );
        List <Integer> newTestValue = new ArrayList <Integer> ();
        newTestValue.add(0);
        newTestValue.add(7);
        assertEquals("Testing if delete works" , 6 , trie.delete("she" , 6) );
        assertEquals("Testing getting value after deleting" , newTestValue , trie.getAllSorted("she"));
    }

    @Test
    public void TestDeleteSingleNodeNullReturn() {
        trie.put("she " , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        assertEquals("Testing Deleting Single Node when nothing to delete" , null , trie.delete("she" , 8) );
    }

    @Test
    public void TestDeletePrefix() {
        trie.put("she" , 0);
        trie.put("sells" , 1);
        trie.put("sea" , 2);
        trie.put("shells" , 3);
        trie.put("by" , 4);
        trie.put("the" , 5);
        trie.put("she" , 6);
        trie.put("she says she" , 7);
        List <Integer> testValue = new ArrayList <Integer> ();
        testValue.add(0);
        testValue.add(6);
        testValue.add(7);
        testValue.add(3);
        assertEquals("Testing if get works" , testValue , trie.getAllWithPrefixSorted("sh") );
        Set <Integer> deletedVals = new HashSet<Integer>(testValue); 
        assertEquals("Testing if delete works" , deletedVals , trie.deleteAllWithPrefix("sh") );
        assertEquals("Testing getting value after deleting" , null , trie.getAllSorted("she"));  // NEED TO FIX THIS PROBLEM. DELETE UNECESSARY NODES!!
    }
}