package edu.yu.cs.com1320.project.stage1.impl;

import edu.yu.cs.com1320.project.stage1.DocumentStore;
import edu.yu.cs.com1320.project.stage1.impl.DocumentStoreImpl;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage1.impl.DocumentImpl;

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

    private HashTableImpl <URI , DocumentImpl > hashTable;
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
        int expected = Math.abs(dataOfFile.hashCode());
        assertEquals("Testing if hashcode with put PDF works" , expected , testPut);
    }

    @Test
    public void testIfInputStreamIsNull() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if I get old value when I return null" , hashCodeOfStream, doc.putDocument(null , uri , DocumentStore.DocumentFormat.TXT));
    }

    @Test
    public void testIfInputStreamIsNullV2() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        assertEquals("Testing if I get old value when I return null and no prev. value exists" , 0, doc.putDocument(null , uri , DocumentStore.DocumentFormat.TXT));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfUriIsNull() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        int testPutHashCode = doc.putDocument(inputStream , null , DocumentStore.DocumentFormat.TXT);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfDocTypeIsNull() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
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
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if hash codes are correct" , hashCodeOfStream, testPutHashCode);
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
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
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
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        String txt = doc.getDocumentAsTxt(uri);
        assertEquals("Testing if GetDocAsTxt returns null when no txt exists" , null , txt);
    }

    @Test
    public void testDeleteDocument() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        int testPutHashCode = doc.putDocument(inputStream , uri , DocumentStore.DocumentFormat.TXT);
        assertEquals("Testing if deleting a value is true" , true , doc.deleteDocument(uri));
    }

    @Test
    public void testDeleteDocumentThatDoesntExist() throws URISyntaxException{
        DocumentStoreImpl doc = new DocumentStoreImpl();
        String inputStreamContent = "Hey, I'm Tom";
        int hashCodeOfStream = Math.abs(inputStreamContent.hashCode() );
        InputStream inputStream = new ByteArrayInputStream(inputStreamContent.getBytes() );
        URI uri = new URI("Tom'sURI");
        assertEquals("Testing if deleting a value is true" , false , doc.deleteDocument(uri));
    }
}
