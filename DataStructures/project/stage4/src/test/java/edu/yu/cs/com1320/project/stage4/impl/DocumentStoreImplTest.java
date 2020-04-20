package edu.yu.cs.com1320.project.stage4.impl;

import edu.yu.cs.com1320.project.stage4.DocumentStore;
import edu.yu.cs.com1320.project.stage4.impl.DocumentStoreImpl;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage4.impl.DocumentImpl;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.*;
import static org.junit.Assert.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DocumentStoreImplTest  {

    private HashTableImpl <URI , DocumentImpl > hashTable = new HashTableImpl<URI , DocumentImpl>();
    private DocumentStoreImpl docStore = new DocumentStoreImpl();

    // PDF Test:
    @Test
    public void testPutDocumentPDFNewPdf() throws IOException , URISyntaxException {
        /**
         * Required for constructor:
         * InputStream , uri , DocumentFormat
         * Cannot make a file for the test code!
         */

        PDDocument doc = new PDDocument();
        String dataOfFile = "Hi, I'm Tom";
        PDPage page = new PDPage();
        PDFont font = PDType1Font.HELVETICA;
        doc.addPage(page);
        PDPageContentStream data = new PDPageContentStream(doc , page);
        data.beginText();
        data.setFont(font , 12);
        data.newLineAtOffset(100, 700);
        data.showText(dataOfFile);
        data.endText();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream ();
        data.close();
        doc.save(bytes);
        doc.close();
        byte [] returnValue = bytes.toByteArray();
        InputStream txtAsIS = new ByteArrayInputStream(returnValue);
        URI uri = new URI ("uri1");
        int testPut = docStore.putDocument(txtAsIS , uri , DocumentStore.DocumentFormat.PDF);
        int expected = dataOfFile.hashCode();
    //    assertEquals("Testing if hashcode with put PDF works" , expected , testPut);
        assertEquals("Testing if hashcode with put PDF works" , 0 , testPut);
        DocumentImpl testDoc = new DocumentImpl (uri , dataOfFile , testPut , returnValue);
        assertEquals("Testing if get doc as text for pdf works" , "Hi, I'm Tom" , testDoc.getDocumentAsTxt());
    }

    @Test
    public void testIfInputStreamIsNull() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if I get old value when I return null" , hashCodeOfStream, doc.putDocument(null , uri , DocumentStore.DocumentFormat.TXT));
    }

    @Test
    public void testIfInputStreamIsNullV2() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        assertEquals("Testing if I get old value when I return null and no prev. value exists" , 0, doc.putDocument(null , uri , DocumentStore.DocumentFormat.TXT));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfUriIsNull() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        int testPutHashCode = doc.putDocument(inputStream , null , DocumentStore.DocumentFormat.TXT);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfDocTypeIsNull() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , null);
    }

    // TXT Test
    @Test
    public void testPutDocumentTXT() throws IOException, URISyntaxException {
        /**
         * Constructor for PutDoc: (InputStream input, URI uri, DocumentFormat format)
         * Test Cases:
         * 1) Make sure hashCodes are equal to each other.
         * 2) Return hashcode and not put if you have a duplicate -- test this later.
         */
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if hash codes are correct" , 0, testPutHashCode);
        String inputStreamContent2 = "Hey, I'm Pau";
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent.getBytes() );
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if hash codes are correct after putting second doc." , hashCodeOfStream, testPutHashCode2);
    }


    @Test
    public void testGetDocAsText() throws URISyntaxException {
        /**
         * Test Cases:
         * 1) Check if document exists.
         *    If document does not exist then return null, if it returns null, then I have to test in a separate test case, expecting a nullPointer exception.
         *    If document does exist, then return the String or text.
         */

        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        String txt = doc.getDocumentAsTxt(uri);
        assertEquals("Testing if GetDocAsTxt returns correct string" , "Hey, I'm Tom" , txt);
    }

    @Test
    public void testGetDocAsTextNullReturn ()  throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        String txt = doc.getDocumentAsTxt(uri);
        assertEquals("Testing if GetDocAsTxt returns null when no txt exists" , null , txt);
    }

    @Test
    public void testDeleteDocument() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if deleting a value is true" , true , doc.deleteDocument(uri));
    }

    @Test
    public void testDeleteDocumentThatDoesntExist() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = inputStreamContent.hashCode();
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        assertEquals("Testing if deleting a value is true" , false , doc.deleteDocument(uri));
    }

    @Test
    public void testUndo() throws URISyntaxException {
        /**
         * TEST CASES:
         * 1) Undoing putting in a doc.
         * 2) Undoing deleting a doc.
         */
        DocumentStoreImpl doc = new DocumentStoreImpl(); 
        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "doc1";
        String inputStreamContent2 = "doc2";
        String inputStreamContent3 = "doc3";
        String inputStreamContent4 = "doc4";
        String inputStreamContent5 = "doc5";

        DocumentImpl doc1 = new DocumentImpl (uri1 , "doc1" , "doc1".hashCode() );
        DocumentImpl doc2 = new DocumentImpl (uri2 , "doc2" , "doc2".hashCode() );
        DocumentImpl doc3 = new DocumentImpl (uri3 , "doc3" , "doc3".hashCode() );
        DocumentImpl doc4 = new DocumentImpl (uri4 , "doc4" , "doc4".hashCode() );
        DocumentImpl doc5 = new DocumentImpl (uri5 , "doc5" , "doc5".hashCode() );

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode1);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode2);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode3);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode4);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode5);

        assertEquals("Testing if hash codes are correct" , null , hashTable.get(uri1));
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode2);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode3);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode4);
        assertEquals("Testing if hash codes are correct" , 0 , testPutHashCode5);
    }

    @Test
    public void testUndoMethod() throws URISyntaxException{

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");

        // First round of putting in docs:
        String inputStreamContent1 = "doc1";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);

        // Second round of putting in docs:
        String inputStreamContent1Dup = "doc1Dup";
        InputStream inputStream1Dup = new ByteArrayInputStream(inputStreamContent1Dup.getBytes() );
        int testPutHashCode1Dup = doc.putDocument(inputStream1Dup , uri1 , DocumentStore.DocumentFormat.TXT);

        // Third round of putting in docs, actually deleting them:
        boolean testPutHashCode1Delete = doc.deleteDocument(uri1);

        doc.undo();
        assertEquals("Testing if undoing a delete method works" , "doc1Dup" , doc.getDocumentAsTxt(uri1));
        doc.undo();
        assertEquals("Testing if undoing switching a value of a doc works" , "doc1" , doc.getDocumentAsTxt(uri1));
        doc.undo();
        assertEquals("Testing if undoing an initial entry works" , null , doc.getDocumentAsTxt(uri1));
    }

    @Test
    public void testUndoDuplicates() throws URISyntaxException{

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");

        // First round of putting in docs:
        String inputStreamContent1 = "doc1";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode1ExactDup = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);

        doc.undo();
        assertEquals("Testing if undoing a delete method works" , "doc1" , doc.getDocumentAsTxt(uri1));
        doc.undo();
        assertEquals("Testing if undoing switching a value of a doc works" , null , doc.getDocumentAsTxt(uri1));
    }

    @Test
    public void testUndoNullInputStream() throws URISyntaxException{

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");

        // First round of putting in docs:
        String inputStreamContent1 = "doc1";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode1ExactDup = doc.putDocument(null , uri1 , DocumentStore.DocumentFormat.TXT);

        doc.undo();
        assertEquals("Testing if undoing a delete method works" , "doc1" , doc.getDocumentAsTxt(uri1));
        doc.undo();
        assertEquals("Testing if undoing switching a value of a doc works" , null , doc.getDocumentAsTxt(uri1));
    }

    @Test
    public void testUndoWhenDeletingNothing() throws URISyntaxException{

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");

        // First round of putting in docs:
        String inputStreamContent1 = "doc1";
        // InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        boolean testPutHashCode1Delete = doc.deleteDocument(uri1);
        doc.undo();
        assertEquals("Testing if undoing a delete method works" , null , doc.getDocumentAsTxt(uri1));
    }

    @Test(expected = IllegalStateException.class)
    public void undoNothing () {

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        doc.undo();
    }

    @Test(expected = IllegalStateException.class)
    public void undoNotFound () throws URISyntaxException {

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        String inputStreamContent1 = "doc1";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        doc.undo(uri2);
    }

    @Test(expected = IllegalStateException.class)
    public void undoNothingTwo () throws URISyntaxException {

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        doc.undo(uri1);
    }

    @Test(expected = IllegalStateException.class)
    public void undoNothingTwoV2 () throws URISyntaxException {

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        String inputStreamContent1 = "doc1";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        doc.undo();
        doc.undo(uri1);
    }

    @Test
    public void testUndoMethoBig() throws URISyntaxException{

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "doc1";
        String inputStreamContent2 = "doc2";
        String inputStreamContent3 = "doc3";
        String inputStreamContent4 = "doc4";
        String inputStreamContent5 = "doc5";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // First round of putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        String inputStreamContent1Dup = "doc1Dup";
        String inputStreamContent2Dup = "doc2Dup";
        String inputStreamContent3Dup = "doc3Dup";
        String inputStreamContent4Dup = "doc4Dup";
        String inputStreamContent5Dup = "doc5Dup";

        InputStream inputStream1Dup = new ByteArrayInputStream(inputStreamContent1Dup.getBytes() );
        InputStream inputStream2Dup = new ByteArrayInputStream(inputStreamContent2Dup.getBytes() );
        InputStream inputStream3Dup = new ByteArrayInputStream(inputStreamContent3Dup.getBytes() );
        InputStream inputStream4Dup = new ByteArrayInputStream(inputStreamContent4Dup.getBytes() );
        InputStream inputStream5Dup = new ByteArrayInputStream(inputStreamContent5Dup.getBytes() );
        
        // Second round of putting in docs:
        int testPutHashCode1Dup = doc.putDocument(inputStream1Dup , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2Dup = doc.putDocument(inputStream2Dup , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3Dup = doc.putDocument(inputStream3Dup , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4Dup = doc.putDocument(inputStream4Dup , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5Dup = doc.putDocument(inputStream5Dup , uri5 , DocumentStore.DocumentFormat.TXT);

        // Third round of putting in docs, actually deleting them:
        boolean testPutHashCode1Delete = doc.deleteDocument(uri1);
        boolean testPutHashCode2Delete = doc.deleteDocument(uri2);
        boolean testPutHashCode3Delete = doc.deleteDocument(uri3);
        boolean testPutHashCode4Delete = doc.deleteDocument(uri4);
        boolean testPutHashCode5Delete = doc.deleteDocument(uri5);

        doc.undo(uri1);
        assertEquals("Testing if undoing a delete method works" , "doc1Dup" , doc.getDocumentAsTxt(uri1));
        doc.undo(uri1);
        assertEquals("Testing if undoing switching a value of a doc works" , "doc1" , doc.getDocumentAsTxt(uri1));
        doc.undo(uri1);
        assertEquals("Testing if undoing an initial entry works" , null , doc.getDocumentAsTxt(uri1));    

        doc.undo(uri2);
        assertEquals("Testing if undoing a delete method works" , "doc2Dup" , doc.getDocumentAsTxt(uri2));
        doc.undo(uri2);
        assertEquals("Testing if undoing switching a value of a doc works" , "doc2" , doc.getDocumentAsTxt(uri2));
        doc.undo(uri3);
        assertEquals("Testing if undoing a delete method works" , "doc3Dup" , doc.getDocumentAsTxt(uri3));
        doc.undo(uri3);
        assertEquals("Testing if undoing switching a value of a doc works" , "doc3" , doc.getDocumentAsTxt(uri3));
        doc.undo(uri4);
        assertEquals("Testing if undoing a delete method works" , "doc4Dup" , doc.getDocumentAsTxt(uri4));
        doc.undo(uri5);
        assertEquals("Testing if undoing a delete method works" , "doc5Dup" , doc.getDocumentAsTxt(uri5));
        doc.undo(uri2);
        assertEquals("Testing if undoing an initial entry works" , null , doc.getDocumentAsTxt(uri2));    
        doc.undo(uri3);
        assertEquals("Testing if undoing an initial entry works" , null , doc.getDocumentAsTxt(uri3)); 
        doc.undo(uri5);  
        assertEquals("Testing if undoing switching a value of a doc works" , "doc5" , doc.getDocumentAsTxt(uri5));
        doc.undo(uri4);  
        assertEquals("Testing if undoing switching a value of a doc works" , "doc4" , doc.getDocumentAsTxt(uri4));
        doc.undo(uri5);
        assertEquals("Testing if undoing an initial entry works" , null , doc.getDocumentAsTxt(uri5));    
        doc.undo(uri4);
        assertEquals("Testing if undoing an initial entry works" , null , doc.getDocumentAsTxt(uri4)); 
    }

    @Test
    public void testUndoWhenNothingToDelete() throws URISyntaxException {

        DocumentStoreImpl doc = new DocumentStoreImpl(); 
        URI uri = new URI ("hi");
        assertEquals ("testUndoWhenNothingToDelete" , false , doc.deleteDocument(uri) ); // problem with this test bc the actual method returns null as well.
    }

    @Test
    public void testSearchTextDocsNoDup() throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Testing search for texts" , testList , doc.search("Hey") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        testListTwo.add(inputStreamContent2);
        assertEquals("Testing search for texts" , testListTwo , doc.search("Lenny") );
        ArrayList <String> emptyList = new ArrayList <String> ();
        assertEquals("Testing search for texts" , emptyList , doc.search("fewfwqwf") );
    }

    @Test
    public void testSearchTextDocsYesDups() throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "3 hey hey hey";
        String inputStreamContent2 = "2 hey hey";
        String inputStreamContent3 = "4 hey hey hey hey";
        String inputStreamContent4 = "1 Hey Im Tania";
        String inputStreamContent5 = "1 Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Testing search for texts1" , testList , doc.search("Hey") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        testListTwo.add(inputStreamContent2);
        assertEquals("Testing search for texts2" , testListTwo , doc.search("2") );
        ArrayList <String> emptyList = new ArrayList <String> ();
        assertEquals("Testing search for texts3" , emptyList , doc.search("fewfwqwf") );
    }

    // @Test
    // public void testSearchPDFDocsNoDup() throws URISyntaxException {
    //     DocumentStoreImpl doc = new DocumentStoreImpl(); 

    //     URI uri1 = new URI("1");
    //     URI uri2 = new URI("2");
    //     URI uri3 = new URI("3");
    //     URI uri4 = new URI("4");
    //     URI uri5 = new URI("5");

    //     String inputStreamContent1 = "Hey Im Tom";
    //     String inputStreamContent2 = "Hey Im Lenny";
    //     String inputStreamContent3 = "Hey Im Ruben";
    //     String inputStreamContent4 = "Hey Im Tania";
    //     String inputStreamContent5 = "Hey Im Maalon";

    //     InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
    //     InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
    //     InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
    //     InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
    //     InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

    //     byte [] byteArray1 = inputStreamContent1.getBytes();
    //     byte [] byteArray2 = inputStreamContent1.getBytes();
    //     byte [] byteArray3 = inputStreamContent1.getBytes();
    //     byte [] byteArray4 = inputStreamContent1.getBytes();
    //     byte [] byteArray5 = inputStreamContent1.getBytes();

    //     // putting in docs:
    //     int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
    //     int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
    //     int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
    //     int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
    //     int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

    //     List <byte []> testList = doc.searchPDFs("Hey");
    //     ArrayList <String> actualValueAsString = new ArrayList <String> ();
    //     for (byte [] bytes: testList) {
    //         String docText = new String (bytes);
    //         actualValueAsString.add(docText);
    //     }
    //     ArrayList <String> expectedValueAsString = new ArrayList <String> ();
    //     expectedValueAsString.add(inputStreamContent1);
    //     expectedValueAsString.add(inputStreamContent2);
    //     expectedValueAsString.add(inputStreamContent3);
    //     expectedValueAsString.add(inputStreamContent4);
    //     expectedValueAsString.add(inputStreamContent5);
    //     assertEquals("Testing search for texts" , expectedValueAsString , actualValueAsString );
    //     // ArrayList <byte []> testListTwo = new ArrayList <byte []> ();
    //     // testListTwo.add(byteArray2);
    //     // assertEquals("Testing search for texts" , testListTwo , doc.search("Lenny") );
    // }

    @Test
    public void testSearchPrefixNoDups() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "She";
        String inputStreamContent2 = "Sells";
        String inputStreamContent3 = "Sea";
        String inputStreamContent4 = "Shells";
        String inputStreamContent5 = "shunuhnuh";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Testing search for texts 1 " , testList , doc.searchByPrefix("s") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        testListTwo.add(inputStreamContent1);
        testListTwo.add(inputStreamContent4);
        testListTwo.add(inputStreamContent5);        
        assertEquals("Testing search for texts 2 " , testListTwo , doc.searchByPrefix("sh") );
        ArrayList <String> testListThree = new ArrayList <String> ();
        testListThree.add(inputStreamContent5);        
        assertEquals("Testing search for texts 3 " , testListThree , doc.searchByPrefix("shu") );
    }

    @Test
    public void testSearchPrefixYesDups() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "5 She sh shu sha shy";
        String inputStreamContent2 = "0 Sells";
        String inputStreamContent3 = "3 Sea shy shy";
        String inputStreamContent4 = "9 Shells shell sha shu sha sha shu shim shimmy";
        String inputStreamContent5 = "1 shunuhnuh";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent5);
        assertEquals("Testing search for texts 1 " , testList , doc.searchByPrefix("s") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        testListTwo.add(inputStreamContent4);
        testListTwo.add(inputStreamContent1);
        testListTwo.add(inputStreamContent3);   
        testListTwo.add(inputStreamContent5);             
        assertEquals("Testing search for texts 2 " , testListTwo , doc.searchByPrefix("sh") );
        ArrayList <String> testListThree = new ArrayList <String> ();
        testListThree.add(inputStreamContent4);    
        testListThree.add(inputStreamContent1);  
        testListThree.add(inputStreamContent5);                      
        assertEquals("Testing search for texts 3 " , testListThree , doc.searchByPrefix("shu") );
    }

    @Test
    public void putNullDeleteTrieNodeTxt() throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Testing doc beforing putting null as value" , testList , doc.search("Hey") );
        ArrayList <String> testList2 = new ArrayList <String> ();
        testList2.add(inputStreamContent1);
        testList2.add(inputStreamContent4);
        testList2.add(inputStreamContent5);
        doc.putDocument(null , uri3 , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing doc aftering putting null as value" , testList2 , doc.search("Hey") );
    } 

    @Test
    public void testDeleteAllDocs() throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        HashSet <URI> testList = new HashSet <URI> ();
        testList.add(uri1);
        testList.add(uri3);
        testList.add(uri4);
        testList.add(uri5);
        assertEquals("Testing delete all" , testList , doc.deleteAll("Hey") );
        ArrayList <String> secondTestSet = new ArrayList <String>();
        secondTestSet.add(inputStreamContent2);
        assertEquals("Testing searching for nondeleted values" , secondTestSet , doc.search("Im"));
    }

    @Test
    public void testDeletePrefixAllDocs() throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey shIm Tom";
        String inputStreamContent2 = "shIm Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im shTania";
        String inputStreamContent5 = "Hey shIm Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        HashSet <URI> testList = new HashSet <URI> ();
        testList.add(uri1);
        testList.add(uri2);
        testList.add(uri4);
        testList.add(uri5);
        assertEquals("Testing delete all" , testList , doc.deleteAllWithPrefix("sh") );
        ArrayList <String> secondTestSet = new ArrayList <String>();
        secondTestSet.add(inputStreamContent3);
        assertEquals("Testing searching for nondeleted values" , secondTestSet , doc.search("Hey"));
        assertEquals("Testing searching for nondeleted values" , secondTestSet , doc.search("Im"));
    }

    @Test
    public void TestTrieUndoPutDocAsTextUndoGeneral() throws URISyntaxException{
        // undoing in  the case where I put a doc text and now want to undo the last command in my command stack.
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Testing search for texts" , testList , doc.search("Hey") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        testListTwo.add(inputStreamContent2);
        assertEquals("Testing search for texts" , testListTwo , doc.search("Lenny") );
        // Now will test for undo:
        doc.undo();
        ArrayList <String> undidOnce = new ArrayList <String> ();
        undidOnce.add(inputStreamContent1);
        undidOnce.add(inputStreamContent2);
        undidOnce.add(inputStreamContent3);
        undidOnce.add(inputStreamContent4);
        assertEquals("Undid one time" ,undidOnce , doc.search("Hey") );
        // Now test for two undos in a row:
        doc.undo();
        doc.undo();
        ArrayList <String> undidTwice = new ArrayList <String> ();
        undidTwice.add(inputStreamContent1);
        undidTwice.add(inputStreamContent2);
        assertEquals("Undid two times" ,undidTwice , doc.search("Hey") );
        // Undo everything:
        doc.undo();
        doc.undo();
        ArrayList <String> undidEverything = new ArrayList <String> ();
        assertEquals("Undid everything" ,undidEverything , doc.search("Hey") );
    }

    @Test
    public void TestTrieUndoPutDocAsTextUndoSpecific() throws URISyntaxException{ // assuming PDF test will work as well. Maybe Ill test later to make sure, but should work.
        // undoing in  the case where I put a doc text and now want to undo the last command in my command stack.
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Testing search for texts" , testList , doc.search("Hey") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        testListTwo.add(inputStreamContent2);
        assertEquals("Testing search for texts" , testListTwo , doc.search("Lenny") );
        // Now will test for undo:
        doc.undo(uri2);
        ArrayList <String> undidOnce = new ArrayList <String> ();
        undidOnce.add(inputStreamContent1);
        undidOnce.add(inputStreamContent3);
        undidOnce.add(inputStreamContent4);
        undidOnce.add(inputStreamContent5);
        assertEquals("Undid one time" ,undidOnce , doc.search("Hey") );
        // Now test for two undos in a row:
        doc.undo(uri1);
        doc.undo(uri5);
        ArrayList <String> undidTwice = new ArrayList <String> ();
        undidTwice.add(inputStreamContent3);
        undidTwice.add(inputStreamContent4);
        assertEquals("Undid two times" ,undidTwice , doc.search("Hey") );
        // Undo everything:
        doc.undo(uri3);
        doc.undo(uri4);
        ArrayList <String> undidEverything = new ArrayList <String> ();
        assertEquals("Undid everything" ,undidEverything , doc.search("Hey") );
    }

    @Test
    public void TestTrieUndoPutNullDocUndoGeneral() throws URISyntaxException{
        // undoing in  the case where I put a doc text and now want to undo the last command in my command stack.
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        // putting in docs:
        // int testPutHashCode1Null = doc.putDocument(null , uri1 , DocumentStore.DocumentFormat.TXT);
        // int testPutHashCode2Null = doc.putDocument(null , uri2 , DocumentStore.DocumentFormat.TXT);
        // int testPutHashCode3Null = doc.putDocument(null , uri3 , DocumentStore.DocumentFormat.TXT);
        // int testPutHashCode4Null = doc.putDocument(null , uri4 , DocumentStore.DocumentFormat.TXT);
        // int testPutHashCode5Null = doc.putDocument(null , uri5 , DocumentStore.DocumentFormat.TXT);

        // deleting docs:
        doc.deleteDocument(uri1);
        doc.deleteDocument(uri2);
        doc.deleteDocument(uri3);
        doc.deleteDocument(uri4);
        doc.deleteDocument(uri5);

        ArrayList <String> testList = new ArrayList <String> ();
        assertEquals("Testing search for texts" , testList , doc.search("Hey") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        assertEquals("Testing search for texts" , testListTwo , doc.search("Lenny") );
        // Now will test for undo:
        doc.undo();
        ArrayList <String> undidOnce = new ArrayList <String> ();
        undidOnce.add(inputStreamContent5);
        assertEquals("Undid one time" ,undidOnce , doc.search("Hey") );
        // Now test for two undos in a row:
        doc.undo();
        doc.undo();
        ArrayList <String> undidTwice = new ArrayList <String> ();
        undidTwice.add(inputStreamContent5);
        undidTwice.add(inputStreamContent4);
        undidTwice.add(inputStreamContent3);
        assertEquals("Undid two times" ,undidTwice , doc.search("Hey") );
        // Undo everything:
        doc.undo();
        doc.undo();
        ArrayList <String> undidEverything = new ArrayList <String> ();
        undidEverything.add(inputStreamContent5);
        undidEverything.add(inputStreamContent4);
        undidEverything.add(inputStreamContent3);
        undidEverything.add(inputStreamContent2);
        undidEverything.add(inputStreamContent1);
        assertEquals("Undid everything" ,undidEverything , doc.search("Hey") );
    }

    @Test
    public void TestTrieUndoDeleteDocUndoGeneral() throws URISyntaxException{
        // undoing in  the case where I put a doc text and now want to undo the last command in my command stack.
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);

        // putting in docs:
        doc.deleteDocument(uri1);
        doc.deleteDocument(uri2);
        doc.deleteDocument(uri3);
        doc.deleteDocument(uri4);
        doc.deleteDocument(uri5);

        ArrayList <String> testList = new ArrayList <String> ();
        assertEquals("Testing search for texts" , testList , doc.search("Hey") );
        ArrayList <String> testListTwo = new ArrayList <String> ();
        assertEquals("Testing search for texts" , testListTwo , doc.search("Lenny") );
        // Now will test for undo:
        doc.undo();
        ArrayList <String> undidOnce = new ArrayList <String> ();
        undidOnce.add(inputStreamContent5);
        assertEquals("Undid one time" ,undidOnce , doc.search("Hey") );
        // Now test for two undos in a row:
        doc.undo();
        doc.undo();
        ArrayList <String> undidTwice = new ArrayList <String> ();
        undidTwice.add(inputStreamContent5);
        undidTwice.add(inputStreamContent4);
        undidTwice.add(inputStreamContent3);
        assertEquals("Undid two times" ,undidTwice , doc.search("Hey") );
        // Undo everything:
        doc.undo();
        doc.undo();
        ArrayList <String> undidEverything = new ArrayList <String> ();
        undidEverything.add(inputStreamContent5);
        undidEverything.add(inputStreamContent4);
        undidEverything.add(inputStreamContent3);
        undidEverything.add(inputStreamContent2);
        undidEverything.add(inputStreamContent1);
        assertEquals("Undid everything" ,undidEverything , doc.search("Hey") );
    }

    @Test
    public void testDeleteDocsFromTrie() throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");

        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";

        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 

        // putting in docs:
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        // int testPutHashCode2 = doc.putDocument(null , uri1 , DocumentStore.DocumentFormat.TXT);

        // putting in docs:
        doc.deleteDocument(uri1);
        // doc.deleteDocument(uri2);
        // doc.deleteDocument(uri3);
        // doc.deleteDocument(uri4);
        // doc.deleteDocument(uri5);

        ArrayList <String> testList = new ArrayList <String> ();
        ArrayList <String> emptyList = new ArrayList <String> ();
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals("Try to get doc 1 from Trie in doc store Hey" , testList , doc.search("Hey"));
        assertEquals("Try to get doc 1 from Trie in doc store Im" , testList , doc.search("Im"));
        assertEquals("Try to get doc 1 from Trie in doc store Tom" , emptyList , doc.search("Tom"));
    }

    @Test
    public void testUndoADocReplacetion () throws URISyntaxException {
        DocumentStoreImpl docStore = new DocumentStoreImpl(); 
        URI uri1 = new URI ("1");
        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        docStore.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        assertEquals("Try to get doc 1 from Trie in doc store Hey" , testList , docStore.search("Hey"));
        // Replace old doc:
        docStore.putDocument(inputStream2 , uri1 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList2 = new ArrayList <String> ();
        testList2.add(inputStreamContent2);
        assertEquals("Try to get doc 1 from Trie in doc store Hey" , testList2 , docStore.search("Hey"));
        docStore.undo();
        assertEquals("Try to get doc 1 from Trie in doc store Hey" , testList , docStore.search("Hey"));
        docStore.undo();
        ArrayList emptyList = new ArrayList <>();
        assertEquals("Try to get doc 1 from Trie in doc store Hey" , emptyList , docStore.search("Hey"));
    }

    @Test
    public void testUndoCommandSet () throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");
        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals ("Testing if everything was added correctly" , testList , doc.search("hey") );
        doc.deleteAll("Hey");
        ArrayList <String> testList2 = new ArrayList <String> ();
        assertEquals ("Testing if everything was deleted correctly 1" , testList2 , doc.search("hey") );
        assertEquals ("Testing if everything was deleted correctly 2" , testList2 , doc.search("im") );
        doc.undo();
        assertEquals ("Testing if everything was undid correctly" , testList , doc.search("hey") );
        doc.undo();
        testList.remove(inputStreamContent5);
        assertEquals ("Testing if everything was undid correctly 2" , testList , doc.search("hey") );
        doc.undo();
        doc.undo();
        doc.undo();
        doc.undo();
        assertEquals ("Testing if everything was undid correctly 3" , testList2 , doc.search("hey") );
    }

    @Test
    public void testUndoURICommandSet () throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");
        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals ("Testing if everything was added correctly" , testList , doc.search("hey") );
        HashSet testSet = new HashSet ();
        testSet.add(uri1);
        testSet.add(uri2);
        testSet.add(uri3);
        testSet.add(uri4);
        testSet.add(uri5);
        assertEquals ("Testing if everything was added correctly" , testSet , doc.deleteAll("hey") );
        ArrayList <String> testList2 = new ArrayList <String> ();
        assertEquals ("Testing if everything was deleted correctly 1" , testList2 , doc.search("hey") );
        assertEquals ("Testing if everything was deleted correctly 2" , testList2 , doc.search("im") );
        doc.undo(uri1);
        ArrayList <String> testList3 = new ArrayList <String> ();
        testList3.add(inputStreamContent1);
        assertEquals ("Testing if everything was undid correctly" , testList3 , doc.search("hey") );
        doc.undo(uri4);
        testList3.add(inputStreamContent4);
        assertEquals ("Testing if everything was undid correctly 2" , testList3 , doc.search("hey") );
        doc.undo(uri3);
        doc.undo(uri2);
        doc.undo(uri5);
        assertEquals ("Testing if everything was undid correctly 3" , testList.size() , doc.search("hey").size() );
    }

    @Test
    public void testUndoURICommandSetPrefix () throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");
        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals ("Testing if everything was added correctly" , testList , doc.search("hey") );
        HashSet testSet = new HashSet ();
        testSet.add(uri1);
        testSet.add(uri4);
        assertEquals ("Testing if everything was added correctly" , testSet , doc.deleteAllWithPrefix("t") );
        ArrayList <String> testSearch = new ArrayList <String> ();
        testSearch.add(inputStreamContent2);
        testSearch.add(inputStreamContent3);
        testSearch.add(inputStreamContent5);
        assertEquals ("Testing if everything was added correctly" , testSearch , doc.search("hey") );
        assertEquals ("Testing if everything was added correctly" , testSearch , doc.search("im") );
        ArrayList <String> emptyList1 = new ArrayList <String> ();
        assertEquals ("Testing if everything was added correctly 41" , emptyList1 , doc.search("tom") );
        doc.deleteAllWithPrefix("i");
        ArrayList <String> testList2 = new ArrayList <String> ();
        assertEquals ("Testing if everything was deleted correctly 1" , testList2 , doc.search("hey") );
        assertEquals ("Testing if everything was deleted correctly 2" , testList2 , doc.search("im") );
        doc.undo(uri1);
        ArrayList <String> testList3 = new ArrayList <String> ();
        testList3.add(inputStreamContent1);
        assertEquals ("Testing if everything was undid correctly" , testList3 , doc.search("hey") );
        doc.undo(uri4);
        testList3.add(inputStreamContent4);
        assertEquals ("Testing if everything was undid correctly 2" , testList3 , doc.search("hey") );
        doc.undo(uri3);
        doc.undo(uri2);
        doc.undo(uri5);
        assertEquals ("Testing if everything was undid correctly 3" , testList.size() , doc.search("hey").size() );
    }

    @Test
    public void testUndoCommandSetPrefix () throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");
        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals ("Testing if everything was added correctly" , testList , doc.search("hey") );
        doc.deleteAllWithPrefix("I");
        ArrayList <String> testList2 = new ArrayList <String> ();
        assertEquals ("Testing if everything was deleted correctly 1" , testList2 , doc.search("hey") );
        assertEquals ("Testing if everything was deleted correctly 2" , testList2 , doc.search("im") );
        doc.undo();
        assertEquals ("Testing if everything was undid correctly" , testList , doc.search("hey") );
        doc.undo();
        testList.remove(inputStreamContent5);
        assertEquals ("Testing if everything was undid correctly 2" , testList , doc.search("hey") );
        doc.undo();
        doc.undo();
        doc.undo();
        doc.undo();
        assertEquals ("Testing if everything was undid correctly 3" , testList2 , doc.search("hey") );
    }

    @Test
    public void DeletePrefixNothing () throws URISyntaxException {
        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
        URI uri2 = new URI("2");
        URI uri3 = new URI("3");
        URI uri4 = new URI("4");
        URI uri5 = new URI("5");
        String inputStreamContent1 = "Hey Im Tom";
        String inputStreamContent2 = "Hey Im Lenny";
        String inputStreamContent3 = "Hey Im Ruben";
        String inputStreamContent4 = "Hey Im Tania";
        String inputStreamContent5 = "Hey Im Maalon";
        InputStream inputStream1 = new ByteArrayInputStream(inputStreamContent1.getBytes() );
        InputStream inputStream2 = new ByteArrayInputStream(inputStreamContent2.getBytes() );
        InputStream inputStream3 = new ByteArrayInputStream(inputStreamContent3.getBytes() );
        InputStream inputStream4 = new ByteArrayInputStream(inputStreamContent4.getBytes() );
        InputStream inputStream5 = new ByteArrayInputStream(inputStreamContent5.getBytes() ); 
        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        ArrayList <String> testList = new ArrayList <String> ();
        testList.add(inputStreamContent1);
        testList.add(inputStreamContent2);
        testList.add(inputStreamContent3);
        testList.add(inputStreamContent4);
        testList.add(inputStreamContent5);
        assertEquals ("Testing if everything was added correctly" , testList , doc.search("hey") );
        HashSet <URI> emptySet = new HashSet <URI> ();
        assertEquals ("Testing if everything was added correctly" , emptySet , doc.deleteAllWithPrefix("fdsahbkgf") );
        assertEquals ("Testing if everything was added correctly" , emptySet , doc.deleteAll("fdsahbkgf") ); 
    }
}