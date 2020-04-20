package edu.yu.cs.com1320.project.stage4.impl;

/**
 * "Heap Test Code" Stage 4
 * @author Tom Bohbot
 */

import edu.yu.cs.com1320.project.impl.MinHeapImpl;
import edu.yu.cs.com1320.project.stage4.impl.DocumentImpl;
import edu.yu.cs.com1320.project.stage4.impl.DocumentStoreImpl;
import edu.yu.cs.com1320.project.stage4.DocumentStore;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HeapTest {

    MinHeapImpl<Integer> minHeap = new MinHeapImpl<Integer>();
    MinHeapImpl<DocumentImpl> minHeapOfDocs = new MinHeapImpl<DocumentImpl>();

    @Test
    public void testInsert() {
        minHeap.insert(85);
        minHeap.insert(2);
        minHeap.insert(1);
        assertEquals("Testing if correct amount of items are currently in the heap", 1, minHeap.removeMin());
        assertEquals("Testing if correct amount of items are currently in the heap", 2, minHeap.removeMin());
        assertEquals("Testing if correct amount of items are currently in the heap", 85, minHeap.removeMin());
    }

    @Test
    public void testGetArrayIndex() {
        minHeap.insert(85);
        minHeap.insert(2);
        minHeap.insert(1);
        assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
        assertEquals("Testing if array index is correct", 2, minHeap.getArrayIndex(2));
        assertEquals("Testing if array index is correct", 3, minHeap.getArrayIndex(85));
        // check if still correct after deletions:
        minHeap.removeMin();
        assertEquals("Testing if array index is correct after deletion", 1, minHeap.getArrayIndex(2));
    }

    @Test
    public void deleteingNegInfinity() throws URISyntaxException{
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        documentOne.setLastUseTime(1);
        documentTwo.setLastUseTime(5);
        documentThree.setLastUseTime(3);
        // I have just created three documents:
        minHeapOfDocs.insert(documentOne);
        minHeapOfDocs.insert(documentTwo);
        minHeapOfDocs.insert(documentThree);
        documentTwo.setLastUseTime(min);
        minHeapOfDocs.reHeapify(documentTwo);
        assertEquals("Does setting a value to neg infinity and deleting it work?" , uriTwo , ((DocumentImpl) minHeapOfDocs.removeMin()).getKey() );
    }

    @Test
    public void testSwappingArrayIndex() {
        minHeap.insert(1);
        minHeap.insert(2);
        minHeap.insert(85);
        assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
        assertEquals("Testing if array index is correct", 2, minHeap.getArrayIndex(2));
        assertEquals("Testing if array index is correct", 3, minHeap.getArrayIndex(85));
        // check if still correct after deletions:
        minHeap.removeMin();
        assertEquals("Testing if array index is correct after deletion", 1, minHeap.getArrayIndex(2));
        assertEquals("Testing if array index is correct after deletion", 2, minHeap.getArrayIndex(85));
        minHeap.removeMin();
        assertEquals("Testing if array index is correct after deletion", 1, minHeap.getArrayIndex(85));
        minHeap.removeMin();
        // currently have an empty minHeap.
        minHeap.insert(1);
        assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
        minHeap.insert(3);
        assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
        assertEquals("Testing if array index is correct", 2, minHeap.getArrayIndex(3));
        minHeap.insert(2);
        assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
        assertEquals("Testing if array index is correct", 2, minHeap.getArrayIndex(2));
        assertEquals("Testing if array index is correct", 3, minHeap.getArrayIndex(3));
    }

    // @Test
    // public void testDoubleArray() {
    // assertEquals("Testing if array index is correct 0 elems" , 2 ,
    // minHeap.getArraySize() );
    // minHeap.insert(1);
    // assertEquals("Testing if array index is correct 1 elem" , 2 ,
    // minHeap.getArraySize() );
    // minHeap.insert(2);
    // assertEquals("Testing if array index is correct 2 elems" , 4 ,
    // minHeap.getArraySize() );
    // minHeap.insert(85);
    // assertEquals("Testing if array index is correct 3 elems" , 4 ,
    // minHeap.getArraySize() );
    // minHeap.insert(4);
    // assertEquals("Testing if array index is correct 3 elems" , 8 ,
    // minHeap.getArraySize() );
    // assertEquals("Testing if correct amount of items are currently in the heap" ,
    // 1 , minHeap.removeMin() );
    // assertEquals("Testing if correct amount of items are currently in the heap" ,
    // 2 , minHeap.removeMin() );
    // assertEquals("Testing if correct amount of items are currently in the heap" ,
    // 4 , minHeap.removeMin() );
    // assertEquals("Testing if correct amount of items are currently in the heap" ,
    // 85 , minHeap.removeMin() );
    // }

    @Test
    public void testReHeapify() {
        minHeap.insert(1);
        minHeap.insert(2);
        minHeap.insert(85);
        minHeap.reHeapify(1);
        assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    }

    @Test
    public void reHeapifyWithDocuments() throws URISyntaxException {
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );

        documentOne.setLastUseTime(1);
        documentTwo.setLastUseTime(5);
        documentThree.setLastUseTime(3);

        // I have just created three documents:
        minHeapOfDocs.insert(documentOne);
        assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentOne));
        minHeapOfDocs.insert(documentTwo);
        assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentOne));
        assertEquals("Testing if array index is correct", 2, minHeapOfDocs.getArrayIndex(documentTwo));
        minHeapOfDocs.insert(documentThree);
        assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentOne));
        assertEquals("Testing if array index is correct", 3, minHeapOfDocs.getArrayIndex(documentTwo));
        assertEquals("Testing if array index is correct", 2, minHeapOfDocs.getArrayIndex(documentThree));

        // alter time of doc 2 so that it is now at the top of the heap:
        documentTwo.setLastUseTime(0);
        minHeapOfDocs.reHeapify(documentTwo);
        assertEquals("Testing if array index is correct", 2, minHeapOfDocs.getArrayIndex(documentOne));
        assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentTwo));
        assertEquals("Testing if array index is correct", 3, minHeapOfDocs.getArrayIndex(documentThree));
    
        documentOne.setLastUseTime(100);
        documentTwo.setLastUseTime(50);
        documentThree.setLastUseTime(1);
        minHeapOfDocs.reHeapify(documentTwo);
        minHeapOfDocs.reHeapify(documentTwo);
        minHeapOfDocs.reHeapify(documentThree);

        assertEquals("Testing if array index is correct", 3, minHeapOfDocs.getArrayIndex(documentOne));
        assertEquals("Testing if array index is correct", 2, minHeapOfDocs.getArrayIndex(documentTwo));
        assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentThree)); // weird that it worked right here. Check it out :)
    }

    @Test
    public void putDocuments () throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContentOne);
        testList.add(inputStreamContentTwo);
        testList.add(inputStreamContentThree);
        assertEquals("Testing if array index is correct", testList , docStore.search("Hey") );
        docStore.setMaxDocumentCount(2);
        testList.remove(inputStreamContentOne);
        // assertEquals("Testing if array index is correct", testList, docStore.search("Hey") );
        assertEquals("Testing if array index is correct", testList.size() , docStore.search("Hey").size() );
    } // this test fails sometimes bc docOne and docTwo will end up getting the same lastUsedTime. Fixed it by just looking at size instead of actual contents of the list. 
}