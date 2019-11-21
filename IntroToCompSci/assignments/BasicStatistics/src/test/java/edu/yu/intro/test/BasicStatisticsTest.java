package edu.yu.intro.test;

/** " FindingRationalNumbersTest " Assignment #8
*
* @author Tom Bohbot
*/

import edu.yu.intro.BasicStatistics;
import org.junit.*;
import static org.junit.Assert.*;

public class BasicStatisticsTest {

	BasicStatistics statsInstance = new BasicStatistics();

// BEGINNING: TESTING GetNDatums METHOD
	@Test
	public void testingGetNDatums () {
		statsInstance.enter(1.0);
		assertEquals ("testing getNDatums Method for only one enter." , 1 , statsInstance.getNDatums() );
	}
	@Test
	public void testingGetNDatumsV2 () {
		statsInstance.enter(1.0);
		statsInstance.enter(2.0);
		statsInstance.enter(-1.0);
		statsInstance.enter(0.0);
		assertEquals ("testing getNDatums Method." , 4 , statsInstance.getNDatums() );
	}
	@Test
	public void testingGetNDatumsV3 () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals ("testing getNDatums Method." , 4 , statsInstance.getNDatums() );
	}
	@Test
	public void testingGetNDatumsV4 () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals ("testing getNDatums Method." , 8 , statsInstance.getNDatums() );
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals ("testing getNDatums Method." , 16 , statsInstance.getNDatums() );
	}
	@Test
	public void testingGetNDatumsV5 () {
		statsInstance.enter(1.0);
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		statsInstance.enter(-1.0);
		assertEquals ("testing getNDatums Method." , 6 , statsInstance.getNDatums() );
	}
	@Test 																					// ERROR WHEN TRYING THIS ONE!!!!
	public void testingGetNDatumsV6 () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		statsInstance.enter(1.0);
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals ("testing getNDatums Method." , 9 , statsInstance.getNDatums() );
	}
	@Test
	public void testingGetNDatumsV8 () {
		assertEquals ("testing getNDatums Method." , 0 , statsInstance.getNDatums() );
	}
	@Test
	public void testingGetNDatumsV7 () {
		statsInstance.enter(new double[] {0 , 0 , 0 , 0});
		assertEquals ("testing getNDatums Method." , 4 , statsInstance.getNDatums() );
	}
// END: TESTING GetNDatums METHOD

// BEGINNING: TESTING GetSum METHOD	
	@Test
	public void testingGetSum () {
		statsInstance.enter(1.0);
		statsInstance.enter(-1.0);
		statsInstance.enter(1.0);
		assertEquals("testing getSum Method." , 1 , statsInstance.getSum(), 0 );
	}
	@Test
	public void testingGetSumV2 () {
		statsInstance.enter(new double[] {-1.0 , 2.1 , 3.1 , 4.1});
		assertEquals("testing getSum Method." , 8.3 , statsInstance.getSum(), 0 );
	}
	@Test
	public void testingGetSumV3 () {
		statsInstance.enter(1.0);
		statsInstance.enter(new double[] {-1.0 , 2.1 , 3.1 , 4.1});
		assertEquals("testing getSum Method." , 9.3 , statsInstance.getSum(), 0 );
	}
	@Test
	public void testingGetSumV4 () { 
		statsInstance.enter(new double[] {-1.0 , 2.1 , 3.1 , 4.1});
		statsInstance.enter(1.0);
		assertEquals("testing getSum Method." , 9.3 , statsInstance.getSum(), 0 ); // error, this sum is equal to 1. 
	}
	@Test
	public void testingGetSumV5 () {
		statsInstance.enter(1.0);
		statsInstance.enter(new double[] {-1.0 , 2.1 , 3.1 , 4.1});
		assertEquals("testing getSum Method." , 9.3 , statsInstance.getSum(), 0 );
	}
	@Test
	public void testingGetNSumV6 () {
		statsInstance.enter(0.0);
		statsInstance.enter(new double[] {0 , 0 , 0 , 0});
		assertEquals ("testing getNDatums Method." , 0 , statsInstance.getSum(), 0 );
	}
	@Test
	public void testingGetSumV7 () {
		statsInstance.enter(new double[] {0 , 0 , 0 , 0});
		assertEquals ("testing getNDatums Method." , 0 , statsInstance.getSum(), 0 );
	}
	@Test
	public void testingGetSumV8 () {
		statsInstance.enter(new double[] {-1.0 , 2.1 , 3.1 , 4.1});
		assertEquals("testing getSum Method." , 8.3 , statsInstance.getSum(), 0 ); // error when trying this one. Array out of index error. 
		statsInstance.enter(1.0);
		statsInstance.enter(new double[] {-1.0 , 2.1 , 3.1 , 4.1});
		assertEquals("testing getSum Method." , 17.6 , statsInstance.getSum(), 0 ); // error when trying this one. Array out of index error. 
	}
	@Test
	public void testingGetSumV9 () {
		double nan = 0/0.0;
		assertEquals("testing getSum V8 Method." , nan , statsInstance.getSum(), 0.01 );
	}
// END: TESTING GetSum METHOD	

