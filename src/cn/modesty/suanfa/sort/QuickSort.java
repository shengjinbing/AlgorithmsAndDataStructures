package cn.modesty.suanfa.sort;

public class QuickSort {
    public static void quickSort(int[] a,int lo,int hi){
        if (lo >= hi)return;
        int r = findMind(a,lo,hi);
        quickSort(a,lo,r-1);
        quickSort(a,r+1,hi);
    }


    /**
     * 三向切分，将数据切换成3段
     *
     * @param a
     * @param lo
     * @param hi
     */
    public static void quickSortThree(int[] a,int lo,int hi){
       if (lo >= hi){
           return;
       }
       int p = lo;
       int r = hi;
       int i = lo+1;//从第二个元素开始遍历
       int pivot = a[lo];//切分点
       while (i <= r){
           int value = a[i] - pivot;
           if (value > 0){
               //需要与末尾交换数据
               int temp = a[i];
               a[i] = a[r];
               a[r] = temp;
               r--;
           }else if (value < 0){
               //需要将且分点的值前移一位
               int temp = a[p];
               a[p] = a[i];
               a[i] = temp;
               i++;
               p++;
           }else {
               //相等,指针前移
               i++;
           }
       }
       quickSort(a,lo,p-1);
       quickSort(a,r+1,hi);
    }
    /**
     *
     * 基本快速排序
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int findMind(int[] a, int lo, int hi) {
        int pivot = a[lo];//选取区分点
        int i = lo;
        int j = hi+1;
        while (true){
            while (a[++i] < pivot){
                if (i == hi)break;
            }
            while (a[--j] > pivot){
             }

            if (i >= j)break;
            //交换位子,上面必须使用++i和--j，否则这里交换位置会出错
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        //将切分点放到正确的位子
        int temp = a[j];
        a[j] = a[lo];
        a[lo] = temp;

        return j;
    }

    public static int partition(int[] a, int p, int r){
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

