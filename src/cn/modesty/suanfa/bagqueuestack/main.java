package cn.modesty.suanfa.bagqueuestack;

import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        //数组实现队列
       /* ArrayQueue<Integer> arrayQueue = new ArrayQueue();
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(4);
        arrayQueue.enqueue(5);
        arrayQueue.enqueue(6);
        arrayQueue.print();*/
       /* System.out.println(arrayQueue.dequeue());
        arrayQueue.print();*/


       //匹配正确括号
        /*StackAlg stackAlg = new StackAlg();
        System.out.println(stackAlg.isBracketCorrect("){"));
        System.out.println(stackAlg.isBracketCorrect("()[]{}"));
        System.out.println(stackAlg.isBracketCorrect("(]"));
        System.out.println(stackAlg.isBracketCorrect("([)]"));
        System.out.println(stackAlg.isBracketCorrect("{[]}"));*/

        //优先队列
        MaxPQ<PQdata> maxPQ = new MaxPQ(20);
        maxPQ.insert(new PQdata(4));
        maxPQ.insert(new PQdata(2));
        maxPQ.insert(new PQdata(3));
        maxPQ.insert(new PQdata(1));
        maxPQ.insert(new PQdata(5));
        System.out.println(maxPQ.getSize());

        maxPQ.getPq();
        PQdata pQdata = maxPQ.delMax();
        System.out.println(pQdata.getData());
        maxPQ.getPq();
    }
}
