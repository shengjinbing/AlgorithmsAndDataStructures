package cn.modesty.suanfa.practice;

/**
 * 合并多个链表23
 * <p>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 *  * 1->3->4,
 *  * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 难度：困难
 * 难点：分治算法（divide and conquer）的核心思想其思就是四个字，分而治之，也就是将原问题划分成
 * n个规模较小，并且结构与原问题相似的子问题，递归的解决这些子问题，然后再合并其结果，就得到原问题的解。
 * （解题技巧类似归并算法）
 */
public class MergeKLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        return  null;
    }


}
