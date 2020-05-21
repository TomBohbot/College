package edu.yu.cs.com1320.project.stage5.impl;

/**
 * Stage 5:
 * @author Tom Bohbot
 */

import edu.yu.cs.com1320.project.impl.BTreeImpl;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import edu.yu.cs.com1320.project.stage5.DocumentStore;
import edu.yu.cs.com1320.project.stage5.impl.DocumentStoreImpl;

public class TestBTree {

    BTreeImpl <Integer , String> bTree = new BTreeImpl <Integer , String> ();

    @Test
    public void putDocs() {
        assertEquals("Putting in first object" , null , bTree.put(1 , "Lenny") );
        assertEquals("Putting in second object" , null , bTree.put(2 , "Ruben") );
        assertEquals("Putting in third object" , null , bTree.put(3 , "Tom") );
        assertEquals("Putting in fourth object" , null , bTree.put(4 , "Tania") );
        assertEquals("Putting in fifth object" , null , bTree.put(5 , "Maalon") );
    }

    @Test
    public void putDuplicateDocs() {
        assertEquals("Putting in first object" , null , bTree.put(1 , "Lenny") );
        assertEquals("Putting in second object" , null , bTree.put(2 , "Ruben") );
        assertEquals("Putting in third object" , "Lenny" , bTree.put(1 , "Tom") );
        assertEquals("Putting in fourth object" , "Tom" , bTree.put(1 , "Tania") );
        assertEquals("Putting in fifth object" , "Ruben" , bTree.put(2 , "Maalon") );
    }

    @Test
    public void getDocuments() {
        bTree.put(1 , "Lenny");
        bTree.put(2 , "Ruben");
        bTree.put(3 , "Tom");
        bTree.put(4 , "Tania");
        bTree.put(5 , "Maalon");
        assertEquals("Putting in first object" , "Lenny" , bTree.get(1) );
        assertEquals("Putting in second object" , "Ruben" , bTree.get(2) );
        assertEquals("Putting in third object" , "Tom" , bTree.get(3) );
        assertEquals("Putting in fourth object" , "Tania" , bTree.get(4) );
        assertEquals("Putting in fifth object" , "Maalon" , bTree.get(5) );
    }

    @Test
    public void getReplacedDocuments() {
        bTree.put(1 , "Lenny");
        bTree.put(1 , "Ruben");
        bTree.put(3 , "Tom");
        bTree.put(1 , "Tania");
        bTree.put(5 , "Maalon");
        assertEquals("Putting in second object" , "Tom" , bTree.get(3) );
        assertEquals("Putting in fourth object" , "Tania" , bTree.get(1) );
        assertEquals("Putting in fifth object" , "Maalon" , bTree.get(5) );
    }

    @Test
    public void deleteDocuments() {
        bTree.put(1 , "Lenny");
        bTree.put(2 , "Ruben");
        bTree.put(3 , "Tom");
        bTree.put(4 , "Tania");
        bTree.put(5 , "Maalon");
        assertEquals("Putting in first object" , "Lenny" , bTree.get(1) );
        assertEquals("Putting in second object" , "Ruben" , bTree.get(2) );
        assertEquals("Putting in third object" , "Tom" , bTree.get(3) );
        assertEquals("Putting in fourth object" , "Tania" , bTree.get(4) );
        assertEquals("Putting in fifth object" , "Maalon" , bTree.get(5) );
        bTree.put(1 , null);
        bTree.put(2 , null);
        bTree.put(3 , null);
        bTree.put(4 , null);
        bTree.put(5 , null);
        // assertEquals("Putting in first object" , null , bTree.get(1) );
        // assertEquals("Putting in first object" , null , bTree.get(2) );
        // assertEquals("Putting in first object" , null , bTree.get(3) );
        // assertEquals("Putting in first object" , null , bTree.get(4) );
        // assertEquals("Putting in first object" , null , bTree.get(5) );
    }

    @Test
    public void memoryLimits1() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl ();
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
        ArrayList <String> testList = new ArrayList <>();
        testList.add(inputStreamContentOne);
        testList.add(inputStreamContentTwo);
        testList.add(inputStreamContentThree);
        assertEquals ("Testing initial search 1" , testList , docStore.search("hey") );
        docStore.setMaxDocumentCount(2);
        assertEquals ("Testing initial search 2" , inputStreamContentOne , docStore.getDocumentAsTxt(uriOne) );
    }

    @Test
    public void memoryLimits() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl ();
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
        docStore.setMaxDocumentCount(2);
        assertEquals ("Testing initial get after memory limit" , inputStreamContentOne , docStore.getDocumentAsTxt(uriOne) );
    }
    
}