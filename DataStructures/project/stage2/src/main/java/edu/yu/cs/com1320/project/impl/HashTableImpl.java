package edu.yu.cs.com1320.project.impl;

/** "Document Store Implementation" Stage 2
*
* @author Tom Bohbot
*
*/

import edu.yu.cs.com1320.project.HashTable;

public class HashTableImpl <Key , Value> implements HashTable <Key , Value> {

    private class LinkedList <Key , Value> {

        private Key key;
        private Value value;
        private LinkedList <Key , Value> next;

        private LinkedList (Key key , Value value, LinkedList next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private Key getKey () {
            return key;
        }

        private Value getValue () {
            return value;
        }

        private void setValue(Value newValue) {
            this.value = newValue;
        }

        private LinkedList getNext() {
            return next;
        }

    }

    private LinkedList [] hashTable;
    private Key key;
    private Value value;
    private int m = 2; // size of array.
    private int n;

    public HashTableImpl () {
        hashTable = new LinkedList [m];
    }  // no arg constructor

    public HashTableImpl (Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    private int getHashCode(Key k) {
        return k.hashCode() & 0x7fffffff % m;
    }

    private void deleteNode (Key k , Value v) {
        LinkedList <Key , Value> headNode = hashTable[getHashCode(k)];
        // If the first element in the list is node, but there are future elements in the list:
        if (headNode.getKey().equals(k) && headNode.next != null) {
            hashTable[getHashCode(k)] = hashTable[getHashCode(k)].next;
        }
        // If the first element in the list is node, but the list has no future elements:
        if (headNode.getKey().equals(k) && headNode.next == null ) {
            hashTable[getHashCode(k)] = null;
        }
        // If the element your searching for is further down the chain, then you must find where it is. Checking if headNode != null to make sure that this does not loop forever.
        if (headNode != null) {
            while (headNode.next != null && headNode.next.getKey() != k) {
                headNode = headNode.next;
            }
        }
        // Once you found the next element you are looking for, which is the next element in the list, skip it, so that you skip remove it from the list.
        if (headNode.next != null) {
            headNode.next = headNode.next.next;
        }
    }

    private void doubleHashTableV3() {
        int prevLength = m;
        m = m * 2;
        // Create a new empty doubled array:
        HashTableImpl <Key, Value> doubledHashTable = new HashTableImpl <Key, Value> ();
        LinkedList [] doubledHashTableArray = new LinkedList [m];
        for (int i = 0; i < prevLength; i ++){
            LinkedList <Key , Value> currentNode = hashTable[i];
            if (hashTable[i] == null) {
                continue;
            }
            doubledHashTable.put(currentNode.getKey() , currentNode.getValue() );
            while (currentNode.next != null) {
                currentNode  = currentNode.next;
                doubledHashTable.put(currentNode.getKey() , currentNode.getValue() );
            }
        }
    }

    private void doubleHashTableV2 () {
        int prevLength = m;
        m = m * 2;
        // Create a new empty doubled array:
        LinkedList [] doubledHashTable = new LinkedList [m];
        // Loop through the previous hashTable and copy it's values to the new, empty hashTable:
        for (int i = 0; i < prevLength; i ++) {
            LinkedList <Key , Value> currentNode = hashTable[i];
            if (currentNode == null) { continue;}
            int newIndex = getHashCode(currentNode.getKey());
            if (doubledHashTable[newIndex] == null) {
                doubledHashTable[newIndex] = currentNode;
            }
            else if (doubledHashTable[newIndex] != null) {
                LinkedList <Key , Value> tempNode = doubledHashTable[newIndex];
                while (tempNode.next != null) {
                    tempNode = tempNode.next;
                }
                tempNode.next = currentNode;
            }
            while(currentNode.next != null) {
                currentNode = currentNode.next;
                newIndex = getHashCode(currentNode.getKey());
                if (doubledHashTable[newIndex] == null) {
                    doubledHashTable[newIndex] = currentNode;
                }
            }
        }
        hashTable = doubledHashTable;
    }

    @Override
    public Value get(Key k) {
        LinkedList <Key , Value> currentValue = hashTable[getHashCode(k)];
            while (currentValue != null) {
                if (currentValue.getKey().equals(k) ) {
                    return (Value) currentValue.getValue();
                }
                if (currentValue.next == null) {
                    break;
                }
                currentValue = currentValue.next;
            }
        return null;
    }

    @Override
    public Value put(Key k, Value v) {
        /** Goals for the method Put(k , v)
         *  Create an object instance of the thing I want to put
         *  Check if the key of the object instance already exists. If true, replace the old value with the new value and return old value.
         *  If the key is not present, then add it to the hashtable, and check for collisions and return null.
         */
        LinkedList <Key , Value> currentValue = hashTable[getHashCode(k)]; // currently set to the head of the list.
        LinkedList <Key , Value> obj = new LinkedList <Key , Value> (k , v , null);
        // If null is the value, then delete the node from the list:
        if (v == null) {
            if (get(k) == null) {return null;}
            Value oldValue = get(k);
            deleteNode(k , v);
            return oldValue;
        }
        // Check if the HashTable should be doubled in size:
        n += 1;
        if (m <= n/4) {
            doubleHashTableV2();
        }        
        // If head is null then there is no chance of collisions of duplicate so insert put.
        currentValue = hashTable[getHashCode(k)];
        if (currentValue == null) {
            hashTable[getHashCode(k)] = obj;
            return null;
        }
        // Check for duplicate:
        while (currentValue != null) {
            if(currentValue.getKey().equals(k) ) {
                n = n - 1;
                Value prevValue = currentValue.getValue();
                currentValue.setValue(obj.getValue());
                return prevValue;
            }
            currentValue = currentValue.next;
        }
        // Add new node to list using separate chaining:
        currentValue = hashTable[getHashCode(k)];
            while (currentValue.getNext() != null) {
                currentValue = currentValue.getNext();
            }
            currentValue.next = obj;
        return null;
    }
    // public static void main (final String [] args) {

    //     HashTableImpl <String , String> hashTable = new HashTableImpl<String , String>();

    //     hashTable.put("Firstobj" , "1");
    //     hashTable.put("second" , "2");
    //     hashTable.put("third" , "3");
    //     hashTable.put("fourth" , "4");
    //     hashTable.put("fifth" , "5");
    //     hashTable.put("sixth" , "6");
    //     hashTable.put("seventh" , "7");
    //     hashTable.put("eighth" , "8");
    //     hashTable.put("ninth" , "9");
        
    //     System.out.println("HASHCODES");
    //     hashTable.get("Firstobj");
    //     hashTable.get("second");
    //     hashTable.get("third");
    //     hashTable.get("fourth");
    //     hashTable.get("fifth");
    //     hashTable.get("sixth");
    //     hashTable.get("seventh");
    //     hashTable.get("eighth");
    //     hashTable.get("ninth");        
    // }
}
