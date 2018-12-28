package cn.modesty.suanfa.sort;

public class PracticeMain {
    public static void main(String[] args) {
        int[] data = new int[]{1,0,4,3,2,9,6,8,7,5};
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

    public static void quickSort(int[] data, int start, int end){
        if (start >= end ) return;
        int mid = findMid(data,start,end);
        quickSort(data,start,mid-1);
        quickSort(data,mid+1,end);
    }

    private static int findMid(int[] data,int start,int end){
        int p = data[start];//区分点
        int i = start;
        int j = end+1;
        while (true){
            while (data[++i]< p){
                if (i>=end)break;
            }
            while (data[--j] > p){
                if (j<=start)break;
            }
            if (i < j){
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }else {
                break;
            }

        }
        int temp = data[j];
        data[j] = data[start];
        data[start] = temp;

        return j;
    }

}
