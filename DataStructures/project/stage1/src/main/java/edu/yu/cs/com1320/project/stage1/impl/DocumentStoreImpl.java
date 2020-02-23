package edu.yu.cs.com1320.project.stage1.impl;

/** "Document Store Implementation" Stage 1
 *
 * @author Tom Bohbot
 *
 * Code to compile: javac -cp src/main/java src/main/java/edu/yu/cs/com1320/project/stage1/impl/DocumentStoreImpl.java
 * Code to run:     java -cp target/classes edu.yu.cs.com1320.project.stage1.impl.DocumentImpl
 */

import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage1.DocumentStore;
import edu.yu.cs.com1320.project.stage1.impl.DocumentImpl;

import java.net.URI;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.ByteArrayOutputStream;

public class DocumentStoreImpl implements DocumentStore {

    private HashTableImpl <URI , DocumentImpl > hashTableOfDocs = new HashTableImpl<URI , DocumentImpl>();
    private int hashCodeOfStream;

    public DocumentStoreImpl () {

    }

    private byte []inputStreamToByteArray(InputStream inputStream) throws IOException {
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
        if (hashTableOfDocs.get(uri) != null) {
            if (hashTableOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream ) {
                return hashCodeOfStream;
            }
        }
        hashTableOfDocs.put(uri, new DocumentImpl(uri, txt, hashCodeOfStream));
        return hashCodeOfStream;
    }

    private int putPDF (byte [] streamAsBytes , URI uri) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String strippedByteArray =  pdfStripper.getText(PDDocument.load(streamAsBytes)).trim();
        hashCodeOfStream = Math.abs(strippedByteArray.hashCode());
        if (hashTableOfDocs.get(uri) != (null) ) {
            if ( hashTableOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream ) {
                return hashCodeOfStream;
            }
        }
        hashTableOfDocs.put(uri, new DocumentImpl(uri, strippedByteArray , hashCodeOfStream , streamAsBytes));
        return hashCodeOfStream;
    }

    @Override
    public int putDocument(InputStream input, URI uri, DocumentFormat format) {
      try{
        if (uri == null || format == null) { throw new IllegalArgumentException(); }
        if (input == null) { return ifNull(input , uri); }
        byte [] streamAsBytes = inputStreamToByteArray(input);
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
        if (hashTableOfDocs.get(uri) == null) {
            return null;
        }
        String text =  hashTableOfDocs.get(uri).getDocumentAsTxt();
        if (text == null) {
            return null;
        }
        return text;
    }

    @Override
    public boolean deleteDocument(URI uri) {
        DocumentImpl doc = hashTableOfDocs.get(uri);
        if (doc == null) { return false;}
        hashTableOfDocs.put(uri , null);
        return true;
    }
}
