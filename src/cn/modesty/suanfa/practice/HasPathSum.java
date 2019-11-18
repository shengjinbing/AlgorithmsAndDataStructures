package cn.modesty.suanfa.practice;

import cn.modesty.suanfa.tree.TreeNode;

/**
 * 路径总和（112）
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 *
 * 难度：简单
 *
 */
public class HasPathSum {
    public static void main(String[] args) {

    }

    int value = 0;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        value+=root.val;
        if (value == sum && root.left == null && root.right == null){
            return true;
        }
        if (hasPathSum(root.left,sum)) {
            return true;
        }
        if (hasPathSum(root.right,sum)) {
            return true;
        }
        value-=root.val;
        return false;

    }
}
