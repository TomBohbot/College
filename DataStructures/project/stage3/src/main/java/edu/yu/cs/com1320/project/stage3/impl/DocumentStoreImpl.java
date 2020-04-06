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
import edu.yu.cs.com1320.project.CommandSet;
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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocumentStoreImpl implements DocumentStore {

    private HashTableImpl<URI, DocumentImpl> hashTableOfDocs = new HashTableImpl<URI, DocumentImpl>();
    private int hashCodeOfStream;
    private StackImpl<Undoable> commandStack = new StackImpl<Undoable>();
    private TrieImpl <DocumentImpl> trie = new TrieImpl <DocumentImpl> ();
    private Comparator <DocumentImpl>  compareDocs = new ComparatorImpl();
    private Comparator <DocumentImpl>  compareDocsPrefixes = new ComparatorImplPrefixes();


    private class ComparatorImpl implements Comparator <DocumentImpl>{

        @Override
        public int compare (DocumentImpl o1, DocumentImpl o2) {
            DocumentImpl docOne = (DocumentImpl) o1;
            DocumentImpl docTwo = (DocumentImpl) o2;
            return docTwo.wordCount(keyWordForKey) - docOne.wordCount(keyWordForKey);
        }
    }

    private class ComparatorImplPrefixes implements Comparator <DocumentImpl>{
        // private DocumentStoreImpl docStore = new DocumentStoreImpl();
        @Override
        public int compare (DocumentImpl o1, DocumentImpl o2) {
            DocumentImpl docOne = (DocumentImpl) o1;
            DocumentImpl docTwo = (DocumentImpl) o2;
            return countPrefixes(docTwo.getDocumentAsTxt() , keyWordForKey) - countPrefixes(docOne.getDocumentAsTxt() , keyWordForKey);
        }
    }

    public DocumentStoreImpl() { 
        // no arg constructor
    }

    private String parseSpecialCharacters (String str) {
		str = str.toLowerCase();
		String parsedString = new String ();
		for (char seekRegChar: str.toCharArray() ) {
			int AsciiVal = (int) seekRegChar;
			if (AsciiVal >= 48 && AsciiVal <= 57 || AsciiVal >= 97 && AsciiVal <= 122 || AsciiVal == ' ') {
				parsedString = parsedString + seekRegChar;
			} 
		}
		return parsedString;
    }

    private int countPrefixes (String docText , String prefix) {
        if (docText == null ) {return 0;}
        if (prefix == null ) {return 0;}
        docText = docText.toLowerCase();
        prefix = prefix.toLowerCase();
        docText = parseSpecialCharacters(docText);
        prefix = parseSpecialCharacters(prefix);
        if (prefix == null) {return 0; }
        char [] prefixAsChars = prefix.toCharArray();
        int prefixLength = prefixAsChars.length;
        int counter = 0;
        String [] allWordsInKey = docText.split(" ");
        for (int i = 0; i < allWordsInKey.length; i ++) {
            if (allWordsInKey[i].length() < prefixLength) {
                continue;
            }
            if (allWordsInKey[i].substring(0 , prefixLength).equals(prefix)) {
                counter ++;
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
                trie.put(oldDocText , oldDoc);
                hashTableOfDocs.put(uri, oldDoc);
                return true;
            };
            commandStack.push(new GenericCommand(uri, lambda));
            trie.delete(oldDocText, oldDoc);
            hashTableOfDocs.put(uri, null);
            return oldValue;
        }
        if (format == DocumentStore.DocumentFormat.TXT) {
            DocumentImpl oldDoc = hashTableOfDocs.get(uri);
            String oldDocText = oldDoc.getDocumentAsTxt();
            int oldValue = hashTableOfDocs.get(uri).getDocumentTextHashCode();
            Function lambda = (x) -> {
                trie.put(oldDocText , oldDoc);
                hashTableOfDocs.put(uri, oldDoc);
                return true;
            };
            trie.delete(oldDocText, oldDoc);
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
                return hashCodeOfStream; }
        }
        if (hashTableOfDocs.get(uri) == null) {
            Function lambda = (x) -> {
                String [] allWordsInDoc = txt.split(" ");
                for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], new DocumentImpl(uri, txt, hashCodeOfStream)); }
                hashTableOfDocs.put(uri, null);
                return true; };
            DocumentImpl doc = new DocumentImpl(uri, txt, hashCodeOfStream);
            trie.put(txt , doc);
            commandStack.push(new GenericCommand(uri, lambda));
            hashTableOfDocs.put(uri, new DocumentImpl(uri, txt, hashCodeOfStream));
            return 0;
        }
        DocumentImpl oldValue = hashTableOfDocs.get(uri);
        Function lambda = (x) -> {
            String [] allWordsInDoc = txt.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], new DocumentImpl(uri, txt, hashCodeOfStream)); }
            // trie.delete(txt , new DocumentImpl(uri, txt, hashCodeOfStream));
            hashTableOfDocs.put(uri, oldValue);
            trie.put(hashTableOfDocs.get(uri).getDocumentAsTxt() , hashTableOfDocs.get(uri));
            return true; };
        trie.delete(hashTableOfDocs.get(uri).getDocumentAsTxt() , hashTableOfDocs.get(uri));
        trie.put(txt , new DocumentImpl(uri, txt, hashCodeOfStream));
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
                String [] allWordsInDoc = strippedByteArray.split(" ");
                for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], new DocumentImpl(uri, strippedByteArray, hashCodeOfStream , streamAsBytes)); }
                // trie.delete(strippedByteArray , new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
                hashTableOfDocs.put(uri, null);
                return true;
            };
            trie.put(strippedByteArray , new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
            commandStack.push(new GenericCommand(uri, lambda));
            hashTableOfDocs.put(uri, new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
            return 0;
        }
        DocumentImpl oldValue = hashTableOfDocs.get(uri);
        Function lambda = (x) -> {
            String [] allWordsInDoc = strippedByteArray.split(" ");
            for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], new DocumentImpl(uri, strippedByteArray, hashCodeOfStream , streamAsBytes)); }
            // trie.delete(strippedByteArray , new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
            hashTableOfDocs.put(uri, oldValue);
            trie.put(hashTableOfDocs.get(uri).getDocumentAsTxt() , hashTableOfDocs.get(uri));
            return true;
        };
        trie.delete(hashTableOfDocs.get(uri).getDocumentAsTxt() , hashTableOfDocs.get(uri));
        trie.put(strippedByteArray , new DocumentImpl(uri, strippedByteArray, hashCodeOfStream, streamAsBytes));
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
            trie.put(doc.getDocumentAsTxt() , doc);
            hashTableOfDocs.put(uri, doc);
            return true;
        };
        String [] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
        for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], doc); }
        // trie.delete(doc.getDocumentAsTxt() , doc);
        commandStack.push(new GenericCommand(uri, lambda));
        hashTableOfDocs.put(uri, null);
        return true;
    }

    private boolean deleteDocumentNoUndo(URI uri) {
        DocumentImpl doc = hashTableOfDocs.get(uri);
        if (doc == null) {
            return false;
        }
        String [] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
        for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], doc); }
        // trie.delete(doc.getDocumentAsTxt() , doc);
        hashTableOfDocs.put(uri, null);
        return true;
    }

    @Override
    public void undo() throws IllegalStateException {
        if (commandStack.size() == 0) {
            throw new IllegalStateException();
        }
        if (commandStack.peek().getClass().getName().equals("edu.yu.cs.com1320.project.CommandSet") ) {
            CommandSet undoSet = (CommandSet) commandStack.pop();
            undoSet.undoAll();
        }
        else {
            GenericCommand undoElem = (GenericCommand) commandStack.pop();
            undoElem.undo();
        }
    }

    @Override
    public void undo(URI uri) throws IllegalStateException {
        if (commandStack.size() == 0) { throw new IllegalStateException(); }
        boolean foundUri = false;
        int commandStackSize = commandStack.size();
        StackImpl <Undoable> tempStack = new StackImpl <Undoable>();
        // Sort through command stack, until you find the URI:
        for (int i = 0; i < commandStackSize; i ++) {
            if (commandStack.peek().getClass().getName().equals("edu.yu.cs.com1320.project.CommandSet") ) {
                CommandSet undoSet = (CommandSet) commandStack.pop();
                if (undoSet.containsTarget(uri) ) {
                    undoSet.undo(uri);
                    foundUri = true;
                    tempStack.push(undoSet);
                    break;
                }
                tempStack.push(undoSet);
            }
            else { 
                GenericCommand <URI> tempNode = (GenericCommand) commandStack.pop();
                if (tempNode.getTarget().equals(uri) ){
                    tempNode.undo();
                    foundUri = true;
                    break;
                }
            tempStack.push(tempNode);
            }
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

    private String keyWordForKey;
    @Override
    public List<String> search(String keyword) {
        keyword = keyword.toLowerCase();
        keyWordForKey = keyword;
        // list of all URIs that correspond to keyword:
        ArrayList <DocumentImpl> listOfURIsSorted = (ArrayList) trie.getAllSorted(keyword , compareDocs);
        // make list of URIs into list of actual docs:
        ArrayList <String> returnValue = new ArrayList <String> ();
        for (DocumentImpl doc: listOfURIsSorted) {
            if (doc == null) { continue; }
            String docContent = doc.getDocumentAsTxt();
            returnValue.add(docContent);
        }
        return returnValue;
    }

    @Override
    public List<byte[]> searchPDFs(String keyword) {
        keyword = keyword.toLowerCase();
        keyWordForKey = keyword;
        // list of all URIs that correspond to keyword:
        ArrayList <DocumentImpl> listOfURIsSorted = (ArrayList) trie.getAllSorted(keyword , compareDocs);
        // make list of URIs into list of actual docs:
        ArrayList <byte []> returnValue = new ArrayList <byte []> ();
        for (DocumentImpl doc: listOfURIsSorted) {
            byte [] docContent = doc.getDocumentAsPdf();
            returnValue.add(docContent);
        }
        return returnValue;
    }

    @Override
    public List<String> searchByPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        keyWordForKey = prefix;
        // Get a list of all URIs that have that prefix, sorted in the correct order:
        List <DocumentImpl> sortedURIs = trie.getAllWithPrefixSorted(prefix , compareDocsPrefixes);
        // Transform URI list into a list of text documents:
        ArrayList <String> sortedTextDocs = new ArrayList <String> ();
        for (DocumentImpl doc: sortedURIs) {
            String textOfDoc = doc.getDocumentAsTxt();
            sortedTextDocs.add(textOfDoc);
        }
        return sortedTextDocs;
    }

    public List<byte[]> searchPDFsByPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        keyWordForKey = prefix;
        // Get a list of all URIs that have that prefix, sorted in the correct order:
        List <DocumentImpl> sortedURIs = trie.getAllWithPrefixSorted(prefix , compareDocsPrefixes);
        // Transform URI list into a list of text documents:
        ArrayList <byte[]> sortedTextDocs = new ArrayList <byte[]> ();
        for (DocumentImpl doc: sortedURIs) {
            byte[] textOfDoc = doc.getDocumentAsPdf();
            sortedTextDocs.add(textOfDoc);
        }
        return sortedTextDocs;
    }   

    @Override
    public Set<URI> deleteAll(String key) {
        key = key.toLowerCase();
        keyWordForKey = key;
        // get words to delete:
        CommandSet <Function> commandSet = new CommandSet <> ();
        List <DocumentImpl> willDeleteNodes = trie.getAllSorted(key , compareDocs);
        HashSet <DocumentImpl> willDeleteNodesSet = new HashSet <DocumentImpl> (willDeleteNodes);
        if (willDeleteNodesSet.size() == 0) {
            Function lambda = (x) -> { return true; };
            try { commandStack.push(new GenericCommand(new URI("str"), lambda));
            } catch (URISyntaxException e) {}
            HashSet <URI> emptySet = new HashSet <URI> ();
            return emptySet;
        }
        HashSet <URI> willDeleteUris = new HashSet <URI> ();
        // Now delete these values everywhere:
        for (DocumentImpl doc: willDeleteNodesSet) {
            URI uri = doc.getKey();
            willDeleteUris.add(uri);
            // String docContent = doc.getDocumentAsTxt();
            String [] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
            for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], doc); }
            Function lambda = (x) -> {
                trie.put(doc.getDocumentAsTxt() , doc);
                hashTableOfDocs.put(uri, doc);
                return true;
            };
            commandSet.addCommand(new GenericCommand(uri, lambda) );
            hashTableOfDocs.put(doc.getKey() , null);
            deleteDocumentNoUndo(uri);
        }
        commandStack.push(commandSet);
        return willDeleteUris;
    }

    @Override
    public  Set<URI> deleteAllWithPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        keyWordForKey = prefix;
        // Get words to delete:
        CommandSet <Function> commandSet = new CommandSet <> ();
        List <DocumentImpl> willDeleteNodes = trie.getAllWithPrefixSorted(prefix , compareDocsPrefixes);
        HashSet <DocumentImpl> willDeleteNodesSet = new HashSet <DocumentImpl> (willDeleteNodes);
        if (willDeleteNodesSet.size() == 0) {
            Function lambda = (x) -> { return true; };
            try { commandStack.push(new GenericCommand(new URI("str"), lambda));
            } catch (URISyntaxException e) {}
            HashSet <URI> emptySet = new HashSet <URI> ();
            return emptySet;
        }
        HashSet <URI> willDeleteUris = new HashSet <URI> ();
        // Node delete all those nodes:
        for (DocumentImpl doc: willDeleteNodesSet) {
            URI uri = doc.getKey();
            willDeleteUris.add(uri);
            String [] allWordsInDoc = doc.getDocumentAsTxt().split(" ");
            for (int i = 0; i < allWordsInDoc.length; i ++) { trie.delete(allWordsInDoc[i], doc); }
            Function lambda = (x) -> {
                trie.put(doc.getDocumentAsTxt() , doc);
                hashTableOfDocs.put(uri, doc);
                return true;
            };
            commandSet.addCommand(new GenericCommand(uri, lambda) );
            hashTableOfDocs.put(doc.getKey() , null);
            deleteDocumentNoUndo(uri);
        }
        commandStack.push(commandSet);
        return willDeleteUris;
    }
}
