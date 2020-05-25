package cn.modesty.suanfa.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树一些
 */
public class IsSymmetric {
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
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        //treeNode3.right = treeNode7;


        //System.out.println(zigzagLevelOrder(treeNode1));
        System.out.println(layerOrder(treeNode1));

    }


    /**
     * 101对称二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        //使用迭代的方式
        Queue<TreeNode> linkedList = new LinkedList();
        linkedList.add(root);
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            TreeNode left = linkedList.poll();
            TreeNode right = linkedList.poll();

            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            linkedList.add(left.left);
            linkedList.add(right.right);
            linkedList.add(left.right);
            linkedList.add(right.left);
        }

        return true;
        //return isMirror(root,root);
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 记录是否反转
        boolean isReverse = false;
        while (!queue.isEmpty()) {
            LinkedList<Integer> oneLevel = new LinkedList<>();
            // 每次都取出一层的所有数据
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (!isReverse)
                    oneLevel.add(node.val);
                else
                    oneLevel.addFirst(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            isReverse = !isReverse;
            result.add(oneLevel);
        }
        return result;

    }

    /**
     * 树的层级遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> layerOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList();
        if (root == null) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> data = new ArrayList();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.poll();
                data.add(top.val);
                if (top.left != null) queue.add(top.left);
                if (top.right != null) queue.add(top.right);
            }
            lists.add(data);
        }
        return lists;

    }


    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     *
     * 示例:
     *
     * 给定有序数组: [-10,-3,0,5,9],
     *
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     * 难度简单
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(0, nums.length - 1,nums);
    }

    /**
     * 中序遍历：始终选择中间位置左边元素作为根节点
     */
    public TreeNode helper(int left, int right,int[] nums) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

        // inorder traversal: left -> node -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1,nums);
        root.right = helper(p + 1, right,nums);
        return root;
    }
}
