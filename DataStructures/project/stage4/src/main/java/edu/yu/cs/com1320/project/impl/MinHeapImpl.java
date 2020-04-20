package edu.yu.cs.com1320.project.impl;

import edu.yu.cs.com1320.project.MinHeap;

/**
 * MinHeap  Implementation Code for Stage 4
 * @author Tom Bohbot
 */

public class MinHeapImpl <E> extends MinHeap {

    public MinHeapImpl () {
         
    }

    @Override
    public void reHeapify(Comparable element) {
        int index = getArrayIndex(element);
            if (index == 1) {
                if (elements[2] == null) {
                    return;
                }
                if (isGreater(index, index + 1) ) {
                    downHeap(index);
                }
                upHeap(index);
            } 
            else {
                if (isGreater(index - 1, index) ) {
                        downHeap(index);
                    }
                    upHeap(index);
            }
        // never found the element:
    }

    @Override
    public int getArrayIndex(Comparable element) {
        int arrayIndex = (int) elementsToArrayIndex.get(element);
        return arrayIndex;
    }

    // public int getArraySize() {
    //     return elements.length;
    // }

    @Override
    public void doubleArraySize() {
        int numbOfElemsInArray = elements.length;
        int newArrayLength = numbOfElemsInArray * 2;
        Comparable [] doubledArray  = new Comparable [newArrayLength];
        for (int i = 0; i < elements.length; i ++) {
            doubledArray[i] = (Comparable) elements[i];
        }
        elements =  doubledArray;
    }
	
}