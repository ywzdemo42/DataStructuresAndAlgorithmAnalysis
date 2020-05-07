package com.ywz.com.datasturctures;

import java.util.*;

/**
 * 自定义链表集合
 * @param <T>
 */
public class MyLinkedList<T> implements Iterable<T>{

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class Node<T> {
        //Node节点 里面有数据以及前后节点链
        public Node(T d, Node<T> p, Node<T> n) {
            data = d;
            pri = p;
            next = n;
        }

        private T data;
        private Node<T> pri;
        private Node<T> next;
    }

    public MyLinkedList() {
        doClear();
    }

    //初始化链表
    public void doClear() {
        clear();
    }

    private void clear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount ++;
    }

    //返回长度
    public int size() {
        return this.theSize;
    }
    //是否为空
    public boolean isEmpty() {
        return size() == 0;
    }
    //增加元素,默认是加在链表尾部
    public boolean add(T x) {
        add(size(), x);
        return true;
    }
    public void add(int idx, T x) {
        addBefore(getNode(idx,0,size()),x);
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<>(x, p.pri, p);
        p.pri.next = newNode;
        p.pri = newNode;
        theSize ++;
        modCount ++;
    }

    //获取节点
    private Node<T> getNode(int idx) {
        return getNode(idx,0,size()-1);
    }
    private Node<T> getNode(int idx, int lower, int upper) {
        //要取得的元素
        Node<T> p;
        if (idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }
        //采用二分法
        if(idx < size()/2){
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        }else {
            p = endMarker;
            for (int i = size(); i > idx ; i--) {
                p = p.pri;
            }
        }

        return p;
    }

    //删除节点
    public T remove(int idx) {
        return remove(getNode(idx));
    }
    public T remove( Node<T> p) {
        Node<T> oldNode = p;
        p.pri.next = p.next;
        p.next.pri = p.pri;
        theSize --;
        modCount ++;
        return oldNode.data;
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            //如果被修改过则非法
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            //如果被修改过则非法
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            //这段没理解什么意思
            //MyLinked.false.remove(current.prev);
            expectedModCount  ++;
            okToRemove = false;
        }
    }

    //链表长度
    private int theSize;
    //链表修改次数
    private int modCount = 0;
    //起始节点
    private Node<T> beginMarker;
    //末尾节点
    private Node<T> endMarker;
}
