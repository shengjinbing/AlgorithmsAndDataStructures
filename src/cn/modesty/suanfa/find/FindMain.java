package cn.modesty.suanfa.find;

import java.util.ArrayList;
import java.util.List;

public class FindMain {
    public static void main(String[] args) {
        //int[] data = {0,0,1,2,3,3,4,5,6,7,7,7,8,9,10};
        int[] data = {0,0,1,3,3,4,6,7,7,7,8,9,10};
        //System.out.println(BinarySearch.recursionFind(7, data, 0, data.length - 1));
       /* System.out.println(BinarySearch.searchFirst(7, data, 0, data.length - 1));
        System.out.println(BinarySearch.searchEnd(7, data, 0, data.length - 1));
        System.out.println(BinarySearch.searchFirstBig(2, data, 0, data.length - 1));
        System.out.println(BinarySearch.searchFirstLess(7, data, 0, data.length - 1));*/

        BST<Integer, Integer> bst = new BST<>();
        bst.put(2,2);
        bst.put(5,5);
        bst.put(1,1);
        bst.put(7,7);
        bst.put(3,3);
        bst.put(6,6);
       /* System.out.println(bst.size());
        System.out.println(bst.get(6));
        System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.floor(4));*/
        bst.print();
        bst.delete(5);
        bst.print();

    }


    public static String ExRl(int n){
        if (n <= 0) return "";
        return ExRl(n-3)+n+ExRl(n-2)+n;
    }

    private static long F(int N){
        if (N ==  0)return 0;
        if (N == 1)return 1;
        return F(N-1)+F(N-2);
    }

    public static double fac(int n){
        if(n==0||n==1) return 1;
        return n*fac(n-1);
    }
}
