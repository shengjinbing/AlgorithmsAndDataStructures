package cn.modesty.suanfa.sort;

import edu.princeton.cs.algs4.Quick;

import java.util.Arrays;

/**
 * 3种线性排序
 */
public class LinearSort {
    //假设只有 8 个考生，分数在 0 到 5 分之间。.
    public static void main(String[] args) {
        int[] a = new int[]{2,5,3,7,2,3,7,3};
        countSort(a,a.length);
        printf(a);
    }

    public static void printf(int[] data){
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]+",");
        }
    }
    /**
     * 桶排序
     *
     * @param a
     */
    public static void bucketSort(int[] a) {

    }

    /**
     * 计数排序
     *
     * @param a
     */
    public static void countSort(int[] a,int n) {
        int max = a[0];
        for (int i = 0; i < n; i++) {
            if (max < a[i]){
                max = a[i];
            }
        }
        //划分成n个桶,赋初始值
        int[] bucket = new int[max+1];
        for (int i = 0; i <= max; i++) {
            bucket[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            //就算每种分数在桶内的位子,计数
            bucket[a[i]]= bucket[a[i]]+1;
        }

        //累顺序累加桶的值
        for (int i = 1; i <= max; i++) {
            bucket[i] = bucket[i]+bucket[i-1];
            //System.out.println(bucket[i]);
        }

        int[] temp = new int[n];
        //最后一步，进行排序划分坐标
        /*for (int i = n-1; i >= 0; i--) {
                temp[bucket[a[i]]-1] = a[i];
                bucket[a[i]] = bucket[a[i]] -1;
        }*/
        for (int i = 0; i < n; i++) {
            temp[bucket[a[i]]-1] = a[i];
            bucket[a[i]] = bucket[a[i]] -1;
        }

        for (int i = 0; i < temp.length; i++) {
            a[i] = temp[i];
        }

    }

    /**
     * 基数排序
     *
     * @param a
     */
    public static void radixSort(int[] a) {

    }
}
