package cn.modesty.suanfa.sort;

public class InsertSort {

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[]  a){
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (a[j] < a[j-1]){
                  int temp =  a[j];
                  a[j] = a[j-1];
                  a[j-1] = temp;
                }else {
                    break;
                }
            }
        }
    }



    /**
     * 希尔排序
     *
     * @param a
     */
    public static  void shell(int[] a){
       int N = a.length;
       int h = 1;
       while (h < 3/N){
           h = h*3 + 1;
       }
       
       while (h >= 1){
           for (int i = 1; i < N; i++) {
               for (int j = i; j > 0 ; j=j-h) {
                   if (a[j] < a[j-h]){
                       int temp =  a[j];
                       a[j] = a[j-h];
                       a[j-h] = temp;
                   }else {
                       break;
                   }
               }
           }
           h = h/3;
       }
    }

}
