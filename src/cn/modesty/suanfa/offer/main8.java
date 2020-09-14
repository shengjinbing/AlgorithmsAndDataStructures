package cn.modesty.suanfa.offer;

import cn.modesty.suanfa.tree.TreeNode;

/**
 * 二叉树的下一个节点
 * 题目：给定一颗二叉树和其中一个节点，如何找出中序遍历序列的下一个节点？树中的节点除了有两个分别指向左、右子节点的指针，
 * 还有一个指向父节点的指针。
 */
public class main8 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.parent = treeNode1;
        treeNode2.parent = treeNode1;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode4.parent = treeNode2;
        treeNode5.parent = treeNode2;
        treeNode6.parent = treeNode4;
        treeNode7.parent = treeNode5;
        treeNode4.left = treeNode6;
        treeNode5.left = treeNode7;


        System.out.println(FindNextNode(treeNode4).val);


    }

    /**
     * 1.有右子树找到右子树最后一个左孩子
     * 2.没有右子树找到父亲,如果是根节点就没有父节点找到右子树最后一个左孩子
     * 3.如果一个节点没有右子树并且它还是它父节点的右子节点（这种情况复杂）
     * @param treeNode2
     */
    public static TreeNode FindNextNode(TreeNode treeNode2){
       if (treeNode2 == null) return null;
       if (treeNode2.right == null){
           //右子树为空
           if (treeNode2.parent == null){
               return null;
           }else {
               TreeNode cur = treeNode2;
               while (cur.parent != null){
                   if (cur.parent.left == cur){
                       return cur.parent;
                   }else {
                       cur = cur.parent;
                   }
               }
               return null;
           }
       }else {
           //右子树不为空
           TreeNode tem = treeNode2.right;
           while ( tem.left != null){
               tem = tem.left;
           }
           return tem;
       }
    }
}
