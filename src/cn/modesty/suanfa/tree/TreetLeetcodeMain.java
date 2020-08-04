package cn.modesty.suanfa.tree;

import java.util.LinkedList;

public class TreetLeetcodeMain {
    public static void main(String[] args) {
        System.out.println(numTrees(4));
        int[] preorder = new int[]{3, 9, 20, 15, 7};//前序遍历
        int[] inorder = new int[]{9, 3, 15, 20, 7};//中序遍历
        TreeNode node1 = buildTree1(preorder, inorder);
        TreeNode node2 = buildTree1(preorder, inorder);

    }

    /**
     * 动态规划求解
     * 95
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树有多少种?
     * <p>
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
            for (int j = 1; j <= i; j++) {
                res[i] += res[j - 1] * res[i - j];
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


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * <p>
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 例如，给出
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length)
            return null;
        if (preorder.length == 0)
            return null;
        if (preorder.length == 1)
            return new TreeNode(preorder[0]);
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);

    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, int prei, int prej, int ini, int inj) {
        if (prei > prej || ini > inj || prei < 0 || prej >= preorder.length || ini < 0 || inj >= inorder.length)
            return null;
        if (prej - prei < 0)
            return null;
        if (prei == prej)
            return new TreeNode(preorder[prei]);
        //头结点
        TreeNode root = new TreeNode(preorder[prei]);
        int inFlag = 0;
        for (int i = ini; i <= inj; i++) {
            if (inorder[i] == root.val) {
                inFlag = i;
                break;
            }
        }
        //左边距离
        int num_left = inFlag - ini;
        //右边距离
        int num_right = inj - inFlag;
        root.left = buildTree(preorder, inorder, prei + 1, prei + num_left, ini, inFlag - 1);
        root.right = buildTree(preorder, inorder, prej - num_right + 1, prej, inFlag + 1, inj);
        return root;

    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length != inorder.length)
            return null;
        if (inorder.length == 0)
            return null;
        if (inorder.length == 1)
            return new TreeNode(inorder[0]);
        return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode helper(int[] inorder, int[] preorder, int postEnd, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        TreeNode current = new TreeNode(preorder[postEnd]);
        int inFlag = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == current.val) {
                inFlag = i;
                break;
            }
        }
        current.left = helper(inorder,preorder, postEnd-(inEnd-inFlag)-1, inStart,inFlag-1);
        current.right = helper(inorder,preorder, postEnd -1, inFlag+1, inEnd);
        return current;

    }
}
