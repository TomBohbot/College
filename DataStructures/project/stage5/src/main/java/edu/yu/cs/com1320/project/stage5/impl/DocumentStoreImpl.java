package edu.yu.cs.com1320.project.stage5.impl;

/** 
 * "Document Store Implementation" Stage 5
 * @author Tom Bohbot
 *
 */

import edu.yu.cs.com1320.project.stage5.DocumentStore;
import edu.yu.cs.com1320.project.impl.BTreeImpl;
import edu.yu.cs.com1320.project.stage5.impl.DocumentImpl;
import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.impl.StackImpl;
import edu.yu.cs.com1320.project.GenericCommand;
import edu.yu.cs.com1320.project.Undoable;
import edu.yu.cs.com1320.project.CommandSet;
import java.util.function.Function;

import edu.yu.cs.com1320.project.impl.TrieImpl;
import edu.yu.cs.com1320.project.impl.MinHeapImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.ByteArrayOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DocumentStoreImpl implements DocumentStore {

    private File filePath;
    private BTreeImpl<URI, DocumentImpl> bTreeOfDocs = new BTreeImpl<URI, DocumentImpl>(filePath);
    private int hashCodeOfStream;
    private StackImpl<Undoable> commandStack = new StackImpl<Undoable>();
    private TrieImpl<URI> trie = new TrieImpl<URI>();
    private Comparator<URI> compareDocs = new ComparatorImpl();
    private Comparator<URI> compareDocsPrefixes = new ComparatorImplPrefixes();
    private MinHeapImpl<UriAndLastUseTime> heap = new MinHeapImpl<UriAndLastUseTime>();
    private int maxDocCount = Integer.MAX_VALUE;
    private int maxDocBytes = Integer.MAX_VALUE;
    private long timeInMillisecs;
    private long min;
    private int documentCount;
    private int bytesCount;
    private HashMap<URI, UriAndLastUseTime> bTreeOfObj = new HashMap<URI, UriAndLastUseTime>();
    private HashSet <URI> setOfDeletedDocs = new HashSet <URI> ();

    private class UriAndLastUseTime implements Comparable<UriAndLastUseTime> {
        URI uri;
        long lastUseTime;

        UriAndLastUseTime(URI uri, long lastUseTime) {
            this.uri = uri;
            this.lastUseTime = lastUseTime;
        }

        private URI getUri() {
            return uri;
        }

        private long getLastUseTime() {
            return lastUseTime;
        }

        private void setLastUseTime(long newUseTime) {
            this.lastUseTime = newUseTime;
        }

        @Override
        public int compareTo(UriAndLastUseTime o) {
            return (int) (this.getLastUseTime() - o.getLastUseTime());
        }
    }

    
    private class ComparatorImpl implements Comparator<URI> {
        @Override
        public int compare(URI o1, URI o2) {
            DocumentImpl docOne = (DocumentImpl) bTreeOfDocs.get(o1);
            DocumentImpl docTwo = (DocumentImpl) bTreeOfDocs.get(o2);
                // if (docOne == null || docTwo == null) {
                //     return 0;
                // }
            return docTwo.wordCount(keyWordForKey) - docOne.wordCount(keyWordForKey);
        }
    }

    private class ComparatorImplPrefixes implements Comparator<URI> {
        // private DocumentStoreImpl docStore = new DocumentStoreImpl();
        @Override
        public int compare(URI o1, URI o2) {
            DocumentImpl docOne = (DocumentImpl) bTreeOfDocs.get(o1);
            DocumentImpl docTwo = (DocumentImpl) bTreeOfDocs.get(o2);
            return countPrefixes(docTwo.getDocumentAsTxt(), keyWordForKey) - countPrefixes(docOne.getDocumentAsTxt(), keyWordForKey);
        }
    }

    private void setBTree () {
        try {
            URI nullURi = new URI ("");
            bTreeOfDocs.put(nullURi , null);
        } catch (URISyntaxException e) {}
    }

    public DocumentStoreImpl() {
        // no arg constructor
        this.min = System.nanoTime();
        // DocumentPersistenceManager <URI , DocumentImpl> pm = new
        // DocumentPersistenceManager <URI , DocumentImpl> (baseDir);
        this.filePath = new File(System.getProperty("user.dir") );
        setBTree();
        bTreeOfDocs.setPersistenceManager(new DocumentPersistenceManager(filePath));
    }

    public DocumentStoreImpl(File baseDir) {
        this.min = System.nanoTime();
        this.filePath = baseDir;
        // DocumentPersistenceManager <URI , DocumentImpl> pm = new
        // DocumentPersistenceManager <URI , DocumentImpl> (baseDir);
        setBTree();
        bTreeOfDocs.setPersistenceManager(new DocumentPersistenceManager(baseDir));
    }

    private void setCurrentTimeInMillis() {
        timeInMillisecs = System.nanoTime();
    }

    private String parseSpecialCharacters(String str) {
        str = str.toLowerCase();
        String parsedString = new String();
        for (char seekRegChar : str.toCharArray()) {
            int AsciiVal = (int) seekRegChar;
            if (AsciiVal >= 48 && AsciiVal <= 57 || AsciiVal >= 97 && AsciiVal <= 122 || AsciiVal == ' ') {
                parsedString = parsedString + seekRegChar;
            }
        }
        return parsedString;
    }

    private int countPrefixes(String docText, String prefix) { // line count is good
        if (docText == null) {
            return 0;
        }
        if (prefix == null) {
            return 0;
        }
        docText = docText.toLowerCase();
        prefix = prefix.toLowerCase();
        docText = parseSpecialCharacters(docText);
        prefix = parseSpecialCharacters(prefix);
        if (prefix == null) {
            return 0;
        }
        char[] prefixAsChars = prefix.toCharArray();
        int prefixLength = prefixAsChars.length;
        int counter = 0;
        String[] allWordsInKey = docText.split(" ");
        for (int i = 0; i < allWordsInKey.length; i++) {
            if (allWordsInKey[i].length() < prefixLength) {
                continue;
            }
            if (allWordsInKey[i].substring(0, prefixLength).equals(prefix)) {
                counter++;
            }
        }
        return counter;
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

    private int ifNull(InputStream input, URI uri, DocumentFormat format) throws IOException { // line count is good
        if (bTreeOfDocs.get(uri) == null) {
            Function lambda = (x) -> {
                return true;
            };
            commandStack.push(new GenericCommand(uri, lambda));
            return 0;
        }
        if (format == DocumentStore.DocumentFormat.PDF || format == DocumentStore.DocumentFormat.TXT) {
            DocumentImpl oldDoc = bTreeOfDocs.get(uri);
            // addBackToBtree(uri , oldDoc);
            String oldDocText = oldDoc.getDocumentAsTxt();
            int oldValue = bTreeOfDocs.get(uri).getDocumentTextHashCode();
            Function lambda = (x) -> {
                bytesCount = bytesCount + getBytesPerDocument(oldDoc);
                documentCount = documentCount + 1;
                deleteDueToMemoryLimit();
                oldDoc.setLastUseTime(System.nanoTime());
                UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, oldDoc.getLastUseTime());
                bTreeOfObj.put(uri, objForHeap);
                heap.insert(objForHeap);
                trie.put(oldDocText, uri);
                bTreeOfDocs.put(uri, oldDoc);
                return true;
            };
            commandStack.push(new GenericCommand(uri, lambda));
            oldDoc.setLastUseTime(min);
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(min);
            heap.reHeapify(objForHeap);
            heap.removeMin();
            trie.delete(oldDocText, uri);
            bTreeOfDocs.put(uri, null);
            documentCount = documentCount - 1;
            bytesCount = bytesCount - getBytesPerDocument(oldDoc);
            return oldValue;
        }
        return 0;
    }

    private int putText(byte[] streamAsBytes, URI uri) throws IOException { // line count is good
        String txt = new String(streamAsBytes);
        hashCodeOfStream = txt.hashCode();
        if (bTreeOfDocs.get(uri) != null) {
            if (bTreeOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream) {
                addBackToBtree(uri , bTreeOfDocs.get(uri) );
                bTreeOfDocs.get(uri).setLastUseTime(System.nanoTime());
                UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
                objForHeap.setLastUseTime(System.nanoTime());
                // objForHeap.setLastUseTime(bTreeOfDocs.get(uri).getLastUseTime()); // this wasbhow it was may 19, 2020.
                heap.reHeapify(objForHeap); 
                Function lambda = (x) -> {
                    return true;
                };
                commandStack.push(new GenericCommand(uri, lambda));
                return hashCodeOfStream;
            }
        }
        deleteDueToMemoryLimit();
        if (bTreeOfDocs.get(uri) == null) {
            return putNewTextDocument(uri, txt);
        }
        return putDuplicateTextDocument(uri, txt);
    }

    private int putNewTextDocument(URI uri, String txt) {
        DocumentImpl doc = new DocumentImpl(uri, txt, hashCodeOfStream);
        documentCount++;
        bytesCount = bytesCount + getBytesPerDocument(doc);
        deleteDueToMemoryLimit();
        Function lambda = (x) -> {
            bTreeOfDocs.get(uri).setLastUseTime(min);
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(bTreeOfDocs.get(uri).getLastUseTime());
            heap.reHeapify(objForHeap);
            heap.removeMin();
            bTreeOfObj.put(uri, null);
            String[] allWordsInDoc = txt.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            documentCount = documentCount - 1;
            bytesCount = bytesCount - getBytesPerDocument(bTreeOfDocs.get(uri));
            bTreeOfDocs.put(uri, null);
            return true;
        };
        doc.setLastUseTime(System.nanoTime());
        UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, doc.getLastUseTime());
        bTreeOfObj.put(uri, objForHeap);
        heap.insert(objForHeap);
        trie.put(txt, uri);
        commandStack.push(new GenericCommand(uri, lambda));
        bTreeOfDocs.put(uri, doc);
        return 0;
    }

    private int putDuplicateTextDocument(URI uri, String txt) {
        DocumentImpl oldValue = bTreeOfDocs.get(uri);
        oldValue.setLastUseTime(min);
        UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
        objForHeap.setLastUseTime(bTreeOfDocs.get(uri).getLastUseTime());
        heap.reHeapify(objForHeap);
        heap.removeMin();
        bytesCount = bytesCount - getBytesPerDocument(oldValue);
        DocumentImpl newDoc = new DocumentImpl(uri, txt, hashCodeOfStream);
        newDoc.setLastUseTime(System.nanoTime());
        UriAndLastUseTime objForHeapNew = bTreeOfObj.get(uri);
        objForHeap.setLastUseTime(newDoc.getLastUseTime());
        heap.insert(objForHeapNew);
        bTreeOfObj.put(uri, objForHeapNew);
        bytesCount = bytesCount + getBytesPerDocument(newDoc);
        deleteDueToMemoryLimit();
        Function lambda = (x) -> {
            bTreeOfDocs.get(uri).setLastUseTime(min);
            UriAndLastUseTime objForHeapLambda = bTreeOfObj.get(uri);
            objForHeapLambda.setLastUseTime(min);
            heap.reHeapify(objForHeapLambda);
            heap.removeMin();
            bytesCount = bytesCount - getBytesPerDocument(bTreeOfDocs.get(uri));
            oldValue.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeapLambdaNew = new UriAndLastUseTime(uri, oldValue.getLastUseTime());
            heap.insert(objForHeapLambdaNew);
            bTreeOfObj.put(uri, objForHeapLambdaNew);
            String[] allWordsInDoc = txt.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            bytesCount = bytesCount + getBytesPerDocument(bTreeOfDocs.get(uri));
            deleteDueToMemoryLimit();
            bTreeOfDocs.put(uri, oldValue);
            trie.put(bTreeOfDocs.get(uri).getDocumentAsTxt(), uri);
            return true;
        };
        trie.delete(bTreeOfDocs.get(uri).getDocumentAsTxt(), uri);
        trie.put(txt, uri);
        commandStack.push(new GenericCommand(uri, lambda));
        bTreeOfDocs.put(uri, newDoc);
        return oldValue.getDocumentTextHashCode();
    }

    private int putPDF(byte[] streamAsBytes, URI uri) throws IOException { // line count is good
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String strippedByteArray = pdfStripper.getText(PDDocument.load(streamAsBytes)).trim();
        hashCodeOfStream = strippedByteArray.hashCode();
        if (bTreeOfDocs.get(uri) != (null)) {
            if (bTreeOfDocs.get(uri).getDocumentTextHashCode() == hashCodeOfStream) {
                addBackToBtree(uri , bTreeOfDocs.get(uri) );
                bTreeOfDocs.get(uri).setLastUseTime(System.nanoTime());
                UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
                objForHeap.setLastUseTime(bTreeOfDocs.get(uri).getLastUseTime());
                heap.reHeapify(objForHeap); // confirm that it should be updated for duplicate docs.. I posted this
                Function lambda = (x) -> {
                    return true;
                };
                commandStack.push(new GenericCommand(uri, lambda));
                return hashCodeOfStream;
            }
        }
        deleteDueToMemoryLimit();
        if (bTreeOfDocs.get(uri) == null) {
            return putNewUriPdfDocument(uri, strippedByteArray, hashCodeOfStream, streamAsBytes);
        }
        return putDuplicateUriPdfDocument(uri, strippedByteArray, hashCodeOfStream, streamAsBytes);
    }

    private int putNewUriPdfDocument(URI uri, String strippedByteArray, int hashCodeOfStream, byte[] streamAsBytes) {
        DocumentImpl doc = new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes);
        documentCount++;
        bytesCount = bytesCount + getBytesPerDocument(doc);
        deleteDueToMemoryLimit();
        Function lambda = (x) -> {
            bTreeOfDocs.get(uri).setLastUseTime(min);
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(bTreeOfDocs.get(uri).getLastUseTime());
            heap.reHeapify(objForHeap);
            heap.removeMin();
            bTreeOfObj.put(uri, null);
            String[] allWordsInDoc = strippedByteArray.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            documentCount = documentCount - 1;
            bytesCount = bytesCount - getBytesPerDocument(bTreeOfDocs.get(uri));
            bTreeOfDocs.put(uri, null);
            return true;
        };
        doc.setLastUseTime(System.nanoTime());
        UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, doc.getLastUseTime());
        bTreeOfObj.put(uri, objForHeap);
        heap.insert(objForHeap);
        trie.put(strippedByteArray, uri);
        commandStack.push(new GenericCommand(uri, lambda));
        bTreeOfDocs.put(uri, doc);
        return 0;
    }

    private int putDuplicateUriPdfDocument(URI uri, String strippedByteArray, int hashCodeOfStream,
        byte[] streamAsBytes) {
        DocumentImpl oldValue = bTreeOfDocs.get(uri);
        oldValue.setLastUseTime(min);
        UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
        objForHeap.setLastUseTime(bTreeOfDocs.get(uri).getLastUseTime());
        heap.reHeapify(objForHeap);
        heap.removeMin();
        bytesCount = bytesCount - getBytesPerDocument(oldValue);
        DocumentImpl newDoc = new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes);
        newDoc.setLastUseTime(System.nanoTime());
        UriAndLastUseTime objForHeapNew = bTreeOfObj.get(uri);
        objForHeap.setLastUseTime(newDoc.getLastUseTime());
        heap.insert(objForHeapNew);
        bTreeOfObj.put(uri, objForHeapNew);
        bytesCount = bytesCount + getBytesPerDocument(newDoc);
        deleteDueToMemoryLimit();
        Function lambda = (x) -> {
            bTreeOfDocs.get(uri).setLastUseTime(min);
            UriAndLastUseTime objForHeapLambda = bTreeOfObj.get(uri);
            objForHeapLambda.setLastUseTime(min);
            heap.reHeapify(objForHeapLambda);
            heap.removeMin();
            bytesCount = bytesCount - getBytesPerDocument(bTreeOfDocs.get(uri));
            oldValue.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeapLambdaNew = new UriAndLastUseTime(uri, oldValue.getLastUseTime());
            heap.insert(objForHeapLambdaNew);
            bTreeOfObj.put(uri, objForHeapLambdaNew);
            String[] allWordsInDoc = strippedByteArray.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            bTreeOfDocs.put(uri, oldValue);
            bytesCount = bytesCount + getBytesPerDocument(bTreeOfDocs.get(uri));
            deleteDueToMemoryLimit();
            trie.put(bTreeOfDocs.get(uri).getDocumentAsTxt(), uri);
            return true;
        };
        trie.delete(oldValue.getDocumentAsTxt(), uri);
        trie.put(strippedByteArray, uri);
        commandStack.push(new GenericCommand(uri, lambda));
        bTreeOfDocs.put(uri, newDoc);
        return oldValue.getDocumentTextHashCode();
    }

    public Document getDocument(URI uri) { // line count is good
        if (setOfDeletedDocs.contains(uri) ){
            return null;
        }
        if (bTreeOfDocs.get(uri) == null) {
            return null;
        }
        DocumentImpl doc = bTreeOfDocs.get(uri);
        return bTreeOfDocs.get(uri);
    }

    @Override
    public int putDocument(InputStream input, URI uri, DocumentFormat format) { // line count is good
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
    public byte[] getDocumentAsPdf(URI uri) { // line count is good
        if (bTreeOfDocs.get(uri) == null || uri == null) {
            return null;
        }
        DocumentImpl obj = bTreeOfDocs.get(uri);
        addBackToBtree(uri , obj);
        obj.setLastUseTime(System.nanoTime());
        UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
        objForHeap.setLastUseTime(obj.getLastUseTime());
        heap.reHeapify(objForHeap);
        return obj.getDocumentAsPdf();
    }

    @Override
    public String getDocumentAsTxt(URI uri) { // line count is good
        if (bTreeOfDocs.get(uri) == null) {
            return null;
        }
        DocumentImpl doc = bTreeOfDocs.get(uri);
        String text = doc.getDocumentAsTxt();
        addBackToBtree(uri , doc);
        doc.setLastUseTime(System.nanoTime());
        UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
        objForHeap.setLastUseTime(doc.getLastUseTime());
        heap.reHeapify(objForHeap);
        return text;
    }

    @Override
    public boolean deleteDocument(URI uri) { // line count is good
        DocumentImpl doc = bTreeOfDocs.get(uri);
        if (doc == null) {
            Function lambda = (x) -> {
                return true;
            };
            commandStack.push(new GenericCommand(uri, lambda));
            return false;
        }
        Function lambda = (x) -> {
            documentCount = documentCount + 1;
            bytesCount = bytesCount + getBytesPerDocument(doc);
            deleteDueToMemoryLimit();
            doc.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, doc.getLastUseTime());
            heap.insert(objForHeap);
            bTreeOfObj.put(uri, objForHeap);
            trie.put(doc.getDocumentAsTxt(), uri);
            bTreeOfDocs.put(uri, doc);
            return true;
        };
        String[] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
        for (int i = 0; i < allWordsInDoc.length; i++) {
            trie.delete(allWordsInDoc[i], uri);
        }
        commandStack.push(new GenericCommand(uri, lambda));
        if (!setOfDeletedDocs.contains(uri) ) {
            doc.setLastUseTime(min);
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(min);
            heap.reHeapify(objForHeap);
            heap.removeMin();
            bTreeOfObj.put(uri, null);
            documentCount = documentCount - 1;
            bytesCount = bytesCount - getBytesPerDocument(doc);
        }
        bTreeOfDocs.put(uri, null);
        return true;
    }

    private boolean deleteDocumentNoUndo(URI uri) {
        DocumentImpl doc = bTreeOfDocs.get(uri);
        if (doc == null) {
            return false;
        }
        String[] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
        for (int i = 0; i < allWordsInDoc.length; i++) {
            trie.delete(allWordsInDoc[i], uri);
        }
        if (!setOfDeletedDocs.contains(uri) ) {
            doc.setLastUseTime(min);
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(min);
            heap.reHeapify(objForHeap);
            heap.removeMin();
            bTreeOfObj.put(uri, null);
            documentCount = documentCount - 1;
            bytesCount = bytesCount - getBytesPerDocument(doc);
        }
        bTreeOfDocs.put(uri, null);
        return true;
    }

    @Override
    public void undo() throws IllegalStateException {
        if (commandStack.size() == 0) {
            throw new IllegalStateException();
        }
        if (commandStack.peek().getClass().getName().equals("edu.yu.cs.com1320.project.CommandSet")) {
            CommandSet undoSet = (CommandSet) commandStack.pop();
            undoSet.undoAll();
        } else {
            GenericCommand undoElem = (GenericCommand) commandStack.pop();
            undoElem.undo();
        }
    }

    private boolean removeElementFromSet(URI uri, StackImpl tempStack) {
        boolean foundUri = false;
        CommandSet undoSet = (CommandSet) commandStack.pop();
        if (undoSet.containsTarget(uri)) {
            Iterator<GenericCommand> iterator = undoSet.iterator();
            while (iterator.hasNext()) {
                GenericCommand genericCommand = iterator.next();
                if (genericCommand.getTarget().equals(uri)) {
                    iterator.remove();
                }
            }
            // undoSet.remove(uri);
            foundUri = true;
            tempStack.push(undoSet);
        }
        tempStack.push(undoSet);
        return foundUri;
    }

    private void deleteUndoDueToMemory(URI uri) throws IllegalStateException { // word count is good :)
        if (commandStack.size() == 0) {
            return;
        }
        boolean foundUri = false;
        int commandStackSize = commandStack.size();
        StackImpl<Undoable> tempStack = new StackImpl<Undoable>();
        // Sort through command stack, until you find the URI:
        for (int i = 0; i < commandStackSize; i++) {
            if (commandStack.peek().getClass().getName().equals("edu.yu.cs.com1320.project.CommandSet")) {
                foundUri = removeElementFromSet(uri, tempStack);
            } else {
                GenericCommand<URI> tempNode = (GenericCommand) commandStack.pop();
                if (tempNode.getTarget().equals(uri)) {
                    foundUri = true;
                    continue;
                }
                tempStack.push(tempNode);
            }
        }
        if (tempStack.size() == 0) {
            return;
        }
        // If the URI was never found:
        if (commandStack.size() == 0 && foundUri == false) {
            for (int i = 0; i < tempStack.size(); i++) {
                commandStack.push(tempStack.pop());
            }
            return;
        }
        // If the URI was found:
        for (int i = 0; i < commandStackSize; i++) {
            foundUri = false;
            commandStack.push(tempStack.pop());
            if (tempStack.size() == 0) {
                break;
            }
        }
    }

    @Override
    public void undo(URI uri) throws IllegalStateException {
        if (commandStack.size() == 0) {
            throw new IllegalStateException();
        }
        boolean foundUri = false;
        int commandStackSize = commandStack.size();
        StackImpl<Undoable> tempStack = new StackImpl<Undoable>();
        // Sort through command stack, until you find the URI:
        for (int i = 0; i < commandStackSize; i++) {
            if (commandStack.peek().getClass().getName().equals("edu.yu.cs.com1320.project.CommandSet")) {
                CommandSet undoSet = (CommandSet) commandStack.pop();
                if (undoSet.containsTarget(uri)) {
                    undoSet.undo(uri);
                    foundUri = true;
                    tempStack.push(undoSet);
                    break;
                }
                tempStack.push(undoSet);
            } else {
                GenericCommand<URI> tempNode = (GenericCommand) commandStack.pop();
                if (tempNode.getTarget().equals(uri)) {
                    tempNode.undo();
                    foundUri = true;
                    break;
                }
                tempStack.push(tempNode);
            }
        }
        if (tempStack.size() == 0) {
            return;
        }
        // If the URI was never found:
        if (commandStack.size() == 0 && foundUri == false) {
            for (int i = 0; i < tempStack.size(); i++) {
                commandStack.push(tempStack.pop());
            }
            throw new IllegalStateException();
        }
        // If the URI was found:
        for (int i = 0; i < commandStackSize; i++) {
            foundUri = false;
            commandStack.push(tempStack.pop());
            if (tempStack.size() == 0) {
                break;
            }
        }
    }

    private String keyWordForKey;

    @Override
    public List<String> search(String keyword) {
        keyword = keyword.toLowerCase();
        keyWordForKey = keyword;
        // list of all URIs that correspond to keyword:
        ArrayList<URI> listOfURIsSorted = (ArrayList) trie.getAllSorted(keyword, compareDocs);
        // make list of URIs into list of actual docs:
        ArrayList<String> returnValue = new ArrayList<String>();
        for (URI uri : listOfURIsSorted) {
            if (uri == null) {
                continue;
            }
            DocumentImpl doc = bTreeOfDocs.get(uri);
            addBackToBtree(uri , doc);
            String docContent = doc.getDocumentAsTxt();
            returnValue.add(docContent);
            doc.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(doc.getLastUseTime());
            heap.reHeapify(objForHeap);
        }
        return returnValue;
    }

    @Override
    public List<byte[]> searchPDFs(String keyword) {
        keyword = keyword.toLowerCase();
        keyWordForKey = keyword;
        // list of all URIs that correspond to keyword:
        ArrayList<URI> listOfURIsSorted = (ArrayList) trie.getAllSorted(keyword, compareDocs);
        // make list of URIs into list of actual docs:
        ArrayList<byte[]> returnValue = new ArrayList<byte[]>();
        for (URI uri : listOfURIsSorted) {
            DocumentImpl doc = bTreeOfDocs.get(uri);
            addBackToBtree(uri , doc);
            byte[] docContent = doc.getDocumentAsPdf();
            returnValue.add(docContent);
            doc.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(doc.getLastUseTime());
            heap.reHeapify(objForHeap);
        }
        return returnValue;
    }

    @Override
    public List<String> searchByPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        keyWordForKey = prefix;
        // Get a list of all URIs that have that prefix, sorted in the correct order:
        List<URI> sortedURIs = trie.getAllWithPrefixSorted(prefix, compareDocsPrefixes);
        // Transform URI list into a list of text documents:
        ArrayList<String> sortedTextDocs = new ArrayList<String>();
        for (URI uri : sortedURIs) {
            DocumentImpl doc = bTreeOfDocs.get(uri);
            addBackToBtree(uri , doc);
            String textOfDoc = doc.getDocumentAsTxt();
            sortedTextDocs.add(textOfDoc);
            doc.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(doc.getLastUseTime());
            heap.reHeapify(objForHeap);
        }
        return sortedTextDocs;
    }

    @Override
    public List<byte[]> searchPDFsByPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        keyWordForKey = prefix;
        // Get a list of all URIs that have that prefix, sorted in the correct order:
        List<URI> sortedURIs = trie.getAllWithPrefixSorted(prefix, compareDocsPrefixes);
        // Transform URI list into a list of text documents:
        ArrayList<byte[]> sortedTextDocs = new ArrayList<byte[]>();
        for (URI uri : sortedURIs) {
            DocumentImpl doc = bTreeOfDocs.get(uri);
            addBackToBtree(uri , doc);
            byte[] textOfDoc = doc.getDocumentAsPdf();
            sortedTextDocs.add(textOfDoc);
            doc.setLastUseTime(System.nanoTime());
            UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
            objForHeap.setLastUseTime(doc.getLastUseTime());
            heap.reHeapify(objForHeap);
        }
        return sortedTextDocs;
    }

    @Override
    public Set<URI> deleteAll(String key) { // line count is good
        key = key.toLowerCase();
        keyWordForKey = key;
        // get words to delete:
        CommandSet<Function> commandSet = new CommandSet<>();
        List<URI> willDeleteNodes = trie.getAllSorted(key, compareDocs);
        HashSet<URI> willDeleteNodesSet = new HashSet<URI>(willDeleteNodes);
        if (willDeleteNodesSet.size() == 0) {
            Function lambda = (x) -> {
                return true;
            };
            try {
                commandStack.push(new GenericCommand(new URI("str"), lambda));
            } catch (URISyntaxException e) {}
            HashSet<URI> emptySet = new HashSet<URI>();
            return emptySet;
        }
        HashSet<URI> willDeleteUris = new HashSet<URI>();
        // Now delete these values everywhere:
        long nanoTimeForDeletedSet = System.nanoTime();
        for (URI uri : willDeleteNodesSet) {
            DocumentImpl doc = bTreeOfDocs.get(uri);
            willDeleteUris.add(uri);
            // String docContent = doc.getDocumentAsTxt();
            String[] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
            for (int i = 0; i < allWordsInDoc.length; i++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            Function lambda = (x) -> {
                documentCount = documentCount + 1;
                bytesCount = bytesCount + getBytesPerDocument(doc);
                deleteDueToMemoryLimit();
                doc.setLastUseTime(nanoTimeForDeletedSet);
                UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, nanoTimeForDeletedSet);
                heap.insert(objForHeap);
                bTreeOfObj.put(uri, objForHeap);
                trie.put(doc.getDocumentAsTxt(), uri);
                bTreeOfDocs.put(uri, doc);
                return true;
            };
            if (!setOfDeletedDocs.contains(uri) ) {
                doc.setLastUseTime(min);
                UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
                objForHeap.setLastUseTime(min);
                heap.reHeapify(objForHeap);
                heap.removeMin();
                documentCount = documentCount - 1;
                bytesCount = bytesCount - getBytesPerDocument(doc);
            }
            commandSet.addCommand(new GenericCommand(uri, lambda));
            bTreeOfDocs.put(doc.getKey(), null);
        }
        commandStack.push(commandSet);
        return willDeleteUris;
    }

    @Override
    public Set<URI> deleteAllWithPrefix(String prefix) { // line count is good
        prefix = prefix.toLowerCase();
        keyWordForKey = prefix;
        // Get words to delete:
        CommandSet<Function> commandSet = new CommandSet<>();
        List<URI> willDeleteNodes = trie.getAllWithPrefixSorted(prefix, compareDocsPrefixes);
        HashSet<URI> willDeleteNodesSet = new HashSet<URI>(willDeleteNodes);
        if (willDeleteNodesSet.size() == 0) {
            Function lambda = (x) -> {
                return true;
            };
            try {
                commandStack.push(new GenericCommand(new URI("str"), lambda));
            } catch (URISyntaxException e) {
            }
            HashSet<URI> emptySet = new HashSet<URI>();
            return emptySet;
        }
        HashSet<URI> willDeleteUris = new HashSet<URI>();
        // Node delete all those nodes:
        long nanoTimeForDeletedSet = System.nanoTime();
        for (URI uri : willDeleteNodesSet) {
            DocumentImpl doc = bTreeOfDocs.get(uri);
            willDeleteUris.add(uri);
            String[] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
            for (int i = 0; i < allWordsInDoc.length; i++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            Function lambda = (x) -> {
                documentCount = documentCount + 1;
                bytesCount = bytesCount + getBytesPerDocument(doc);
                deleteDueToMemoryLimit();
                doc.setLastUseTime(nanoTimeForDeletedSet);
                UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, nanoTimeForDeletedSet);
                heap.insert(objForHeap);
                bTreeOfObj.put(uri, objForHeap);
                trie.put(doc.getDocumentAsTxt(), uri);
                bTreeOfDocs.put(uri, doc);
                return true;
            };
            commandSet.addCommand(new GenericCommand(uri, lambda));
            bTreeOfDocs.put(doc.getKey(), null);
            if (!setOfDeletedDocs.contains(uri) ) {
                doc.setLastUseTime(min);
                UriAndLastUseTime objForHeap = bTreeOfObj.get(uri);
                objForHeap.setLastUseTime(min);
                heap.reHeapify(objForHeap);
                heap.removeMin();
                documentCount = documentCount - 1;
                bytesCount = bytesCount - getBytesPerDocument(doc);
            }
        }
        commandStack.push(commandSet);
        return willDeleteUris;
    }

    private Set deleteDueToMemoryLimit() {
        HashSet<DocumentImpl> deletedDocs = new HashSet<DocumentImpl>();
        while (getBytesCount() > getMaxDocumentBytes() || getDocumentCount() > getMaxDocumentCount()) {
            UriAndLastUseTime heapObj = heap.removeMin();
            URI uri = heapObj.getUri();
            DocumentImpl doc = bTreeOfDocs.get(uri);
            try {
                bTreeOfDocs.moveToDisk(uri);
            } catch (Exception e) { }
            deleteUndoDueToMemory(doc.getKey() );
            documentCount = documentCount - 1;
            bytesCount = bytesCount - getBytesPerDocument(doc);
            setOfDeletedDocs.add(uri);
        }
        return deletedDocs;
    }

    private boolean addBackToBtree (URI uri , DocumentImpl doc) {
        /**
         * @return returns true if it was added back and false if it was already in BTree.
         */
        if (setOfDeletedDocs.contains(uri) ) {
            // this means that the set was deleted.
            documentCount = documentCount + 1;
            bytesCount = bytesCount + getBytesPerDocument(doc);   
            deleteDueToMemoryLimit();    
            UriAndLastUseTime objForHeap = new UriAndLastUseTime(uri, doc.getLastUseTime());
            bTreeOfObj.put(uri, objForHeap);
            heap.insert(objForHeap);
            bTreeOfDocs.put(uri , doc);
            setOfDeletedDocs.remove(uri);
            return true;
        }
        return false;
    }

    private int getDocumentCount () {
        return documentCount;
    }

    private int getBytesCount () {
        return bytesCount;
    }

    private int getBytesPerDocument (DocumentImpl doc) {
        byte[] bytesArrayText = doc.getDocumentAsTxt().getBytes();
        byte[] bytesArrayPDF = doc.getDocumentAsPdf();
        int bytesPerText = bytesArrayText.length;
        int bytesPerPDF = bytesArrayPDF.length;
        int bytesPerDoc = bytesPerText + bytesPerPDF;
        return bytesPerDoc;
    }

    private int getMaxDocumentCount () {
        return maxDocCount;
    }

    private int getMaxDocumentBytes () {
        return maxDocBytes;
    }

    @Override
    public void setMaxDocumentCount(int limit) {
        this.maxDocCount = limit;
        deleteDueToMemoryLimit();
    }

    @Override
    public void setMaxDocumentBytes(int limit) {
        this.maxDocBytes = limit;
        deleteDueToMemoryLimit();
    }
}