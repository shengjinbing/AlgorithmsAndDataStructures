package cn.modesty.suanfa.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreetLeetcodeMain {
    public static void main(String[] args) {
        System.out.println(numTrees(4));
    }

    /**
     * 动态规划求解
     * 95
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树有多少种?
     *
     * G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
     * f(i)=G(i−1)∗G(n−i)
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i;j++) {
                res[i] += res[j-1] * res[i - j];
            }
        }
        return res[n];
    }


    /**
     * 96
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树
     * 输入: 3
     * 输出:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释:
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     *
     * @param
     * @return
     */
    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }


}
