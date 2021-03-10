package cn.modesty.suanfa.sort;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Quick;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SortMain {

    private static int[] temp;

    public static void main(String[] args) {
        int[] data = {0,9,6,7,2,3,8};
        //int[] data = {1,2,3,4,5,6,7};

        //冒泡
        //BubbleSort.sort(data);
        SelectSort.select(data);
        //InsertSort.shell(data);
        temp = new int[data.length];
        //merger(data,0,data.length-1);
        //QuickSort.quickSortThree(data,0,data.length-1);
        //QuickSort.quickSort(data,0,data.length-1);
       // HeapSort.sort(data,data.length-1);
        printf(data);
        //System.out.println(data.length);
    }
    public static void merger(int[] a, int low, int hi) {
        if (low >= hi) {
            return;
        }
        int mid = low+(hi - low) / 2;
        merger(a, low, mid);
        merger(a, mid + 1, hi);
        mergerData(a, low, mid, hi);
        //mergeSPData(a, low, mid, hi);
    }

    /**
     * 合并两组数据
     *
     * @param a
     * @param low
     * @param hi
     */
    private static void mergerData(int[] a, int low, int mid, int hi) {
        int   i    = low;//左边数组下标
        int   j    = mid + 1;//右边数组下标
        for (int k = low; k <= hi; k++) {
            //将临时数组赋值
            temp[k] = a[k];
        }
        for (int k = low; k <= hi; k++) {
            if (i > mid) {
                //左边的元素已经用尽
                a[k] = temp[j++];
            } else if (j > hi) {
                //右边的元素用尽
                a[k] = temp[i++];
            } else if (temp[j] > temp[i]) {
                a[k] = temp[i++];
            } else{
                a[k] = temp[j++];
            }
        }
    }

    public static void mergeSPData(int[] a, int p, int q, int r){
        int i = p;
        int j = q+1;
        int k = 0; // 初始化变量i, j, k
        int[] tmp = new int[r-p+1]; // 申请一个大小跟a[p...r]一样的临时数组
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]; // i++等于i:=i+1
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = tmp[i];
        }
    }


    public static void printf(int[] data){
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]+",");
        }
    }

    // 快速排序递归函数，p,r为下标
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q-1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for(int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                ++i;
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        System.out.println("i=" + i);
        return i;
    }
}
