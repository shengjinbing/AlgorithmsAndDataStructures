package cn.modesty.suanfa.offer;

import java.util.HashMap;

/**
 * 复杂链表的复制
 * <p>
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next
 * 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class main35 {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Node node = copyRandomList(node1);
    }

    public static Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>(); //创建HashMap集合
        Node cur = head;
        //复制结点值
        while (cur != null) {
            //存储put:<key,value1>
            map.put(cur, new Node(cur.val)); //顺序遍历，存储老结点和新结点(先存储新创建的结点值)
            cur = cur.next;
        }
        //复制结点指向
        cur = head;
        while (cur != null) {
            //得到get:<key>.value2,3
            map.get(cur).next = map.get(cur.next); //新结点next指向同旧结点的next指向
            map.get(cur).random = map.get(cur.random); //新结点random指向同旧结点的random指向
            cur = cur.next;
        }

        //返回复制的链表
        return map.get(head);
    }


    //复制单链表方法1
    public static Node copyList1(Node head) {
        Node p = head;
        Node copyHead = null;
        Node temp = null;
        while (p != null) {
            Node node = new Node(p.val);
            if (copyHead == null) {
                copyHead = node;
                temp = copyHead;
            } else {
                temp.next = node;
                temp = node;
            }
            p = p.next;
        }
        return copyHead;
    }

    //复制单链表方法2
    public static Node copyList2(Node head) {
        while (head != null) {
            Node cloneNode = new Node(head.val);
            Node nextNode = head.next;
            head.next = cloneNode;
            cloneNode.next = nextNode;
            head = cloneNode.next;
        }
        return null;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}


