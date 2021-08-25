package edu.yu.da;
 
/** 
 * Test MaxRectangle
 * @author Tom Bohbot
 * @version April 5, 2021s
 */

import edu.yu.da.MaxRectangle;

import org.junit.*;
import static org.junit.Assert.*;

public class TestMaxRectangle {

    MaxRectangle obj = new MaxRectangle();
    
    // Illegal Paramaters:
    @Test(expected = IllegalArgumentException.class)
	public void nullArray() {          
        obj.get(null);
	}

    @Test(expected = IllegalArgumentException.class)
    public void zeroElem() {    
        int [] heights = new int[0];      
        obj.get(heights);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneElem() {          
        int [] heights = new int []{1};      
        obj.get(heights);
    }

    // Vanilla Tests:
    @Test
	public void testOne () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{5,1,1,1,5};
        assertEquals("Test 1: area",   20, maxRect.get(heights).max   );
        assertEquals("Test 1: left",   0, maxRect.get(heights).left  );
        assertEquals("Test 1: right",  4, maxRect.get(heights).right );
        assertEquals("Test 1: height", 5, maxRect.get(heights).height);
    }

    @Test
	public void testTwo () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{5,1,1,1,4};
        assertEquals("Test 2: area",  16, maxRect.get(heights).max   );
        assertEquals("Test 2: left",  0, maxRect.get(heights).left  );
        assertEquals("Test 2: right", 4, maxRect.get(heights).right );
        assertEquals("Test 2: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testThree () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{4,1,1,1,5};
        assertEquals("Test 3: area",  16, maxRect.get(heights).max   );
        assertEquals("Test 3: left",  0, maxRect.get(heights).left  );
        assertEquals("Test 3: right", 4, maxRect.get(heights).right );
        assertEquals("Test 3: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testFour () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{1,5,1,1,1,5};
        assertEquals("Test 4: area",  20, maxRect.get(heights).max   );
        assertEquals("Test 4: left",   1, maxRect.get(heights).left  );
        assertEquals("Test 4: right",  5, maxRect.get(heights).right );
        assertEquals("Test 4: height", 5, maxRect.get(heights).height);
    }

    @Test
	public void testFive () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{0,5,1,1,1,4};
        assertEquals("Test 5: area", 16, maxRect.get(heights).max   );
        assertEquals("Test 5: left",  1, maxRect.get(heights).left  );
        assertEquals("Test 5: right", 5, maxRect.get(heights).right );
        assertEquals("Test 5: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testSix () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{3,4,1,1,1,5,0};
        assertEquals("Test 6: area", 16, maxRect.get(heights).max   );
        assertEquals("Test 6: left",  1, maxRect.get(heights).left  );
        assertEquals("Test 6: right", 5, maxRect.get(heights).right );
        assertEquals("Test 6: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testSeven () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{1,5,1,1,1,5};
        assertEquals("Test 7: area",  20, maxRect.get(heights).max   );
        assertEquals("Test 7: left",   1, maxRect.get(heights).left  );
        assertEquals("Test 7: right",  5, maxRect.get(heights).right );
        assertEquals("Test 7: height", 5, maxRect.get(heights).height);
    }

    @Test
	public void testEight () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{0,5,1,1,1,4,1};
        assertEquals("Test 8: area", 16, maxRect.get(heights).max   );
        assertEquals("Test 8: left",  1, maxRect.get(heights).left  );
        assertEquals("Test 8: right", 5, maxRect.get(heights).right );
        assertEquals("Test 8: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testNine () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{3,4,1,1,1,5,2};
        assertEquals("Test 9: area", 16, maxRect.get(heights).max   );
        assertEquals("Test 9: left",  1, maxRect.get(heights).left  );
        assertEquals("Test 9: right", 5, maxRect.get(heights).right );
        assertEquals("Test 9: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testTen () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{3,1,5,1,1,1,5,1,2};
        assertEquals("Test 10: area",  20, maxRect.get(heights).max   );
        assertEquals("Test 10: left",   2, maxRect.get(heights).left  );
        assertEquals("Test 10: right",  6, maxRect.get(heights).right );
        assertEquals("Test 10: height", 5, maxRect.get(heights).height);
    }

    @Test
	public void testEleven () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{1,1,5,1,1,1,4,1,1};
        assertEquals("Test 11: area", 16, maxRect.get(heights).max   );
        assertEquals("Test 11: left",  2, maxRect.get(heights).left  );
        assertEquals("Test 11: right", 6, maxRect.get(heights).right );
        assertEquals("Test 11: height",4, maxRect.get(heights).height);
    }

    @Test
	public void testTwelve () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{1,0,4,1,1,1,5,2,2};
        assertEquals("Test 12: area", 16, maxRect.get(heights).max   );
        assertEquals("Test 12: left",  2, maxRect.get(heights).left  );
        assertEquals("Test 12: right", 6, maxRect.get(heights).right );
        assertEquals("Test 12: height",4, maxRect.get(heights).height);
    }

    @Test
	public void sanityCheck () {
        MaxRectangle maxRect = new MaxRectangle();
        int [] heights = new int []{5, 5, 5, 5};
        assertEquals("Test 12: area", 15, maxRect.get(heights).max   );
        assertEquals("Test 12: left",  0, maxRect.get(heights).left  );
        assertEquals("Test 12: right", 3, maxRect.get(heights).right );
        assertEquals("Test 12: height",5, maxRect.get(heights).height);
    }
}
