package edu.yu.introtoalgs;

/**
 * The test class SearchWithATweak.
 * @author  Tom Bohbot
 * @version August 26, 2020
 */


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import edu.yu.introtoalgs.SearchWithATweak;

import java.util.List;
import java.util.ArrayList;


public class SearchWithATweakTest {

   @Test
   public void findFirstInstanceBaseCase() {
       ArrayList <Integer> list = new ArrayList <>();
       for (int i = 1; i < 10; i ++) { list.add(i); }
       assertEquals(SearchWithATweak.findFirstInstance(list , 3) , 2);
   }

    @Test
    public void findFirstInstanceDoesExist() {
        ArrayList <Integer> list = new ArrayList <>();
       for (int i = 1; i < 10; i ++) {
           list.add(i);
       }
        assertEquals(SearchWithATweak.findFirstInstance(list , 1) ,0);
    }

   @Test
   public void findFirstInstanceOne() {
       ArrayList <Integer> list = new ArrayList <>();
       for (int i = 1; i < 10; i ++) { list.add(i); }
       assertEquals(SearchWithATweak.findFirstInstance(list , 0) ,-1);
   }

   @Test
   public void findFirstInstanceDoesNotExistTooBig() {
       ArrayList <Integer> list = new ArrayList <>();
       for (int i = 1; i < 10; i ++) { list.add(i); }
       assertEquals(SearchWithATweak.findFirstInstance(list , 10) , -1);
   }

   @Test
   public void dups1() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(1);
       list.add(2);
       list.add(2);
       list.add(3);
       list.add(4);
       list.add(5);
       list.add(5);
       list.add(6);
       assertEquals(SearchWithATweak.findFirstInstance(list , 1) , 0);
   }

   @Test
   public void dups18() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(18);
       list.add(18);
       list.add(18);
       list.add(18);
       list.add(18);
       list.add(18);
       list.add(18);
       list.add(18);
       assertEquals(SearchWithATweak.findFirstInstance(list , 18) , 0);
   }

   @Test
   public void dups2() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(1);
       list.add(2);
       list.add(2);
       list.add(3);
       list.add(4);
       list.add(5);
       list.add(5);
       list.add(6);
       assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 1);
   }

   @Test
   public void dups5() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(1);
       list.add(2);
       list.add(2);
       list.add(3);
       list.add(4);
       list.add(5);
       list.add(5);
       list.add(6);
       assertEquals(SearchWithATweak.findFirstInstance(list , 5) , 5);
   }

   @Test
   public void jumps() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(1);
       list.add(2);
       list.add(2);
       list.add(16);
       list.add(23);
       list.add(68);
       list.add(72);
       list.add(400);
       assertEquals(SearchWithATweak.findFirstInstance(list , 400) , 7);
   }

   @Test
   public void jumps2() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(1);
       list.add(2);
       list.add(2);
       list.add(16);
       list.add(23);
       list.add(68);
       list.add(72);
       list.add(400);
       assertEquals(SearchWithATweak.findFirstInstance(list , 68) , 5);
   }

   @Test
   public void jumps3() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(1);
       list.add(2);
       list.add(2);
       list.add(16);
       list.add(23);
       list.add(68);
       list.add(72);
       list.add(400);
       assertEquals(SearchWithATweak.findFirstInstance(list , 300) , -1);
   }

   @Test
   public void negatives() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(-10);
       list.add(-7);
       list.add(-2);
       list.add(16);
       list.add(23);
       list.add(68);
       list.add(72);
       list.add(400);
       assertEquals(SearchWithATweak.findFirstInstance(list , 23) , 4);
   }

   @Test
   public void baseCase() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(-5);
    list.add(-3);
    list.add(2);
    list.add(16);
    list.add(23);
    list.add(68);
    list.add(72);
    list.add(400);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , 2);
   }

   @Test
   public void baseCase1() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(-5);
    list.add(-3);
    list.add(1);
    list.add(16);
    list.add(23);
    list.add(68);
    list.add(72);
    list.add(400);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , -1);
   }

   @Test
   public void baseCase2() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(0);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , 2);
   }

   @Test
   public void test1() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(-4);
    list.add(-2);
    list.add(0);
    list.add(2);
    list.add(4);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , 4);
   }

   @Test
   public void test2() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(-4);
    list.add(-2);
    list.add(0);
    list.add(2);
    list.add(8);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , -1);
   }

   @Test
   public void verifyEarliestMatch() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(1);
    list.add(2);
    list.add(2);
    list.add(2);
    list.add(3);
    assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 1);
    }

    @Test
    public void emptyList() {
        ArrayList <Integer> list = new ArrayList <>();
        assertEquals(SearchWithATweak.findFirstInstance(list , 2) , -1);
     }

     @Test
    public void emptyList2() {
        ArrayList <Integer> list = new ArrayList <>();
        assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , -1);
     }

     @Test
     public void nullList() {
        ArrayList <Integer> list = null;
        assertEquals(SearchWithATweak.findFirstInstance(list , 2) , -1);
      }

     @Test
     public void nullList2() {
        ArrayList <Integer> list = null;
        assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , -1);
      }

      @Test
      public void verifyEarliestMatch1() {
       ArrayList <Integer> list = new ArrayList <>();
       list.add(2);
       list.add(2);
       list.add(2);
       list.add(2);
       list.add(2);
       assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 0);
       }

    //    @Test
    //    public void verifyEarliestMatch2() {
    //     ArrayList <Integer> list = new ArrayList <>();
    //     list.add(2);
    //     list.add(2);
    //     list.add(null);
    //     list.add(2);
    //     list.add(2);
    //     assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 0);
    //     }   @Test

    @Test
   public void inverseListTest() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(6);
    list.add(5);
    list.add(4);
    list.add(3);
    list.add(2);
    list.add(1);
    assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 4);
    }

    @Test
    public void inverseListTestDups() {
     ArrayList <Integer> list = new ArrayList <>();
     list.add(6);
     list.add(5);
     list.add(4);
     list.add(2);
     list.add(2);
     list.add(1);
     assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 3);
     }
