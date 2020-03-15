package edu.yu.cs.com1320.project.stage2.impl;

import edu.yu.cs.com1320.project.stage2.DocumentStore;
import edu.yu.cs.com1320.project.stage2.impl.DocumentStoreImpl;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage2.impl.DocumentImpl;

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

public class DocumentStoreImplTest  {

    private HashTableImpl <URI , DocumentImpl > hashTable = new HashTableImpl<URI , DocumentImpl>();
    private DocumentStoreImpl docStore = new DocumentStoreImpl();

    //PDF Test:
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
    public void undoNothingTwo () throws URISyntaxException {

        DocumentStoreImpl doc = new DocumentStoreImpl(); 

        URI uri1 = new URI("1");
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


}