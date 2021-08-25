package edu.yu.introtoalgs;

/**
 * @author Tom Bohbot
 * @version December 8, 2020
 * @assignment Index Min PQ
 */

import java.util.*;

// import javax.management.RuntimeErrorException;

public class IndexMinPQ <Key extends Comparable < Key>> implements Iterable<Integer> {

    @Override 
    public Iterator<Integer> iterator() {
        return new MinPqIterator();
    }

    private class MinPqIterator implements Iterator<Integer> {

        private IndexMinPQ <Key> obj = new IndexMinPQ(pq, qp, keys, n, maxN);

        @Override
        public boolean hasNext() {
            if (obj.size() < 1 ) {
                return false;
            }
            return true;
        }

        @Override
        public Integer next() {
            return obj.delMin();
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
            return true;
        }
    
        @Override 
        public int hashCode() {
            return this.obj.hashCode();
        }

        @Override
        public String toString() {
        return "" + this.obj.toString();
        }

    }
    
    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int maxN;

    public IndexMinPQ(int maxN) {
        if (maxN < 0) {
            throw new RuntimeException("Max number of elements must be at least zero.");
        }
        this.maxN = maxN;
        this.keys = (Key []) new Comparable [maxN + 1];
        this.pq = new int [maxN + 1];
        this.qp = new int [maxN + 1];
        for (int i = 0; i <= maxN; i ++) {
            qp[i] = -1;
        }
    }

    private IndexMinPQ(int [] pq, int [] qp ,  Key [] keys, int n , int maxN) {
        this.pq = new int[maxN + 1];
        this.keys = (Key []) new Comparable [maxN + 1];
        this.qp = new int [maxN + 1];
        for (int i = 0; i < pq.length; i ++) {
            this.pq[i] = pq[i];
            this.qp[i] = pq[i];
            this.keys[i] = keys[i];
        }
        this.n = n;
        this.maxN = n;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new RuntimeException("Illegal index.");
        }
        if (this.size() >= maxN) { 
            throw new RuntimeException("Maximum amount of elements currently inserted");
        }
        if (qp[i] != -1) {
            throw new RuntimeException("Cannot insert duplicate indexes.");
        }
        if (key == null) {
            throw new RuntimeException("Cannot insert null keys.");
        }
        // Code from book:
        n ++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) {
            throw new RuntimeException("Illegal index.");
        }
        if (qp[i] == -1) {
            throw new RuntimeException("Invalid index.");
        }
        // Have not done yet:
        return keys[i];
    }

    public int size() {
        // My own code:
        return n;
    }

    public boolean isEmpty() {
        // Code from book:
        return n == 0;
    }

    public int delMin() {
        if (isEmpty() == true) {
            throw new RuntimeException("Currently Empty.");
        }
        // Code from book:
        int indexOfMin = pq[1];
        exch(1 , n--);
        sink(1);
        keys[pq[n+1]] = null;
        qp[pq[n+1]] = -1;
        return indexOfMin;
    }

    public Key minKey() {
        if (isEmpty() == true) {
            throw new RuntimeException("Currently Empty.");
        }
        // Code from book:
        return keys[ pq[1] ];
    }

    public int minIndex() {
        if (isEmpty() == true) {
            throw new RuntimeException("Currently Empty.");
        }
        // Have not done yet:
        return pq[1];
    }

    private boolean less (int i , int j) {
        // Code from book:
        Key one = keys[pq[i]];
        Key two = keys[pq[j]];
        return one.compareTo(two) > 0;
    }

    private void exch (int i , int j) {
        // Code from book:
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim (int k) {
        // Code from book:
        while (k > 1 && less(k/2 , k) ) {
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink (int k) {
        // Code from book:
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j , j + 1) ) {
                j++;
            }
            if (!less(k,j) ) {
                break;
            }
            exch(k , j);
            k = j;
        }
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
        IndexMinPQ thatMinPQ = (IndexMinPQ) that;
        if (this.minIndex()!= thatMinPQ.minIndex() ) {
            return false;
        }
        if (this.size() != thatMinPQ.size() ) {
            return false;
        }
        return true;
    }
    
    @Override 
    public int hashCode() {
        return this.minIndex();
    }

    @Override
    public String toString() {
      return "" + this.minIndex();
    }
}