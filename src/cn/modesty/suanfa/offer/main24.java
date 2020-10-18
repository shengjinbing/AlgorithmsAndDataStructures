package cn.modesty.suanfa.offer;


/**
 * 链表反转
 */
public class main24 {
    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode pre = null;
        while (p != null){
            ListNode temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        return  pre;
    }
}
