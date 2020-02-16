/** "Document Store Implementation" Stage 1
*
* @author Tom Bohbot
* Code to compile: javac -cp src/main/java src/main/java/edu/yu/cs/com1320/project/impl/HashTableImpl.java 
* Code to run:     java -cp src/main/java edu.yu.cs.com1320.project.impl.HashTableImpl
*
* Code to run: java -cp target/classes edu.yu.cs.com1320.project.impl.HashTableImpl
* Error with above run statement. 
*/

package edu.yu.cs.com1320.project.impl;
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

    public HashTableImpl () {
        hashTable = new LinkedList [5];
    }

    public HashTableImpl (Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    private int getHashCode(Key k) {
        return k.hashCode() % hashTable.length;
    }

    @Override
    public Value get(Key k) {
        LinkedList <Key , Value> currentValue = hashTable[getHashCode(k)];
        try {
            while (currentValue.getKey().equals(k) || currentValue.getNext() != null) {
                if (currentValue.getKey().equals(k) ) {
                    return (Value) currentValue.getValue();
                }
                currentValue = currentValue.getNext();
            }
        } catch (NullPointerException e) {}
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
        // If head is null then there is no chance of collisions of duplicate so insert put.
        if (currentValue == null) {
            hashTable[getHashCode(k)] = obj;
            return null;     
        }
        // Check for duplicate:
        while (currentValue != null) {
            if(currentValue.getKey().equals(k) ) { 
                Value prevValue = currentValue.getValue();
                currentValue.setValue(obj.getValue());
                return prevValue;
            }
            currentValue = currentValue.getNext();
        }
        // Add new node to list using seperate chaining:
        currentValue = hashTable[getHashCode(k)]; 
        try {
            while (currentValue.getNext() != null) {
                currentValue = currentValue.getNext();
            }
            currentValue.next = obj;
        } catch (NullPointerException e) {}
        return null;
    }

    public static void main (final String [] args) {
        HashTableImpl <String, String> htImp = new HashTableImpl <String, String> ();
        String get0 = htImp.get("hi");
        String value1  = htImp.put("hi" , "1");
        String get1 = htImp.get("hi");
        String value2  = htImp.put("hi" , "2");
        String get2 = htImp.get("hi");
        String value3  = htImp.put("hi" , "3");
        String get3 = htImp.get("hi");
        String value4  = htImp.put("hi" , "4");
        String get4 = htImp.get("hi");
        String c = htImp.put("c" , "c"); // hashcode is 4 - same as hi
        String cGet = htImp.get("c");
        String c1 = htImp.put("c" , "c1"); // hashcode is 4 - same as hi
        String c2 = htImp.put("c" , "c2"); // hashcode is 4 - same as hi
        String cGet1 = htImp.put("c" , "c"); // hashcode is 4 - same as hi

        String obj3 = htImp.put("Tom" , "Tomv1");
        String obj3Copy = htImp.put("Tom" , "Tomv2");
        String obj3Copy1 = htImp.put("Tom" , "Tomv3");

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
        System.out.println(value4);
        System.out.println();
        System.out.println(get0);
        System.out.println(get1);
        System.out.println(get2);
        System.out.println(get3);
        System.out.println(get4);
        System.out.println();
        System.out.println(c);
        System.out.println(cGet);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(cGet1);
        System.out.println();
        System.out.println(obj3);
        System.out.println(obj3Copy);
        System.out.println(obj3Copy1);

    }
}