// BEGINNING: TESTING GetMean METHOD	
	@Test
	public void testingGetMean () {
		statsInstance.enter(1.0);
		statsInstance.enter(2.0);
		assertEquals("testing getMean V1 Method." , 1.5 , statsInstance.getMean(), 0.01 );
		statsInstance.enter(3.0);
		assertEquals("testing getMean V1 Method." , 2 , statsInstance.getMean(), 0.01 );
	}
	@Test
	public void testingGetMeanV2 () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals("testing getMean V2 Method." , 245.45 , statsInstance.getMean(), 0.01 );
	}
	@Test
	public void testingGetMeanV3 () {
		statsInstance.enter(1.0);
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals("testing getMean V3 Method." , 196.56 , statsInstance.getMean(), 0.01 );
	}
	@Test
	public void testingGetMeanV4 () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		statsInstance.enter(1.0);
		assertEquals("testing getMean V4 Method." , 196.56 , statsInstance.getMean(), 0.01 ); // ERROR
	}
	@Test
	public void testingGetMeanV5 () {
		statsInstance.enter(0.0);
		assertEquals("testing getMean V4 Method." , 0 , statsInstance.getMean(), 0.01 ); 
	}
	@Test
	public void testingGetMeanV6 () {
		double nan = 0/0.0;
		assertEquals("testing getMean V4 Method." , nan , statsInstance.getMean(), 0.01 );
	}
// END: TESTING GetMean METHOD		

// BEGINNING: TESTING GetStandardDeviation METHOD	
	@Test
	public void testingGetStandardDeviation () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals("testing getMean V1 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 );
	}
	@Test
	public void testingGetStandardDeviationV1 () {
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals("testing getMean V2 part 1 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 );
		statsInstance.enter(new double[] {22.3 , 872.1 , 39.8 , 47.6});
		assertEquals("testing getMean V2 part 2 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 );
	}
	@Test
	public void testingGetStandardDeviationV10 () {
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		statsInstance.enter(22.3);
		statsInstance.enter(872.1);
		assertEquals("testing getMean V2 part 1 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 );
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		statsInstance.enter(22.3);
		statsInstance.enter(872.1);
		assertEquals("testing getMean V2 part 2 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 );
	}
	@Test
	public void testingGetStandardDeviationV2 () {
		statsInstance.enter(22.3);
		statsInstance.enter(872.1);
		statsInstance.enter(new double[] {39.8 , 47.6});
		assertEquals("testing getMean V3 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 );
	}
	@Test
	public void testingGetStandardDeviationV3 () {
		statsInstance.enter(new double[] {22.3 , 872.1});
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		assertEquals("testing getMean V4 Method." , 361.913  , statsInstance.getStandardDeviation(), 0.01 ); // ERROR
	}
	@Test
	public void testingGetStandardDeviationV4 () {
		double nan = 0/0.0;
		assertEquals("testing getMean V5 Method." , nan  , statsInstance.getStandardDeviation(), 0.01 );
	}

// END: TESTING GetStandardDeviation METHOD	

// BEGINNING: TESTING getMin METHOD	
	@Test
	public void testingGetMinDeviation () {
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		statsInstance.enter(new double[] {22.3 , 872.1});
		assertEquals("testing GetMin V1 Method." , 22.3  , statsInstance.getMin(), 0.01 );
	}
	@Test
	public void testingGetMinDeviationV1 () {
		statsInstance.enter(new double[] {22.3 , 872.1});
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		assertEquals("testing GetMin V1 Method." , 22.3  , statsInstance.getMin(), 0.01 );  // Error
	}
	@Test
	public void testingGetMinDeviationV2 () {
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		assertEquals("testing GetMin V2 Method." , 39.8  , statsInstance.getMin(), 0.01 );
		statsInstance.enter(-5);
		assertEquals("testing GetMin V2 Method." , -5  , statsInstance.getMin(), 0.01 );
	}
	@Test
	public void testingGetMinDeviationV3 () {
		statsInstance.enter(-5);
		assertEquals("testing GetMin V3 Method." , -5  , statsInstance.getMin(), 0.01 );
	}
	@Test
	public void testingGetMinDeviationV4 () {
		statsInstance.enter(new double[] {22.3 , 872.1});
		statsInstance.enter(new double[] {22.3 , 872.1 , 5});
		assertEquals("testing GetMin V4 Method." , 5  , statsInstance.getMin(), 0.01 );
	}
	@Test
	public void testingGetMinDeviationV5 () {
		double min = Double.POSITIVE_INFINITY;
		assertEquals("testing GetMin V5 Method." , min  , statsInstance.getMin(), 0.01 );
	}

// END: TESTING getMin METHOD		

// BEGINNING: TESTING getMin METHOD	
	@Test
	public void testingGetMaxDeviation () {
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		assertEquals("testing GetMax V1 Method." , 47.6 , statsInstance.getMax(), 0.01 );
		statsInstance.enter(new double[] {22.3 , 872.1});
		assertEquals("testing GetMax V1 Method." , 872.1 , statsInstance.getMax(), 0.01 );
	}
	@Test
	public void testingGetMaxDeviationV2 () {
		statsInstance.enter(new double[] {22.3 , 872.1});
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		assertEquals("testing GetMax V1 Method." , 872.1  , statsInstance.getMax(), 0.01 );  // Error
	}
	@Test
	public void testingGetMaxDeviationV3 () {
		statsInstance.enter(39.8);
		statsInstance.enter(47.6);
		statsInstance.enter(-5);
		assertEquals("testing GetMax V2 Method." , 47.6  , statsInstance.getMax(), 0.01 );
	}
	@Test
	public void testingGetMaxDeviationV4 () {
		statsInstance.enter(-5);
		assertEquals("testing GetMax V3 Method." , -5  , statsInstance.getMax(), 0.01 );
	}
	@Test
	public void testingGetMaxDeviationV5 () {
		statsInstance.enter(new double[] {22.3 , 872.1});
		statsInstance.enter(new double[] {22.3 , 872.1 , 5});
		assertEquals("testing GetMax V4 Method." , 872.1  , statsInstance.getMax(), 0.01 );
	}
	@Test
	public void testingGetMaxDeviationV6 () {
		double max = Double.NEGATIVE_INFINITY;
		assertEquals("testing GetMax V5 Method." , max  , statsInstance.getMax(), 0.01 );
	}

// END: TESTING getMin METHOD		

}
