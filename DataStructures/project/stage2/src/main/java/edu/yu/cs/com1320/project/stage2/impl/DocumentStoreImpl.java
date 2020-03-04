package edu.yu.cs.com1320.project.stage2.impl;

/** "Document Store Implementation" Stage 2
 *
 * @author Tom Bohbot
 *
 */

import edu.yu.cs.com1320.project.stage2.DocumentStore;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage2.impl.DocumentImpl;
import edu.yu.cs.com1320.project.stage2.impl.StackImpl;
import edu.yu.cs.com1320.project.Command;
import java.util.function.Function;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.ByteArrayOutputStream;

import java.io.*;

public class DocumentStoreImpl implements DocumentStore {

    private HashTableImpl<URI, DocumentImpl> hashTableOfDocs = new HashTableImpl<URI, DocumentImpl>();
    private int hashCodeOfStream;
    private StackImpl<DocumentImpl> stack = new StackImpl<DocumentImpl>();
    private StackImpl<Command> commandStack = new StackImpl<Command>();

    public DocumentStoreImpl() {

    }

    private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        byte[] data = new byte[inputStream.available()];
        int atEnd = inputStream.read(data, 0, data.length); // if int is -1, then it is at the end.
        while ((atEnd != -1)) {
            byteArrayStream.write(data, 0, atEnd);
            atEnd = inputStream.read(data, 0, data.length);
        }
        return byteArrayStream.toByteArray();
    }

    private int ifNull(InputStream input, URI uri, DocumentFormat format) throws IOException {
        if (hashTableOfDocs.get(uri) == null) {
            return 0;
        }
        // Add deleted doc to the command stack:
        // Function<URI , Boolean> lambdaFunction = () -> (return )
        // commandStack.push(new Command(uri , Function< URI , Boolean > ))
        // Need to push the deleted docs to the stack.
        if (format == DocumentStore.DocumentFormat.PDF) {
            DocumentImpl oldDoc = hashTableOfDocs.get(uri);
            int oldValue = hashTableOfDocs.get(uri).getDocumentTextHashCode();
            Function lambda = (x) -> {
                hashTableOfDocs.put(uri, oldDoc);
                return true;
            };
            commandStack.push(new Command(uri, lambda));
            hashTableOfDocs.put(uri, null);
            return oldValue;
        }
        if (format == DocumentStore.DocumentFormat.TXT) {
            DocumentImpl oldDoc = hashTableOfDocs.get(uri);
            int oldValue = hashTableOfDocs.get(uri).getDocumentTextHashCode();
            Function lambda = (x) -> {
                hashTableOfDocs.put(uri, oldDoc);
                return true;
            };
            commandStack.push(new Command(uri, lambda));
            hashTableOfDocs.put(uri, null);
            return oldValue;
        }
        return 0;
    }

    private int putText(byte[] streamAsBytes, URI uri) throws IOException {
        String txt = new String(streamAsBytes);
        hashCodeOfStream = txt.hashCode();
        if (hashTableOfDocs.get(uri) != null) {
            if (hashTableOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream) {
                return hashCodeOfStream;
            }
        }
        if (hashTableOfDocs.get(uri) == null) {
            Function lambda = (x) -> {
                hashTableOfDocs.put(uri, null);
                return true;
            };
            commandStack.push(new Command(uri, lambda));
            hashTableOfDocs.put(uri, new DocumentImpl(uri, txt, hashCodeOfStream));
            return 0;
        }
        DocumentImpl oldValue = hashTableOfDocs.get(uri);
        Function lambda = (x) -> {
            hashTableOfDocs.put(uri, oldValue);
            return true;
        };
        commandStack.push(new Command(uri, lambda));
        hashTableOfDocs.put(uri, new DocumentImpl(uri, txt, hashCodeOfStream));
        return oldValue.getDocumentTextHashCode();
    }

    private int putPDF(byte[] streamAsBytes, URI uri) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String strippedByteArray = pdfStripper.getText(PDDocument.load(streamAsBytes)).trim();
        hashCodeOfStream = strippedByteArray.hashCode();
        if (hashTableOfDocs.get(uri) != (null)) {
            if (hashTableOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream) {
                return hashCodeOfStream;
            }
        }
        if (hashTableOfDocs.get(uri) == null) {
            Function lambda = (x) -> {
                hashTableOfDocs.put(uri, null);
                return true;
            };
            commandStack.push(new Command(uri, lambda));
            hashTableOfDocs.put(uri, new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
            return 0;
        }
        DocumentImpl oldValue = hashTableOfDocs.get(uri);
        Function lambda = (x) -> {
            hashTableOfDocs.put(uri, oldValue);
            return true;
        };
        commandStack.push(new Command(uri, lambda));
        hashTableOfDocs.put(uri, new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
        return oldValue.getDocumentTextHashCode();
    }

    @Override
    public int putDocument(InputStream input, URI uri, DocumentFormat format) {
        try {
            if (uri == null || format == null) {
                throw new IllegalArgumentException();
            }
            if (input == null) {
                return ifNull(input, uri, format);
            }
            byte[] streamAsBytes = inputStreamToByteArray(input);
            switch (format) {
                case TXT:
                    return putText(streamAsBytes, uri);
                case PDF:
                    return putPDF(streamAsBytes, uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashCodeOfStream;
    }

    @Override
    public byte[] getDocumentAsPdf(URI uri) {
        if (hashTableOfDocs.get(uri) == null && uri == null) {
            return null;
        }
        DocumentImpl obj = hashTableOfDocs.get(uri);
        return obj.getDocumentAsPdf();
    }

    @Override
    public String getDocumentAsTxt(URI uri) {
        if (hashTableOfDocs.get(uri) == null) {
            return null;
        }
        String text = hashTableOfDocs.get(uri).getDocumentAsTxt();
        if (text == null) {
            return null;
        }
        return text;
    }

    @Override
    public boolean deleteDocument(URI uri) {
        DocumentImpl doc = hashTableOfDocs.get(uri);
        if (doc == null) {
            return false;
        }
        Function lambda = (x) -> {
            hashTableOfDocs.put(uri, doc);
            return true;
        };
        commandStack.push(new Command(uri, lambda));
        hashTableOfDocs.put(uri, null);
        return true;
    }

    @Override
    public void undo() throws IllegalStateException {
        if (commandStack.size() == 0) {
            throw new IllegalStateException();
        }
        Command undoElem = commandStack.pop();
        undoElem.undo();
    }
    
    // Last node is not making the stack size zero, byst staying constant at 1. Check this error.
    @Override
    public void undo(URI uri) throws IllegalStateException {
        System.out.println("THIS IS THE URI: " + uri);
        if (commandStack.size() == 0) {
            throw new IllegalStateException();
        }
        boolean foundUri = false;
        int commandStackSize = commandStack.size();
        StackImpl <Command> tempStack = new StackImpl <Command>();
        // Sort through command stack, until you find the URI:
        System.out.println("CS SIZE: " + commandStackSize);
        for (int i = 0; i < commandStackSize; i ++) {
            Command tempNode = commandStack.pop();
            if (tempNode.getUri().equals(uri) ){
                System.out.println("PRE: " + hashTableOfDocs.get(uri));
                tempNode.undo();
                System.out.println("POST: " + hashTableOfDocs.get(uri));
                foundUri = true;
                break;
            }
            tempStack.push(tempNode);
        }
        if (tempStack.size() == 0) {return;}
        // If the URI was never found:
        if (commandStack.size() == 0 && foundUri == false) {
            for (int i = 0; i < tempStack.size(); i ++) {
                commandStack.push(tempStack.pop() );
            }
            throw new IllegalStateException();
        }
        // If the URI was found:
        for (int i = 0; i < commandStackSize; i ++) {
            foundUri = false;
            commandStack.push(tempStack.pop() );
            if (tempStack.size() == 0) {break;}
        }    
    }

    public static void main(final String[] args) throws URISyntaxException {
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


        int testPutHashCode1 = doc.putDocument(inputStream1 , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2 = doc.putDocument(inputStream2 , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3 = doc.putDocument(inputStream3 , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4 = doc.putDocument(inputStream4 , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5 = doc.putDocument(inputStream5 , uri5 , DocumentStore.DocumentFormat.TXT);
        
        // Testing undoing docs insrted for the first time:
        // doc.undo(uri5);
        // doc.undo(uri4);        
        // doc.undo(uri3);
        // doc.undo(uri2);
        // doc.undo(uri1);

        // doc.undo(uri1);
        // doc.undo(uri2);        
        // doc.undo(uri3);
        // doc.undo(uri4);
        // doc.undo(uri5);


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
        
        int testPutHashCode1Dup = doc.putDocument(inputStream1Dup , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2Dup = doc.putDocument(inputStream2Dup , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3Dup = doc.putDocument(inputStream3Dup , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4Dup = doc.putDocument(inputStream4Dup , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5Dup = doc.putDocument(inputStream5Dup , uri5 , DocumentStore.DocumentFormat.TXT);
        
        // Testing docs that replace the old value:
        // doc.undo(uri5);
        // doc.undo(uri4);        
        // doc.undo(uri3);
        // doc.undo(uri2);
        // doc.undo(uri1);

        // doc.undo(uri1);
        // doc.undo(uri2);        
        // doc.undo(uri3);
        // doc.undo(uri4);
        // doc.undo(uri5);

        int testPutHashCode1null = doc.putDocument(null , uri1 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode2null = doc.putDocument(null , uri2 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode3null = doc.putDocument(null , uri3 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode4null = doc.putDocument(null , uri4 , DocumentStore.DocumentFormat.TXT);
        int testPutHashCode5null = doc.putDocument(null , uri5 , DocumentStore.DocumentFormat.TXT);

        doc.undo(uri5);
        doc.undo(uri4);        
        doc.undo(uri3);
        doc.undo(uri2);
        doc.undo(uri1);

        doc.undo(uri1);
        doc.undo(uri2);        
        doc.undo(uri3);
        doc.undo(uri4);
        doc.undo(uri5);

        doc.undo(uri1);
        doc.undo(uri2);        
        doc.undo(uri3);
        doc.undo(uri4);
        doc.undo(uri5);

        doc.undo(uri1);
        doc.undo(uri2);        
        doc.undo(uri3);
        doc.undo(uri4);
        doc.undo(uri5);
    }
    
}
