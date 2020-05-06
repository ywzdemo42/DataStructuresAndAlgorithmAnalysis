package com.ywz.com.datasturctures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义链表集合
 * @param <T>
 */
public class MyLinkedList<T> implements Iterable<T>{

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

    @Override
    public Iterator<T> iterator() {
        return null;
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
