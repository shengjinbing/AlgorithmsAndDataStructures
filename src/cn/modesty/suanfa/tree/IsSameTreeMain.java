package cn.modesty.suanfa.tree;

/**
 * 100相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 *
 */
public class IsSameTreeMain {
    public static void main(String[] args) {
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(3);

        p1.left = p2;
        p1.right = p3;


        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(3);

        q1.left = q2;
        q1.right = q3;

        System.out.println(isSameTree(p1, q1));

    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        }else if (p == null && q != null){
            return false;
        }else if (p != null && q == null){
            return false;
        }
        if (p.val != q.val)return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

}
