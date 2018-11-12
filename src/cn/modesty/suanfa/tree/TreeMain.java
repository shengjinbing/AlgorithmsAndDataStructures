package cn.modesty.suanfa.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeMain {
    public static void main(String[] args) {
        /***************二叉搜素树******************/
       /* BST<Integer, Integer> bst = new BST<>();
        bst.put(2,2);
        bst.put(5,5);
        bst.put(1,1);
        bst.put(7,7);
        bst.put(3,3);
        bst.put(6,6);
       *//* System.out.println(bst.size());
        System.out.println(bst.get(6));
        System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.floor(4));*//*
        bst.print();
        bst.delete(5);
        bst.print();
*/
        /***************红黑树******************/
        RedBlackBST<Integer, Integer> RBbst = new RedBlackBST<>();
        RBbst.put(7, 7);
        RBbst.put(6, 6);
        RBbst.put(5, 5);
        RBbst.put(3, 3);
        RBbst.put(2, 2);
        RBbst.put(1, 1);
        RBbst.print();
        System.out.println(RBbst.size(RBbst.getRoot()));

        System.out.println(Math.pow(0.00001,
                2147483647));


    }

}


