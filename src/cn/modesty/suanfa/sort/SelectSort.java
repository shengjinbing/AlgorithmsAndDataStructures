package cn.modesty.suanfa.sort;

/**
 * 选择
 *
 */
public class SelectSort {

    public static void select(int [] data){
        int temp = 0;
        for (int i = 0; i < data.length ; i++) {
            for (int j = i+1; j < data.length; j++) {
                if (data[i] > data[j]){
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }
}
