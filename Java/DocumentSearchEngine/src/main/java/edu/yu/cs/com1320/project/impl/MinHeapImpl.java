package edu.yu.cs.com1320.project.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import edu.yu.cs.com1320.project.MinHeap;
import edu.yu.cs.com1320.project.stage5.impl.DocumentImpl;

/**
 * Stage 4:
 * @author Tom Bohbot
 */

public class MinHeapImpl <E extends Comparable <E> > extends MinHeap <E> {

    protected E[] elements;
    protected int count=0;
    protected Map<E,Integer> elementsToArrayIndex; //used to store the index in the elements array

    public MinHeapImpl () {
        elements = (E[]) new Comparable [29];
        elementsToArrayIndex = new HashMap <E , Integer> (); //used to store the index in the elements array
    }

    @Override
    public void reHeapify(Comparable element) {
        int index = getArrayIndex(element);
        upHeap(index);
        downHeap(index);
        return;
    }

    @Override
    protected int getArrayIndex(Comparable element) {
        int arrayIndex = (int) elementsToArrayIndex.get(element);
        return arrayIndex;
    }

    // public int getArraySize() {
    //     return elements.length;
    // }

    @Override
    protected void doubleArraySize() {
        int numbOfElemsInArray = elements.length;
        int newArrayLength = numbOfElemsInArray * 2;
        E [] doubledArray  = (E[]) new Comparable [newArrayLength];
        for (int i = 0; i < elements.length; i ++) {
            doubledArray[i] = elements[i];
        }
        elements =  doubledArray;
    }




    @Override
    protected  boolean isEmpty()
    {
        return this.count == -1;
    }
    /**
     * is elements[i] > elements[j]?
     */
    @Override
    protected  boolean isGreater(int i, int j)
    {
        if (this.elements[i].getClass().getName().equals("edu.yu.cs.com1320.project.stage4.impl.DocumentImpl") && this.elements[i].getClass().getName().equals("edu.yu.cs.com1320.project.stage4.impl.DocumentImpl") ) {
            DocumentImpl docI = (DocumentImpl) this.elements[i];
            DocumentImpl docJ = (DocumentImpl) this.elements[j];
            Long docILastUseTime = docI.getLastUseTime();
            Long docJLastUseTime = docJ.getLastUseTime();
            return docILastUseTime.compareTo(docJLastUseTime) > 0;
        }
        return this.elements[i].compareTo(this.elements[j]) > 0; // index out of bound exception when heap array size is a small number. beware.
    }

    /**
     * swap the values stored at elements[i] and elements[j]
     */
    @Override
    protected void swap(int i, int j) {
        E temp = this.elements[i];
        this.elements[i] = this.elements[j];
        this.elements[j] = temp;
        swapArrayIndex(this.elements[i], this.elements[j]);
    }

    protected void swapArrayIndex (E i , E j) {
        Integer oldI = elementsToArrayIndex.get(i);
        Integer oldJ = elementsToArrayIndex.get(j);
        elementsToArrayIndex.replace(i , oldJ);
        elementsToArrayIndex.replace(j , oldI);
        // elementsToArrayIndex.put(i , oldJ);
        // elementsToArrayIndex.put(j , oldI);
    }


    /**
     *while the key at index k is less than its
     *parent's key, swap its contents with its parent’s
     */
    @Override
    protected  void upHeap(int k)
    {
        while (k > 1 && this.isGreater(k/2, k))
        {
            this.swap(k, k /2);
            k = k /2;
        }
    }

    /**
     * move an element down the heap until it is less than
     * both its children or is at the bottom of the heap
     */
    @Override
    protected  void downHeap(int k)
    {
        while (2 * k <= this.count)
        {
            //identify which of the 2 children are smaller
            int j = 2 * k;
            if (j < this.count && this.isGreater(j, j + 1))
            {
                j++;
            }
            //if the current value is < the smaller child, we're done
            if (!this.isGreater(k, j))
            {
                break;
            }
            //if not, swap and continue testing
            this.swap(k, j);
            k = j;
        }
    }

    protected void setArrayIndex (E element , Integer index) {
        if (elementsToArrayIndex.containsKey(element) ) {
            elementsToArrayIndex.replace(element , index);
        }
        else {
            elementsToArrayIndex.put(element, index);
        }
    }

    @Override
    public void insert(E x)
    {
        // double size of array if necessary
        if (this.count >= this.elements.length - 1)
        {
            this.doubleArraySize();
        }
        //add x to the bottom of the heap
        this.elements[++this.count] = (E) x;
        setArrayIndex((E) x , this.count);
        //percolate it up to maintain heap order property
        this.upHeap(this.count);
    }

    protected void deleteArrayIndex (E i) {
        elementsToArrayIndex.remove(i);
    }

    @Override
    public E removeMin()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("Heap is empty");
        }
        E min = this.elements[1];
        //swap root with last, decrement count
        this.swap(1, this.count--);
        deleteArrayIndex(min);
        //move new root down as needed
        this.downHeap(1);
        this.elements[this.count + 1] = null; //null it to prepare for GC
        return min;
    }
	
}
