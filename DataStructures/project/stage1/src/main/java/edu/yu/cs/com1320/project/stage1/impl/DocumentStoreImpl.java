/** "Document Store Implementation" Stage 1
 *
 * @author Tom Bohbot
 *
 * Code to compile: javac -cp src/main/java src/main/java/edu/yu/cs/com1320/project/stage1/impl/DocumentStoreImpl.java
 * Code to run:     java -cp target/classes edu.yu.cs.com1320.project.stage1.impl.DocumentImpl
 */

package edu.yu.cs.com1320.project.stage1.impl;

import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage1.DocumentStore;
import edu.yu.cs.com1320.project.stage1.impl.DocumentImpl;

import java.net.URI;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.io.IOUtils;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPage;

public class DocumentStoreImpl implements DocumentStore {

    private HashTableImpl <URI , DocumentImpl > hashTableOfDocs = new HashTableImpl<URI , DocumentImpl>();
    private int hashCodeOfStream;

    public DocumentStoreImpl () { // no arg constructor

    }

    private static byte []inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        byte[] data = new byte [inputStream.available()];
        int atEnd = inputStream.read(data, 0, data.length); // if int is -1, then it is at the end.
        while ((atEnd != -1)) {
            byteArrayStream.write(data, 0, atEnd);
            atEnd = inputStream.read(data, 0, data.length);
        }
        return byteArrayStream.toByteArray();
    }

    private int ifNull (InputStream input , URI uri) throws IOException {
        if (hashTableOfDocs.get(uri) == null) {
            return 0;
        }
        int oldValue = hashTableOfDocs.get(uri).getDocumentTextHashCode();
        hashTableOfDocs.put(uri, null);
        return oldValue;
    }


    private int putText (byte [] streamAsBytes , URI uri) throws IOException {
        String txt = new String (streamAsBytes);
        hashCodeOfStream = Math.abs(txt.hashCode() );
        if (hashTableOfDocs.get(uri) != null) {                                                        // Check if key already exists in hashtable
            if (hashTableOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream ) {             // Check for duplicate uri
                return hashCodeOfStream;
            }
        }
        hashTableOfDocs.put(uri, new DocumentImpl(uri, txt, hashCodeOfStream));
        return hashCodeOfStream;
    }

    private int putPDF (byte [] streamAsBytes , URI uri) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String strippedByteArray =  pdfStripper.getText(PDDocument.load(streamAsBytes)).trim(); // End of file error at the .load. Need to fix. Fixed it, just wasnt putting pdf files in..
        hashCodeOfStream = Math.abs(strippedByteArray.hashCode());
        if (hashTableOfDocs.get(uri) != (null) ) {       // Check for duplicate hashcode value of txt.
            if ( hashTableOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream ) {        // Check for duplicate uri
                return hashCodeOfStream;
            }
        }
        hashTableOfDocs.put(uri, new DocumentImpl(uri, strippedByteArray , hashCodeOfStream , streamAsBytes));
        return hashCodeOfStream;
    }

    @Override
    public int putDocument(InputStream input, URI uri, DocumentFormat format) {
      try{
        if (uri == null) { throw new IllegalArgumentException(); }
        if (input == null) { return ifNull(input , uri); }
        byte [] streamAsBytes = inputStreamToByteArray(input); // converts input stream to byte array.
        switch (format) {
            case TXT:
                return putText(streamAsBytes , uri);
            case PDF:
                return putPDF(streamAsBytes , uri);
        }
      } catch (IOException e) { e.printStackTrace(); }
      return hashCodeOfStream;
    }

    @Override
    public byte[] getDocumentAsPdf(URI uri) {
        if (hashTableOfDocs.get(uri) == null && uri == null) {
            return null;
        }
        DocumentImpl obj =  hashTableOfDocs.get(uri);
        return obj.getDocumentAsPdf();
    }

    @Override
    public String getDocumentAsTxt(URI uri){
        String text =  hashTableOfDocs.get(uri).getDocumentAsTxt();
        if (text == null) {
            return null;
        }
        return text;
    }


    //Restart the delete doc method. and fix it in the hashtable impl method.
    @Override
    public boolean deleteDocument(URI uri) {
        DocumentImpl doc = hashTableOfDocs.get(uri);
        if (doc == null) { return false;}           // in the case that there is nothing to delete.
        hashTableOfDocs.put(uri , null);
        return true;
    }

//    public static void main (final String [] args) throws IOException , URISyntaxException {
//        DocumentStoreImpl docStore = new DocumentStoreImpl();
//        URI uri = new URI ("uri1"); // create a uri to add in constructor.
//        String txt = "Hey, I'm Tom"; // The string I will pass in as the Input Stream.
//
//        byte [] txtAsBytes = txt.getBytes();
//        InputStream txtAsIS = new ByteArrayInputStream(txtAsBytes); // this is the input stream.
////
////        byte [] is = docStore.inputStreamToByteArray(txtAsIS);
////        String str = new String (is);
////        System.out.println(str);
//
//        int testPut = docStore.putDocument(txtAsIS ,  uri , DocumentStore.DocumentFormat.TXT);
//        String str = new String(docStore.getDocumentAsPdf(uri));
//        System.out.println(str);





//        File file = new File("//Users/tombohbot/Desktop/testDoc.pdf");
//        URI uri = file.toURI();
//        InputStream inputStreamOg = new FileInputStream(file);          // this is ths input stream. Untouched and will go in method signature.
//        InputStream inputStreamCopy = new FileInputStream(file);        // this is ths input stream #2.
//        byte [] txtAsBytes = IOUtils.toByteArray(inputStreamCopy);
//        PDFTextStripper pdfStripper = new PDFTextStripper();
//        String is2AsString = pdfStripper.getText(PDDocument.load(txtAsBytes) );
//        int hashCode = is2AsString.hashCode();
//        int testPut = docStore.putDocument(inputStreamOg , uri , DocumentStore.DocumentFormat.PDF);
//        String str = new String(docStore.getDocumentAsPdf(uri));
//        System.out.println(str);



//        byte [] bytes = docStore.inputStreamToByteArray(txtAsIS);
//        String str = new String (bytes);
//        System.out.println("String is: " + str);

//    }


}
