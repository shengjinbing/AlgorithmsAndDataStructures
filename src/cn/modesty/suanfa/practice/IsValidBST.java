package cn.modesty.suanfa.practice;

import cn.modesty.suanfa.tree.TreeNode;

/**
 * 验证二叉搜索树(98)
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * <p>
 * 难度：中等
 * 解法1：因为二叉搜索树中序遍历是递增的,所以我们可以中序遍历判断前一数是否小于后一个数
 * 解法2：对每个节点，检测其值是否大于左子树的最大值，是否小于右子树的最小值。思路正确，但效率较低。
 */
public class IsValidBST {
    public static void main(String[] args) {

    }

    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pre != null && pre.val >= root.val){
            return false;
        }
        pre = root;
        if (!isValidBST(root.right)) {
            return false;
        }

        return true;
    }
}
