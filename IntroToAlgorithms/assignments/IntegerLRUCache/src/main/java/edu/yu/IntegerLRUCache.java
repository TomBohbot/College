package edu.yu.introtoalgs;

/**
 * Assignment: IntegerLRUCache.
 * @author  Tom Bohbot
 * @version November 11, 2020
 */

import java.util.*;
import java.util.HashSet;

/** A class that provides a cache associating Integer-valued keys and values.
 * The implementation is constrained to a given capacity such that if the
 * number of entries exceeds the capacity, entries are removed to to maintain
 * the "does not exceed capacity" constraint.  When removing elements to
 * maintain the capacity constraint, the implementation follows the
 * "least-recently-used" policy.
 *
 */

public class IntegerLRUCache {

    private static class Node {
        private Node next;
        private Node prev;
        private Integer key;

        private Node (Integer key) {
            this.key = key;
        }

        private Integer getKey () {
            return this.key;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getNext() {
            return this.next;
        }

        private void setPrevious(Node prev) {
            this.prev = prev;
        }

        private Node getPrevious() {
            return this.prev;
        }
    }


    // private int lruInt;
    // private int capacity;
    // private int counter;
    // private int numberedObjInMap;
    // private HashMap <Integer , Integer > map;
    // private HashMap <Integer , Integer > lruTracker;
    // private HashMap <Integer , Integer > keyToLruTracker = new HashMap <Integer , Integer >();;

    private HashMap <Integer , Node> mapToNode;
    private int capacity;
    private int counter;
    private HashMap <Integer , Integer> map;
    private Node head;
    private Node tail;

    /** Constructs an empty cache with the specified capacity.  The cache
     * implementation is forbidden from exceeding this capacity, but may choose
     * to use less than this capacity.
     *
     * @param initialCapacity the initial capacity
     */
    public IntegerLRUCache(final int capacity) {
        this.mapToNode = new HashMap <Integer , Node>();;
        this.map = new HashMap <Integer , Integer>();
        this.counter = 0;
        this.capacity = capacity;
    }

    /** Returns the cached value associated with the specified key, null if not
     * cached previously
     *
     * @param key the key whose associated value is to be returned
     * @return the previously cached value, or null if not previously cached
     * @throws IllegalArgumentException if the key is null
     */
    public Integer get(final Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (map.get(key) == null) {
            return null;
        }
        // maintain correct linkedlist order:
        Node node = mapToNode.get(key);
        if (head == node) {
            head = head.next;
            head.prev = null;
            node.prev = tail;
            tail.next = node;
            tail = node;
            return map.get(key);
        }
        Node previousNode = node.prev; 
        Node nextNode = node.next;
        previousNode.next = nextNode;
        nextNode.prev = previousNode;
        // append current node to end of list:
        node.prev = tail;
        tail.next = node;
        tail = node;
        return map.get(key);
    }

    /** Associates the specified value with the specified key. If the cache
     * previously contained an association for this key, the old value is
     * replaced, and the key is now associated with the specified value.  If
     * inserting this item will cause the cache to exceed its capacity, the
     * method will evict some other item to maintain the capacity constraint.
     * The item selected is the least-recently-used ("accessed") item.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be cached
     * @return the value associated with this key if previously cached, otherwise
     * null
     * @throws IllegalArgumentException if either the key or value is null
     */
    public Integer put (final Integer key, final Integer value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException ("Paramaters cannot be null.");
        }
        // if the key already exists:
        if (map.get(key) != null) {
            get(key); 
            return map.replace(key , value);
        }
        if (counter == capacity) {
            fullCapacity();
        }
        Node node = new Node(key);
        if (counter == 0) {
            head = node;
            tail = node;
        }
        else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        mapToNode.put(key , node);
        map.put(key , value);
        counter ++;
        return null;
    }

    /** Removes the specified key-value mapping if present (returning the value
     * previously associated with the key), no-op returns null if no previous
     * association.
     *
     * @param key key whose mapping is to be removed
     * @return previous value associated with key, otherwise null
     * @throws IllegalArgumentException if the key is null
     */
    public Integer remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        if (map.remove(key) == null) {
            return null;
        }
        Integer removedObj = map.remove(key);
        //now must remove from LRU:
        Node node = mapToNode.get(key);
        if (head == node) {
            head = head.next;
            head.prev = null;
        }
        else if (tail == node) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        mapToNode.remove(node);
        counter --;
        return removedObj;
    }

    private void fullCapacity() {
        Node lru = head;
        Integer removeKey = lru.key;
        head = head.next;
        head.prev = null;
        map.remove(removeKey);
        counter --;
    }
}