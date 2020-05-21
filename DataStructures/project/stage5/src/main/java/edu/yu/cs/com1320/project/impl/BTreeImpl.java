package edu.yu.cs.com1320.project.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Stage 5:
 * @author Tom Bohbot
 */

import edu.yu.cs.com1320.project.BTree;
import edu.yu.cs.com1320.project.stage5.PersistenceManager;

public class BTreeImpl<Key extends Comparable<Key>, Value> implements BTree<Key, Value> {

    private static final int MAX = 8;
    private int max = 8;
    private Node root; // root of the B-tree
    private Node leftMostExternalNode;
    private int height;
    private int n;
    private File baseDir;
    private HashSet <File> files = new HashSet <File> ();

    private static class Node {
        private int entryCount; // number of entries
        private Entry[] entries = new Entry[MAX]; // the array of children
        private Node next;
        private Node previous;

        // create a node with k entries
        private Node(int k) {
            this.entryCount = k;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getNext() {
            return this.next;
        }

        private void setPrevious(Node previous) {
            this.previous = previous;
        }

        private Node getPrevious() {
            return this.previous;
        }

        private Entry[] getEntries() {
            return Arrays.copyOf(this.entries, this.entryCount);
        }
    }

    private static class Entry {
        private Comparable key;
        private Object val;
        private Node child;

        private Entry(Comparable key, Object val, Node child) {
            this.key = key;
            this.val = val;
            this.child = child;
        }

        private Object getValue() {
            return this.val;
        }

        private Comparable getKey() {
            return this.key;
        }
    }

    public BTreeImpl() {
        this.root = new Node(0);
        this.leftMostExternalNode = this.root;
    }

    public BTreeImpl(File baseDir) {
        this.root = new Node(0);
        this.leftMostExternalNode = this.root;
        this.baseDir = baseDir;
    }

    PersistenceManager<Key, Value> persistenceManager;

    private Entry get(Node currentNode, Key key, int height) {
        Entry[] entries = currentNode.entries;

        // current node is external (i.e. height == 0)
        if (height == 0) {
            for (int j = 0; j < currentNode.entryCount; j++) {
                if (isEqual(key, entries[j].key)) {
                    return entries[j];
                }
            }
            return null;
        } else {
            for (int j = 0; j < currentNode.entryCount; j++) {
                if (j + 1 == currentNode.entryCount || less(key, entries[j + 1].key)) {
                    return this.get(entries[j].child, key, height - 1);
                }
            }
            return null;
        }
    }

    private Value checkIfDeseralized(Key key) throws IOException {
        return persistenceManager.deserialize(key);
        // return null;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot insert null as key.");
        }
        Entry entry = this.get(this.root, key, this.height);
        if (entry == null) {
            return null;
        }
        if (entry.val == null) {
            Value tryDesearilizing = null;
            try {
                tryDesearilizing = checkIfDeseralized(key);
            } catch (IOException e) {}
            if (tryDesearilizing == null) {
                return null;
            }
            put(key, (Value) tryDesearilizing);
            return (Value) tryDesearilizing;
        }
        return (Value) entry.val;
    }

    @Override
    public Value put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null");
        }
        Entry alreadyThere = this.get(this.root, key, this.height);
        if (alreadyThere != null) {
            if (alreadyThere.val == null) {
                try {
                    try {
                        Value deletedDoc = checkIfDeseralized(key);
                        alreadyThere.val = value;
                        return deletedDoc;
                    } catch (NullPointerException e) {
                        alreadyThere.val = value;
                        return null;
                    }
				} catch (IOException e) {}
            }
            Value oldValue = (Value) alreadyThere.getValue();
            alreadyThere.val = value;
            return oldValue;
        }
        Node newNode = this.put(this.root, key, value, this.height);
        this.n++;
        if (newNode == null) {
            return null;
        }
        Node newRoot = new Node(2);
        newRoot.entries[0] = new Entry(this.root.entries[0].key, null, this.root);
        newRoot.entries[1] = new Entry(newNode.entries[0].key, null, newNode);
        this.root = newRoot;
        // a split at the root always increases the tree height by 1
        this.height++;
        return null;
    }

    // comparison functions - make Comparable instead of Key to avoid casts
    private static boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private static boolean isEqual(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    private Node split(Node currentNode, int height) {
        Node newNode = new Node(max / 2);
        currentNode.entryCount = max / 2;
        for (int j = 0; j < max / 2; j++) {
            newNode.entries[j] = currentNode.entries[max / 2 + j];
        }
        // external node
        if (height == 0) {
            newNode.setNext(currentNode.getNext());
            newNode.setPrevious(currentNode);
            currentNode.setNext(newNode);
        }
        return newNode;
    }

    private Node put(Node currentNode, Key key, Value val, int height) {
        int j;
        Entry newEntry = new Entry(key, val, null);
        if (height == 0) {
            for (j = 0; j < currentNode.entryCount; j++) {
                if (less(key, currentNode.entries[j].key)) {
                    break;
                }
            }
        }
        // internal node
        else {
            // find index in node entry array to insert the new entry
            for (j = 0; j < currentNode.entryCount; j++) {
                if ((j + 1 == currentNode.entryCount) || less(key, currentNode.entries[j + 1].key)) {
                    // increment j (j++) after the call so that a new entry created by a split
                    // will be inserted in the next slot
                    Node newNode = this.put(currentNode.entries[j++].child, key, val, height - 1);
                    if (newNode == null) {
                        return null;
                    }
                    newEntry.key = newNode.entries[0].key;
                    newEntry.val = null;
                    newEntry.child = newNode;
                    break;
                }
            }
        }
        // shift entries over one place to make room for new entry
        for (int i = currentNode.entryCount; i > j; i--) {
            currentNode.entries[i] = currentNode.entries[i - 1];
        }
        // add new entry
        currentNode.entries[j] = newEntry;
        currentNode.entryCount++;
        if (currentNode.entryCount < max) {
            return null;
        } else {
            return this.split(currentNode, height);
        }
    }

    @Override
    public void moveToDisk(Key k) throws Exception {
        Key key = k;
        Value val = get(k);
        persistenceManager.serialize(key, val);
        put(key , null);
    }

    @Override
    public void setPersistenceManager(PersistenceManager<Key, Value> pm) {
        this.persistenceManager = pm;
    }	
}