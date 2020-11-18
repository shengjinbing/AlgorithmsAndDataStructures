package cn.modesty.suanfa.sort;

public class MergerSort {

    public static void merger(int[] a, int low, int hi) {
        if (low >= hi) {
            return;
        }
        int mid = (low + hi) / 2;
        merger(a, low, mid);
        merger(a, mid + 1, hi);
        mergerData(a, low, mid, hi);
    }

    /**
     * 合并两组数据
     *
     * @param a
     * @param low
     * @param hi
     */
    private static void mergerData(int[] a, int low, int mid, int hi) {
        int[] temp = new int[a.length];
        int   i    = low;//左边数组下标
        int   j    = mid + 1;//右边数组下标
        for (int k = low; k < hi; k++) {
            //将临时数组赋值
            temp[k] = a[k];
        }
        for (int k = low; k < hi; k++) {
            if (i >= mid) {
                //左边的元素已经用尽
                a[k] = temp[j++];
            } else if (j >= mid) {
                //右边的元素用尽
                a[k] = temp[i++];
            } else if (a[j] > a[i]) {
                a[k] = a[i++];
            } else{
                a[k] = a[j++];
            }
        }
    }

    /**
     * 合并数据第二种写法
     * @param data
     * @param start
     * @param mid
     * @param end
     */
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
}
