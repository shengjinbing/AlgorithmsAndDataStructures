package cn.modesty.suanfa.practice;

/**
 * 是否有环
 *
 * 难度：简单
 * 难点：定义个变量一快一慢
 */
public class HasCycle {
    public static void main(String[] args) {

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 判断是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode tmpQuick = head;
        ListNode tmpSlow = head;
        while(tmpSlow != null){
            tmpSlow = tmpSlow.next;
            if(tmpQuick == null || tmpQuick.next == null) return false;
            tmpQuick = tmpQuick.next.next;
            if(tmpSlow.equals(tmpQuick)){
                return true;
            }
        }

        return false;
    }
}
