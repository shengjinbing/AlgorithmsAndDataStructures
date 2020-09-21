package cn.modesty.suanfa.offer;

import cn.modesty.suanfa.tree.TreeNode;

/**
 * 二叉树的镜像
 * <p>
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 例如输入：
 * <p>
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 * <p>
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * <p>
 *  
 */
public class main27 {
    public static void main(String[] args) {

    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}

