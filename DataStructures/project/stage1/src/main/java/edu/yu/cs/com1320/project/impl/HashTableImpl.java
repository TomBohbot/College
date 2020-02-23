/** "Document Store Implementation" Stage 1
*
* @author Tom Bohbot
* Code to compile: javac -cp src/main/java src/main/java/edu/yu/cs/com1320/project/impl/HashTableImpl.java
* Code to run:     java -cp src/main/java edu.yu.cs.com1320.project.impl.HashTableImpl
*
* Code to run: java -cp target/classes edu.yu.cs.com1320.project.impl.HashTableImpl Now I just run through the IDE. STOP USING IDEs AS TEXT EDITOR!!
*/

package edu.yu.cs.com1320.project.impl;
import edu.yu.cs.com1320.project.HashTable;
import edu.yu.cs.com1320.project.stage1.DocumentStore;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.lang.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

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
    }  // no arg constructor

    public HashTableImpl (Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    private int getHashCode(Key k) {
        return Math.abs(k.hashCode() % hashTable.length);
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
            Value oldValue = get(k);
            deleteNode(k , v);
            return oldValue;
        }
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
        // Add new node to list using separate chaining:
        currentValue = hashTable[getHashCode(k)];
            while (currentValue.getNext() != null) {
                currentValue = currentValue.getNext();
            }
            currentValue.next = obj;
        return null;
    }

//    public static void main (final String [] args) throws IOException {
//        HashTableImpl <String, String> htImp = new HashTableImpl <String, String> ();
//        HashTableImpl <URI, String> htImp1 = new HashTableImpl <URI, String> ();
//        File file = new File("//Users/tombohbot/Desktop/testDoc.pdf");
//        URI uri = file.toURI();
//        InputStream inputStreamOg = new FileInputStream(file);          // this is ths input stream. Untouched and will go in method signature.
//        InputStream inputStreamCopy = new FileInputStream(file);        // this is ths input stream #2.
//        byte [] txtAsBytes = IOUtils.toByteArray(inputStreamCopy);
//        PDFTextStripper pdfStripper = new PDFTextStripper();
//        String is2AsString = pdfStripper.getText(PDDocument.load(txtAsBytes) );
//        int hashCode = is2AsString.hashCode();
//
//        String testGetter = htImp1.put(uri , is2AsString);
//        String getTest = htImp1.get(uri);
//
//        System.out.println(testGetter);
//        System.out.println(getTest);



//        String get0 = htImp.get("hi");
//        String value1  = htImp.put("hi" , "1");
//        String get1 = htImp.get("hi");
//        String value2  = htImp.put("hi" , "2");
//        String get2 = htImp.get("hi");
//        String value3  = htImp.put("hi" , "3");
//        String get3 = htImp.get("hi");
//        String value4  = htImp.put("hi" , "4");
//        String get4 = htImp.get("hi");
//        String c = htImp.put("c" , "c"); // hashcode is 4 - same as hi
//        String cGet = htImp.get("c");
//        String c1 = htImp.put("c" , "c1"); // hashcode is 4 - same as hi
//        String c2 = htImp.put("c" , "c2"); // hashcode is 4 - same as hi
//        String cGet1 = htImp.put("c" , "c"); // hashcode is 4 - same as hi
//
//        String obj3 = htImp.put("Tom" , "Tomv1");
//        String obj3Copy = htImp.put("Tom" , "Tomv2");
//        String obj3Copy1 = htImp.put("Tom" , "Tomv3");
//
////        String remHello = htImp.put("hi" , null);
//        String getrmv = htImp.get("hi");
//        String remc = htImp.put("c" , null);
//        String getrmvc = htImp.get("c");
//
//        System.out.println(value1);
//        System.out.println(value2);
//        System.out.println(value3);
//        System.out.println(value4);
//        System.out.println("Getters:");
//        System.out.println(get0);
//        System.out.println(get1);
//        System.out.println(get2);
//        System.out.println(get3);
//        System.out.println(get4);
//        System.out.println();
//        System.out.println(c);
//        System.out.println(cGet);
//        System.out.println(c1);
//        System.out.println(c2);
////        System.out.println(cGet1);
//        System.out.println();
//        System.out.println(obj3);
//        System.out.println(obj3Copy);
//        System.out.println(obj3Copy1);
//        System.out.println("Removed Objects:");
////        System.out.println(remHello);
//        System.out.println(getrmv);
////        System.out.println(remc);
//        System.out.println(getrmvc);


//    }
}
