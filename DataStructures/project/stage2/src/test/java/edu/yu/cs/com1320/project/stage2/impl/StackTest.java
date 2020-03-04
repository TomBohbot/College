package edu.yu.cs.com1320.project.stage2.impl;

/**
 * "Stack Test Code" Stage 2
 *
 * @author Tom Bohbot
 *
 */


import edu.yu.cs.com1320.project.stage2.impl.StackImpl;
import org.junit.*;
import static org.junit.Assert.*;

public class StackTest {

    StackImpl <String> stack = new StackImpl <String>();

    @Test
    public void testPush() {
        /** 
         * Test Cases:
         * 1) If they are accuratley pushed in a LIFO Manner.
         * 2) If the array is accurately doubled, and all values are copied correctly.
         */  
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");   
        assertEquals("Testing if peek is 5" , "5" , stack.peek() );
        assertEquals("Testing if pop is 5" , "5" , stack.pop() );
        assertEquals("Testing if pop is 4" , "4" , stack.pop() );
        assertEquals("Testing if pop is 3" , "3" , stack.pop() );
        assertEquals("Testing if pop is 2" , "2" , stack.pop() );
        assertEquals("Testing if peek is 2" , "1" , stack.peek() );
        assertEquals("Testing if pop is 1" , "1" , stack.pop() );
        assertEquals("Testing if pop is null" , null , stack.peek() );
    }

    @Test
    public void testSize() {
        /** 
         * Test Cases:
         * 1) If the length is correct as I add elements.
         * 2) If length is correct as I remove elemts.
         */  

         stack.push("1");
         stack.push("2");
         stack.push("3");
         stack.push("4");
         stack.push("5");
         assertEquals("Testing if length is 5" , 5 , stack.size() );
         stack.pop();
         stack.pop();
         assertEquals("Testing if length is 3" , 3 , stack.size() );
    }

    @Test
    public void testNull() {
        /** 
         * Test Cases:
         * 1) If length of stack is zero when zero elems inserted
         * 2) If return value of pop is null when zero elems inserted in stack
         * 3) If return value of peek is null when zero elems inserted in stack
         */  
        assertEquals("Testing if 0 when elem is zero" , 0 , stack.size() );
        assertEquals("Testing if null when elem is zero" , null , stack.pop() );
        assertEquals("Testing if null when elem is zero" , null , stack.peek() );
    }
}