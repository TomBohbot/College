package edu.yu.cs.com1320.project.impl;

import edu.yu.cs.com1320.project.MinHeap;
import edu.yu.cs.com1320.project.stage4.impl.DocumentImpl;

/**
 * Stage 4:
 * @author Tom Bohbot
 */

public class MinHeapImpl <E> extends MinHeap {

    public MinHeapImpl () {
         
    }

    private void reheapDocument(Comparable element) {
        int index = getArrayIndex(element);
        if (index == 1) {
            if (elements[2] == null) {
                return;
            }
            int counter = 1;
            int counterPlusOne = 2;
            DocumentImpl shouldBeLess = (DocumentImpl) elements[1];
            DocumentImpl shouldBeGreater = (DocumentImpl) elements[2];
            while (shouldBeLess.getLastUseTime() > shouldBeGreater.getLastUseTime() && elements[counterPlusOne] != null) {
                DocumentImpl temp = shouldBeLess;
                elements[counter] = shouldBeGreater;
                elements[counterPlusOne] = shouldBeLess;
                counter ++;
                counterPlusOne ++;
            }
        }
    }

    @Override
    public void reHeapify(Comparable element) {

    int index = getArrayIndex(element);
    upHeap(index);
    downHeap(index);
    return;

    // if (element.getClass().getName().equals("edu.yu.cs.com1320.project.stage4.impl.DocumentImpl") ){
    //     reheapDocument(element);
    //     return elements [0];
    // }
    // System.out.println("Entered reheapify");
    // DocumentImpl doc = (DocumentImpl) element;
    // System.out.println(doc.getDocumentAsTxt() + " and index is " + getArrayIndex(element));
    // int index = getArrayIndex(element);
    //     if (index == 1) {
    //         if (elements[2] == null) {
    //             return elements[1];
    //         }
    //         if (isGreater(index, index * 2) ) {
    //             upHeap(index);
    //         }
    //         downHeap(index);
    //     } 
    //     else {
    //         if (index / 2 == 0) {
    //             return elements[1];
    //         }
    //         if (isGreater(index, index / 2) ) {
    //             System.out.println("HIIIII");
    //                 downHeap(index);
    //             }
    //         else {
    //             upHeap(index);
    //         }
    //     }
    //     return elements[1];
    //     // never found the element:
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