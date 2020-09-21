package cn.modesty.suanfa.offer;


/**
 *  链表中倒数第k个节点
 *
 *  输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *  例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 */
public class main22 {
    public static void main(String[] args) {

    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode temp = null;
        int i = 0;
        while (p1!= null){
            if (i >= k-1){
                temp = p2;
                p2 = p2.next;
            }
            p1 = p1.next;
            i++;
        }
        return temp;
    }
}
