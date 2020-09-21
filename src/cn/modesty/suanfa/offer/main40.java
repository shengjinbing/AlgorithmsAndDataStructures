package cn.modesty.suanfa.offer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * TOP K问题
 * 一、用快排最最最高效解决 TopK 问题
 * 二、大根堆(前 K 小) / 小根堆（前 K 大),Java中有现成的 PriorityQueue，实现起来最简单
 * 三、二叉搜索树也可以 解决 TopK 问题
 * 四、数据范围有限时直接计数排序就行了
 * <p>
 * <p>
 * 最小的k个数
 * <p>
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
 * 则最小的4个数字是1、2、3、4。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 */
public class main40 {
    public static void main(String[] args) {
        int[] data = {0, 1, 1, 2, 4, 4, 1, 3, 3, 2};
        int[] leastNumbers = getLeastNumbers(data, 6);
        getLeastNumbers1(data, 6);
        getLeastNumbers2(data,6);

    }

    public static int[] getLeastNumbers1(int[] arr, int k) {
        if (arr == null || arr.length == 0) return arr;
        if (k == 0) return new int[k];
        int[] data = quickSearch(arr, 0, arr.length - 1, k);
        return data;
    }

    public static int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num: arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for(int num: pq) {
            res[idx++] = num;
        }
        return res;

    }



    /**
     * 快排
     *
     * @param arr
     * @param s
     * @param e
     */
    public static int[] quickSearch(int[] arr, int s, int e, int k) {
        int mid = findMid(arr, s, e);
        if (mid == k) return Arrays.copyOf(arr, mid);
        return mid > k ? quickSearch(arr, s, mid - 1, k) :
                quickSearch(arr, mid + 1, e, k);
    }

    private static int findMid(int[] arr, int s, int e) {
        int q = arr[s];
        int i = s;
        int j = e + 1;

        while (true) {
            while (++i <= e && arr[i] < q) ;
            while (--j >= s && arr[j] > q) ;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                break;
            }
        }
        arr[s] = arr[j];
        arr[j] = q;
        return j;
    }


    /**
     * 最复杂解法
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0) return arr;
        if (k == 0) return new int[k];
        int[] data = new int[k];
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                data[i] = arr[i];
            } else {
                int max = data[0];
                int maxIndex = 0;
                for (int j = 0; j < k; j++) {
                    if (max < data[j]) {
                        max = data[j];
                        maxIndex = j;
                    }
                }
                if (max > arr[i]) {
                    data[maxIndex] = arr[i];
                }
            }
        }
        return data;
    }
}
