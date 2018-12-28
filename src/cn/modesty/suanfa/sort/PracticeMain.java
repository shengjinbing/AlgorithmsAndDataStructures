package cn.modesty.suanfa.sort;

public class PracticeMain {
    public static void main(String[] args) {
        int[] data = new int[]{1,0,4,3,2};
        int start = 0;
        int end = data.length-1;
        //quickSort(data,start,end);
        print(data);
    }

    public static void print(int[] data){
        for (int i = 0; i <data.length ; i++) {
            System.out.print(data[i]+",");
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
