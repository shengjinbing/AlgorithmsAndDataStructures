package cn.modesty.suanfa.collection;


import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class QueueMain {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        priorityQueue.add(4);
        priorityQueue.add(5);
        priorityQueue.add(1);
        System.out.println(priorityQueue.poll());

        Queue<Integer> queue = new LinkedList();
        queue.add(1);
        queue.offer(1);
        queue.poll();
        queue.peek();

        LinkedList linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.poll();
        linkedList.pop();
        System.out.println(linkedList.size());

        ArrayQueue arrayQueue = new ArrayQueue<Integer>(10);
        arrayQueue.add(1);
        arrayQueue.add(2);
        arrayQueue.add(3);
        arrayQueue.add(4);
        System.out.println(arrayQueue.get(0));
        System.out.println(arrayQueue.get(1));
        System.out.println(arrayQueue.get(2));
        System.out.println(arrayQueue.get(3));

        //双端队列
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);
        arrayDeque.add(4);
        System.out.println(arrayDeque.pop());
        System.out.println(arrayDeque.pop());
        System.out.println(arrayDeque.pop());
        System.out.println(arrayDeque.pop());

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        countAndSay(10);

    }

    public static String countAndSay(int n) {
        StringBuilder s = new StringBuilder();
        int p1 = 0;
        int cur = 1;
        if ( n == 1 )
            return "1";
        String str = countAndSay(n - 1);
        System.out.println(str);
        for ( cur = 1; cur < str.length(); cur++ ) {
            if ( str.charAt(p1) != str.charAt(cur) ) {// 如果碰到当前字符与前面紧邻的字符不等则更新此次结果
                int count = cur - p1;
                s.append(count).append(str.charAt(p1));
                p1 = cur;
            }
        }
        if ( p1 != cur ){// 防止最后一段数相等，如果不等说明p1到cur-1这段字符串是相等的
            int count = cur - p1;
            s.append(count).append(str.charAt(p1));
        }
        return s.toString();


    }
}
