package edu.yu.da;
/**
 * Encapsulates a set of "ancestors and descendants", modeled as a binary tree,
 * whose vertices are represented as unique non-negative integers. Each vertex_i
 * is associated with a double-valued value_i which need not be unique over the
 * set of vertices.
 *
 * @author Avraham Leff - skeleton code
 * @author Tom Bohbot - implementation
 * @version February 23, 2021
 */

import java.util.HashMap;

public class MaxOverBTDescendants {

    /**
     * Make a private node class to build fundamentals of my BST tree. 
     * Code inspired from SedgeWick and Wayne Chapter 3 Algorithms Fourth Edition
     * The parent is the key in this case because the role of the BST is to find the
     * parent and then add a child to it if 2 children do not already exist. 
     */    
    private class Node {
        private int childKey;
        private double maxVal;
        private Node parent;
        private Node lChild;
        private Node rChild;

        public Node (int childKey, double maxVal, Node parent) {
            this.childKey = childKey;
            this.maxVal = maxVal;
            this.parent = parent;
        }

        @Override 
        public boolean equals (Object that) {
            if (this == that) {
                return true;
            }
            if (that == null) {
                return false;
            }
            if (this.getClass() != that.getClass() ) {
                return false;
            }
            Node thatNode = (Node) that;
            if (thatNode.childKey != this.childKey) {
                return false;
            }
            if (thatNode.maxVal != this.maxVal) {
                return false;
            }
            return true;
        }
        
        @Override 
        public int hashCode() {
          return childKey;
        }
    
        @Override
        public String toString() {
          return "{childKey: " + childKey + ", maxVal: " + maxVal + ", numbKids: " + "}" ;
        }
    }

    private double [] trueValDesc;
    private HashMap <Integer, Node> map;
    private int V;

    /** 
     * Constructor: initializes a binary tree to have a root and value, and
     * specifies the number of vertices that will eventually populate the tree.
     *
     * @param V the number of vertices that will eventually populate the tree,
     * must be >= 1 (because tree must at least have a root)
     * @param root must be >= 0 and < V-1, and specifies the root vertex of the tree.
     * @param value the value associated with the root.
     */
    public MaxOverBTDescendants(final int V, final int root, final double value) {
        if (V <= 0 || root < 0 || root >= V) {
            throw new IllegalArgumentException("Illegal paramater(s).");
        }
        this.V = V;
        trueValDesc = new double [V];
        map = new HashMap<>();
        trueValDesc[root] = value;
        map.put(root, new Node(root, value, null) );
    }

    /** 
     * Connects the specified child vertex to the specified parent (which must
     * already be connected to the tree)
     *
     * @param parent a non-negative integer that identifies a vertex already
     * connected to the tree
     * @param child a non-negative integer that identifies a vertex being added
     * for the first (and only) time to the tree.
     * @param value the value associated with the child node
     * @throw IllegalArgumentException if the invariants are violated
     */
    public void addChild(final int parent, final int child, final double value) {
        // Edge Cases:
        if (parent < 0 || parent >= V || child < 0 || child >= V) {
            throw new IllegalArgumentException("Parent and/or child cannot be negative or greater than number of vertices.");
        }
        if (map.get(parent) == null) {
            throw new IllegalArgumentException("Parent does not exist.");
        }
        if (map.get(child) != null) {
            throw new IllegalArgumentException("This child already exists.");
        }
        Node parentNode = map.get(parent);
        if (parentNode.lChild != null && parentNode.rChild != null) {
            throw new IllegalArgumentException("Parent can only have a maximum of 2 children.");
        }
        // Vanilla Code:
        else {
            // Find the max val between all the descendants and child and put into final array:
            double maxVal = value;
            if (parentNode.maxVal > value) {
                maxVal = parentNode.maxVal;
            }
            trueValDesc[child] = maxVal;
            // Insert the child into the family tree:
            Node childNode = new Node(child, maxVal, parentNode);
            map.put(child, childNode);
            // Update parentNode to have new child:
            if (parentNode.lChild == null) {
                parentNode.lChild = childNode;
            }
            else if (parentNode.rChild == null) {
                parentNode.rChild = childNode;
            }
        }
    }

    /** 
     * Returns an array whose ith element is the the maximum value, over all
     * values associated with the ith element's descendants in the tree,
     * including the value associated with the ith element itself.
     *
     * @return array of doubles with the semantics specified above.
     */
    public double[] maxOverAllBTDescendants() {
        return trueValDesc;
    }

}