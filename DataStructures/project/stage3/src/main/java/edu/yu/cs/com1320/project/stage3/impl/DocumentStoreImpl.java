package edu.yu.cs.com1320.project.stage3.impl;

/** "Document Store Implementation" Stage 3
 *
 * @author Tom Bohbot
 *
 */

import edu.yu.cs.com1320.project.stage3.DocumentStore;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage3.impl.DocumentImpl;
import edu.yu.cs.com1320.project.stage3.Document;
import edu.yu.cs.com1320.project.impl.StackImpl;
import edu.yu.cs.com1320.project.GenericCommand;
import edu.yu.cs.com1320.project.Undoable;
import java.util.function.Function;
import edu.yu.cs.com1320.project.impl.TrieImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.ByteArrayOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocumentStoreImpl implements DocumentStore {

    private HashTableImpl<URI, DocumentImpl> hashTableOfDocs = new HashTableImpl<URI, DocumentImpl>();
    private int hashCodeOfStream;
    private StackImpl<Undoable> commandStack = new StackImpl<Undoable>();
    private TrieImpl <URI> trie = new TrieImpl <URI> ();

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
            commandStack.push(new GenericCommand(uri, lambda));
            return 0;
        }
        if (format == DocumentStore.DocumentFormat.PDF) {
            DocumentImpl oldDoc = hashTableOfDocs.get(uri);
            String oldDocText = oldDoc.getDocumentAsTxt();
            int oldValue = hashTableOfDocs.get(uri).getDocumentTextHashCode();
            Function lambda = (x) -> {
                trie.put(oldDocText , uri);
                hashTableOfDocs.put(uri, oldDoc);
                return true;
            };
            commandStack.push(new GenericCommand(uri, lambda));
            trie.delete(oldDocText, uri);
            hashTableOfDocs.put(uri, null);
            return oldValue;
        }
        if (format == DocumentStore.DocumentFormat.TXT) {
            DocumentImpl oldDoc = hashTableOfDocs.get(uri);
            String oldDocText = oldDoc.getDocumentAsTxt();
            int oldValue = hashTableOfDocs.get(uri).getDocumentTextHashCode();
            Function lambda = (x) -> {
                trie.put(oldDocText , uri);
                hashTableOfDocs.put(uri, oldDoc);
                return true;
            };
            trie.delete(oldDocText, uri);
            commandStack.push(new GenericCommand(uri, lambda));
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
                commandStack.push(new GenericCommand(uri, lambda));
                return hashCodeOfStream;
            }
        }
        if (hashTableOfDocs.get(uri) == null) {
            Function lambda = (x) -> {
                trie.delete(txt , uri);
                hashTableOfDocs.put(uri, null);
                return true;
            };
            trie.put(txt , uri);
            commandStack.push(new GenericCommand(uri, lambda));
            hashTableOfDocs.put(uri, new DocumentImpl(uri, txt, hashCodeOfStream));
            return 0;
        }
        DocumentImpl oldValue = hashTableOfDocs.get(uri);
        Function lambda = (x) -> {
            trie.delete(txt , uri);
            hashTableOfDocs.put(uri, oldValue);
            return true;
        };
        trie.put(txt , uri);
        commandStack.push(new GenericCommand(uri, lambda));
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
                commandStack.push(new GenericCommand(uri, lambda));
                return hashCodeOfStream;
            }
        }
        if (hashTableOfDocs.get(uri) == null) {
            Function lambda = (x) -> {
                trie.delete(strippedByteArray , uri);
                hashTableOfDocs.put(uri, null);
                return true;
            };
            trie.put(strippedByteArray , uri);
            commandStack.push(new GenericCommand(uri, lambda));
            hashTableOfDocs.put(uri, new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
            return 0;
        }
        DocumentImpl oldValue = hashTableOfDocs.get(uri);
        Function lambda = (x) -> {
            trie.delete(strippedByteArray , uri);
            hashTableOfDocs.put(uri, oldValue);
            return true;
        };
        trie.put(strippedByteArray , uri);
        commandStack.push(new GenericCommand(uri, lambda));
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
            commandStack.push(new GenericCommand(uri, lambda));
            return false;
        }
        Function lambda = (x) -> {
            trie.put(doc.getDocumentAsTxt() , uri);
            hashTableOfDocs.put(uri, doc);
            return true;
        };
        commandStack.push(new GenericCommand(uri, lambda));
        hashTableOfDocs.put(uri, null);
        return true;
    }

    @Override
    public void undo() throws IllegalStateException {
        if (commandStack.size() == 0) {
            throw new IllegalStateException();
        }
        GenericCommand undoElem = (GenericCommand) commandStack.pop();
        undoElem.undo();
    }
    
    @Override
    public void undo(URI uri) throws IllegalStateException {
        if (commandStack.size() == 0) { throw new IllegalStateException(); }
        boolean foundUri = false;
        int commandStackSize = commandStack.size();
        StackImpl <Undoable> tempStack = new StackImpl <Undoable>();
        // Sort through command stack, until you find the URI:
        for (int i = 0; i < commandStackSize; i ++) {
            GenericCommand <URI> tempNode = (GenericCommand) commandStack.pop();
            if (tempNode.getTarget().equals(uri) ){
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

    @Override
    public List<String> search(String keyword) {
        keyword = keyword.toLowerCase();
        // list of all URIs that correspond to keyword:
        ArrayList <URI> listOfURIsSorted = (ArrayList) trie.getAllSorted(keyword);
        // make list of URIs into list of actual docs:
        ArrayList <String> returnValue = new ArrayList <String> ();
        for (URI uri: listOfURIsSorted) {
            DocumentImpl doc = hashTableOfDocs.get(uri);
            // if (doc.getDocumentAsTxt() == null) { continue; }
            String docContent = doc.getDocumentAsTxt();
            returnValue.add(docContent);
        }
        return returnValue;
    }

    @Override
    public List<byte[]> searchPDFs(String keyword) {
        keyword = keyword.toLowerCase();
        // list of all URIs that correspond to keyword:
        ArrayList <URI> listOfURIsSorted = (ArrayList) trie.getAllSorted(keyword);
        // make list of URIs into list of actual docs:
        ArrayList <byte []> returnValue = new ArrayList <byte []> ();
        for (URI uri: listOfURIsSorted) {
            DocumentImpl doc = hashTableOfDocs.get(uri);
            byte [] docContent = doc.getDocumentAsPdf();
            returnValue.add(docContent);
        }
        return returnValue;
    }

    /**
     * Retrieve all documents whose text starts with the given prefix
     * Documents are returned in sorted, descending order, sorted by the number of times the prefix appears in the document.
     * Search is CASE INSENSITIVE.
     * @param prefix
     * @return a List of the matches. If there are no matches, return an empty list.
     */
    @Override
    public List<String> searchByPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        // Get a list of all URIs that have that prefix, sorted in the correct order:
        List <URI> sortedURIs = trie.getAllWithPrefixSorted(prefix);
        // Transform URI list into a list of text documents:
        ArrayList <String> sortedTextDocs = new ArrayList <String> ();
        for (URI uri: sortedURIs) {
            DocumentImpl doc = hashTableOfDocs.get(uri);
            String textOfDoc = doc.getDocumentAsTxt();
            sortedTextDocs.add(textOfDoc);
        }
        return sortedTextDocs;
    }

    // /**
    //  * same logic as searchByPrefix, but returns the docs as PDFs instead of as Strings
    //  */
    // public List<byte[]> searchPDFsByPrefix(String prefix) {

    // }

    /**
     * delete ALL exact matches for the given key
     * @param key
     * @return a Set of URIs of the documents that were deleted.
     */
    @Override
    public Set<URI> deleteAll(String key) {
        key = key.toLowerCase();
        // get words to delete:
        List <URI> willDeleteNodes = trie.getAllSorted(key);
        HashSet <URI> willDeleteNodesSet = new HashSet <URI> (willDeleteNodes);
        // Now delete these values everywhere:
        for (URI uri: willDeleteNodesSet) {
            
            DocumentImpl doc = hashTableOfDocs.get(uri);
            String docContent = doc.getDocumentAsTxt();
            String [] allWordsInDoc = docContent.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i ++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            deleteDocument(uri);
        }
        return willDeleteNodesSet;
    }

    @Override
    public  Set<URI> deleteAllWithPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        // Get words to delete:
        List <URI> willDeleteNodes = trie.getAllWithPrefixSorted(prefix);
        HashSet <URI> willDeleteNodesSet = new HashSet <URI> (willDeleteNodes);
        // Node delete all those nodes:
        for (URI uri: willDeleteNodesSet) {
            DocumentImpl doc = hashTableOfDocs.get(uri);
            String docContent = doc.getDocumentAsTxt();
            String [] allWordsInDoc = docContent.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i ++) {
                trie.delete(allWordsInDoc[i], uri);
            }
            deleteDocument(uri);
        }
        return willDeleteNodesSet;
    }
}
