package edu.yu.cs.com1320.project.stage2.impl;

/** "Document Store Implementation" Stage 2
 *
 * @author Tom Bohbot
 *
 */

import edu.yu.cs.com1320.project.stage2.DocumentStore;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage2.impl.DocumentImpl;
import edu.yu.cs.com1320.project.stage2.Document;
import edu.yu.cs.com1320.project.impl.StackImpl;
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
            Function lambda = (x) -> { return true; };
            commandStack.push(new Command(uri, lambda));
            return 0;
        }
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
                Function lambda = (x) -> { return true; };
                commandStack.push(new Command(uri, lambda));
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
                Function lambda = (x) -> { return true; };
                commandStack.push(new Command(uri, lambda));
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

    protected Document getDocument(URI uri) {
        if (hashTableOfDocs.get(uri) == null) {
            return null;
        }
        return hashTableOfDocs.get(uri);
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
        if (hashTableOfDocs.get(uri) == null || uri == null) {
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
            Function lambda = (x) -> { return true; };
            commandStack.push(new Command(uri, lambda));
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
        if (commandStack.size() == 0) { throw new IllegalStateException(); }
        boolean foundUri = false;
        int commandStackSize = commandStack.size();
        StackImpl <Command> tempStack = new StackImpl <Command>();
        // Sort through command stack, until you find the URI:
        for (int i = 0; i < commandStackSize; i ++) {
            Command tempNode = commandStack.pop();
            if (tempNode.getUri().equals(uri) ){
                tempNode.undo();
                foundUri = true;
                break;
            }
            tempStack.push(tempNode);
        }
        if (tempStack.size() == 0) {return;}
        // If the URI was never found:
        if (commandStack.size() == 0 && foundUri == false) {
            for (int i = 0; i < tempStack.size(); i ++) { commandStack.push(tempStack.pop() ); }
            throw new IllegalStateException();
        }
        // If the URI was found:
        for (int i = 0; i < commandStackSize; i ++) {
            foundUri = false;
            commandStack.push(tempStack.pop() );
            if (tempStack.size() == 0) { break; }
        }    
    }    
}
