package Final.code;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;



/**
 * The test class TestRouter.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestRouter
{

    //private TrieST<Integer> router;
    private IPRouter router;
    /**
     * Default constructor for test class TestRouter
     */
    public TestRouter(){
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
       this.router = new IPRouter(21,4); 
        try {
            router.loadRoutes("routes1.txt");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Bad routes file name. Tests aborted");
        }
    }

    /**
     * Handle an unroutable address
     */
    @Test
    public void testBadRoute()
    {
        IPAddress address = new IPAddress("73.73.0.1");
        assertEquals(-1, this.router.getRoute(address));
    }

    /**
     * Handle an address that only matches one prefix
     */
    @Test
    public void port2Test() {
        IPAddress address = new IPAddress("85.2.0.1");
        int res = this.router.getRoute(address);
        assertEquals(2, res);
    }

    /**
     * Handle an address that only matches multiple prefixes. Only the longest one counts
     */
    @Test
    public void port1Test() {
        IPAddress address = new IPAddress("85.85.85.85");
        int res = this.router.getRoute(address);
        assertEquals(1, res);
    }

    @Test
    public void usingText2() {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.24.24.24");
        int res = this.router.getRoute(address);
        assertEquals(20, res);
    }

    @Test
    public void usingTextFirstOne() {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.96.0.0");
        int res = this.router.getRoute(address);
        System.out.println(address);
        assertEquals(20, res);
    }

    @Test
    public void usingText2GetNothing() {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("68.68.68.68");
        int res = this.router.getRoute(address);
        assertEquals(-1, res);
    }

    @Test
    public void usingText2V3 () {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.30.24.24");
        int res = this.router.getRoute(address);
        assertEquals(5, res);
    }

    @Test
    public void usingText2V4 () {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.60.24.24");
        int res = this.router.getRoute(address);
        assertEquals(5, res);
    }

    @Test
    public void usingText2V5 () {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.91.5.88");
        int res = this.router.getRoute(address);
        assertEquals(7, res);
    }

    @Test
    public void usingText2V6 () {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.64.0.0");
        int res = this.router.getRoute(address);
        assertEquals(20, res);
    }

    // @Test
    // public void printOutIpAdresses () {
    //     IPAddress q = new IPAddress ("24.0.0.0/8");
    //     IPAddress w = new IPAddress ("24.0.0.0/9");
    //     IPAddress e = new IPAddress ("24.128.0.0/9");
    //     IPAddress r = new IPAddress ("24.64.0.0/10");
    //     IPAddress f = new IPAddress ("24.0.0.0/12");
    //     IPAddress g = new IPAddress ("24.16.0.0/13");
    //     IPAddress h = new IPAddress ("24.30.0.0/17");
    //     IPAddress j = new IPAddress ("24.34.0.0/16");
    //     IPAddress z = new IPAddress ("+24.60.0.0/14");
    //     IPAddress x = new IPAddress ("24.91.0.0/16");
    //     IPAddress c = new IPAddress ("24.98.0.0/15");
    //     IPAddress v = new IPAddress ("85.0.0.0/8");
    //     IPAddress b = new IPAddress ("85.85.0.0/15");
    //     System.out.println(q);
    //     System.out.println(w);
    //     System.out.println(e);
    //     System.out.println(r);
    //     System.out.println(f);
    //     System.out.println(g);
    //     System.out.println(h);
    //     System.out.println(j);
    //     System.out.println(z);
    //     System.out.println(x);
    //     System.out.println(c);
    //     System.out.println(v);
    //     System.out.println(b);
    // }

    @Test 
    public void textThree () {
        IPAddress address = new IPAddress("24.0.0.0/8");
        int res = this.router.getRoute(address);
        // System.out.println(address);
        assertEquals(-1, res);
    }

    @Test
    public void tryGettingNothingUsingJustFileOne() {
        IPAddress address = new IPAddress("68.68.68.68");
        int res = this.router.getRoute(address);
        assertEquals(-1, res);
    }


    @Test
    public void usingText2V2() {
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        IPAddress address = new IPAddress("24.16.24.24");
        int res = this.router.getRoute(address);
        assertEquals(4, res);
    }

    // Test all exceptions that should be thrown:

    @Test (expected = IllegalArgumentException.class)
    public void moreThanOneSlashesInACIDR () {
        String cidr = "73.73.0.1/10/5";
        IPAddress obj = new IPAddress(cidr);
    }  
    
    @Test (expected = IllegalArgumentException.class)
    public void tooFewSymbolsInACIDR () {
        String cidr = "";
        IPAddress obj = new IPAddress(cidr);
    }  

    @Test (expected = IllegalArgumentException.class)
    public void tooManySymbolsInACIDR () {
        String cidr = "73.73.0.1/33";
        IPAddress obj = new IPAddress(cidr);
    }  

    @Test (expected = IllegalArgumentException.class)
    public void tooFewDivisionSymbolsInACIDR () {
        String cidr = "73.73.0.1/0";
        IPAddress obj = new IPAddress(cidr);
    } 

    @Test (expected = IllegalArgumentException.class)
    public void negativeSymbolsInACIDR () {
        String cidr = "73.73.0.1/-1";
        IPAddress obj = new IPAddress(cidr);
    } 

    // @Test  (expected = IllegalArgumentException.class)
    // public void greaterThan32Size () {
    //     BitVector obj = new BitVector(20);
    //     System.out.println("HEYY THIS IS THE BITZ.size: " + obj.size() );
    //     obj.setSize(1);
    //     obj.setSize(22);
    // }

    @Test 
    public void testIpAdressClass() {
        String cidr = "73.73.0.1";
        IPAddress obj = new IPAddress(cidr);
        // assertEquals("Testing if tests works" , 0100 , obj );
        assertEquals ("Checking if it reverts back to cidr correctly" , "73.73.0.1" , obj.toCIDR() );
    }

    @Test 
    public void checkBitVectorClass () {
        BitVector bitVector = new BitVector(32);
        assertEquals ("Checking if the size is correct" , 32 , bitVector.size() );
        bitVector.setSize(8);
        assertEquals ("Checking if the size is correct" , 8 , bitVector.size() );
        bitVector.setSize(4);
        bitVector.set(0 , 1);
        bitVector.set(1 , 0);
        bitVector.set(2 , 1);
        bitVector.set(3 , 0);
        // assertEquals ("Checking what get method returns" , 1 , bitVector.get(1) );
        assertEquals ("Checking what get method returns" , "1010" , bitVector.toString() );
    }

    BitVectorTrie <Integer> trie = new BitVectorTrie<Integer>();

    @Test 
    public void getLongestPrefix () {
        String cidr = "73.73.0.1/4";
        IPAddress obj = new IPAddress(cidr);
        trie.put(obj , 1);
        assertEquals ("Getting the same element" , (Integer) 1 , trie.get(obj) );

    }

    // // now going to test the cache:
    @Test
    public void putElemInCache () {
        RouteCache routeCache = new RouteCache (4);
        IPAddress addressOne = new IPAddress("11.11.11.11");
        IPAddress addressTwo = new IPAddress("22.22.22.22");
        IPAddress addressThree = new IPAddress("33.33.33.33");
        IPAddress addressFour = new IPAddress("44.44.44.44");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        routeCache.updateCache(addressOne, 1);
        routeCache.updateCache(addressTwo, 2);
        routeCache.updateCache(addressThree, 3);
        assertEquals ("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , null , routeCache.lookupRoute(addressFour) );
        routeCache.updateCache(addressFour, 4);
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        routeCache.updateCache(addressFive, 5);
        assertEquals("Is 5 in cache" , (Integer) 5 , routeCache.lookupRoute(addressFive) );
        assertEquals ("Is 1 in cache" , null , routeCache.lookupRoute(addressOne) );
    }

    @Test
    public void putElemInCacheTestTwo () {
        RouteCache routeCache = new RouteCache (4);
        IPAddress addressOne = new IPAddress("11.11.11.11");
        IPAddress addressTwo = new IPAddress("22.22.22.22");
        IPAddress addressThree = new IPAddress("33.33.33.33");
        IPAddress addressFour = new IPAddress("44.44.44.44");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        routeCache.updateCache(addressOne, 1);
        routeCache.updateCache(addressTwo, 2);
        routeCache.updateCache(addressThree, 3);
        assertEquals ("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , null , routeCache.lookupRoute(addressFour) );
        routeCache.updateCache(addressFour, 4);
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        routeCache.updateCache(addressFive, 5);
        assertEquals("Is 5 in cache" , (Integer) 5 , routeCache.lookupRoute(addressFive) );
        assertEquals ("Is 1 in cache" , null , routeCache.lookupRoute(addressOne) );
        routeCache.updateCache(addressTwo, 2);
        assertEquals ("Is 1 in cache" , null , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        assertEquals("Is 5 in cache" , (Integer) 5 , routeCache.lookupRoute(addressFive) );
        routeCache.updateCache(addressOne, 1);
        assertEquals ("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , null , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        assertEquals("Is 5 in cache" , (Integer) 5 , routeCache.lookupRoute(addressFive) );
    }

    @Test
    public void putElemInCacheTestThree () {
        RouteCache routeCache = new RouteCache (4);
        IPAddress addressOne = new IPAddress("11.11.11.11");
        IPAddress addressTwo = new IPAddress("22.22.22.22");
        IPAddress addressThree = new IPAddress("33.33.33.33");
        IPAddress addressFour = new IPAddress("44.44.44.44");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        routeCache.updateCache(addressOne, 1);
        routeCache.updateCache(addressTwo, 2);
        routeCache.updateCache(addressThree, 3);
        assertEquals ("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , null , routeCache.lookupRoute(addressFour) );
        routeCache.updateCache(addressFour, 4);
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        routeCache.updateCache(addressOne , 1);
        routeCache.updateCache(addressFive, 5);
        assertEquals("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals("Is 2 in cache" , null , routeCache.lookupRoute(addressTwo) );
        assertEquals("Is 5 in cache" , (Integer) 5 , routeCache.lookupRoute(addressFive) );
    }

    @Test
    public void testCacheFinal () {
        RouteCache routeCache = new RouteCache (3);
        IPAddress addressOne = new IPAddress("11.11.11.11");
        IPAddress addressTwo = new IPAddress("22.22.22.22");
        IPAddress addressThree = new IPAddress("33.33.33.33");
        IPAddress addressFour = new IPAddress("44.44.44.44");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        routeCache.updateCache(addressOne, 1);
        routeCache.updateCache(addressTwo, 2);
        routeCache.updateCache(addressThree, 3);
        assertEquals ("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , null , routeCache.lookupRoute(addressFour) );
        assertEquals("Is 5 in cache" , null , routeCache.lookupRoute(addressFive) );
        // cache is currently full:
        routeCache.updateCache(addressFour, 4);
        assertEquals ("Is 1 in cache" , null , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        assertEquals("Is 5 in cache" , null , routeCache.lookupRoute(addressFive) );
        routeCache.updateCache(addressOne , 1);
        routeCache.updateCache(addressFive, 5);
        assertEquals ("Is 1 in cache" , (Integer) 1 , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , null , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , null , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        assertEquals("Is 5 in cache" , (Integer) 5 , routeCache.lookupRoute(addressFive) );
        routeCache.updateCache(addressFour , 4);
        routeCache.updateCache(addressTwo , 2);
        routeCache.updateCache(addressThree , 3);
        assertEquals ("Is 1 in cache" , null , routeCache.lookupRoute(addressOne) );
        assertEquals ("Is 2 in cache" , (Integer) 2 , routeCache.lookupRoute(addressTwo) );
        assertEquals ("Is 3 in cache" , (Integer) 3 , routeCache.lookupRoute(addressThree) );
        assertEquals("Is 4 in cache" , (Integer) 4 , routeCache.lookupRoute(addressFour) );
        assertEquals("Is 5 in cache" , null , routeCache.lookupRoute(addressFive) );
    }

    @Test
    public void testCacheThroughIPRouter() {
        IPAddress addressOne = new IPAddress("24.0.0.0");
        IPAddress addressTwo = new IPAddress("24.128.0.0");
        IPAddress addressThree = new IPAddress("24.98.0.0");
        IPAddress addressFour = new IPAddress("85.0.0.0");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        try { router.loadRoutes("routes2.txt"); }
        catch (FileNotFoundException e) { throw new RuntimeException("Bad routes file name. Tests aborted"); }
        // cache is empty:
        assertEquals ("Is 1 in cache" , false , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , false , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , false , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , false , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , false , router.isCached(addressFive) );
        //cache should have one elem:
        router.getRoute(addressOne);
        assertEquals ("Is 1 in cache" , true , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , false , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , false , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , false , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , false , router.isCached(addressFive) );
        //cache should have two elem:
        router.getRoute(addressTwo);
        assertEquals ("Is 1 in cache" , true , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , true , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , false , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , false , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , false , router.isCached(addressFive) );
        //cache should have three elem:
        router.getRoute(addressThree);
        assertEquals ("Is 1 in cache" , true , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , true , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , true , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , false , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , false , router.isCached(addressFive) );
        //cache should have four elem:
        router.getRoute(addressFour);
        assertEquals ("Is 1 in cache" , true , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , true , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , true , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , true , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , false , router.isCached(addressFive) );
        //cache should have four elem:
        router.getRoute(addressFive);
        assertEquals ("Is 1 in cache" , false , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , true , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , true , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , true , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , true , router.isCached(addressFive) );
        router.getRoute(addressTwo);
        router.getRoute(addressOne);
        assertEquals ("Is 1 in cache" , true , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , true , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , false , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , true , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , true , router.isCached(addressFive) );
        router.getRoute(addressFive);
        router.getRoute(addressFour);
        router.getRoute(addressThree);
        assertEquals ("Is 1 in cache" , true , router.isCached(addressOne) );
        assertEquals ("Is 2 in cache" , false , router.isCached(addressTwo) );
        assertEquals ("Is 3 in cache" , true , router.isCached(addressThree) );
        assertEquals("Is 4 in cache" , true , router.isCached(addressFour) );
        assertEquals("Is 5 in cache" , true , router.isCached(addressFive) );
        // router.getRoute(addressOne);
        // router.getRoute(addressTwo);
        // router.getRoute(addressThree);
        // router.getRoute(addressFour);
        // router.getRoute(addressFive);
    }

    @Test
    public void emptyDumpFromCache () {
        RouteCache routeCache = new RouteCache (3);
        IPAddress addressOne = new IPAddress("11.11.11.11");
        IPAddress addressTwo = new IPAddress("22.22.22.22");
        IPAddress addressThree = new IPAddress("33.33.33.33");
        IPAddress addressFour = new IPAddress("44.44.44.44");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        routeCache.updateCache(addressOne, 1);
        routeCache.updateCache(addressTwo, 2);
        routeCache.updateCache(addressThree, 3);
        String [] array = routeCache.dumpQueue();
        assertEquals ("Is 3 in cache" , addressThree.toCIDR() , array[0] );
        assertEquals ("Is 2 in cache" , addressTwo.toCIDR() , array[1] );
        assertEquals ("Is 1 in cache" , addressOne.toCIDR() , array[2] );
        routeCache.updateCache(addressTwo, 2);
        routeCache.updateCache(addressFive, 5);
        routeCache.updateCache(addressFour, 4);
        array = routeCache.dumpQueue();
        assertEquals ("Is 4 in cache" , addressFour.toCIDR() , array[0] );
        assertEquals ("Is 5 in cache" , addressFive.toCIDR() , array[1] );
        assertEquals ("Is 2 in cache" , addressTwo.toCIDR() , array[2] );
    }

    @Test
    public void emptyDumpFromIPRouter() {
        IPRouter router = new IPRouter(21, 3);
        IPAddress addressOne = new IPAddress("11.11.11.11");
        IPAddress addressTwo = new IPAddress("22.22.22.22");
        IPAddress addressThree = new IPAddress("33.33.33.33");
        IPAddress addressFour = new IPAddress("44.44.44.44");
        IPAddress addressFive = new IPAddress("55.55.55.55");
        router.addRule(addressOne.toCIDR(), 1);
        router.addRule(addressTwo.toCIDR(), 2);
        router.addRule(addressThree.toCIDR(), 3);
        router.addRule(addressFour.toCIDR(), 4);
        router.addRule(addressFive.toCIDR(), 5);
        router.getRoute(addressOne);
        router.getRoute(addressTwo);
        router.getRoute(addressThree);
        IPAddress [] array = router.dumpCache();
        assertEquals ("Is 3 in cache" , addressThree , array[0] );
        assertEquals ("Is 2 in cache" , addressTwo , array[1] );
        assertEquals ("Is 1 in cache" , addressOne , array[2] );
        router.getRoute(addressTwo);
        router.getRoute(addressFive);
        router.getRoute(addressFour);
        array = router.dumpCache();
        assertEquals ("Is 4 in cache" , addressFour , array[0] );
        assertEquals ("Is 5 in cache" , addressFive , array[1] );
        assertEquals ("Is 2 in cache" , addressTwo , array[2] );
    }



    // /**
    //  * Tears down the test fixture.
    //  *
    //  * Called after every test case method.
    //  */
    @After
    public void tearDown()
    {
    }
}
