package edu.yu.cs.com1320.project.impl;

/**
 * Stage 4:
 * @author Tom Bohbot
 */

 import edu.yu.cs.com1320.project.Stack;

 public class StackImpl <T> implements Stack <T> {

    private class LinkedList <T> {
        private T t;
        private LinkedList <T> next;

        private LinkedList (T t ,  LinkedList next) {
            this.t = t;
            this.next = next;
        }

        private T getT () {
            return t;
        }
    }

    private int elemInStack;
    private LinkedList <T> headNode;

    public StackImpl() {
        headNode = new LinkedList <T> (null , null);
    }
    
    @Override 
    public void push(T element) {
        elemInStack += 1;
        if (elemInStack == 0) {
            headNode = new LinkedList <T> (element , null);
        }
        LinkedList <T> newHeadNode = new LinkedList <T> (element , null);
        newHeadNode.next = headNode;
        headNode = newHeadNode; 
    }

    @Override
    public T pop(){
        if (elemInStack == 0) {
            return null;
        }
        elemInStack -= 1;
        LinkedList <T> value = headNode;
        headNode = headNode.next;
        return value.getT();
    }

    @Override
    public T peek(){
        if (elemInStack == 0) {
            return null;
        }    
        LinkedList <T> value = headNode;
        return value.getT();
    }

    @Override
    public int size(){
        return elemInStack;
    }
 }