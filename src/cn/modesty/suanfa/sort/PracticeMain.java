package cn.modesty.suanfa.sort;

public class PracticeMain {
    public static void main(String[] args) {
        int[] data = new int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
        int start = 0;
        int end = data.length-1;
        //quickSort(data,start,end);
        merge(data,start,end);
        print(data);
    }

    public static void print(int[] data){
        for (int i = 0; i <data.length ; i++) {
            System.out.print(data[i]+",");
        }
    }

    //归并
    private static void merge(int[] data, int start, int end){
        if (start >= end){
            return;
        }
        int mid = start+(end - start)/2;
        merge(data,start,mid);
        merge(data,mid+1,end);
        mergeData(data,start,mid,end);
    }

    private static void mergeData(int[] data, int start, int mid, int end) {
         int[] temp = new int[data.length];
        for (int i = start; i <=end ; i++) {
            temp[i] = data[i];
        }

        int j = mid+1;
        int i = start;
        while (start<= mid && j <= end){
            if (temp[start] < temp[j]){
                data[i++] = temp[start++];
            }else {
                data[i++] = temp[j++];
            }
        }
        if (start <= mid){
            //左边有剩余
            for (int k = start; k <=mid ; k++) {
                data[i++] = temp[k];
            }
        }

        if (j <= end){
            //右边有剩余
            for (int k = j; k <=end ; k++) {
                data[i++] = temp[k];
            }
        }
    }

    //快排
    public static void quickSort(int[] data, int start, int end){
        if (start >= end ) return;
        int mid = findMid(data,start,end);
        quickSort(data,start,mid-1);
        quickSort(data,mid+1,end);
    }

    //正确的查找中间值
    public static int findMid(int[] a,int i,int j){
        int l = i;
        int r = j+1;
        int v = a[i];
        while(l < r){
            while(a[++l] <= v && l < j){ }
            while(a[--r] >= v && r > i){ }
            if(l < r){
                int temp = a[l];
                a[l] = a[r];
                a[r] = temp;
            }
        }
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        return r;
    }

}
