package cn.modesty.suanfa.linked;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.List;

/**
 * 单链表
 *
 * @param <T>
 */
public class Linked<T> {
    private int  size = 0;
    private Node head;
    private Node tail;
    private Node current;

    public Linked() {
    }

    static class Node<T> {
        Node next;
        T    data;
    }

    public void add(T data) {
        Node node = new Node();
        node.data = data;
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void insert(int index, T data) {
        Node node = new Node();
        node.data = data;
        if (index == 0) {
            if (size == 0) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
        } else {
            indexElement(index - 1);
            node.next = current.next;
            current.next = node;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void delete(int index) {
        if (index == 0) {
            //删除头结点
            head = head.next;
        } else {
            indexElement(index - 1);
            current.next = current.next.next;
        }
        size--;
    }

    public Node indexElement(int index) {
        int j = 0;
        current = head;
        while (current != null) {
            if (index == j) {
                break;
            }
            current = current.next;
            j++;
        }
        return current;
    }

    /**
     * 查找元素
     *
     * @return
     */
    public void findElement() {

    }

    public int getSize() {
        return size;
    }

    public void printElement() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + ",");
            current = current.next;
        }
    }

    public void printElement(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + ",");
            current = current.next;
        }
    }

    /**
     * 获取头结点
     *
     * @return
     */
    public Node getHead() {
        return head;
    }

    public void setHead(Node node) {
        head = node;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * 获取当前结点
     *
     * @return
     */
    public Node getCurrent() {
        return current;
    }

    /**
     * 链表反转
     */
    public void reversalLinked() {
        int  i            = 1;
        Node tempHeadNode = null;
        while (size - i - 1 >= 0) {
            indexElement(size - i - 1);
            if (i == 1) {
                //保存临时头结点
                tempHeadNode = current.next;
            }
            current.next.next = current;
            if (size - i - 1 == 0) {
                //反转过后的头结点指针为空
                current.next = null;
            }
            i++;
        }
        head = tempHeadNode;

    }

    /**
     * 递归反转单链表
     */
    public Node recursionReversalLinked(Node head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            Node newHeadNode = recursionReversalLinked(head.next);
            System.out.println(newHeadNode.data);
            head.next.next = head;
            head.next = null;
            return newHeadNode;
        }
    }

    /**
     * 找出链表环的长度
     *
     * @return
     */
    private Node slow;
    private Node fast;
    private int firstStep;
    private int secondStep;
    public int findLinkedRingLength() {
        int stepCount = 0;//走的总路程
        int meetCount = 0;//相遇的次数
        slow = head;
        fast = head;
        while (meetCount < 2 ) {
            if (getSlowStep() || getFastStep()) {
                break;
            }
            stepCount++;
            if (slow == fast){
                meetCount++;
                if (meetCount == 1){
                    firstStep = stepCount;
                }
                if (meetCount == 2){
                    secondStep = stepCount;
                }
            }
        }
        return secondStep - firstStep;
    }

    public boolean getSlowStep() {
        if (slow == null || slow.next == null) {
            return true;
        }
        slow = slow.next;
        return false;
    }

    public boolean getFastStep() {
        if (fast == null || fast.next == null || fast.next.next == null) {
            return true;
        }
        fast = fast.next.next;
        return false;
    }

    public boolean getFastTwoStep() {
        if (fast == null || fast.next == null) {
            return true;
        }
        fast = fast.next;
        return false;
    }

    /**
     * 找到环中的第一个结点
     *
     * @return
     */
    public Node findCoincideNode(){
        int stepCount = 0;//走的总路程
        int meetCount = 0;//相遇的次数
        slow = head;
        fast = head;
        while (meetCount < 2 ) {
            if (getSlowStep() || getFastStep()) {
                break;
            }
            stepCount++;
            if (slow == fast){
                meetCount++;
                if (meetCount == 1){
                    firstStep = stepCount;
                    fast = head;
                    return findCoincideNodeTwoStep();
                }
            }
        }
        return null;
    }

    /**
     * 相遇
     * @return
     */
    public Node findCoincideNodeTwoStep(){
        int meetCount = 0;//相遇的次数
        while (meetCount < 1 ) {
            if (getSlowStep() || getFastTwoStep()) {
                break;
            }
            if (slow == fast){
                meetCount++;
                if (meetCount == 1){
                    return slow;
                }
            }
        }
        return null;
    }

    /**
     * 合并两个有序的链表
     *
     * @param headone
     * @param headtwo
     * @return
     */
    public Node mergeLinked(Node headone,Node headtwo){

        if (headone == null)return headtwo;
        if (headtwo == null)return headone;

        Node mergeHeadNode = null;
        if ((int)headone.data < (int)headtwo.data){
            mergeHeadNode = headone;
            mergeHeadNode.next = mergeLinked(headone.next,headtwo);
        }else {
            mergeHeadNode = headtwo;
            mergeHeadNode.next = mergeLinked(headtwo.next,headone);
        }

        return mergeHeadNode;
    }
}