//------------------------------------------------------------------------------------------------------------

    @Test
    public void regList() {
        ArrayList <Integer> list = new ArrayList <>();
        list.add(-2);
        list.add(0);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        assertEquals(SearchWithATweak.findFirstInstance(list , -10) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , -2) , 0);
        assertEquals(SearchWithATweak.findFirstInstance(list , -1) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 0) , 1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 1) , -1);    
        assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 2);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 6) , 4);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 7) , -1);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 8) , 5);      
    }

    @Test
    public void regListDups() {
        ArrayList <Integer> list = new ArrayList <>();
        list.add(-2);
        list.add(0);
        list.add(2);
        list.add(2);
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(8);
        assertEquals(SearchWithATweak.findFirstInstance(list , -10) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , -2) , 0);
        assertEquals(SearchWithATweak.findFirstInstance(list , -1) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 0) , 1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 1) , -1);    
        assertEquals(SearchWithATweak.findFirstInstance(list , 2) , 2);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 6) , 5);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 5) , 4);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 8) , 6);      
    }

    @Test
    public void regListInverse() {
        ArrayList <Integer> list = new ArrayList <>();
        list.add(25);
        list.add(20);
        list.add(15);
        list.add(10);
        list.add(5);
        list.add(0);
        list.add(-1);
        list.add(-10);
        assertEquals(SearchWithATweak.findFirstInstance(list , 100) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 25) , 0);
        assertEquals(SearchWithATweak.findFirstInstance(list , 22) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 20) , 1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 16) , -1);    
        assertEquals(SearchWithATweak.findFirstInstance(list , 15) , 2);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 0) , 5);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 5) , 4);      
        assertEquals(SearchWithATweak.findFirstInstance(list , -1) , 6);      
    }

    @Test
    public void regListDupsInverse() {
        ArrayList <Integer> list = new ArrayList <>();
        list.add(25);
        list.add(25);
        list.add(15);
        list.add(15);
        list.add(5);
        list.add(0);
        list.add(-10);
        list.add(-10);
        assertEquals(SearchWithATweak.findFirstInstance(list , 100) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 25) , 0);
        assertEquals(SearchWithATweak.findFirstInstance(list , 22) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 20) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 16) , -1);    
        assertEquals(SearchWithATweak.findFirstInstance(list , 15) , 2);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 0) , 5);      
        assertEquals(SearchWithATweak.findFirstInstance(list , 5) , 4);      
        assertEquals(SearchWithATweak.findFirstInstance(list , -10) , 6);      
    }

    @Test
    public void onlyRepeats() {
        ArrayList <Integer> list = new ArrayList <>();
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);
        assertEquals(SearchWithATweak.findFirstInstance(list , 100) , -1);     
        assertEquals(SearchWithATweak.findFirstInstance(list , 25) , 0);
        assertEquals(SearchWithATweak.findFirstInstance(list , 22) , -1);       
    }

    @Test
    public void emptyListFirstMethod() {
        ArrayList <Integer> list = new ArrayList <>();     
        assertEquals(SearchWithATweak.findFirstInstance(list , -10) , -1);      
    }

    @Test
    public void nullFirstMethod() {
        assertEquals(SearchWithATweak.findFirstInstance(null , -10) , -1);      
    }


//------------------------------------------------------------------------------------------------------------

@Test
public void regListMethod2() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(-2);
    list.add(0);
    list.add(2);
    list.add(4);
    list.add(6);
    list.add(8);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , 2);     
}

@Test
public void regListDupsMethodTwo() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(-2);
    list.add(0);
    list.add(2);
    list.add(2);
    list.add(5);
    list.add(5);
    list.add(8);
    list.add(8);
    assertTrue(SearchWithATweak.elementEqualToItsIndex(list) == 2 || SearchWithATweak.elementEqualToItsIndex(list) == 5);     
}



@Test
public void regListInverseMethodTwo() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(25);
    list.add(20);
    list.add(15);
    list.add(10);
    list.add(8);
    list.add(5);
    list.add(-1);
    list.add(-10);
    assertTrue(SearchWithATweak.elementEqualToItsIndex(list) == 5);
}

// @Test
// public void regListDupsInverseMethodTwo() {
//     ArrayList <Integer> list = new ArrayList <>();
//     list.add(25);
//     list.add(25);
//     list.add(15);
//     list.add(15);
//     list.add(5);
//     list.add(5);
//     list.add(-10);
//     list.add(-10);
//     assertTrue(SearchWithATweak.elementEqualToItsIndex(list) == 5);      
// }

@Test
public void onlyRepeatsBadPaths() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(25);
    list.add(25);
    list.add(25);
    list.add(25);
    list.add(25);
    list.add(25);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , -1);     
}

@Test
public void onlyRepeatshappyPaths() {
    ArrayList <Integer> list = new ArrayList <>();
    list.add(3);
    list.add(3);
    list.add(3);
    list.add(3);
    list.add(3);
    list.add(3);
    assertEquals(SearchWithATweak.elementEqualToItsIndex(list) , 3);     
}

@Test
public void emptyListSecondMethod() {
    ArrayList <Integer> list = new ArrayList <>();     
    assertEquals(SearchWithATweak.findFirstInstance(list , -10) , -1);      
}

@Test
public void nullSecondMethod() {
    assertEquals(SearchWithATweak.findFirstInstance(null , -10) , -1);      
}


}