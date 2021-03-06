package edu.yu.intro.test;

/** " AckermannFnTest " Assignment #6
*
* @author Tom Bohbot
*/

import edu.yu.intro.AckermannFn;
import org.junit.*;
import static org.junit.Assert.*;

public class AckermannFnTest {

	@Test
	public void ZeroZero() {
		assertEquals("testing when m = 0 and n = 0" , 1L , AckermannFn.ackermann(0,0) );
	}
	@Test
	public void ZeroOne() {
		assertEquals("testing when m = 0 and n = 1" , 3L , AckermannFn.ackermann(0,2) );
	}
	@Test
	public void OneZero() {
		assertEquals("testing when m = 1 and n = 0" , 2L , AckermannFn.ackermann(1,0) );
	}
	@Test
	public void TwoTwo() {
		assertEquals("testing when m = 2 and n = 2" , 7L , AckermannFn.ackermann(2,2) );
	}
	@Test
	public void OneTwo() {
		assertEquals("testing when m = 1 and n = 1" , 4L , AckermannFn.ackermann(1,2) );
	}
	// @Test
	// public void TwoT() {
	// 	assertEquals("testing when m = 1 and n = 0" , 13L , AckermannFn.ackermann(3,1) );
	// }
	// @Test
	// public void ThreeTwo() {
	// 	assertEquals("testing when m = 1 and n = 0" , 29L , AckermannFn.ackermann(3,2) );
	// }
	// @Test
	// public void ThreeThree() {
	// 	assertEquals("testing when m = 1 and n = 0" , 61L , AckermannFn.ackermann(3,3) );
	// }
	@Test(expected = StackOverflowError.class)
	public void StackOverflowError () {
		AckermannFn.ackermann(4,1);
	}
	// @Test(expected = StackOverflowError.class)
	// public void StackOverflowErrorv2 () {
	// 	AckermannFn.ackermann(1,4);
	// }
	@Test(expected = StackOverflowError.class)
	public void StackOverflowErrorv3 () {
		AckermannFn.ackermann(4,4);
	}
	@ Test (expected = IllegalArgumentException.class)
	public void IllegalArgumentException () {
		AckermannFn.ackermann(-1,1);
	}


}