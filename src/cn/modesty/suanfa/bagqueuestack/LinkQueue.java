package cn.modesty.suanfa.bagqueuestack;

import jdk.nashorn.internal.ir.IfNode;

public class LinkQueue<T> {
    Node head;
    Node tail;
    private int N = 0;
    private class Node{
        Node next;
        T data;
    }
    public boolean  isEmpty(){return head == null;}
    public void enqueue(T t){
        Node oldtail = this.tail;
        tail = new Node();
        tail.data = t;
        if (isEmpty()){
            head = this.tail;
        }else {
            oldtail.next = tail;
        }
        N++;
    }

    public T dequeue(T t){
        T item = head.data;
        head = head.next;
        if (isEmpty())tail = null;
        N--;
        return item;
    }
}
