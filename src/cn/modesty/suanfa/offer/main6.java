package cn.modesty.suanfa.offer;


import java.util.Stack;

/**
 * 从尾到头打印链表
 *  输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
 */
public class main6 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        method1(listNode1);
        method2(listNode1);
        method3(listNode1);

    }

    /**
     * @param head
     * 使用栈
     */
    public static void method1(ListNode head){
        Stack<Integer> stack = new Stack();
        if (head == null) return;
        ListNode p = head;
        while (p != null){
            stack.push(p.key);
            p = p.next;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    /**
     * @param head
     * 递归
     */
    public static void method2(ListNode head){
        if (head == null ) return;
        if (head.next != null){
            method2(head.next);
        }
        System.out.println(head.key);
    }

    /**
     * @param head
     * 先逆序后打印
     */
    public static void method3(ListNode head){
        if (head == null) return;
        ListNode pre = null;
        ListNode p = head;
        while (p.next != null){
            ListNode tem = p.next;
            p.next = pre;
            pre = p;
            p = tem;
        }
        p.next = pre;
        while (p != null){
            System.out.println(p.key);
            p = p.next;
        }


    }
    public static class ListNode{
        int key;
        ListNode next;
        ListNode(int key){
            this.key = key;
        }
    }

}
