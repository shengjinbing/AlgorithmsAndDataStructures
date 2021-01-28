package cn.modesty.suanfa.linked;

/**
 * 设计链表
 */
class MyLinkedList {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3,0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        System.out.println(myLinkedList.get(4));
    }
    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    Node head = null;
    Node tail = null;
    int size = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node cur = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            cur = cur.next;
        }
        return cur.val;

    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node(val);
        insertHead(node);
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = new Node(val);
        insertTail(node);
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        Node node = new Node(val);
        if (index == size) {
            //如果 index 等于链表的长度，则该节点将附加到链表的末尾
            insertTail(node);
        } else if (index > size) {
            //如果 index 大于链表长度，则不会插入节点
            return;
        } else if (index <= 0) {
            //如果index小于0，则在头部插入节点
            insertHead(node);
        } else {
            Node cur = head;
            Node pre = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    pre.next = node;
                    node.next = cur;
                }
                pre = cur;
                cur = cur.next;
            }
        }
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)return;
        Node pre = head;
        Node cur = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                if (size == 1){
                    head = tail = null;
                }else if (i == 0){
                    //删除头
                    head = cur.next;
                }else if (i == size-1){
                    //删除尾部
                    tail = pre;
                }
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        size--;
    }

    public void insertHead(Node node){
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void insertTail(Node node){
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
