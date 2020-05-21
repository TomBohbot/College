package edu.yu.cs.com1320.project.stage5.impl;

/**
 * "Heap Test Code" Stage 4
 * @author Tom Bohbot
 */

import edu.yu.cs.com1320.project.impl.MinHeapImpl;
import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.stage5.impl.DocumentImpl;
import edu.yu.cs.com1320.project.stage5.impl.DocumentStoreImpl;
import edu.yu.cs.com1320.project.stage5.DocumentStore;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HeapTest {

    MinHeapImpl<Integer> minHeap = new MinHeapImpl<Integer>();
    MinHeapImpl<Document> minHeapOfDocs = new MinHeapImpl<Document>();

    @Test
    public void testInsert() {
        minHeap.insert(85);
        minHeap.insert(2);
        minHeap.insert(1);
        assertEquals("Testing if correct amount of items are currently in the heap", 1, (int) minHeap.removeMin());
        assertEquals("Testing if correct amount of items are currently in the heap", 2, (int) minHeap.removeMin());
        assertEquals("Testing if correct amount of items are currently in the heap", 85, (int) minHeap.removeMin());
    }

    // @Test
    // public void testGetArrayIndex() {
    //     minHeap.insert(85);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(85));
    //     minHeap.insert(2);
    //     assertEquals("Testing if array index is correct", 2, minHeap.getArrayIndex(85));
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(2));
    //     minHeap.insert(1);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    //     assertTrue("Testing if array index is correct", minHeap.getArrayIndex(2) == 2 ^ minHeap.getArrayIndex(2) == 3);
    //     assertTrue("Testing if array index is correct", minHeap.getArrayIndex(85) == 2 ^ minHeap.getArrayIndex(85) == 3);
    //     // check if still correct after deletions:
    //     minHeap.removeMin();
    //     assertEquals("Testing if array index is correct after deletion", 1, minHeap.getArrayIndex(2));
    // }

    // @Test
    // public void deleteingNegInfinity() throws URISyntaxException{
    //     long min = Long.MIN_VALUE;
    //     String inputStreamContentOne = "Hey, I'm Lenny";
    //     InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
    //     URI uriOne = new URI("Lenny's_URI");
    //     DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
    //     String inputStreamContentTwo = "Hey, I'm Ruben";
    //     InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
    //     URI uriTwo = new URI("Ruben's_URI");
    //     DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
    //     String inputStreamContentThree = "Hey, I'm Tom";
    //     InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
    //     URI uriThree = new URI("Tom's_URI");
    //     DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
    //     documentOne.setLastUseTime(1);
    //     documentTwo.setLastUseTime(5);
    //     documentThree.setLastUseTime(3);
    //     // I have just created three documents:
    //     minHeapOfDocs.insert(documentOne);
    //     minHeapOfDocs.insert(documentTwo);
    //     minHeapOfDocs.insert(documentThree);
    //     documentTwo.setLastUseTime(min);
    //     minHeapOfDocs.reHeapify(documentTwo);
    //     assertEquals("Does setting a value to neg infinity and deleting it work?" , uriTwo , ((DocumentImpl) minHeapOfDocs.removeMin()).getKey() );
    // }

    // @Test
    // public void testSwappingArrayIndex() {
    //     minHeap.insert(1);
    //     minHeap.insert(2);
    //     minHeap.insert(85);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    //     assertTrue("Testing if array index is correct", minHeap.getArrayIndex(2) == 2 ^ minHeap.getArrayIndex(2) == 3);
    //     assertTrue("Testing if array index is correct", minHeap.getArrayIndex(85) == 2 ^ minHeap.getArrayIndex(85) == 3);
    //     // check if still correct after deletions:
    //     minHeap.removeMin();
    //     assertEquals("Testing if array index is correct after deletion", 1, minHeap.getArrayIndex(2));
    //     assertEquals("Testing if array index is correct after deletion", 2, minHeap.getArrayIndex(85));
    //     minHeap.removeMin();
    //     assertEquals("Testing if array index is correct after deletion", 1, minHeap.getArrayIndex(85));
    //     minHeap.removeMin();
    //     // // currently have an empty minHeap.
    //     minHeap.insert(1);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    //     minHeap.insert(3);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    //     assertEquals("Testing if array index is correct", 2, minHeap.getArrayIndex(3));
    //     minHeap.insert(2);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    //     assertTrue("Testing if array index is correct", minHeap.getArrayIndex(2) == 2 ^ minHeap.getArrayIndex(2) == 3);
    //     assertTrue("Testing if array index is correct", minHeap.getArrayIndex(3) == 2 ^ minHeap.getArrayIndex(3) == 3);
    // }

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

    // @Test
    // public void testReHeapify() {
    //     minHeap.insert(1);
    //     minHeap.insert(2);
    //     minHeap.insert(85);
    //     minHeap.reHeapify(1);
    //     assertEquals("Testing if array index is correct", 1, minHeap.getArrayIndex(1));
    // }

    // @Test
    // public void reHeapifyWithDocuments() throws URISyntaxException {
    //     String inputStreamContentOne = "Hey, I'm Lenny";
    //     InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
    //     URI uriOne = new URI("Lenny's_URI");
    //     DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
    //     String inputStreamContentTwo = "Hey, I'm Ruben";
    //     InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
    //     URI uriTwo = new URI("Ruben's_URI");
    //     DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
    //     String inputStreamContentThree = "Hey, I'm Tom";
    //     InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
    //     URI uriThree = new URI("Tom's_URI");
    //     DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );

    //     documentOne.setLastUseTime(1);
    //     documentTwo.setLastUseTime(5);
    //     documentThree.setLastUseTime(3);

    //     // I have just created three documents:
    //     minHeapOfDocs.insert(documentOne);
    //     assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentOne));
    //     minHeapOfDocs.insert(documentTwo);
    //     assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentOne));
    //     assertEquals("Testing if array index is correct", 2, minHeapOfDocs.getArrayIndex(documentTwo));
    //     minHeapOfDocs.insert(documentThree);
    //     assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentOne));
    //     assertTrue("Testing if array index is correct", minHeapOfDocs.getArrayIndex(documentTwo) == 2 ^ minHeapOfDocs.getArrayIndex(documentTwo) == 3);
    //     assertTrue("Testing if array index is correct", minHeapOfDocs.getArrayIndex(documentThree) == 2 ^ minHeapOfDocs.getArrayIndex(documentThree) == 3);
    //     // alter time of doc 2 so that it is now at the top of the heap:
    //     documentTwo.setLastUseTime(0);
    //     minHeapOfDocs.reHeapify(documentTwo);
    //     assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentTwo));
    //     assertTrue("Testing if array index is correct", minHeapOfDocs.getArrayIndex(documentOne) == 2 ^ minHeapOfDocs.getArrayIndex(documentOne) == 3);
    //     assertTrue("Testing if array index is correct", minHeapOfDocs.getArrayIndex(documentThree) == 2 ^ minHeapOfDocs.getArrayIndex(documentThree) == 3);
    
    //     documentOne.setLastUseTime(100);
    //     documentTwo.setLastUseTime(50);
    //     documentThree.setLastUseTime(1);
    //     minHeapOfDocs.reHeapify(documentTwo);
    //     minHeapOfDocs.reHeapify(documentTwo);
    //     minHeapOfDocs.reHeapify(documentThree);

    //     assertEquals("Testing if array index is correct", 1, minHeapOfDocs.getArrayIndex(documentThree));
    //     assertTrue("Testing if array index is correct", minHeapOfDocs.getArrayIndex(documentOne) == 2 ^ minHeapOfDocs.getArrayIndex(documentOne) == 3);
    //     assertTrue("Testing if array index is correct", minHeapOfDocs.getArrayIndex(documentTwo) == 2 ^ minHeapOfDocs.getArrayIndex(documentTwo) == 3);
    // }

    @Test
    public void putDocuments () throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/hi/tom/lenny/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/hi/tom/lenny/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/hi/tom/lenny/Tom's_URI");
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
        assertEquals("Testing if array index is correct", 3 , docStore.search("Hey").size() );
    }

    @Test
    public void getDocumentAsTxtAndAsPdf() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/bannana/hi/tom/lenny/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/bannana/hi/tom/lenny/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/bannana/hi/tom/lenny/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        docStore.getDocumentAsTxt(uriOne);
        docStore.getDocumentAsTxt(uriTwo);
        docStore.setMaxDocumentCount(2);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContentOne);
        testList.add(inputStreamContentTwo);
        testList.add(inputStreamContentThree);
        assertEquals("Testing if array index is correct", 3 , docStore.search("Hey").size() );
        docStore.getDocumentAsPdf(uriOne);
        testList.remove(inputStreamContentOne);
        testList.remove(inputStreamContentTwo);
        testList.remove(inputStreamContentThree);
        docStore.setMaxDocumentCount(1);
        assertEquals("Testing if array index is correct", 3 , docStore.search("Hey").size() );
    }

    @Test
    public void searchTextAndPdfDocuments() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I' Ruben";
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
        docStore.search("Im");
        docStore.setMaxDocumentCount(2);
        assertEquals("Testing if array index is correct", 3 , docStore.search("Hey").size() );
        docStore.searchPDFs("Lenny");
        docStore.setMaxDocumentCount(1);
        assertEquals("Testing if array index is correct", 3 , docStore.search("Hey").size() );
    }

    @Test
    public void searchTextAndPdfDocumentsByPrefix() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I' Ruben";
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
        docStore.searchByPrefix("He");
        docStore.setMaxDocumentCount(2);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContentOne);
        testList.add(inputStreamContentThree);
        assertEquals("Testing if array index is correct", 2 , docStore.search("Hey").size() );
        docStore.searchPDFsByPrefix("L");
        testList.remove(inputStreamContentThree);
        docStore.setMaxDocumentCount(1);
        assertEquals("Testing if array index is correct", 2 , docStore.search("Hey").size() );
    }

    @Test
    public void undoPuts() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I' Ruben";
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
        assertEquals("Initial search after putting in documents" , testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentThree);
        assertEquals("Search after undoing third document" , testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentTwo);
        assertEquals("Search after undoing second document" , testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentOne);
        assertEquals("Search after undoing first document" , testList , docStore.search("Hey") );
    }


    @Test 
    public void undoDocuments() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I' Ruben";
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
        docStore.deleteDocument(uriOne);
        docStore.deleteDocument(uriTwo);
        docStore.deleteDocument(uriThree);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContentThree);
        docStore.undo();
        assertEquals("undo first deleted doc.", testList , docStore.search("Hey") );
        docStore.undo();
        testList.add(inputStreamContentTwo);
        assertEquals("undo second deleted doc.", testList , docStore.search("Hey") );
        docStore.undo();
        testList.add(inputStreamContentOne);
        assertEquals("undo third deleted doc", testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentThree);
        assertEquals("undo third put doc", testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentTwo);
        assertEquals("Testing undo. 2.", testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentOne);
        assertEquals("Testing undo. 2.", testList , docStore.search("Hey") );
    }

    @Test 
    public void undoDocumentsDeletedDocsThroughNullDocs() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I' Ruben";
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
        docStore.putDocument(null, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(null, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(null, uriThree, DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContentThree);
        docStore.undo();
        assertEquals("Testing undo. 1.", testList , docStore.search("Hey") );
        docStore.undo();
        testList.add(inputStreamContentTwo);
        assertEquals("Testing undo. 2.", testList , docStore.search("Hey") );
        docStore.undo();
        testList.add(inputStreamContentOne);
        assertEquals("Testing undo. 2.", testList , docStore.search("Hey") );
        docStore.undo();
        docStore.undo();
        testList.remove(inputStreamContentThree);
        testList.remove(inputStreamContentTwo);
        assertEquals("Testing undo. 2.", testList , docStore.search("Hey") );
        docStore.undo();
        testList.remove(inputStreamContentOne);
        assertEquals("Testing undo. 2.", testList , docStore.search("Hey") );
    }

    @Test
    public void deleteAllAndUndoAspect() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I' Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey hey hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        docStore.deleteAll("Hey");
        assertEquals("Checking if delete all works, and worked witht he heap" , testList , docStore.search("Hey") );
        docStore.undo();
        testList.add(inputStreamContentThree);
        testList.add(inputStreamContentOne);
        testList.add(inputStreamContentTwo);
        assertEquals("Checking if delete all works, and worked witht he heap" , testList , docStore.search("Hey") );
        testList.remove(inputStreamContentThree);
        docStore.undo();
        assertEquals("Checking if delete all works, and worked witht he heap" , testList , docStore.search("Hey") );
        testList.remove(inputStreamContentTwo);
        docStore.undo();
        assertEquals("Checking if delete all works, and worked witht he heap" , testList , docStore.search("Hey") );
    }

    @Test 
    public void verifyReHeap() throws URISyntaxException {
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("hi/tom/hi/tomLenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("hi/tom/hi/rben/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("hi/tom/lenny/hi/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        long min = System.nanoTime();      
        documentOne.setLastUseTime(System.nanoTime());
        documentTwo.setLastUseTime(System.nanoTime());
        documentThree.setLastUseTime(System.nanoTime());
        minHeapOfDocs.insert(documentOne);
        minHeapOfDocs.insert(documentTwo);
        minHeapOfDocs.insert(documentThree);
        documentTwo.setLastUseTime(min);
        minHeapOfDocs.reHeapify(documentTwo);
        assertEquals("Checking if delete all works, and worked witht he heap" , documentTwo , minHeapOfDocs.removeMin() );
    }

    // now test for deleting through bytes:
    @Test
    public void deleteDueToTooManyBytes() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl();
        long min = Long.MIN_VALUE;
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I' Ruben";
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
        docStore.searchByPrefix("He");
        docStore.setMaxDocumentBytes(1000);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContentOne);
        testList.add(inputStreamContentThree);
        assertEquals("Testing if array index is correct", 2 , docStore.search("Hey").size() );
        docStore.searchPDFsByPrefix("L");
        testList.remove(inputStreamContentThree);
        docStore.setMaxDocumentCount(1);
        assertEquals("Testing if array index is correct", 1 , docStore.searchPDFsByPrefix("L").size() );
    }
}