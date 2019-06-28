package cn.modesty.suanfa.practice;

/**
 * 设计循环双端队列
 * <p>
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 * <p>
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * 示例：
 * <p>
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有值的范围为 [1, 1000]
 * 操作次数的范围为 [1, 1000]
 * 请不要使用内置的双端队列库。
 * <p>
 * 难得：中等
 * 难点：
 */
public class MyCircularDeque {
    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3); // 设置容量大小为3
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getFront());
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */

    //默认容量
    private int size = 0;
    //元素个数
    private int count = 0;
    //头结点
    private Node head = null;
    //尾节点
    private Node tail = null;


    class Node {
        int value;
        Node next;
        Node pre;

        Node(int value) {
            this.value = value;
        }

    }

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        size = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     * 将一个元素添加到双端队列头部。 如果操作成功返回 true。
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        if (head == null) {
            head = tail = new Node(value);
        } else {
            Node node = new Node(value);
            node.next = head;
            node.pre = tail;
            tail.next = node;
            head.pre = node;
            head = node;
        }
        count++;

        return true;

    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     * 将一个元素添加到双端队列尾部。如果操作成功返回 true。
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        if (head == null) {
            head = tail = new Node(value);
        } else {
            Node node = new Node(value);
            node.next = head;
            node.pre = tail;
            tail.next = node;
            head.pre = node;
            tail = node;
        }
        count++;

        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     * 从双端队列头部删除一个元素。 如果操作成功返回 true。
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        if (count == 1) {
            head = tail = null;
        } else {
            head.pre.next = head.next;
            head.next.pre = head.pre;
            Node tmp = head;
            head = head.next;
            tmp = null;
        }
        count--;

        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     * 从双端队列尾部删除一个元素。如果操作成功返回 true。
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        if (count == 1) {
            head = tail = null;
        } else {
            tail.pre.next = tail.next;
            tail.next.pre = tail.pre;
            Node tmp = tail;
            tail = tail.pre;
            tmp = null;
        }
        count--;

        return true;
    }

    /**
     * Get the front item from the deque.
     * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
     */
    public int getFront() {
        if (isEmpty()) return -1;
        return head.value;

    }

    /**
     * Get the last item from the deque.
     * 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
     */
    public int getRear() {
        if (isEmpty()) return -1;
        return tail.value;

    }

    /**
     * Checks whether the circular deque is empty or not.
     * 检查双端队列是否为空。
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the circular deque is full or not.
     * 检查双端队列是否满了。
     */
    public boolean isFull() {
        return size == count ? true : false;
    }
}
