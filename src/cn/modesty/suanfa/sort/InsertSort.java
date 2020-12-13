package cn.modesty.suanfa.sort;

public class InsertSort {
    /**
     * 最小K个数
     */
    public int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0)return res;
        int size = 0;
        for (int a : arr) {
            if (size == 0) {
                res[0] = a;
                size++;
            } else {
                if (size == k) {
                    if (res[size - 1] > a) {
                        res[size - 1] = a;
                    } else {
                        continue;
                    }
                } else {
                    res[size] = a;
                    size++;
                }
                for (int i = size - 1; i > 0; i--) {
                    if (res[i] < res[i - 1]) {
                        int temp = res[i];
                        res[i] = res[i - 1];
                        res[i - 1] = temp;
                    } else {
                        break;
                    }

                }
            }
        }
        return res;
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } else {
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
    public static void shell(int[] a) {
        int N = a.length;
        int h = 1;
        while (h < 3 / N) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = 1; i < N; i++) {
                for (int j = i; j > 0; j = j - h) {
                    if (a[j] < a[j - h]) {
                        int temp = a[j];
                        a[j] = a[j - h];
                        a[j - h] = temp;
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
    }

}
