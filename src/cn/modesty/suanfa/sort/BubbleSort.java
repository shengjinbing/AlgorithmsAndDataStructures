package cn.modesty.suanfa.sort;

/**
 * 冒泡
 */
public class BubbleSort {
    public static void sort(int[] data){
        int temp = 0;
        for (int i = 0; i < data.length ; i++) {
            for (int j = 0; j < data.length - i -1; j++) {
                if (data[j] > data[j+1]){
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
    }
}
