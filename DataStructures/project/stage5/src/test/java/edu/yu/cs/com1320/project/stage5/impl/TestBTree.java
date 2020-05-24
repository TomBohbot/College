package edu.yu.cs.com1320.project.stage5.impl;

/**
 * Stage 5:
 * @author Tom Bohbot
 */

import edu.yu.cs.com1320.project.impl.BTreeImpl;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
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
        URI uriOne = new URI("/catcat/hi/tom/lenny/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/catcat/hi/tom/lenny/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/catcat/hi/tom/lenny/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        docStore.setMaxDocumentCount(2);
        File fileTwo = new File (System.getProperty("user.dir") + "/catcat/hi/tom/lenny/Lenny's_URI.json");
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsTxt(uriOne);
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
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
        File fileTwo = new File (System.getProperty("user.dir") + "/bannana/hi/tom/lenny/Lenny's_URI.json");
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsTxt(uriOne);
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
    }

    @Test 
    public void doesFileReallyExist () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/iceCream/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/iceCream/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/iceCream/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );

        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        docStore.setMaxDocumentCount(2);
        // assertEquals ("Testing initial get after memory limit" , inputStreamContentOne , docStore.getDocumentAsTxt(uriOne) );
        // docStore.setMaxDocumentCount(1);
        File fileTwo = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Lenny's_URI.json");
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsTxt(uriOne);
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
    }

    @Test 
    public void makeSureCorrectFilesAreMovedToDiscAndRemoved () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/iceCream/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/iceCream/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/iceCream/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(2);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(1);
        assertEquals ("inital second 1" , true , fileOne.exists() );
        assertEquals ("inital second 2" , true , fileTwo.exists() );
        assertEquals ("inital second 3" , false , fileThree.exists() );
        docStore.getDocumentAsPdf(uriOne);
        assertEquals ("getDocumentAsPdf" , false , fileOne.exists() );
        assertEquals ("getDocumentAsPdf" , true , fileTwo.exists() );
        assertEquals ("getDocumentAsPdf" , true , fileThree.exists() );
        docStore.getDocumentAsTxt(uriThree);
        assertEquals ("getDocumentAsTxt" , true , fileOne.exists() );
        assertEquals ("getDocumentAsTxt" , true , fileTwo.exists() );
        assertEquals ("getDocumentAsTxt" , false , fileThree.exists() );
        docStore.search("Ruben");
        assertEquals ("search" , true , fileOne.exists() );
        assertEquals ("search" , false , fileTwo.exists() );
        assertEquals ("search" , true , fileThree.exists() );
        docStore.searchByPrefix("To");
        assertEquals ("searchByPrefix" , true , fileOne.exists() );
        assertEquals ("searchByPrefix" , true , fileTwo.exists() );
        assertEquals ("searchByPrefix" , false , fileThree.exists() );
        docStore.searchPDFs("Lenny");
        assertEquals ("searchPDFs" , false , fileOne.exists() );
        assertEquals ("searchPDFs" , true , fileTwo.exists() );
        assertEquals ("searchPDFs" , true , fileThree.exists() );
        docStore.searchPDFsByPrefix("Rub");
        assertEquals ("searchPDFsByPrefix" , true , fileOne.exists() );
        assertEquals ("searchPDFsByPrefix" , false , fileTwo.exists() );
        assertEquals ("searchPDFsByPrefix" , true , fileThree.exists() );
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void deletedFilesRemovedFromDisk () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(2);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteDocument(uriOne);
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        // docStore.undo();
        // assertEquals ("initial 1" , false , fileOne.exists() );
        // assertEquals ("initial 2" , true , fileTwo.exists() );
        // assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAll("Tom");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.getDocumentAsTxt(uriTwo);
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo();
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("he");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo();
        assertEquals ("undoDeleteWPrefix 1" , false , fileOne.exists() );
        assertEquals ("undoDeleteWPrefix 2" , false , fileTwo.exists() );
        assertEquals ("undoDeleteWPrefix 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("h");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void deleteAllTest () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(2);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("h");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , true , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        assertTrue ("initial 3" , fileOne.exists() ^ fileTwo.exists() ^ fileThree.exists() ); // this is bc they're all given same time so any of them could be deleted from disk
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void putNull () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(2);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.putDocument(null , uriOne, DocumentStore.DocumentFormat.TXT);
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , true , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void putDuplicateDocMovedToDisk () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentCount(2);
        assertEquals ("testing if file really exists" , true , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        InputStream inputStreamOneCopy = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        docStore.putDocument(inputStreamOneCopy, uriOne, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );

        docStore.setMaxDocumentCount(10);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }  

    @Test 
    public void deleteAllTestWBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(1800);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("h");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , true , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        assertTrue ("initial 3" , fileOne.exists() ^ fileTwo.exists() ^ fileThree.exists() ); // this is bc they're all given same time so any of them could be deleted from disk
        docStore.setMaxDocumentBytes(10000);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test
    public void memoryLimits1Bytes() throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl ();
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/catcat/hi/tom/lenny/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/catcat/hi/tom/lenny/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/catcat/hi/tom/lenny/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        docStore.setMaxDocumentBytes(1800);
        File fileTwo = new File (System.getProperty("user.dir") + "/catcat/hi/tom/lenny/Lenny's_URI.json");
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        docStore.setMaxDocumentBytes(5000);
        docStore.getDocumentAsTxt(uriOne);
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
    }

    @Test
    public void memoryLimitsBytes() throws URISyntaxException {
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
        docStore.setMaxDocumentBytes(1800);
        File fileTwo = new File (System.getProperty("user.dir") + "/bannana/hi/tom/lenny/Lenny's_URI.json");
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        docStore.setMaxDocumentBytes(10000);
        docStore.getDocumentAsTxt(uriOne);
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
    }

    @Test 
    public void doesFileReallyExistBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/iceCream/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/iceCream/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/iceCream/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );

        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        docStore.setMaxDocumentBytes(1800);
        // assertEquals ("Testing initial get after memory limit" , inputStreamContentOne , docStore.getDocumentAsTxt(uriOne) );
        // docStore.setMaxDocumentCount(1);
        File fileTwo = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Lenny's_URI.json");
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        docStore.setMaxDocumentBytes(100000);
        docStore.getDocumentAsTxt(uriOne);
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
    }

    @Test 
    public void makeSureCorrectFilesAreMovedToDiscAndRemovedBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/iceCream/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/iceCream/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hey, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/iceCream/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/iceCream/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(1800);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(999);
        assertEquals ("inital second 1" , true , fileOne.exists() );
        assertEquals ("inital second 2" , true , fileTwo.exists() );
        assertEquals ("inital second 3" , false , fileThree.exists() );
        docStore.getDocumentAsPdf(uriOne);
        assertEquals ("getDocumentAsPdf" , false , fileOne.exists() );
        assertEquals ("getDocumentAsPdf" , true , fileTwo.exists() );
        assertEquals ("getDocumentAsPdf" , true , fileThree.exists() );
        docStore.getDocumentAsTxt(uriThree);
        assertEquals ("getDocumentAsTxt" , true , fileOne.exists() );
        assertEquals ("getDocumentAsTxt" , true , fileTwo.exists() );
        assertEquals ("getDocumentAsTxt" , false , fileThree.exists() );
        docStore.search("Ruben");
        assertEquals ("search" , true , fileOne.exists() );
        assertEquals ("search" , false , fileTwo.exists() );
        assertEquals ("search" , true , fileThree.exists() );
        docStore.searchByPrefix("To");
        assertEquals ("searchByPrefix" , true , fileOne.exists() );
        assertEquals ("searchByPrefix" , true , fileTwo.exists() );
        assertEquals ("searchByPrefix" , false , fileThree.exists() );
        docStore.searchPDFs("Lenny");
        assertEquals ("searchPDFs" , false , fileOne.exists() );
        assertEquals ("searchPDFs" , true , fileTwo.exists() );
        assertEquals ("searchPDFs" , true , fileThree.exists() );
        docStore.searchPDFsByPrefix("Rub");
        assertEquals ("searchPDFsByPrefix" , true , fileOne.exists() );
        assertEquals ("searchPDFsByPrefix" , false , fileTwo.exists() );
        assertEquals ("searchPDFsByPrefix" , true , fileThree.exists() );
        docStore.setMaxDocumentBytes(100000);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void deletedFilesRemovedFromDiskBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(1800);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteDocument(uriOne);
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        // docStore.undo();
        // assertEquals ("initial 1" , false , fileOne.exists() );
        // assertEquals ("initial 2" , true , fileTwo.exists() );
        // assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAll("Tom");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.getDocumentAsTxt(uriTwo);
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo();
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("he");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo();
        assertEquals ("undoDeleteWPrefix 1" , false , fileOne.exists() );
        assertEquals ("undoDeleteWPrefix 2" , false , fileTwo.exists() );
        assertEquals ("undoDeleteWPrefix 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("h");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        docStore.setMaxDocumentBytes(4000);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void deleteAllTestBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(1800);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.deleteAllWithPrefix("h");
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , true , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        assertTrue ("initial 3" , fileOne.exists() ^ fileTwo.exists() ^ fileThree.exists() ); // this is bc they're all given same time so any of them could be deleted from disk
        docStore.setMaxDocumentBytes(4000);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void putNullBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(1800);
        assertEquals ("initial 1" , true , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.putDocument(null , uriOne, DocumentStore.DocumentFormat.TXT);
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , false , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.undo(); //BIG BUG WHEN I CALL UNDO AFTER DELETING EVERYTHING
        assertEquals ("initial 1" , false , fileOne.exists() );
        assertEquals ("initial 2" , true , fileTwo.exists() );
        assertEquals ("initial 3" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(4000);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }

    @Test 
    public void putDuplicateDocMovedToDiskBytes () throws URISyntaxException {
        File file = new File (System.getProperty("user.dir"));
        File fileOne = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Lenny's_URI.json");
        File fileTwo = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Ruben's_URI.json");
        File fileThree = new File (System.getProperty("user.dir") + "/KITKAT/hi/tom/Tom's_URI.json");
        DocumentStoreImpl docStore = new DocumentStoreImpl (file);
        String inputStreamContentOne = "Hey, I'm Lenny";
        InputStream inputStreamOne = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        URI uriOne = new URI("/KITKAT/hi/tom/Lenny's_URI");
        DocumentImpl documentOne = new DocumentImpl(uriOne, inputStreamContentOne, inputStreamContentOne.hashCode() );
        String inputStreamContentTwo = "Hy, I'm Ruben";
        InputStream inputStreamTwo = new ByteArrayInputStream(inputStreamContentTwo.getBytes() );
        URI uriTwo = new URI("/KITKAT/hi/tom/Ruben's_URI");
        DocumentImpl documentTwo = new DocumentImpl(uriTwo, inputStreamContentTwo, inputStreamContentTwo.hashCode() );
        String inputStreamContentThree = "Hey, I'm Tom";
        InputStream inputStreamThree = new ByteArrayInputStream(inputStreamContentThree.getBytes() );
        URI uriThree = new URI("/KITKAT/hi/tom/Tom's_URI");
        DocumentImpl documentThree = new DocumentImpl(uriThree, inputStreamContentThree, inputStreamContentThree.hashCode() );
        docStore.putDocument(inputStreamOne, uriOne, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamTwo, uriTwo, DocumentStore.DocumentFormat.TXT);
        docStore.putDocument(inputStreamThree, uriThree, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        docStore.setMaxDocumentBytes(1800);
        assertEquals ("testing if file really exists" , true , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
        InputStream inputStreamOneCopy = new ByteArrayInputStream(inputStreamContentOne.getBytes() );
        docStore.putDocument(inputStreamOneCopy, uriOne, DocumentStore.DocumentFormat.TXT);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , true , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );

        docStore.setMaxDocumentBytes(4000);
        docStore.getDocumentAsPdf(uriOne);
        docStore.getDocumentAsPdf(uriTwo);
        docStore.getDocumentAsPdf(uriThree);
        assertEquals ("testing if file really exists" , false , fileOne.exists() );
        assertEquals ("testing if file really exists" , false , fileTwo.exists() );
        assertEquals ("testing if file really exists" , false , fileThree.exists() );
    }  

    
}