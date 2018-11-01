package cn.modesty.suanfa.bagqueuestack;

import cn.modesty.suanfa.linked.List;

public class ArrayQueue<T> {
    int head;
    int tail;
    int size = 5;
    T[] a = (T[])new Object[size];


    public void enqueue1(T t){
        a[tail] = t;
        ++tail ;
    }

    public T  dequeue1(){
        T tempRet = a[head];
        ++head;
        return tempRet;
    }

    public void print(){
        for (int i = head; i < tail; i++) {
            System.out.print(a[i]+",");
        }
    }

    public boolean enqueue(T t){
        if ((tail+1)% size == head){
            //队列满了
            return false;
        }
        a[tail] = t;
        tail = (tail+1)% size;
        return true;
    }

    public T dequeue(){
        if (head == tail){
            //队列为空了
            return null;
        }
        T ret = a[head];
        head = (head + 1) % size;
        return  ret;
    }
}
