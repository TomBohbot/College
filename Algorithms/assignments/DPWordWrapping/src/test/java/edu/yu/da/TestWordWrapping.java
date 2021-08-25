package edu.yu.da;

/** 
 * Pay out Test Code 
 * @author Tom Bohbot
 * @version February 3, 2021
 */

import edu.yu.da.DPWordWrapping;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestWordWrapping {

	@Test
	public void givenTest () {
        String [] words = new String []{"a", "b"};
        int lineLength = 5;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", 4, obj.minimumPenalty() );
        List <String> lineOne = Arrays.asList("a", "b");
        Map <Integer, List<String>> map = obj.getLayout();
        assertEquals("Testing Case 1", lineOne, map.get(0));
    }

    @Test
	public void vanilla () {
        String [] words = new String []{"a", "b", "c"};
        int lineLength = 5;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", 0, obj.minimumPenalty() );
        List <String> lineOne = Arrays.asList("a", "b", "c");
        Map <Integer, List<String>> map = obj.getLayout();
        assertEquals("Testing Case 1", lineOne, map.get(0));
    }

    @Test
	public void vanillaTwo () {
        String [] words = new String []{"a", "b", "c", "d"};
        int lineLength = 5;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", 8, obj.minimumPenalty() );
        List <String> lineOne = Arrays.asList("a", "b");
        List <String> lineTwo = Arrays.asList("c", "d");
        Map <Integer, List<String>> map = obj.getLayout();
        assertEquals("Testing Case 1", lineOne, map.get(0));
        assertEquals("Testing Case 1", lineTwo, map.get(1));
    }

    @Test
	public void vanillaThree () {
        String [] words = new String []{"ab", "b", "cde", "db"};
        int lineLength = 5;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", 14, obj.minimumPenalty() );
        List <String> lineOne = Arrays.asList("ab", "b");
        List <String> lineTwo = Arrays.asList("cde");
        List <String> lineThree = Arrays.asList("db");
        Map <Integer, List<String>> map = obj.getLayout();
        assertEquals("Testing Case 1", lineOne, map.get(0));
        assertEquals("Testing Case 1", lineTwo, map.get(1));
        assertEquals("Testing Case 1", lineThree, map.get(2));
    }

    @Test
	public void vanillaFour () {
        String [] words = new String []{"ab", "bbs", "cde", "d"};
        int lineLength = 5;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", 13, obj.minimumPenalty() );
        List <String> lineOne = Arrays.asList("ab");
        List <String> lineTwo = Arrays.asList("bbs");
        List <String> lineThree = Arrays.asList("cde", "d");
        Map <Integer, List<String>> map = obj.getLayout();
        assertEquals("Testing Case 1", lineOne, map.get(0));
        assertEquals("Testing Case 1", lineTwo, map.get(1));
        assertEquals("Testing Case 1", lineThree, map.get(2));
    }

    @Test
	public void vanillaFive () {
        String [] words = new String []{"aa", "bb", "ccc", "d", "e", "f"};
        int lineLength = 6;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", 11, obj.minimumPenalty() );
        List <String> lineOne = Arrays.asList("aa", "bb");
        List <String> lineTwo = Arrays.asList("ccc");
        List <String> lineThree = Arrays.asList("d", "e", "f");
        Map <Integer, List<String>> map = obj.getLayout();
        assertEquals("Testing Case 1", lineOne, map.get(0));
        assertEquals("Testing Case 1", lineTwo, map.get(1));
        assertEquals("Testing Case 1", lineThree, map.get(2));
    }

    @Test
	public void vanillaSix () {
        String [] words = new String []{"aa", "bb", "ccc", "d", "e", "f"};
        int lineLength = 2;
        DPWordWrapping obj = new DPWordWrapping(words, lineLength);
        assertEquals("Testing Case 1", Integer.MAX_VALUE , obj.minimumPenalty() );
    }
}