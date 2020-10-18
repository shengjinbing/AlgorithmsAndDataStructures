package cn.modesty.suanfa.offer;

import cn.modesty.suanfa.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 * <p>
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * <p>
 *  
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * /  \
 * 4    8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class main34 {
    LinkedList<List<Integer>> data = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();


    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return data;
    }

    public void recur(TreeNode root, int sum) {
        if (root == null) return;
        path.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null){
            data.add(new LinkedList(path));
        }
        recur(root.left, sum);
        recur(root.right, sum);
        path.removeLast();
    }
}
