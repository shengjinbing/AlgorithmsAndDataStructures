package cn.modesty.suanfa.offer;

/**
 * 合并两个排序链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class main25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        ListNode head = p;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val){
                p.next = l1;
                p = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                p = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null){
            p.next = l2;
        }
        if (l2 == null){
            p.next = l1;
        }
        return  head;
    }
}
