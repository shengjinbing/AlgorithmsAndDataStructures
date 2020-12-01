package cn.modesty.suanfa.offer;

import cn.modesty.suanfa.tree.TreeNode;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，
 * 否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * <p>
 *  
 * <p>
 * 参考以下这颗二叉搜索树：
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * 示例 1：
 * <p>
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: [1,3,2,6,5]
 * 输出: true
 */
public class main33 {
    public static void main(String[] args) {
        int[] a = {1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(a));
        List<Integer> data = new ArrayList();
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(true){
            a = a.next == null?headB:a.next;
            b = b.next == null?headA:b.next;
            if(a == headB && b == headA)return null;
            if(a == b)return a;
        }
    }


    public static boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) return false;
        return recur(postorder, 0, postorder.length - 1);
    }

    public static boolean recur(int[] postorder, int start, int end) {
        if (start >= end) return true;
        int i = start;
        //右子树
        while (postorder[i] < postorder[end]) i++;
        int mid = i;
        //左子树
        while (postorder[i] > postorder[end]) i++;
        return i == end && recur(postorder, start, mid - 1) &&
                recur(postorder, mid, end - 1);
    }

}
