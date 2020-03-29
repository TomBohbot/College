package edu.yu.cs.com1320.project.impl;

/**
 * "Trie Implementation Code" Stage 3
 * @author Tom Bohbot
 * Things Left to do:
 * 1) Delete Leaf Nodes after deleting values.
 * 2) Make the comparator to return a sorted list. 
 * 3) Exclude and special characters.               DONE
 * 4) Get rid of the queue.                         DONE
 */

import edu.yu.cs.com1320.project.Trie;
import edu.yu.cs.com1320.project.stage3.Document;
import edu.yu.cs.com1320.project.stage3.impl.DocumentImpl;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.standard.CopiesSupported;

public class TrieImpl <Value> implements Trie <Value> {

    private int alphabetSize = 256; // extended ASCII
    private Node root; // root of trie
    private ArrayList <Value> listAtValue;
    private ArrayList <Document> listOfValuesAsDoc;
    private Comparator <Document>  compareDocs = new ComparatorImpl();

    private class ComparatorImpl implements Comparator <Document>{
        private String key;
        
        @Override
        public int compare (Document o1, Document o2) {
            DocumentImpl docOne = (DocumentImpl) o1;
            DocumentImpl docTwo = (DocumentImpl) o2;
            return docOne.wordCount(key) -docTwo.wordCount(key);
        }
    }

    private class Node <Value> {
        private Value val;
        private Node[] links = new Node[alphabetSize];
        private int counter;
    }

    public TrieImpl() { // no arg constructor.
        this.root = null;
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

    @Override
    public void put(String key, Value val) {
        key = key.toLowerCase();
        key = parseSpecialCharacters(key);
        String [] allWordsInKey = key.split(" ");
        if (val == null) {
            return; 
        }
        for (int i = 0; i < allWordsInKey.length; i ++) {
            this.root = put(this.root, allWordsInKey[i], val, 0);
        }
    }

    private Node put(Node x, String key, Value val, int d) {
        ArrayList <Value> listOfValues = new ArrayList <>();
        if (x == null) { x = new Node(); }
        if (d == key.length()) {
            if (x.val == null) {
                listOfValues.add(val);
            }
            ArrayList <Value> copyOfXVal = (ArrayList) x.val;
            for (int i = 0; i < x.counter; i ++) {
                if (copyOfXVal.get(i) == val) { continue; } // changed this from returning x. think it makes a difference but not sure. 
                listOfValues.add(copyOfXVal.get(i) );
            }
            if (listOfValues.contains(val)) {
                x.val = listOfValues;
                x.counter ++;
                return x; 
            }
            listOfValues.add(val);
            x.val = listOfValues;
            x.counter ++;
            return x;
        }
        char c = key.charAt(d);
        x.links[c] = this.put(x.links[c], key, val, d + 1);
        return x;
    }

    /**
     * set the comparator to be used in methods which sort values
     * @param comparator
     */
    public void setComparator(Comparator<Value> comparator) {
        // this.comparator 
    }


    @Override
    public List<Value> getAllSorted(String key) {
        // Make a list of all my docs, and call this method on them. My comparator should resort my list based off of frequency of the key.
        // The put method is not for key words, but entire docs are going into the trie, and eacho contain their own value. Docs that contain certain words should be added to the return list. 
        // Every time I make a doc I parse each word and add each word to the trie. Then, each word's value is a set the has docs that contain that word.
        // have to return an empty list NOT null if there are no matches!
        key = key.toLowerCase();
        Node x = this.get(this.root, key, 0);
        if (x == null) {
            ArrayList <Value> emptyList = new ArrayList <Value> ();
            return emptyList;
        }
        List <Value> unsortedList = (ArrayList<Value>) ((Value)x.val);
        return unsortedList;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) { return x; }
        char c = key.charAt(d);
        return this.get(x.links[c], key, d + 1);
    }

    @Override
    public List<Value> getAllWithPrefixSorted(String prefix) {
        prefix = prefix.toLowerCase();
        ArrayList<Value> results = new ArrayList<Value>();
        Node x = this.get(this.root, prefix, 0);
        if (x != null) {
            this.collect(x, new StringBuilder(prefix), results);
        }
        ArrayList <Value> returnValue = new ArrayList <Value>(results);
        return returnValue;
    }

    private void collect(Node x,StringBuilder prefix, ArrayList<Value> results) {
        if (x.val != null) {
            for (int i = 0; i < ((ArrayList<Value>) x.val).size();i ++) {
                results.add(((ArrayList<Value>) x.val).get(i));
            }
        }
        for (char c = 0; c < alphabetSize; c++) {
            if(x.links[c]!=null){
            prefix.append(c);
            this.collect(x.links[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    @Override
    public Set<Value> deleteAllWithPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        HashSet<Value> results = new HashSet<Value>();
        Node x = this.get(this.root, prefix, 0);
        if (x != null) {
            this.deletePrefixHelper(x, new StringBuilder(prefix), results);
        }
        HashSet <Value> returnValue = new HashSet <Value>(results);
        return returnValue;
    }

    private void deletePrefixHelper (Node x,StringBuilder prefix, HashSet<Value> results) {
        if (x.val != null) {
            for (int i = 0; i < ((ArrayList<Value>) x.val).size();i ++) {
                results.add(((ArrayList<Value>) x.val).get(i));
            }
            x.val = null;
            x.counter = 0;
        }
        for (char c = 0; c < alphabetSize; c++) {
            if(x.links[c]!=null){
            prefix.append(c);
            this.deletePrefixHelper(x.links[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    @Override
    public Set<Value> deleteAll(String key) {
        key = key.toLowerCase();
        Set returnSet = deleteAll(this.root, key, 0);
        return returnSet;
    }

    private HashSet <Value> deletedVals = new HashSet <Value> (); // private instance variable for following method.
    private Set <Value> deleteAll(Node x, String key, int d) {
        if (x == null) { return null;}
        if (d == key.length()) { 
            ArrayList <Value> tempList = (ArrayList<Value>) x.val;
            deletedVals = new HashSet<Value>(tempList); 
            x.val = null;
            x.counter = 0; 
        }
        else {
            char c = key.charAt(d);
            Set deletedSet = this.deleteAll(x.links[c], key, d + 1);
        }
        if (x.val != null) { return deletedVals; }
        for (int c = 0; c < alphabetSize; c++) {
            if (x.links[c] != null) { return deletedVals; }//not empty
        }
        return null;
    }

    @Override
    public Value delete(String key, Value val) {
        key = key.toLowerCase();
        String [] allWordsInKey = key.split(" ");
        Value returnValue = (Value) deleteNode(this.root, allWordsInKey[0], 0 , val);
        return returnValue;
    }

    private Value deletedNode;
    private Value deleteNode (Node x, String key, int d , Value val) {
        if (x == null) { return null;}
        if (d == key.length()) { 
            if (((ArrayList<Value>) x.val).contains(val)) {
                deletedNode = val;
            }
            ((ArrayList<Value>) x.val).remove(val);
            x.counter = x.counter - 1;
        }
        else {
            char c = key.charAt(d);
            Value repeat = this.deleteNode(x.links[c], key, d + 1 , val);
        }
        if (x.val != null) { return deletedNode; }
        for (int c = 0; c < alphabetSize; c++) {
            if (x.links[c] != null) { return deletedNode; }//not empty
        }
        return null;
    }
}