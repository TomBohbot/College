package Final.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BitVectorTrie<Value> {
    
    private static final int R = 2; // this is the aplhabet size
    private Node root;              // root of the trie
    private int nodesInTrie = 0;
    private HashMap <Value , BitVector> map = new HashMap <Value , BitVector>();
    private HashMap <BitVector , Value> bigMap = new HashMap <BitVector , Value>();



    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
        private int counter;
    }

   /****************************************************
    * Is the key in the symbol table?
    ****************************************************/
    public boolean isRoutable(BitVector key) {
        return get(key) != null;
    }

   /****************************************************
    * get needs the most changes since its result depends
    * not on the entire key but on its longest matching 
    * prefix
    ****************************************************/
    public Value get(BitVector key) {
        // the key is an IP Adress and you want to return the port closest to this IP Adress.
        // the port is the decimal notation with the dots.
        // There is a method made to convert binary to decimal notation, toCIDR().
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(root, key, 0 , null);
    }

    private Value get(Node x, BitVector key, int d, Value bestSoFar) {
        // d = the record of bits that match.
        // best so far is the value with the highest record.
        if (x == null) { 
            // this means that I processed all the nodes and this value must be the best one then:
            return bestSoFar;
        }
        if (x.val != null) {
            bestSoFar = (Value) x.val;
        }
        if (d == key.size() ) {
            return bestSoFar;
        }
        int c = key.get(d);
        return get(x.next[c], key, d + 1 , bestSoFar);
   }

   /****************************************************
    * Insert Value value into the prefix Trie.
    * If a different value exists for the same key
    * throw an IllegalArgumentException
    ****************************************************/
    public void put(BitVector key, Value port) {
        if (map.get(port) != null && map.get(port) != key ) {
            // throw new IllegalArgumentException();
        }
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (port == null) {
            return;
        }
        root = put(root, key, port, 0);
        map.put(port , key);
        bigMap.put(key , port);
    }

    private Node put(Node x, BitVector key, Value port, int d) {
        if (x == null){
            x = new Node();
        } 
        if (d == key.size()) {
            if (x.val == null) {
                nodesInTrie ++;
            }
            x.val = port;
            return x;
        }
        int c = key.get(d);
        x.next[c] = put(x.next[c], key, port, d+1);
        return x;
    }

   /****************************************************
    * Delete the value for a key.
    * If no value exists for this key
    * throw and IllegalArgumentException
    ****************************************************/
    public void delete(BitVector key) {
        if (!bigMap.containsKey(key) ) {
            // throw new IllegalArgumentException();
        }
        root = delete(root, key, 0);
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Value port = bigMap.get(key);
        map.remove(port);
        bigMap.remove(key);
    }

    private Node delete(Node x, BitVector key, int d) {
        // finish this
        if (x == null) {
            return null;
        }
        if (d == key.size() ) {
            if (x.val != null) {
                x.val = null;
            }
        }
        else {
            int c = key.get(d);
            x.next[c] = delete (x.next[c] , key , d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
		// return null; // just so it compiles
    }

}
