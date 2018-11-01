package cn.modesty.suanfa.bagqueuestack;

/**
 * 下压栈使用链表的数据结构实现
 *
 * @param <T>
 */
public class LinkStack<T> {
    private Node head;
    private int N = 0;

    public class Node {
        Node next;
        T    data;
    }

    public void push(T t) {
        Node node = new Node();
        node.data = t;
        Node oldhead = head;
        head = node;
        head.next = oldhead;
        N++;
    }

    public T pop() {
        T data = head.data;
        head = head.next;
        N--;
        return data;
    }


}
