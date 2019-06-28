package cn.modesty.suanfa.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1.实现一个二叉查找树，并且支持插入、删除、查找操作
 * 2.实现查找二叉查找树中的某个节点得前驱，后继节点
 * 3.实现二叉查找树得前、中、后以及按层遍历
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


