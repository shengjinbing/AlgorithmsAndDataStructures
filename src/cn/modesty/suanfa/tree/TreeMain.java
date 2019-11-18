package cn.modesty.suanfa.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 树，二叉树，二叉查找树，平衡二叉查找树，红黑树，递归树
 *
 * 高度，深度，层
 * 节点的高度：节点到叶子节点的最长路径（边数）
 * 节点的深度：根节点到这个节点的所经历的边数
 * 节点的层数：节点的深度+1
 * 树的高度：  根节点的高度
 *
 * 二叉树：每个节点最多只有左右两个子节点
 * 满二叉树：叶子节点全在最底层，每个节点都有两个子节点（高度log2n）
 * 完全二叉树：叶子节点全部在最底下两层，做好一排的叶子节点都是靠左排列，并且除了最后一层，其他层的节点个数要达到最大
 *           使用数组作为存储结构是最省内存的（高度log2n）
 *
 *
 * 二叉查找树：在树的任意一个节点，其左子树的每个节点值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值
 * 1.实现一个二叉查找树，并且支持插入、删除、查找操作
 * 2.实现查找二叉查找树中的某个节点得前驱，后继节点
 * 3.实现二叉查找树得前、中、后以及按层遍历
 *
 * 平衡二叉树：任意一个节点的左右子树的高度不能相差大于1
 *   最先被发明的平衡二叉树是AVL树
 */
public class TreeMain {
    public static void main(String[] args) {
        /***************二叉搜素树******************/
        BST<Integer, Integer> bst = new BST<>();
        //插入元素
        bst.put(2,2);
        bst.put(5,5);
        bst.put(1,1);
        bst.put(7,7);
        bst.put(3,3);
        bst.put(6,6);
        //查找元素
        System.out.println(bst.get(6));
        //删除元素
        //bst.delete(5);
        //前序
        bst.print(1);
        System.out.print("\n");
        //中序
        bst.print(2);
        System.out.print("\n");
        //后序
        bst.print(3);
        System.out.print("\n");
        //层级遍历,递归
        bst.print(4);
        //层级遍历.迭代法
        bst.print(5);
        System.out.print("\n");
        //翻转二叉树
        bst.invertTree();


        /***************红黑树******************/
        /*RedBlackBST<Integer, Integer> RBbst = new RedBlackBST<>();
        RBbst.put(7, 7);
        RBbst.put(6, 6);
        RBbst.put(5, 5);
        RBbst.put(3, 3);
        RBbst.put(2, 2);
        RBbst.put(1, 1);
        RBbst.print();
        System.out.println(RBbst.size(RBbst.getRoot()));

        System.out.println(Math.pow(0.00001,
                2147483647));*/


    }

}


