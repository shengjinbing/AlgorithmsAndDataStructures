package cn.modesty.suanfa.sort;

import java.util.logging.Level;

public class HeapSort {

    public static void sort(int[] a,int n){
        //只需要遍历一半元素进行下沉操作，可以规避掉一半大小为1的子堆
        for (int i = n/2;i >0 ; i--) {
            sink(a,i,n);
        }
        while (n > 1){
            exch(a,1,n--);
            sink(a,1,n);
        }
    }

    /**
     * 使数组变成有序堆
     *
     * @param a
     * @param i
     *
     * @param n
     */
    private static void sink(int[] a, int i, int n) {
         while (2*i <= n){
             int j = 2*i;
             System.out.println(j+","+n);
             //j < n这样判断的意义：最后一个元素是最大的,j+1会事最大元素的下标，这样就会交换与j的值
             if (j < n &&less(a,j,j+1))j++;
             if (!less(a,i,j))break;
             exch(a,i,j);
             i = j;
         }
    }

    private static boolean less(int[] a, int i, int j){
       // System.out.println(i+","+j);
        return a[i] < a[j];
    }

    private static void exch(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
